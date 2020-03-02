package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Enrolled;
import universityWebApp.model.EnrolledId;
import universityWebApp.model.Student;

import java.util.List;

@Repository
public interface EnrolledRepository extends JpaRepository<Enrolled, EnrolledId> {

    @Query("select a.id_module from Enrolled a where a.id_student = ?1")
    List<String> findByStudentID(Long book_id) throws ModuleNotFoundException;

    @Query("select a.id_student from Enrolled a where a.id_module = ?1")
    List<String> findByModuleID(Long book_id) throws StudentNotFoundException;
}
