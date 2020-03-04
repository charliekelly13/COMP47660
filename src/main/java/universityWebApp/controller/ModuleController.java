package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.model.Enrollment;
import universityWebApp.model.Grades;
import universityWebApp.model.Module;
import universityWebApp.repository.CoordinatesRepository;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.GradesRepository;
import universityWebApp.repository.ModuleRepository;

import java.util.List;

@Controller
@SessionAttributes({"loggedIn", "studentId", "isStaff"})
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

        return "module";
    }


    /**
     * enroll student in a module
     */
    @RequestMapping(value = "modules/{id}/enroll", method = RequestMethod.POST)
    public String enroll(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }


        Enrollment enrollment = new Enrollment(moduleId, (String) model.getAttribute("studentId"));

        enrollmentRepository.save(enrollment);

        return "module";
    }

    /**
     * enroll student in a module
     */
    @RequestMapping(value = "modules/{id}/unenroll", method = RequestMethod.POST)
    public String unEnroll(@PathVariable("id") long moduleId, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        //todo make this check if the student is actually enrolled
        Enrollment enrollment = new Enrollment(moduleId, (String) model.getAttribute("studentId"));

        enrollmentRepository.delete(enrollment);

        return "module";
    }


    /**
     * set a students grades if your a coordinator
     */
    @RequestMapping(value = "modules/{id}/grade", method = RequestMethod.POST)
    public String setGrade(@PathVariable("id") long moduleId, Model model, @RequestParam String studentID, @RequestParam String grade) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }
        if (!model.containsAttribute("Staff") || !(boolean) model.getAttribute("isStaff")) {
            //not member of staff so can't change grades
        }

        if (model.getAttribute("studentId") == coordinatesRepository.findByModuleID(moduleId)) {
            Grades grades = new Grades(moduleId, studentID, grade);
            gradesRepository.save(grades);
            return "module";
        }

        return "unauthorised";
    }

}
