package universityWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Module;
import universityWebApp.model.Student;
import universityWebApp.repository.EnrollmentRepository;
import universityWebApp.repository.GradesRepository;
import universityWebApp.repository.ModuleRepository;
import universityWebApp.repository.StudentRepository;

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
    /**
     * This endpoint returns all distribution of gender
     */
    @RequestMapping(value = "student", method = RequestMethod.GET)
    public HashMap getGenderDistribtuion() {

        List<Student> students = studentRepository.findAll();
        HashMap<String, Integer> genderDis = new HashMap();
        genderDis.put("Male", 0);
        genderDis.put("Female", 0);
        genderDis.put("Other", 0);
        String gen;
        for(Student s: students){
            gen=s.getGender();
            if(gen.equals("Male")){
                genderDis.put("Male", genderDis.get("Male")+1);
            }
            else if(gen.equals("Female")){
                genderDis.put("Female", genderDis.get("Female")+1);
            }
            else if(gen.equals("Other")){
                genderDis.put("Other", genderDis.get("Other")+1);
            }
        }
        return genderDis;
    }

    /**
     * This endpoint returns all distribution of student nationality
     */
    @RequestMapping(value = "student", method = RequestMethod.GET)
    public HashMap getNationalityDistribution() {
        List<Student> students = studentRepository.findAll();
        HashMap<String, Integer> nationalDis = new HashMap();
        String nat;
        for(Student s: students){
            nat=s.getNationality();
            if(nationalDis.containsKey(nat)){
                nationalDis.put(nat, nationalDis.get(nat)+1);
            }
            else{
                nationalDis.put(nat, 0);;
            }
        }
        return nationalDis;
    }
}
