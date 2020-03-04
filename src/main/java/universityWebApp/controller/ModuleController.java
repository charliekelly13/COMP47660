package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import universityWebApp.exception.*;
import universityWebApp.model.*;
import universityWebApp.model.Module;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.GradesRepository;
import universityWebApp.repository.ModuleRepository;

import java.util.List;

@Controller
@SessionAttributes({"student","loggedIn","isStaff"})
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    GradesRepository gradesRepository;

    /**
     * This endpoint returns all modules(?)
     */
    @RequestMapping(value="modules",method= RequestMethod.GET)
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
    @RequestMapping(value="modules/{id}",method= RequestMethod.GET)
    public String getModule(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        model.addAttribute("module", module);

        return "module";
    }


    /**
     * enroll student in a module
     */
    @RequestMapping(value="modules/{id}/enrol",method= RequestMethod.GET)
    public String enroll(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException,
            ModuleFullException, StudentNotFoundException, FeesNotPaidException, StudentAlreadyEnrolledException {
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

        model.addAttribute("module", module);

        return "module";
    }

    /**
     * enrol student in a module
     */
    @RequestMapping(value="modules/{id}/unenrol",method= RequestMethod.GET)
    public String unEnroll(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        Student student = (Student) model.getAttribute("student");

        Enrollment enrollment = new Enrollment(moduleId, student.getId());

        enrollmentRepository.delete(enrollment);

        return "home";
    }


    /**
     * set a students grades if your a coordinator
     */
    @RequestMapping(value="modules/{id}/grade",method= RequestMethod.POST)
    public String setGrade(@PathVariable("id") long moduleId, Model model, @RequestParam String studentID,@RequestParam String grade) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }
        if(!model.containsAttribute("isStaff") || !(boolean) model.getAttribute("isStaff")){
            //not member of staff so can't change grades
        }


        Grades grades= new Grades(moduleId, studentID, grade);

        gradesRepository.save(grades);

        return "module";
    }

}
