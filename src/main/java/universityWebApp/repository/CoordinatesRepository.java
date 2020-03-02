package universityWebApp.repository;

import org.springframework.data.jpa.repository.Query;
import universityWebApp.exception.ModuleNotFoundException;

import java.util.List;

public interface CoordinatesRepository {

    @Query("select a.moduleId from Coordinates a where a.staffid = ?1")
    List<String> findByStaffID(String staffId) throws ModuleNotFoundException;

    @Query("select a.staffId from Enrollment a where a.moduleId = ?1")
    List<String> findByModuleID(String module_id);
}
