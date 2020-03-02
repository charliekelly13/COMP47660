package universityWebApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "modules")
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

    public String getId() {
        return id;
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

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }
}