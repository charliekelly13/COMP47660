package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "modules")

@NamedQuery(name = "Module.insertModule",
        query = "INSERT INTO modules(id, module_name, module_description, coordinator_name, enrolled_students, maximum_students), VALUES(?1,?2,?3,?4,?4,?5,?6)")
public class Module {
    //Todo  and view module statistics (number of enrolled students, grades distributions for previous editions of the module).
    @NotBlank
    private String moduleName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String moduleCode;
    private String moduleYear;

    @NotBlank
    private String moduleDescription;

    @NotBlank
    private String coordinatorId;

    private int enrolledStudents;
    private int maximumStudents;

    public Module() {}

    public Module(String moduleCode, String moduleYear, String moduleName, String moduleDescription, String coordinatorId, int enrolledStudents, int maximumStudents) {
        this.moduleCode = moduleCode;
        this.moduleYear = moduleYear;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.coordinatorId = coordinatorId;
        this.enrolledStudents = enrolledStudents;
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

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }
}