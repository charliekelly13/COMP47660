package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "enrollment")
@IdClass(EnrollmentId.class)
@NamedQuery(name = "Enrolled.findByStudentID",
        query = "select a.id_module from Enrolled a where a.id_student = ?1")
@NamedQuery(name = "Enrolled.findByModuleID",
        query = "select a.id_student from Enrolled a where a.id_module = ?1")
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
