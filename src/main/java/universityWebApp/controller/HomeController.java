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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes({"loggedIn", "student", "staff"})
public class HomeController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    //this should show the modules a student is in but idk how to get that in the DB
    @RequestMapping("/")
    public String viewHomePage(Model model, Authentication authentication) throws StudentNotFoundException, ModuleNotFoundException {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = authorities.get(0).getAuthority();

        if (role.equals("student")) {
            Student student = ((MyStudentPrincipal) authentication.getPrincipal()).getStudent();
            model.addAttribute("student", student);

            List<Long> enrolledModules = enrollmentRepository.findByStudentID(student.getId());
            List<Module> modules = new ArrayList<>();

            for (Long moduleId : enrolledModules) {
                modules.add(moduleRepository.findById(moduleId)
                        .orElseThrow(() -> new ModuleNotFoundException(moduleId)));
            }

            model.addAttribute("modules", modules);
        } else {
            Staff staff = ((MyStaffPrincipal) authentication.getPrincipal()).getStaff();
            model.addAttribute("staff", staff);

            List<Module> coordinatedModules = moduleRepository.findModulesByCoordinatorIds(staff.getId());

            model.addAttribute("modules", coordinatedModules);
        }

        return "home";
    }
}