package universityWebApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import universityWebApp.exception.ForbiddenException;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Student;
import universityWebApp.repository.StudentRepository;

import java.util.List;
import java.util.Optional;


@Controller
public class FeesController {

    @Autowired
    StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(FeesController.class);

    @RequestMapping(value = "/fee_payment", method = RequestMethod.GET)
    public String viewFeesPage(Model model, Authentication authentication) throws StudentNotFoundException {
        String userId = (String) authentication.getCredentials();

        Student student = getStudentFromAuth(authentication);

        logger.info(String.format("Student %s accessed fee page ", userId));
        model.addAttribute("feesTotal", student.getFeesTotal());
        model.addAttribute("feesOwed", student.getFeesOwed());

        return "fee_payment";
    }

    @RequestMapping(value = "/fee_payment", method = RequestMethod.POST)
    public String payFees(ModelMap model, @RequestParam double feePayment, Authentication authentication)
            throws StudentNotFoundException {
        Student student = getStudentFromAuth(authentication);

        model.addAttribute("feesTotal", student.getFeesTotal());
        model.addAttribute("feesOwed", student.getFeesOwed());

        if (feePayment < 0) {
            logger.info("attempt made to pay negative amount by student " +student.getId());
            model.put("errorMessage", "Can't make negative or zero payments");
            return "fee_payment";
        } else if (feePayment > student.getFeesOwed()) {
            logger.info("attempt made to pay more than owed by student " +student.getId());
            model.put("errorMessage", "Amount paid greater than fees owed");
            return "fee_payment";
        } else if (student.getFeesOwed() == 0) {
            logger.info("attempt made to pay while no fees are outstanding by student" +student.getId());
            model.put("errorMessage", "You have already paid off your fees");
            return "fee_payment";
        } else {
            logger.info("attempt made to pay negative amount by student " +student.getId());
            student.setFeesOwed(student.getFeesOwed() - feePayment);

            studentRepository.save(student);
            return "payment_confirmation";
        }
    }

    public Student getStudentFromAuth(Authentication authentication) throws StudentNotFoundException, ForbiddenException {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = authorities.get(0).getAuthority();
        String userId = (String) authentication.getCredentials();

        if (role.equals("staff")) {
            throw new ForbiddenException();
        }

        Optional<Student> studentOptional = studentRepository.findById(userId);

        if (!studentOptional.isPresent()) {
            throw new StudentNotFoundException(userId);
        }

        return studentOptional.get();
    }
}