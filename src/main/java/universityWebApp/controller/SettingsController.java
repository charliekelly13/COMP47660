package universityWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("loggedIn")
public class SettingsController {


    @RequestMapping(value="/Settings",method= RequestMethod.GET)
    public String viewSettingsPage(Model model) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("login");
        }
        return "settings";
    }

    @RequestMapping(value="/Settings/DeleteAccount",method= RequestMethod.POST)
    public String deleteAccount(Model model) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("login");
        }

        return "redirect_to_login";
    }

}
