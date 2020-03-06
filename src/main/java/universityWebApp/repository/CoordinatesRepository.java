package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.model.Coordinates;
import universityWebApp.model.CoordinatesId;

import java.util.List;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, CoordinatesId> {

    @Query("select a.moduleId from Coordinates a where a.staffId = ?1")
    List<Long> findByStaffID(String staffId) throws ModuleNotFoundException;

    @Query("select a.staffId from Coordinates a where a.moduleId = ?1")
    String findByModuleID(long module_id);
}
