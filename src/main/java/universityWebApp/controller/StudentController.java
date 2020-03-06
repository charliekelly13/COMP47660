package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import universityWebApp.model.Staff;
import universityWebApp.model.Student;
import universityWebApp.repository.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    GradesRepository gradesRepository;

    @Autowired
    StaffRepository staffRepository;


}
