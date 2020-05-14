package universityWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage(Model model, HttpServletRequest request, @RequestParam(required = false) String error) {

		if (error == null) {
			return "login";
		}

		switch (error) {
			case "blocked":
				model.addAttribute("errorMessage", "Your IP Address has been blocked due to repeated failed login attempts. Contact an administrator to unblock your IP address.");
				break;
			case "invalid":
				model.addAttribute("errorMessage", "Invalid credentials.");
				break;
		}

		return "login";
	}
}
