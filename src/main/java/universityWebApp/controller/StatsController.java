package universityWebApp.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

@Controller
@SessionAttributes({"loggedIn", "staff", "student", "gender", "nationality"})
public class StatsController {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StatsController.class);

    /**
     * This endpoint returns all distribution of user details
     */
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String getUserDistribtuion(HttpServletRequest request, Model model) {
        if (!model.containsAttribute("loggedIn") || !(boolean) model.getAttribute("loggedIn")) {
            logger.info("Attempt made to access stats page while not logged in by IP "+ getIP(request));
            return ("redirect_to_login");
        }

        JSONObject staffGender = new JSONObject();
        staffGender.put("Male", 0);
        staffGender.put("Female", 0);
        staffGender.put("Other", 0);

        JSONObject gender = new JSONObject();
        gender.put("Male", 0);
        gender.put("Female", 0);
        gender.put("Other", 0);


        for (Staff s : staffRepository.findAll()) {
            String genderValue = s.getGender().substring(0, 1).toUpperCase() + s.getGender().substring(1).toLowerCase();

            gender.put(genderValue, (Integer) gender.get(genderValue) + 1);
            staffGender.put(genderValue, (Integer) staffGender.get(genderValue) + 1);
        }

        JSONObject studentGender = new JSONObject();
        studentGender.put("Male", 0);
        studentGender.put("Female", 0);
        studentGender.put("Other", 0);

        for (Student s : studentRepository.findAll()) {
            String genderValue = s.getGender().substring(0, 1).toUpperCase() + s.getGender().substring(1).toLowerCase();

            gender.put(genderValue, (Integer) gender.get(genderValue) + 1);
            studentGender.put(genderValue, (Integer) studentGender.get(genderValue) + 1);
        }

        model.addAttribute("staff_gender", staffGender);
        model.addAttribute("student_gender", studentGender);
        model.addAttribute("gender", gender);

        JSONObject nationality = new JSONObject();
        JSONObject staffNationality = new JSONObject();
        JSONObject studentNationality = new JSONObject();

        for (Staff s : staffRepository.findAll()) {
            String nationalityValue = s.getNationality().substring(0, 1).toUpperCase() + s.getNationality().substring(1).toLowerCase();

            if (nationality.has(nationalityValue)) {
                nationality.put(nationalityValue, (Integer) nationality.get(nationalityValue) + 1);
            } else {
                nationality.put(nationalityValue, 1);
            }

            if (staffNationality.has(nationalityValue)) {
                staffNationality.put(nationalityValue, (Integer) staffNationality.get(nationalityValue) + 1);
            } else {
                staffNationality.put(nationalityValue, 1);
            }
        }

        for (Student s : studentRepository.findAll()) {
            String nationalityValue = s.getNationality().substring(0, 1).toUpperCase() + s.getNationality().substring(1).toLowerCase();

            if (nationality.has(nationalityValue)) {
                nationality.put(nationalityValue, (Integer) nationality.get(nationalityValue) + 1);
            } else {
                nationality.put(nationalityValue, 1);
            }

            if (studentNationality.has(nationalityValue)) {
                studentNationality.put(nationalityValue, (Integer) studentNationality.get(nationalityValue) + 1);
            } else {
                studentNationality.put(nationalityValue, 1);
            }
        }

        model.addAttribute("staff_nationality", staffNationality);
        model.addAttribute("student_nationality", studentNationality);
        model.addAttribute("nationality", nationality);

        return "stats";
    }

    public String getIP(HttpServletRequest request) {
        if (request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        return request.getRemoteAddr();
    }

}
