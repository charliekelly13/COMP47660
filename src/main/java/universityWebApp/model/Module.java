package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "modules")
@NamedQuery(name = "Module.findByIDfromCode",
        query = "select a.id from Module a where a.moduleCode = ?1")

public class Module {
    //Todo  and view module statistics (number of enrolled students, grades distributions for previous editions of the module).
    @NotBlank
    private String moduleName;

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

    @NotNull
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

}