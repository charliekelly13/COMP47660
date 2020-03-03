package universityWebApp.repository;

import org.springframework.data.jpa.repository.Query;

public interface StaffRepository {
    @Query("INSERT INTO staff(staff_id,staff_name), VALUES(?1,?2)")
    void insertStaff(String id, String staffName);

}
