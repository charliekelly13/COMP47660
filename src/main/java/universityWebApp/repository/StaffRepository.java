package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Staff;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    @Query("select a.password from Staff a where a.username = ?1")
    String findPasswordByUsername(String username);

    @Query("select a from Staff a where a.username = ?1")
    Staff findStaffByUsername(String username);

    @Query("select a from Staff a where a.id = ?1")
    Staff findStaffById(String id);
}

