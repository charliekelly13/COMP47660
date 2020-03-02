package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {
    @Query("INSERT INTO modules(id, module_name, module_description, coordinator_name, enrolled_students, maximum_students), VALUES(?1,?2,?3,?4,?4,?5,?6)")
    void insertModule(String id, String moduleName, String moduleDescription, String coordinatorName, String enrolledStudents, String maximumStudents);

}
