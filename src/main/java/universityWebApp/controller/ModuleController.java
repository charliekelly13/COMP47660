package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.model.Module;
import universityWebApp.repository.ModuleRepository;

import java.util.List;

@Controller
@SessionAttributes("loggedIn")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    /**
     * This endpoint returns all modules(?)
     */
    @RequestMapping(value="modules/",method= RequestMethod.GET)
    public String getModules(ModelMap model) {
        if (!model.containsAttribute("LoggedIn")) {
            return("login");
        }

        List<Module> modules = moduleRepository.findAll();
        model.addAttribute("modules", modules);
        return "module";
    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.GET)
    public String getModule(@PathVariable("moduleCode") String moduleCode, Model model) throws ModuleNotFoundException {
        if (!model.containsAttribute("LoggedIn")) {
            return("login");
        }

        Module modules = moduleRepository.findById(moduleCode)
                .orElseThrow(() -> new ModuleNotFoundException(moduleCode));

        model.addAttribute("modules", modules);

        return "modulePage";
    }

}
