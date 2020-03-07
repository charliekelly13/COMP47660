package universityWebApp.model;

import java.io.Serializable;
import java.util.Objects;

public class GradeId implements Serializable {


    long moduleId;

    String studentId;

    int grade;

    public GradeId(){}

    public GradeId( long moduleId, int grade, String studentId){
        this.moduleId = moduleId;
        this.studentId = studentId;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeId gradeId = (GradeId) o;
        return (moduleId==gradeId.moduleId) &&
                studentId.equals(gradeId.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, studentId, grade);
    }

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
}
