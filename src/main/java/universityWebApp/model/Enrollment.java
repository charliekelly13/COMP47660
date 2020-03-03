package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "enrollment")
@IdClass(EnrollmentId.class)
@NamedQuery(name = "Enrollment.findByStudentID",
        query = "select a.moduleId from Enrollment a where a.studentId = ?1")
@NamedQuery(name = "Enrollment.findByModuleID",
        query = "select a.studentId from Enrollment a where a.moduleId = ?1")
public class Enrollment {
    @Id
    @NotBlank
    private String moduleId;

    @Id
    @NotBlank
    private String studentId;

    public Enrollment(){super();}

    public Enrollment(String moduleId, String studentId){
        super();
        this.moduleId = moduleId;
        this.studentId = studentId;
    }
}
