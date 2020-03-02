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
@NamedQuery(name = "Enrollment.insertEnrollment",
        query = "INSERT INTO enrollment(student_id, grade, module_id), VALUES(?1,?2,?3);")
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
