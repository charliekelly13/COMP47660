package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Student;
import universityWebApp.repository.StudentRepository;


@Controller
@SessionAttributes({"loggedIn", "student", "staff"})
public class FeesController {


    @Autowired
    StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value = "/fee_payment", method = RequestMethod.GET)
    public String viewFeesPage(Model model) {

        if (model.containsAttribute("student")) {
            Student student = (Student) model.getAttribute("student");
            logger.info(String.format("Student %s accessed fee page ", student.getId()));

            model.addAttribute("feesTotal", student.getFeesTotal());
            model.addAttribute("feesOwed", student.getFeesOwed());
        }

        return "fee_payment";
    }


    @RequestMapping(value = "/fee_payment", method = RequestMethod.POST)
    public String payFees(ModelMap model, Student student, @RequestParam String csrfToken, @RequestParam int feePayment) {
        //if (!csrfToken.equals(model.get("csrfToken"))) {
        //    System.out.println("this happened");
        //    throw new ForbiddenException();
        //}

        if (feePayment < 0) {
            model.put("errorMessage", "Can't make Negative Payments");
            return "fee_payment";
        } else if (feePayment > student.getFeesOwed()) {
            model.put("errorMessage", "Amount paid greater than fees owed");
            return "fee_payment";
        } else if (student.getFeesOwed() == 0) {
            model.put("errorMessage", "You have already paid off your fees");
            return "fee_payment";
        } else {
            student.setFeesOwed(student.getFeesOwed() - feePayment);
            studentRepository.save(student);
            return "payment_confirmation";
        }
    }
}

