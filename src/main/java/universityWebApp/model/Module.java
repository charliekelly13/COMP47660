package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "modules")
@NamedQuery(name = "Module.findByIDfromCode",
        query = "select a.id from Module a where a.moduleCode = ?1")

public class Module {
    @NotBlank
    private String moduleName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @NotBlank
    private String moduleCode;

    @NotBlank
    private String moduleYear;

    @NotBlank
    private String moduleDescription;

    @NotBlank
    private String coordinatorId;
    @NotBlank
    private int maximumStudents;

    public Module() {}

    public Module(long id, String moduleCode, String moduleYear, String moduleName, String moduleDescription, String coordinatorId, int maximumStudents) {
        this.id = id;
        this.moduleCode = moduleCode;
        this.moduleYear = moduleYear;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.coordinatorId = coordinatorId;
        this.maximumStudents = maximumStudents;
    }


    public long getId() {
        return id;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleYear() {
        return moduleYear;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setModuleYear(String moduleYear) {
        this.moduleYear = moduleYear;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public void setMaximumStudents(int maximumStudents) {
        this.maximumStudents = maximumStudents;
    }
}