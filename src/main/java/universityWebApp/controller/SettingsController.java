package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Enrollment;
import universityWebApp.model.Module;
import universityWebApp.model.Student;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.GradesRepository;
import universityWebApp.repository.ModuleRepository;
import universityWebApp.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes({"loggedIn", "student"})
public class SettingsController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    GradesRepository gradesRepository;

    Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String viewSettingsPage(HttpServletRequest request, Model model) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.info("Attempt made to access settings page while not logged in");
            return ("redirect_to_login");
        }
        return "settings";
    }

    @RequestMapping(value = "/settings/deactivate", method = RequestMethod.POST)
    public String deactivate(HttpServletRequest request, ModelMap model, SessionStatus status) throws StudentNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.warn("Attempt made to delete account while not logged in by IP " + getIP(request));
            return ("login");
        }

        Student student = (Student) model.getAttribute("student");

        List<Long> enrolledModules = enrollmentRepository.findByStudentID(student.getId());

        for (Long moduleId : enrolledModules) {
            enrollmentRepository.delete(new Enrollment(moduleId, student.getId()));
        }

        gradesRepository.deleteInBatch(gradesRepository.findByStudentID(student.getId()));
        logger.info(String.format("Student %s deactivated", student.getId()));
        studentRepository.delete(student);

        status.setComplete();

        return "deactivated";
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
