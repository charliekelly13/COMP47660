package universityWebApp.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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
