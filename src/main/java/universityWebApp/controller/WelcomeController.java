package universityWebApp.controller;


import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Student;
import universityWebApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/home")
    public String viewHomePage(Model model) {
        List<Student> listStudents = studentRepository.findAll();
        model.addAttribute("listStudents", listStudents);
        return "welcome";
    }

    // Delete a Note
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long bookId, Model model) throws StudentNotFoundException {
        Student student = studentRepository.findById(bookId)
                .orElseThrow(() -> new StudentNotFoundException(bookId));

        studentRepository.delete(student);

        return viewHomePage(model);

    }
    // Create a new Note
    @RequestMapping("/new")
    public String createBook() {
        return "editform";
    }

    // Create a new Note
    @PostMapping("/students")
    public String createNote(@ModelAttribute("student") Student student, Model model) {
        studentRepository.save(student);
        return viewHomePage(model);
    }

    // Update a Note
    @RequestMapping(value = "students/save", method = RequestMethod.POST)
    public String updateNote(@ModelAttribute("student") Student student, Model model) throws StudentNotFoundException {

        studentRepository.save(student);

        return viewHomePage(model);
    }

    // Get a Single Note
    @GetMapping("/students/{id}")
    public String getNoteById(@PathVariable(value = "id") Long studentId, Model model) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        model.addAttribute("student", student);
        return "editform";
    }

}