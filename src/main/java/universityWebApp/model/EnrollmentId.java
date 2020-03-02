package universityWebApp.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentId implements Serializable {


    Long moduleId;

    Long studentId;

    public EnrollmentId(){}

    public EnrollmentId( Long id_module, Long id_student){
        this.moduleId = id_module;
        this.studentId = id_student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId authorshipId = (EnrollmentId) o;
        return moduleId.equals(authorshipId.moduleId) &&
                studentId.equals(authorshipId.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, studentId);
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
