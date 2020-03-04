package universityWebApp.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentId implements Serializable {

    long moduleId;

    String studentId;

    public EnrollmentId() {}

    public EnrollmentId(long id_module, String id_student){
        this.moduleId = id_module;
        this.studentId = id_student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId authorshipId = (EnrollmentId) o;
        return moduleId == authorshipId.moduleId &&
                studentId.equals(authorshipId.studentId);
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
