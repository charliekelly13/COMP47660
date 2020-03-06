package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.model.Enrollment;
import universityWebApp.model.Module;
import universityWebApp.model.Student;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.GradesRepository;
import universityWebApp.repository.ModuleRepository;
import universityWebApp.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("loggedIn")
public class SettingsController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    GradesRepository gradesRepository;

    @RequestMapping(value="/Settings",method= RequestMethod.GET)
    public String viewSettingsPage(Model model) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("login");
        }
        return "settings";
    }

    @RequestMapping(value="/Settings/DeleteAccount",method= RequestMethod.POST)
    public String deleteAccount(Model model, Student student) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("login");
        }

        List<Long> enrolledModules = enrollmentRepository.findByStudentID(student.getId());
        for (Long moduleId: enrolledModules) {
            enrollmentRepository.delete(new Enrollment(moduleId,student.getId()));
        }

        studentRepository.delete(student);


        return "redirect_to_login";
    }

}
