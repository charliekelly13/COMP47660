package universityWebApp.model;

import javax.persistence.NamedQuery;

@NamedQuery(name = "Staff.insertStaff",
        query = "INSERT INTO staff(staff_id,staff_name), VALUES(?1,?2)")
public class Staff {
}
