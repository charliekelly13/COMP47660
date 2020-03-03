package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "coordinates")
@IdClass(CoordinatesId.class)
@NamedQuery(name = "Coordinates.findByStaffID",
        query = "select a.moduleId from Coordinates a where a.staffId = ?1")
@NamedQuery(name = "Coordinates.findByModuleID",
        query = "select a.staffId from Coordinates a where a.moduleId = ?1")
public class Coordinates {
    @Id
    @NotBlank
    private String moduleId;
    @Id
    @NotBlank
    private String staffId;

    public Coordinates() {
        super();
    }

    public Coordinates(String moduleId, String studentId) {
        super();
        this.moduleId = moduleId;
        this.staffId = staffId;

    }
}
