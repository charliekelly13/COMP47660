package universityWebApp.model;

import javax.persistence.Entity;
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
    @NotBlank
    private String moduleDescription;
    private List<String> topics;
    @NotBlank
    private String coordinatorName;
    private int enrolledStudents;
    private int maximumStudents;

    public Module(String moduleName, String moduleDescription, String coordinatorName, List<String> topics, int enrolledStudents, int maximumStudents) {
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.coordinatorName = coordinatorName;
        this.topics = topics;
        this.enrolledStudents = enrolledStudents;
        this.maximumStudents = maximumStudents;
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

    public List<String> getTopics() {
        return topics;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }
}