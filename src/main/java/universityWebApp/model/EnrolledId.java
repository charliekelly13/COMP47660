package universityWebApp.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrolledId implements Serializable {


    Long id_module;

    Long id_student;

    public EnrolledId(){}

    public EnrolledId( Long id_module, Long id_student){
        this.id_module = id_module;
        this.id_student = id_student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolledId authorshipId = (EnrolledId) o;
        return id_module.equals(authorshipId.id_module) &&
                id_student.equals(authorshipId.id_student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_module, id_student);
    }

    public Long getId_student(){
        return id_student;
    }
    public Long getIid_module(){
        return id_module;
    }
}
