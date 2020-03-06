package universityWebApp.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class Staff {

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
  
    public Staff() {
        super();
    }


    public Staff(String id, String firstName, String lastName, String gender, String address, String phoneNumber, String emailAddress) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
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
