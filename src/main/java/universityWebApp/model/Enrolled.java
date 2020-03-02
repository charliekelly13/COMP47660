package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "enrolled")
@IdClass(EnrolledId.class)
@NamedQuery(name = "Enrolled.findByStudentID",
        query = "select a.id_module from Enrolled a where a.id_student = ?1")
@NamedQuery(name = "Enrolled.findByModuleID",
        query = "select a.id_student from Enrolled a where a.id_module = ?1")
public class Enrolled {
    @Id
    @NotBlank
    private String id_module;

    @Id
    @NotBlank
    private String id_student;

    public Enrolled(){super();}
    public Enrolled(String id_module, String id_student){
        super();
        this.id_module = id_module;
        this.id_student = id_student;
    }

    public String getId_module() {
        return id_module;
    }

    public void setId_module(String id_module) {
        this.id_module = id_module;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }
}
