package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "grades")
@IdClass(GradeId.class)
@NamedQuery(name = "Grades.findByStudentID",
        query = "select a.grade from Grade a where a.studentId = ?1")
@NamedQuery(name = "Grades.findByModuleID",
        query = "select a.grade from Grade a where a.moduleId = ?1")
@NamedQuery(name = "Grades.findByModuleAndStudentID",
        query = "select a.grade from Grade a where a.moduleId = ?1 and a.studentId = ?2")

public class Grade {
    @Id
    @NotBlank
    private long moduleId;

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @NotBlank
    private int grade;
    @Id
    @NotBlank
    private String studentId;

    public Grade() {
        super();
    }

    public Grade(long moduleId, String studentId, int grade) {
        super();
        this.moduleId = moduleId;
        this.grade = grade;
        this.studentId = studentId;

    }
}
