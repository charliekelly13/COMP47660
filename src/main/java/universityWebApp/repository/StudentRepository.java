package universityWebApp.repository;

import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.Query;
import universityWebApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
