package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "enrolled")
@IdClass(EnrolledId.class)
@NamedQuery()//todo add query lol
public class Enrolled {
    @Id
    @NotBlank
    private Long id_module;

    @Id
    @NotBlank
    private Long id_student;

    public Enrolled(){super();}
    public Enrolled(Long id_module, Long id_student){
        super();
        this.id_module = id_module;
        this.id_student = id_student;
    }

    public Long getId_module() {
        return id_module;
    }

    public void setId_module(Long id_module) {
        this.id_module = id_module;
    }

    public Long getId_student() {
        return id_student;
    }

    public void setId_student(Long id_student) {
        this.id_student = id_student;
    }
}