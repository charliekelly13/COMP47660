package universityWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import universityWebApp.model.MyStaffPrincipal;
import universityWebApp.model.MyStudentPrincipal;
import universityWebApp.model.Staff;
import universityWebApp.model.Student;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Student student = studentRepository.findStudentByUsername(username);
        Staff staff = staffRepository.findStaffByUsername(username);

        if (student == null && staff == null) {
            throw new UsernameNotFoundException(username);
        }

        if (student != null) return new MyStudentPrincipal(student);

        return new MyStaffPrincipal(staff);
    }
}
