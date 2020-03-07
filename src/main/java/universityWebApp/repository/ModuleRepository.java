package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Module;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("select a.id from Module a where a.moduleCode = ?1")
    List<Long> findIDByCode(String moduleCode);

    @Query("select a from Module a where a.coordinatorId = ?1")
    List<Module> findIDByCoordinator(String coordinatorId);

}
