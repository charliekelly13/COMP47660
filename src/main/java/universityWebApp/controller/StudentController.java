package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Module;
import universityWebApp.model.Student;
import universityWebApp.repository.EnrolledRepository;
import universityWebApp.repository.ModuleRepository;
import universityWebApp.repository.StudentRepository;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EnrolledRepository enrolledRepository;
}
