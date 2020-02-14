package service.server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModulesService {

    /**
     * This endpoint returns all modules(?)
     */
    @RequestMapping(value="modules/",method= RequestMethod.GET)
    public void getModules() {


    }

    /**
     * This endpoint gets a specific module's details if it exists
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.GET)
    public void getModule(@PathVariable("moduleCode") String moduleCode) {


    }

    /**
     * This endpoint adds a given student to a module
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.POST)
    public void addStudentToModule(@PathVariable("moduleCode") String moduleCode) {


    }

    /**
     * This endpoint removes a given student from a module if they are in it
     */
    @RequestMapping(value="modules/{moduleCode}",method= RequestMethod.PUT)
    public void removeStudentFromModule(@PathVariable("moduleCode") String moduleCode) {


    }

}
