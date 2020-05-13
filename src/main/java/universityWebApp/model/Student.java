package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "students")
@NamedQuery(name= "Student.findPasswordByUsername",
        query= "select a.password from Student a where a.username =?1")
public class Student implements User {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Id
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String gender;

    @NotBlank
    private String nationality;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String emailAddress;

    @NotNull
    private boolean feesPaid;

    public Student() {
        super();
    }

    public Student(String id, String firstName, String lastName, String gender, String address, String phoneNumber, String emailAddress) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.feesPaid = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public boolean isFeesPaid() {
        return feesPaid;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean hasPaidFees() {
        return feesPaid;
    }

    public void setFeesPaid(boolean feesPaid) {
        this.feesPaid = feesPaid;
    }
}