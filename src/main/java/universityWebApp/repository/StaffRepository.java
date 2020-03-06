package universityWebApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Staff;
@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

}
