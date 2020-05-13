package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import universityWebApp.model.Student;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegisterPage(HttpServletRequest request, ModelMap model, Student student) {
        String passwordPattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]).{8,40})";

        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(student.getPassword());

        if (matcher.matches()) {
            if (staffRepository.findStaffByUsername(student.getUsername()) == null && studentRepository.findStudentByUsername(student.getUsername()) == null) {
                student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
                student.setFeesOwed(3000);
                student.setFeesTotal(3000);
                studentRepository.save(student);
                logger.info(String.format("student %s was registered", student.getId()));
                return "register_confirmation";
            } else {
                logger.warn(String.format("An attempt was made to register a user with id %s which is already taken by ip %s", student.getId(), getIP(request)));
                model.put("errorMessage", "Username Already Exists");
                return "register";
            }
        } else {
            model.put("errorMessage", "Password must contain a Uppercase letter, Lowercase letter, number, special character (.,?!#@ etc) and contain at least eight characters");
            return "register";
        }
    }

    public String getIP(HttpServletRequest request) {
        if (request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1") || request.getRemoteAddr().equalsIgnoreCase("127.0.0.1")) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        return request.getRemoteAddr();
    }
}
