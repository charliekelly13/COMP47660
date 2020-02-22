package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import universityWebApp.service.ModuleService;

public class ModuleController {

    @Autowired
    ModuleService service;

    /**
     * This endpoint returns all modules(?)
     */
    @RequestMapping(value="modules/",method= RequestMethod.GET)
    public String getModules(ModelMap model) {

        return "module";
    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.GET)
    public String getModule(@PathVariable("moduleCode") String moduleCode, ModelMap model) {

        return "module";
    }

    /**
     * This endpoint adds a given student to a module
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.POST)
    public String addStudentToModule(@PathVariable("moduleCode") String moduleCode, ModelMap model) {

        return "module";
    }

    /**
     * This endpoint removes a given student from a module if they are in it
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.PUT)
    public String removeStudentFromModule(@PathVariable("moduleCode") String moduleCode, ModelMap model) {

        return "module";
    }

}
