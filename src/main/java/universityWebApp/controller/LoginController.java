package universityWebApp.controller;

import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;
import universityWebApp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"loggedIn", "isStaff", "student", "staff"})
public class LoginController {
	
	@Autowired
	LoginService service;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StaffRepository staffRepository;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		return "login";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {
		if (!password.equals(studentRepository.findPasswordByUsername(name))) {
			if (!password.equals(staffRepository.findPasswordByUsername(name))) {
				model.put("errorMessage", "Invalid Credentials");
				return "login";
			} else {
				model.put("staff", staffRepository.findStaffByUsername(name));
				model.put("loggedIn", true);
				model.put("isStaff", true);
        
				return "welcome";
			}
		}

		model.put("student", studentRepository.findStudentByUsername(name));

		model.put("loggedIn", true);
		model.put("isStaff", false);

		return "welcome";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logOut(ModelMap model) {
		model.put("loggedIn", false);
		model.remove("student");
		model.remove("isStaff");

		return "logout";
	}
}
