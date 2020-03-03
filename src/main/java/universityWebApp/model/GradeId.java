package universityWebApp.model;

import java.io.Serializable;
import java.util.Objects;

public class GradeId implements Serializable {


    String moduleId;

    String studentId;

    String grade;

    public GradeId(){}

    public GradeId( String moduleId, String grade, String studentId){
        this.moduleId = moduleId;
        this.studentId = studentId;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeId gradeId = (GradeId) o;
        return moduleId.equals(gradeId.moduleId) &&
                studentId.equals(gradeId.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, studentId, grade);
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
