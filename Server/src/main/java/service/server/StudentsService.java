package service.server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsService {


    /**
     * This endpoint is for existing students to login
     */
    @RequestMapping(value="students/",method= RequestMethod.GET)
    public void getStudent() {


    }

    /**
     * This endpoint is for registering new students
     */
    @RequestMapping(value="students/",method= RequestMethod.POST)
    public void registerNewStudent() {


    }


}
