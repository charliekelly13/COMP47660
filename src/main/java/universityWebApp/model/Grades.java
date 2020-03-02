package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "grades")
@IdClass(GradeId.class)
@NamedQuery(name = "Grades.findByStudentID",
        query = "select a.grade from Grades a where a.id_student = ?1")
public class Grades {
    @Id
    @NotBlank
    private String moduleId;
    @Id
    @NotBlank
    private String grade;
    @Id
    @NotBlank
    private String studentId;

    public Grades() {
        super();
    }

    public Grades(String moduleId, String studentId) {
        super();
        this.moduleId = moduleId;
        this.grade = grade;
        this.studentId = studentId;

    }
}
