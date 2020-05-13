package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Enrollment;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.GradesRepository;
import universityWebApp.repository.ModuleRepository;
import universityWebApp.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


@Controller
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
    public String viewSettingsPage(ModelMap model, Authentication authentication) {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = authorities.get(0).getAuthority();

        if (role.equals("staff")) {
            model.put("isStaff", true);
        }

        return "settings";
    }

    @RequestMapping(value = "/settings/deactivate", method = RequestMethod.POST)
    public String deactivate(Authentication authentication, HttpServletRequest request) throws StudentNotFoundException, ServletException {
        String id = (String) authentication.getCredentials();

        List<Long> enrolledModules = enrollmentRepository.findByStudentID(id);

        for (Long moduleId : enrolledModules) {
            enrollmentRepository.delete(new Enrollment(moduleId, id));
        }

        gradesRepository.deleteInBatch(gradesRepository.findByStudentID(id));
        logger.info(String.format("Student %s deactivated", id));

        studentRepository.deleteById(id);

        request.logout();

        return "deactivated";
    }

    public String getIP(HttpServletRequest request) {
        if (request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1")|| request.getRemoteAddr().equalsIgnoreCase("127.0.0.1")) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        return request.getRemoteAddr();
    }
}
