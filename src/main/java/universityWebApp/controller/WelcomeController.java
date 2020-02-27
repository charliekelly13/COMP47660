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
    private boolean login=false;



    @RequestMapping("/")
    public String viewHomePage(Model model) {
        if (!login){
            return "login";
        }
        //List<Student> listStudents = studentRepository.findAll();
        //model.addAttribute("listStudents", listStudents);
        return "module";//maybe make it return welcome and make that be the modules
    }


}