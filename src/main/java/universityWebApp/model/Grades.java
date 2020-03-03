package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "grades")
@IdClass(GradeId.class)
@NamedQuery(name = "Grades.findByStudentID",
        query = "select a.grade from Grades a where a.studentId = ?1")
@NamedQuery(name = "Grades.findByModuleID",
        query = "select a.grade from Grades a where a.moduleId = ?1")
@NamedQuery(name = "Grades.findByModuleAndStudentID",
        query = "select a.grade from Grades a where a.moduleId = ?1 and a.studentId = ?2")

public class Grades {
    @Id
    @NotBlank
    private long moduleId;
    @Id
    @NotBlank
    private String grade;
    @Id
    @NotBlank
    private String studentId;

    public Grades() {
        super();
    }

    public Grades(long moduleId, String studentId, String grade) {
        super();
        this.moduleId = moduleId;
        this.grade = grade;
        this.studentId = studentId;

    }
}
