package universityWebApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {
    //name, surname,  student ID, address, phone number, email address, + username&password
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Long id;
    @NotBlank
    private String student_first_name;
    @NotBlank
    private String student_last_name;
    @NotBlank
    private String gender;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String emailAddress;
    private boolean feesPaid;

    public Student() {
        super();
    }

    public Student(Long id, String student_first_name, String student_last_name, String gender, String address, String phoneNumber, String emailAddress) {
        super();
        this.id = id;
        this.student_first_name = student_first_name;
        this.student_last_name = student_last_name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Long getId() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}