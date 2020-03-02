package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> { }
