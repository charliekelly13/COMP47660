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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static universityWebApp.Utilities.getIP;


@Controller
public class FeesController {

    @Autowired
    StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(FeesController.class);

    @RequestMapping(value = "/fee_payment", method = RequestMethod.GET)
    public String viewFeesPage(Model model, Authentication authentication, HttpServletRequest request) throws StudentNotFoundException {
        String userId = (String) authentication.getCredentials();
        Student student = getStudentFromAuth(authentication, request);

        logger.info(String.format("Student %s with ID %s accessed fee page ", student.getUsername(), student.getId()));
        model.addAttribute("feesTotal", student.getFeesTotal());
        model.addAttribute("feesOwed", student.getFeesOwed());

        return "fee_payment";
    }

    @RequestMapping(value = "/fee_payment", method = RequestMethod.POST)
    public String payFees(ModelMap model, @RequestParam double feePayment, Authentication authentication, HttpServletRequest request)
            throws StudentNotFoundException {
        Student student = getStudentFromAuth(authentication, request);

        model.addAttribute("feesTotal", student.getFeesTotal());
        model.addAttribute("feesOwed", student.getFeesOwed());

        if (feePayment < 0) {
            logger.info(String.format("Student %s with ID %s attempted to pay a negative amount of fees.",
                    student.getUsername(), student.getId()));
            model.put("errorMessage", "Can't make negative or zero payments");
            return "fee_payment";
        } else if (feePayment > student.getFeesOwed()) {
            logger.info(String.format("Student %s with ID %s attempted to pay more fees than owed.",
                    student.getUsername(), student.getId()));
            model.put("errorMessage", "Amount paid greater than fees owed");
            return "fee_payment";
        } else if (student.getFeesOwed() == 0) {
            logger.info(String.format("Student %s with ID %s attempted to pay fees while no fees were outstanding.",
                    student.getUsername(), student.getId()));
            model.put("errorMessage", "You have already paid off your fees");
            return "fee_payment";
        } else {
            logger.info(String.format("Student %s with ID %s successfully paid fees amounting to %f. Their new balance is %f",
                    student.getUsername(), student.getId(), feePayment, student.getFeesOwed() - feePayment));
            student.setFeesOwed(student.getFeesOwed() - feePayment);

            studentRepository.save(student);
            return "payment_confirmation";
        }
    }

    public Student getStudentFromAuth(Authentication authentication, HttpServletRequest request) throws StudentNotFoundException, ForbiddenException {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        String role = authorities.get(0).getAuthority();
        String userId = (String) authentication.getCredentials();

        if (role.equals("staff")) {
            String username = (String) authentication.getPrincipal();
            logger.warn(String.format("Staff member with ID %s and IP address %s tried to access fee page", userId, getIP(request)));
            throw new ForbiddenException();
        }

        Optional<Student> studentOptional = studentRepository.findById(userId);

        if (!studentOptional.isPresent()) {
            throw new StudentNotFoundException(userId);
        }

        return studentOptional.get();
    }
}
