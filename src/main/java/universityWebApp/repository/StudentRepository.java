package universityWebApp.repository;

import org.springframework.data.jpa.repository.Query;
import universityWebApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("select a.password from Student a where a.username = ?1")
    List<String> findPasswordByUsername(String username);
}

