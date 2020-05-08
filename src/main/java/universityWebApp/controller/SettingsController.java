package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpClientErrorException;
import universityWebApp.exception.ForbiddenException;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.exception.StudentNotFoundException;
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
@SessionAttributes({"loggedIn", "student", "csrfToken"})
public class SettingsController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    GradesRepository gradesRepository;

    @RequestMapping(value="/settings",method= RequestMethod.GET)
    public String viewSettingsPage(Model model) {
        return "settings";
    }

    @RequestMapping(value="/settings/deactivate",method= RequestMethod.POST)
    public String deactivate(ModelMap model, SessionStatus status, String csrfToken) throws StudentNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("login");
        }

        if (!csrfToken.equals(model.get("csrfToken"))) {
            throw new ForbiddenException();
        }

        Student student = (Student) model.getAttribute("student");

        List<Long> enrolledModules = enrollmentRepository.findByStudentID(student.getId());

        for (Long moduleId: enrolledModules) {
            enrollmentRepository.delete(new Enrollment(moduleId,student.getId()));
        }

        gradesRepository.deleteInBatch(gradesRepository.findByStudentID(student.getId()));

        studentRepository.delete(student);

        status.setComplete();

        return "deactivated";
    }

}
