package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import universityWebApp.exception.*;
import universityWebApp.model.*;
import universityWebApp.model.Module;
import universityWebApp.repository.CoordinatesRepository;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.GradesRepository;
import universityWebApp.repository.ModuleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"student","loggedIn","isStaff","status","enrolled"})
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    GradesRepository gradesRepository;

    @Autowired
    CoordinatesRepository coordinatesRepository;

    /**
     * This endpoint returns all modules(?)
     */
    @RequestMapping(value = "modules", method = RequestMethod.GET)
    public String getModules(ModelMap model) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        List<Module> modules = moduleRepository.findAll();
        model.addAttribute("modules", modules);
        return "modules";
    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value = "modules/{id}", method = RequestMethod.GET)
    public String getModule(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        model.addAttribute("module", module);

        EnrollmentId enrollmentId = new EnrollmentId(moduleId, ((Student) model.getAttribute("student")).getId());

        if (enrollmentRepository.findById(enrollmentId).isPresent()) {
            model.addAttribute("status", "unenrol");
        } else {
            model.addAttribute("status", "enrol");
        }
        //todo: needs to look up the staff db
        addModuleViewDetailsToModel(model, module);

        return "module";
    }

    @RequestMapping(value = "modules/{id}/edit", method = RequestMethod.GET)
    public String editModule(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        if (!(Boolean) model.getAttribute("isStaff")) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        Optional<Module> module = moduleRepository.findById(moduleId);

        if (!module.isPresent()) {
            throw new ModuleNotFoundException(moduleId);
        }

        model.addAttribute("module", module.get());

        return "edit_module";
    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value = "modules/{id}/edit", method = RequestMethod.POST)
    public String editModule(ModelMap model, Module module, @PathVariable("id") long moduleId) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        if(!model.containsAttribute("isStaff") || !(boolean) model.getAttribute("isStaff")){
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        Module oldModule = moduleRepository.getOne(moduleId);
        //moduleRepository.delete(oldModule);
        moduleRepository.save(module);

        model.addAttribute("module", module);

        return "module";
    }
  
    /**
     * enroll student in a module
     */
    @RequestMapping(value="modules/{id}/enrol", method=RequestMethod.POST)
    public String enroll(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException,
            ModuleFullException, FeesNotPaidException, StudentAlreadyEnrolledException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        Student student = (Student) model.getAttribute("student");
        Enrollment enrollment = new Enrollment(moduleId, student.getId());

        if (enrollmentRepository.findById(new EnrollmentId(moduleId, student.getId())).isPresent()) {
            throw new StudentAlreadyEnrolledException(student.getId(), moduleId);
        }

        if (!student.hasPaidFees()) {
            throw new FeesNotPaidException();
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));


        long countEnrolledStudents = enrollmentRepository.findByModuleID(moduleId).size();

        if (countEnrolledStudents >= module.getMaximumStudents()) {
            throw new ModuleFullException(moduleId);
        }

        enrollmentRepository.save(enrollment);

        addModuleViewDetailsToModel(model, module);

        model.addAttribute("status", "enrol");

        return "enrollment_status";
    }

    /**
     * enrol student in a module
     */
    @RequestMapping(value="modules/{id}/unenrol",method= RequestMethod.POST)
    public String unEnrol(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        Student student = (Student) model.getAttribute("student");

        Enrollment enrollment = new Enrollment(moduleId, student.getId());

        enrollmentRepository.delete(enrollment);

        model.addAttribute("status", "unenrol");

        Module module = moduleRepository.findById(moduleId).get();

        addModuleViewDetailsToModel(model, module);

        return "enrollment_status";
    }

    private void addModuleViewDetailsToModel(Model model, Module module) throws ModuleNotFoundException {
        model.addAttribute("module", module);

        model.addAttribute("coordinator", "John Dunnion");

        long numberOfStudentsEnrolled = enrollmentRepository.findByModuleID(module.getId()).size();

        model.addAttribute("amountOfStudents", numberOfStudentsEnrolled);
    }

    /**
     * set a students grades if your a coordinator
     */
    @RequestMapping(value = "modules/{id}/grade", method = RequestMethod.POST)
    public String setGrade(@PathVariable("id") long moduleId, Model model, @RequestParam String studentID, @RequestParam int grade) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }
      
        if(!model.containsAttribute("isStaff") || !(boolean) model.getAttribute("isStaff")){
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        if (model.getAttribute("studentId") == coordinatesRepository.findByModuleID(moduleId)) {
            Grades grades = new Grades(moduleId, studentID, grade);
            gradesRepository.save(grades);
            return "module";
        }

        throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "modules/{id}/stat", method = RequestMethod.GET)
    public HashMap<String, Integer> getGradeStats(@PathVariable("id") String moduleCode, Model model, @RequestParam String studentID, @RequestParam String grade) throws ModuleNotFoundException {
//        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
//            return ("redirect_to_login");
//        }

        List<Long> moduleIds = moduleRepository.findIDByCode(moduleCode);
        HashMap<String, Integer> gradeRange = new HashMap<>();
        gradeRange.put("0-5",0);
        gradeRange.put("5-10",0);
        gradeRange.put("10-15",0);
        gradeRange.put("15-20",0);
        gradeRange.put("20-25",0);
        gradeRange.put("25-30",0);
        gradeRange.put("30-35",0);
        gradeRange.put("35-40",0);
        gradeRange.put("40-45",0);
        gradeRange.put("45-50",0);
        gradeRange.put("50-55",0);
        gradeRange.put("55-60",0);
        gradeRange.put("60-65",0);
        gradeRange.put("65-70",0);
        gradeRange.put("70-75",0);
        gradeRange.put("75-80",0);
        gradeRange.put("80-85",0);
        gradeRange.put("85-90",0);
        gradeRange.put("90-95",0);
        gradeRange.put("95-100",0);

        for(long id: moduleIds) {
            for(int g:gradesRepository.findByModuleID(id)){
                if(g>=0&&g<5){
                    gradeRange.put("0-5",gradeRange.get("0-5")+1);
                }
                else if(g>=5&&g<10){
                    gradeRange.put("5-10",gradeRange.get("5-10")+1);
                }
                else if(g>=10&&g<15){
                    gradeRange.put("10-15",gradeRange.get("10-15")+1);
                }
                else if(g>=15&&g<20){
                    gradeRange.put("15-20",gradeRange.get("15-20")+1);
                }
                else if(g>=20&&g<25){
                    gradeRange.put("20-25",gradeRange.get("20-25")+1);
                }
                else if(g>=25&&g<30){
                    gradeRange.put("25-30",gradeRange.get("25-30")+1);
                }
                else if(g>=30&&g<35){
                    gradeRange.put("30-35",gradeRange.get("30-35")+1);
                }
                else if(g>=35&&g<40){
                    gradeRange.put("35-40",gradeRange.get("35-40")+1);
                }
                else if(g>=40&&g<45){
                    gradeRange.put("40-45",gradeRange.get("40-45")+1);
                }
                else if(g>=45&&g<50){
                    gradeRange.put("45-50",gradeRange.get("45-50")+1);
                }
                else if(g>=50&&g<55){
                    gradeRange.put("50-55",gradeRange.get("50-55")+1);
                }
                else if(g>=55&&g<60){
                    gradeRange.put("55-60",gradeRange.get("55-60")+1);
                }
                else if(g>=60&&g<65){
                    gradeRange.put("60-65",gradeRange.get("60-65")+1);
                }
                else if(g>=65&&g<70){
                    gradeRange.put("65-70",gradeRange.get("65-70")+1);
                }
                else if(g>=70&&g<75){
                    gradeRange.put("70-75",gradeRange.get("70-75")+1);
                }
                else if(g>=75&&g<80){
                    gradeRange.put("75-80",gradeRange.get("75-80")+1);
                }
                else if(g>=80&&g<85){
                    gradeRange.put("80-85",gradeRange.get("80-85")+1);
                }
                else if(g>=85&&g<90){
                    gradeRange.put("85-90",gradeRange.get("85-90")+1);
                }
                else if(g>=90&&g<95){
                    gradeRange.put("90-95",gradeRange.get("90-95")+1);
                }
                else if(g>=95&&g<100){
                    gradeRange.put("95-100",gradeRange.get("95-100")+1);
                }
            }
        }
        return gradeRange;

        //throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
}
