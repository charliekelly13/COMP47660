package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import universityWebApp.model.Student;
import universityWebApp.repository.StudentRepository;

@Controller
@SessionAttributes({"name"})
public class RegistrationController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String showRegisterPage(ModelMap model) {
        return "register";
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String postRegisterPage(ModelMap model, @RequestParam String name,@RequestParam String surname,
                                   @RequestParam String ID,@RequestParam String address,
                                   @RequestParam String phoneNumber, @RequestParam String emailAddress) {

        Student registerStudent = new Student(ID,name,surname,null,address,phoneNumber,emailAddress);
        studentRepository.save(registerStudent);

        return "registerConfirmation";
    }
}
