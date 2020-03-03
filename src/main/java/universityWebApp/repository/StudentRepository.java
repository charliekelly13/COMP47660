package universityWebApp.repository;

import org.springframework.data.jpa.repository.Query;
import universityWebApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("INSERT INTO students(id, first_name, last_name, gender, address, phone_number, email_address, fees_paid), VALUES(?1,?2,?3,?4,?5,?6,?7,?8)")
    void insertStudent(String id, String firstName, String lastName, String gender, String phoneNumber, String emailAddress, boolean feesPaid);

}

