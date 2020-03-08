package universityWebApp.model;

import java.io.Serializable;
import java.util.Objects;

public class GradeId implements Serializable {


    long moduleId;

    String studentId;

    public GradeId(){}

    public GradeId( long moduleId, String studentId){
        this.moduleId = moduleId;
        this.studentId = studentId;
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
        return Objects.hash(moduleId, studentId);
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
