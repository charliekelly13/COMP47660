package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "enrollment")
@IdClass(EnrollmentId.class)
// @NamedQuery()//todo add query lol
public class Enrollment {
    @Id
    @NotBlank
    private Long moduleId;

    @Id
    @NotBlank
    private String studentId;

    public Enrollment(){super();}

    public Enrollment(Long moduleId, String studentId){
        super();
        this.moduleId = moduleId;
        this.studentId = studentId;
    }
}
