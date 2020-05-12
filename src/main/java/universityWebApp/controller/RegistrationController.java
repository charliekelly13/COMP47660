package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import universityWebApp.model.Staff;
import universityWebApp.model.Student;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@SessionAttributes({"name"})
public class RegistrationController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StaffRepository staffRepository;

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(ModelMap model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegisterPage(HttpServletRequest request,ModelMap model, Student student) {
        String passwordPattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(student.getPassword());

        if(matcher.matches()) {
            if (staffRepository.findStaffByUsername(student.getUsername()) == null && studentRepository.findStudentByUsername(student.getUsername()) == null) {
                studentRepository.save(student);
                logger.info(String.format("student %s was registered", student.getId()));
                model.put("csrfToken", UUID.randomUUID());
                return "register_confirmation";
            } else {
                logger.warn(String.format("An attempt was made to register a user with id %s which is already taken by ip"), student.getId(), getIP(request));
                model.put("errorMessage", "Username Already Exists");
                return "register";
            }
        }
        else{
            model.put("errorMessage", "Password must contain a Uppercase letter, Lowercase letter, number, special character (.,?!#@ etc) and contain at least eight characters");
            return "register";
        }
    }

    public String getIP(HttpServletRequest request){
        if (request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1")|| request.getRemoteAddr().equalsIgnoreCase("127.0.0.1")) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            }
            catch (UnknownHostException e) {
                return null;
            }
        }

        return request.getRemoteAddr();
    }
}
