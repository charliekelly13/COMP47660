package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.model.Module;
import universityWebApp.repository.ModuleRepository;
import universityWebApp.service.ModuleService;

import java.util.List;

public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    /**
     * This endpoint returns all modules(?)
     */
    @RequestMapping(value="modules/",method= RequestMethod.GET)
    public String getModules(Model model) {
        List<Module> listModules = moduleRepository.findAll();
        model.addAttribute("listModule", listModules);
        return "module";
    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.GET)
    public String getModule(@PathVariable("moduleCode") Long moduleCode, ModelMap model) throws ModuleNotFoundException {
        Module module = moduleRepository.findById(moduleCode)
                .orElseThrow(() -> new ModuleNotFoundException(moduleCode));


        model.addAttribute("listModule", module);
        return "module";
    }

    /**
     * Deletes a module
     * @param moduleId
     * @param model
     * @return
     * @throws ModuleNotFoundException
     */
    @RequestMapping("module/{moduleCode}/delete")
    public String deleteModule(@PathVariable(value = "moduleCode") Long moduleId, Model model) throws ModuleNotFoundException {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(moduleId));

        moduleRepository.delete(module);
        return getModules(model);
    }


    //TODO idk if this should be in this method discuss @oisín & @charlie
    /**
     * This endpoint adds a given student to a module
     */
    @RequestMapping(value="modules/{moduleCode}/addStudent",method= RequestMethod.POST)
    public String addStudentToModule(@PathVariable("moduleCode") String moduleCode, ModelMap model) {

        return "module";
    }

    /**
     * This endpoint removes a given student from a module if they are in it
     */
    @RequestMapping(value="modules/{moduleCode}/removeStudent",method= RequestMethod.PUT)
    public String removeStudentFromModule(@PathVariable("moduleCode") String moduleCode, ModelMap model) {

        return "module";
    }

}
