package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.support.SessionStatus;
import universityWebApp.exception.IpNotFoundException;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.model.Blacklist;
import universityWebApp.repository.BlacklistRepository;
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

import java.util.HashMap;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@SessionAttributes({"loggedIn", "isStaff", "student", "staff", "csrfToken"})
public class LoginController {
	
	@Autowired
	LoginService service;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	BlacklistRepository blacklistRepository;

	Logger logger = LoggerFactory.getLogger(LoginController.class);


	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		return "login";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, HttpServletRequest request, @RequestParam String name, @RequestParam String password) {
		logger.info("Login attempt made for user " + name + " by the IP " + getIP(request));
		int attempts=0;
		Blacklist black;
		try{
			attempts =blacklistRepository.findAttemptsByIp(getIP(request));
			black =  blacklistRepository.findById(getIP(request)).orElseThrow(IpNotFoundException::new);
		}
		catch(IpNotFoundException i) {
			black = new Blacklist(getIP(request), 0);
			blacklistRepository.save(black);
		}
		if(attempts<3){
			model.put("errorMessage", attempts);
			if (!password.equals(studentRepository.findPasswordByUsername(name))) {
			if (!password.equals(staffRepository.findPasswordByUsername(name))) {
				logger.warn("Login failed for user " + name + " by the IP " + getIP(request) + " attempt"+attempts+" of 3");
				model.put("errorMessage", "Invalid Credentials");
				attempts++;
				blacklistRepository.delete(black);
				Blacklist temp = new Blacklist(getIP(request), attempts);
				blacklistRepository.save(temp);
				return "login";
			} else {
				logger.info("Logged in successfully as staff " + name);
				model.put("staff", staffRepository.findStaffByUsername(name));
				model.put("loggedIn", true);
				model.put("isStaff", true);
				model.put("csrfToken", UUID.randomUUID());

				return "welcome";
			}
		}
		}
		else{
			model.put("errorMessage", "You have failed to login in too many times. You're IP address is now blacklisted.");
		}
		logger.info("Logged in successfully as student " + name);

		model.put("student", studentRepository.findStudentByUsername(name));

		model.put("loggedIn", true);
		model.put("isStaff", false);
		model.put("csrfToken", UUID.randomUUID());

		return "welcome";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logOut(SessionStatus status) {
		status.setComplete();

		return "logout";
	}

	public String getIP(HttpServletRequest request){
		if(request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1") || request.getRemoteAddr().equalsIgnoreCase("127.0.0.1")){
			try {
				return InetAddress.getLocalHost().getHostAddress();
			}
			catch (UnknownHostException e) {
				return null;
			}
		}

		return request.getRemoteAddr();
	}

}
