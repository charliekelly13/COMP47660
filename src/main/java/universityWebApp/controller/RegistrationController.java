package universityWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class RegistrationController {

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String showRegisterpage(ModelMap model){
        return "registrationform";
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String showRegisterpage(ModelMap model){

        return "registrationform";
    }

}
