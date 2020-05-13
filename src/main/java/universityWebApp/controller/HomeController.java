package universityWebApp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.*;
import universityWebApp.model.Module;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    Logger logger = LoggerFactory.getLogger(HomeController.class);


    //this should show the modules a student is in but idk how to get that in the DB
    @RequestMapping("/")
    public String viewHomePage(Model model, Authentication authentication) throws StudentNotFoundException, ModuleNotFoundException {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = authorities.get(0).getAuthority();
        String id = (String) authentication.getCredentials();
        String username = (String) authentication.getPrincipal();

        if (role.equals("student")) {
            logger.info(String.format("Student %s accessed home page ", username));

            Student student = studentRepository.findStudentByUsername(username);
            model.addAttribute("student", student);

            List<Long> enrolledModules = enrollmentRepository.findByStudentID(id);
            List<Module> modules = new ArrayList<>();

            for (Long moduleId : enrolledModules) {
                modules.add(moduleRepository.findById(moduleId)
                        .orElseThrow(() -> new ModuleNotFoundException(moduleId)));
            }

            model.addAttribute("modules", modules);
        } else {
            logger.info(String.format("Staff member %s accessed home page " , authentication.getPrincipal()));

            Staff staff = staffRepository.findStaffByUsername(username);
            model.addAttribute("staff", staff);

            List<Module> coordinatedModules = moduleRepository.findModulesByCoordinatorIds(id);

            model.addAttribute("modules", coordinatedModules);
        }

        return "home";
    }
}