package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "modules")
@NamedQuery(name = "Module.findByIDfromCode",
        query = "select a.id from Module a where a.moduleCode = ?1")
@NamedQuery(name = "Module.findIDByCoordinator",
        query = "select a.id from Module a where a.coordinatorId = ?1")

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

    @NotNull
    private int maximumStudents;

    @NotNull
    private boolean terminated;

    public Module() {}

    public Module(String moduleCode, String moduleYear, String moduleName, String moduleDescription, String coordinatorId, int maximumStudents, boolean terminated) {
        this.moduleCode = moduleCode;
        this.moduleYear = moduleYear;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.coordinatorId = coordinatorId;
        this.maximumStudents = maximumStudents;
        this.terminated = terminated;
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

    public boolean getIfModuleTerminated(){return terminated;}

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

    public void setIfModuleTerminated(boolean terminated){this.terminated=terminated;}

}