package universityWebApp.model;


import org.hibernate.validator.constraints.UniqueElements;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
//@NamedQuery(name = "Enrolled.findUserNameByStudentID",
//        query = "select a.username from Student a where a.id_student = ?1")
//@NamedQuery(name = "Enrolled.findPasswordByStudentID",
//        query = "select a.password from Student a where a.id_student = ?1")
//@NamedQuery(name = "Enrolled.findFirstNameByStudentID",
//        query = "select a.student_first_name from Student a where a.id_student = ?1")
//@NamedQuery(name = "Enrolled.findLastNameByStudentID",
//        query = "select a.student_last_name from Student a where a.id_student = ?1")
//@NamedQuery(name = "Enrolled.findGenderByStudentID",
//        query = "select a.gender from Student a where a.id_student = ?1")
//@NamedQuery(name = "Enrolled.findAddressByStudentID",
//        query = "select a.address from Student a where a.id_student = ?1")
//@NamedQuery(name = "Enrolled.findPhoneByStudentID",
//        query = "select a.phoneNumber from Student a where a.id_student = ?1")
//@NamedQuery(name = "Enrolled.findEmailByStudentID",
//        query = "select a.emailAddress from Student a where a.id_student = ?1")

public class Student {
    //name, surname,  student ID, address, phone number, email address, + username&password

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Id
    @GeneratedValue
    private String id;

    @NotBlank
    private String student_first_name;
    @NotBlank
    private String student_last_name;
    @NotBlank
    private String gender;
    @NotBlank
    private String address;
    @NotBlank
    private String nationality;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private boolean feesPaid;

    public Student() {
        super();
    }

    public Student(String id, String student_first_name, String student_last_name, String gender, String address, String phoneNumber, String emailAddress) {
        super();
        this.id = id;
        this.student_first_name = student_first_name;
        this.student_last_name = student_last_name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    public String getUsername(){ return username;}

    public String getPassword(){ return password;}

    public String getId() {
        return id;
    }

    public String getStudent_first_name() {
        return student_first_name;
    }

    public String getStudent_last_name() {
        return student_last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}