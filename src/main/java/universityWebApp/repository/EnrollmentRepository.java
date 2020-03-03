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
    List<String> findByStudentID(String student_id) throws ModuleNotFoundException;

    @Query("select a.studentId from Enrollment a where a.moduleId = ?1")
    List<String> findByModuleID(String module_id) throws StudentNotFoundException;

    @Query("INSERT INTO enrollment(student_id, grade, module_id), VALUES(?1,?2,?3)")
    void insertEnrollment(String student_id, String grade, String module_id);
}
