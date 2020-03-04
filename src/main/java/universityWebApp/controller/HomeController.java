package universityWebApp.controller;

import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.model.Module;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"loggedIn","studentId", "name"})
public class HomeController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    //this should show the modules a student is in but idk how to get that in the DB
    @RequestMapping("/")
    public String viewHomePage(Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        List<Long> enrolledModules = enrollmentRepository.findByStudentID((String)model.getAttribute("studentId"));
        List<Module> modules = new ArrayList<>();
        for (Long moduleId: enrolledModules) {
            modules.add(moduleRepository.findById(moduleId)
                    .orElseThrow(() -> new ModuleNotFoundException(moduleId)));
        }
        model.addAttribute("modules", modules);

        return "home";
    }
}