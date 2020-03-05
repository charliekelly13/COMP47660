package universityWebApp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import universityWebApp.model.Staff;
import universityWebApp.model.Student;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;

public class StatsController {
    StaffRepository staffRepository;
    StudentRepository studentRepository;
    /**
     * This endpoint returns all distribution of gender
     */
    @RequestMapping(value = "stats/getGenderDistribution", method = RequestMethod.GET)
    public HashMap getGenderDistribtuion(Model model) {

        List<Staff> staff = staffRepository.findAll();
        HashMap<String, Integer> genderDis = new HashMap();
        genderDis.put("Male", 0);
        genderDis.put("Female", 0);
        genderDis.put("Other", 0);
        String gen;
        for(Staff s: staff){
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
        List<Student> student = studentRepository.findAll();
        for(Student s: student){
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
     * This endpoint returns all distribution of staff nationality
     */
    @RequestMapping(value = "stats/getNationalityDisribution", method = RequestMethod.GET)
    public HashMap getNationalityDistribution(Model model) {
        List<Staff> staff = staffRepository.findAll();
        HashMap<String, Integer> nationalDis = new HashMap();
        String nat;
        for(Staff s: staff){
            nat=s.getNationality();
            if(nationalDis.containsKey(nat)){
                nationalDis.put(nat, nationalDis.get(nat)+1);
            }
            else{
                nationalDis.put(nat, 0);;
            }
        }
        List<Student> student = studentRepository.findAll();
        for(Student s: student){
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
