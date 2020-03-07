package universityWebApp.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import universityWebApp.model.Staff;
import universityWebApp.model.Student;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;

@Controller
@SessionAttributes({"loggedIn", "staff", "student", "gender", "nationality"})
public class StatsController {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    StudentRepository studentRepository;
    /**
     * This endpoint returns all distribution of user details
     */
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String getUserDistribtuion(Model model) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            return ("redirect_to_login");
        }

        JSONObject gender = new JSONObject();
        gender.put("male", 0);
        gender.put("female", 0);
        gender.put("other", 0);


        for(Staff s: staffRepository.findAll()) {
            gender.put(s.getGender().toLowerCase(), (Integer) gender.get(s.getGender().toLowerCase()) + 1);
        }

        for(Student s: studentRepository.findAll()) {
            gender.put(s.getGender().toLowerCase(), (Integer) gender.get(s.getGender().toLowerCase()) + 1);
        }

        model.addAttribute("gender", gender);

        JSONObject nationality = new JSONObject();

        for(Staff s : staffRepository.findAll()) {
            if (nationality.has(s.getNationality())) {
                nationality.put(s.getNationality(), (Integer) nationality.get(s.getNationality()) + 1);
            } else {
                nationality.put(s.getNationality(), 1);
            }
        }

        for(Student s : studentRepository.findAll()) {
            if (nationality.has(s.getNationality())) {
                nationality.put(s.getNationality(), (Integer) nationality.get(s.getNationality()) + 1);
            } else {
                nationality.put(s.getNationality(), 1);
            }
        }

        model.addAttribute("nationality", nationality);

        return "stats";
    }

}
