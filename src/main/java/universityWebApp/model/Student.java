package universityWebApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String student_first_name;
    @NotBlank
    private String student_last_name;

    public Student() {
        super();
    }

    public Student(Long id, String student_first_name, String student_last_name) {
        super();
        this.id = id;
        this.student_first_name = student_first_name;
        this.student_last_name = student_last_name;
    }

    public Long getId() {
        return id;
    }

    public String getStudent_first_namee() {
        return student_first_name;
    }

    public String getStudent_last_name() {
        return student_last_name;
    }

}