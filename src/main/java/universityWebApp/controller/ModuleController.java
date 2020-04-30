package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import universityWebApp.repository.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Controller
@SessionAttributes({"student", "loggedIn", "isStaff", "status", "enrolled", "staff", "module"})
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    GradesRepository gradesRepository;

    @Autowired
    StaffRepository staffRepository;

    Logger logger = LoggerFactory.getLogger(ModuleController.class);

    /**
     * This endpoint returns all modules
     */
    @RequestMapping(value = "modules", method = RequestMethod.GET)
    public String getModules(HttpServletRequest request, ModelMap model, @RequestParam(defaultValue = "") String searchTerm) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.info("Attempt made to access modules page while not logged in by the IP " + getIP(request));
            return ("redirect_to_login");
        }

        List<Module> modules = moduleRepository.findAll();

        if (searchTerm.isEmpty()) {
            model.addAttribute("modules", modules);
        } else {
            List<Module> filteredModules = new ArrayList<>();
            searchTerm = searchTerm.toLowerCase();
            for (Module module : modules) {
                Staff coordinator = staffRepository.findStaffById(module.getCoordinatorId());

                if (coordinator.getFirstName().toLowerCase().contains(searchTerm) ||
                        coordinator.getLastName().toLowerCase().contains(searchTerm) ||
                        module.getModuleCode().toLowerCase().contains(searchTerm) ||
                        module.getModuleName().toLowerCase().contains(searchTerm) ||
                        module.getModuleDescription().toLowerCase().contains(searchTerm)
                        || module.getModuleYear().toLowerCase().contains(searchTerm)) {
                    filteredModules.add(module);
                }
            }
            model.addAttribute("modules", filteredModules);
        }

        return "modules";

    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value = "modules/{id}", method = RequestMethod.GET)
    public String getModule(HttpServletRequest request, @PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.info(String.format("Attempt made to access module page %s while not logged in by the IP %s", moduleId, getIP(request)));
            return ("redirect_to_login");
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        model.addAttribute("module", module);


        if (model.containsAttribute("student")) {
            EnrollmentId enrollmentId = new EnrollmentId(moduleId, ((Student) model.getAttribute("student")).getId());

            if (enrollmentRepository.findById(enrollmentId).isPresent()) {
                model.addAttribute("status", "unenrol");
            } else {
                model.addAttribute("status", "enrol");
            }

            Student student = ((Student) model.getAttribute("student"));
            logger.info(String.format("Student %s viewed module %s", student.getId(), moduleId));

            Grade grade = gradesRepository.findByModuleAndStudentID(moduleId, student.getId());

            if (grade != null) {
                model.addAttribute("grade", grade.getGrade());
            } else {
                model.addAttribute("grade", "Not yet graded");
            }
        }

        Staff coordinator = staffRepository.findStaffById(module.getCoordinatorId());
        model.addAttribute("coordinator", coordinator.getFirstName() + " " + coordinator.getLastName());
        long numberOfStudentsEnrolled = enrollmentRepository.findByModuleID(module.getId()).size();
        model.addAttribute("amountOfStudents", numberOfStudentsEnrolled);

        model.addAttribute("gradeMap", getGradeMap(module.getModuleCode()));

        return "module";
    }

    @RequestMapping(value = "modules/{id}/edit", method = RequestMethod.GET)
    public String editModule(HttpServletRequest request, @PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.warn(String.format("Attempt made to access module %s edit page while not logged in by the IP %s", moduleId, getIP(request)));
            return ("redirect_to_login");
        }

        if (!(Boolean) model.getAttribute("isStaff")) {
            logger.warn(String.format("Student Attempt made to access module %s edit page by student %s ", moduleId, ((Student) model.getAttribute("student")).getId()));
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        Optional<Module> module = moduleRepository.findById(moduleId);

        if (!module.isPresent()) {
            logger.info(String.format("Staff Attempt made to access non existing module %s edit page ", moduleId));

            throw new ModuleNotFoundException(moduleId);
        }

        model.addAttribute("module", module.get());
        logger.info(String.format("Staff accessed non existing module %s edit page ", moduleId));
        return "edit_module";
    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value = "modules/{id}/edit", method = RequestMethod.POST)
    public String editModule(HttpServletRequest request, ModelMap model, Module module) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.warn(String.format("Attempt made to edit module %s while not logged in by the IP %s", module.getId(), getIP(request)));
            return ("redirect_to_login");
        }

        if (!model.containsAttribute("isStaff") || !(boolean) model.getAttribute("isStaff")) {
            logger.warn(String.format("Student Attempt made to Edit module %s, by student %s ", module.getId(), ((Student) model.getAttribute("student")).getId()));
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        moduleRepository.save(module);

        model.addAttribute("module", module);

        return "redirect_to_module";
    }

    /**
     * enroll student in a module
     */
    @RequestMapping(value = "modules/{id}/enrol", method = RequestMethod.POST)
    public String enroll(HttpServletRequest request, @PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException,
            ModuleFullException, FeesNotPaidException, StudentAlreadyEnrolledException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.info(String.format("Attempt made to enrol in module %s while not logged in by the IP %s", moduleId, getIP(request)));
            return ("redirect_to_login");
        }

        Student student = (Student) model.getAttribute("student");
        Enrollment enrollment = new Enrollment(moduleId, student.getId());

        if (enrollmentRepository.findById(new EnrollmentId(moduleId, student.getId())).isPresent()) {
            logger.info(String.format("Attempt made to enrol in module %s while already enrolled in it by student %s", moduleId, student.getId()));
            throw new StudentAlreadyEnrolledException(student.getId(), moduleId);
        }

        if (!student.hasPaidFees()) {
            logger.info(String.format("Attempt made to enrol in module %s while fees are owed by student %s", moduleId, student.getId()));
            throw new FeesNotPaidException();
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));


        long countEnrolledStudents = enrollmentRepository.findByModuleID(moduleId).size();

        if (countEnrolledStudents >= module.getMaximumStudents()) {
            logger.info(String.format("Attempt made to enrol in module %s while it is full by student %s", moduleId, student.getId()));
            throw new ModuleFullException(moduleId);
        }

        enrollmentRepository.save(enrollment);

        addModuleViewDetailsToModel(model, module);
        logger.info(String.format("Student %s enrolled in module %s", student.getId(), moduleId));

        model.addAttribute("status", "enrol");

        return "enrollment_status";
    }

    /**
     * enrol student in a module
     */
    @RequestMapping(value = "modules/{id}/unenrol", method = RequestMethod.POST)
    public String unEnrol(HttpServletRequest request, @PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {

        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.info(String.format("Attempt made to unenrol in module %s while not logged in by the IP", moduleId, getIP(request)));
            return ("redirect_to_login");
        }

        Student student = (Student) model.getAttribute("student");
        logger.info(String.format("Student %s unenrolled from module %s", student.getId(), moduleId));

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
    public String setGrade(HttpServletRequest request, @PathVariable("id") long moduleId, Model model, @RequestParam String studentID, @RequestParam int grade) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.warn(String.format("Attempt made to add grade in module %s while not logged in by the IP", moduleId, getIP(request)));
            return ("redirect_to_login");
        }

        if (!model.containsAttribute("isStaff") || !(boolean) model.getAttribute("isStaff")) {
            logger.warn(String.format("Student Attempt made to Edit module %s, by student %s ", moduleId, ((Student) model.getAttribute("student")).getId()));
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        Staff coordinator = ((Staff) model.getAttribute("staff"));

        if (coordinator.getId().equals(module.getCoordinatorId())) {
            if (gradesRepository.findById(new GradeId(moduleId, studentID)).isPresent()) {
                gradesRepository.deleteById(new GradeId(moduleId, studentID));
            }
            logger.info(String.format("Staff member %s added a grade to module %s for student %s", coordinator.getId(), moduleId, studentID));

            gradesRepository.save(new Grade(moduleId, studentID, grade));
            return "grade_confirmation";
        }
        logger.warn(String.format("Staff Attempt made to Edit module %s, by non-coordinator Staff %s ", moduleId, ((Student) model.getAttribute("student")).getId()));

        throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "modules/{id}/", method = RequestMethod.POST)
    public String getGradeStats(HttpServletRequest request, @PathVariable("id") String moduleCode, Model model, @RequestParam String studentID, @RequestParam String grade) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.warn(String.format("Attempt made to modify module %s while not logged in by the IP", moduleCode, getIP(request)));
            return ("redirect_to_login");
        }


        model.addAttribute("gradeMap", getGradeMap(moduleCode));

        return "module";
    }

    private void populateGradeMap(Map<String, Integer> gradeMap) {
        gradeMap.put("0-5", 0);
        gradeMap.put("5-10", 0);
        gradeMap.put("10-15", 0);
        gradeMap.put("15-20", 0);
        gradeMap.put("20-25", 0);
        gradeMap.put("25-30", 0);
        gradeMap.put("30-35", 0);
        gradeMap.put("35-40", 0);
        gradeMap.put("40-45", 0);
        gradeMap.put("45-50", 0);
        gradeMap.put("50-55", 0);
        gradeMap.put("55-60", 0);
        gradeMap.put("60-65", 0);
        gradeMap.put("65-70", 0);
        gradeMap.put("70-75", 0);
        gradeMap.put("75-80", 0);
        gradeMap.put("80-85", 0);
        gradeMap.put("85-90", 0);
        gradeMap.put("90-95", 0);
        gradeMap.put("95-100", 0);
    }

    private Map<String, Integer> getGradeMap(String moduleCode) {
        List<Long> moduleIds = moduleRepository.findIDByCode(moduleCode);
        Map<String, Integer> gradeRange = new TreeMap<>();

        populateGradeMap(gradeRange);

        for (long id : moduleIds) {
            for (Grade g : gradesRepository.findGradesByModuleId(id)) {
                int gradeScore = g.getGrade();

                String bucket = "";

                if (gradeScore >= 0 && gradeScore < 5) {
                    bucket = "0-5";
                } else if (gradeScore >= 5 && gradeScore < 10) {
                    bucket = "5-10";
                } else if (gradeScore >= 10 && gradeScore < 15) {
                    bucket = "10-15";
                } else if (gradeScore >= 15 && gradeScore < 20) {
                    bucket = "15-20";
                } else if (gradeScore >= 20 && gradeScore < 25) {
                    bucket = "20-25";
                } else if (gradeScore >= 25 && gradeScore < 30) {
                    bucket = "25-30";
                } else if (gradeScore >= 30 && gradeScore < 35) {
                    bucket = "30-35";
                } else if (gradeScore >= 35 && gradeScore < 40) {
                    bucket = "35-40";
                } else if (gradeScore >= 40 && gradeScore < 45) {
                    bucket = "40-45";
                } else if (gradeScore >= 45 && gradeScore < 50) {
                    bucket = "45-50";
                } else if (gradeScore >= 50 && gradeScore < 55) {
                    bucket = "50-55";
                } else if (gradeScore >= 55 && gradeScore < 60) {
                    bucket = "55-60";
                } else if (gradeScore >= 60 && gradeScore < 65) {
                    bucket = "60-65";
                } else if (gradeScore >= 65 && gradeScore < 70) {
                    bucket = "65-70";
                } else if (gradeScore >= 70 && gradeScore < 75) {
                    bucket = "70-75";
                } else if (gradeScore >= 75 && gradeScore < 80) {
                    bucket = "75-80";
                } else if (gradeScore >= 80 && gradeScore < 85) {
                    bucket = "80-85";
                } else if (gradeScore >= 85 && gradeScore < 90) {
                    bucket = "85-90";
                } else if (gradeScore >= 90 && gradeScore < 95) {
                    bucket = "90-95";
                } else if (gradeScore >= 95 && gradeScore <= 100) {
                    bucket = "95-100";
                }

                gradeRange.put(bucket, gradeRange.get(bucket) + 1);

            }
        }

        return gradeRange;
    }

    public String getIP(HttpServletRequest request) {
        if (request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        return request.getRemoteAddr();
    }
}
