package universityWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import universityWebApp.model.MyStaffPrincipal;
import universityWebApp.model.MyStudentPrincipal;
import universityWebApp.model.Staff;
import universityWebApp.model.Student;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (loginAttemptService.isBlocked()) {
            throw new SessionAuthenticationException("Your IP Address has been blocked due to repeated failed login attempts. Contact an administrator to unblock your IP address.");
        }

        Student student = studentRepository.findStudentByUsername(username);
        Staff staff = staffRepository.findStaffByUsername(username);

        if (student == null && staff == null) {
            throw new UsernameNotFoundException(username);
        }

        if (student != null) return new MyStudentPrincipal(student);

        return new MyStaffPrincipal(staff);
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
