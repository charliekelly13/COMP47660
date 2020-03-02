package universityWebApp.controller;

import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Student;
import universityWebApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("name")
public class WelcomeController {


    //this should show the modules a student is in but idk how to get that in the DB
    @RequestMapping("/home")
    public String viewHomePage(Model model) {
        //List<Student> listStudents = studentRepository.findAll();
        //model.addAttribute("listStudents", listStudents);
        return "welcome";
    }


}