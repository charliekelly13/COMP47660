package universityWebApp.model;

import java.io.Serializable;
import java.util.Objects;

public class CoordinatesId implements Serializable {

    long moduleId;

    String staffId;

    public CoordinatesId(){}

    public CoordinatesId( long moduleId, String staffId){
        this.moduleId = moduleId;
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesId coordinatesId = (CoordinatesId) o;
        return moduleId == coordinatesId.moduleId &&
                staffId.equals(coordinatesId.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, staffId);
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
