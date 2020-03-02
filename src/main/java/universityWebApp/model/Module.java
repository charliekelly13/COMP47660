package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "modules")

@NamedQuery(name = "Module.insertModule",
        query = "INSERT INTO modules(id, module_name, module_description, coordinator_name, enrolled_students, maximum_students), VALUES(?1,?2,?3,?4,?4,?5,?6);")
public class Module {
    //Todo  and view module statistics (number of enrolled students, grades distributions for previous editions of the module).
    @NotBlank
    private String moduleName;

    @Id
    private String id;

    @NotBlank
    private String moduleDescription;
    @NotBlank
    private String coordinatorName;
    private int enrolledStudents;
    private int maximumStudents;

    public Module() {}

    public Module(String id, String moduleName, String moduleDescription, String coordinatorName, int enrolledStudents, int maximumStudents) {
        this.id = id;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.coordinatorName = coordinatorName;
        this.enrolledStudents = enrolledStudents;
        this.maximumStudents = maximumStudents;
    }
    public String getId(){ return id;}

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }
}