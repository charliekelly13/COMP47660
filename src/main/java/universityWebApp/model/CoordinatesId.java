package universityWebApp.model;

import java.util.Objects;

public class CoordinatesId {

    String moduleId;

    String staffId;

    public CoordinatesId(){}

    public CoordinatesId( String moduleId, String staffId){
        this.moduleId = moduleId;
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesId coordinatesId = (CoordinatesId) o;
        return moduleId.equals(coordinatesId.moduleId) &&
                staffId.equals(coordinatesId.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, staffId);
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
