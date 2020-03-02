package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.exception.ModuleNotFoundException;
import universityWebApp.exception.StudentNotFoundException;
import universityWebApp.model.Enrollment;
import universityWebApp.model.EnrollmentId;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    @Query("select a.moduleId from Enrollment a where a.studentId = ?1")
    List<String> findByStudentID(String book_id) throws ModuleNotFoundException;

    @Query("select a.studentId from Enrollment a where a.moduleId = ?1")
    List<String> findByModuleID(String book_id) throws StudentNotFoundException;
}
