package universityWebApp.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Staff {
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private String nationality;

//    @NotBlank
//    private String address;
//
//    @NotBlank
//    private String phoneNumber;
//
//    @NotBlank
//    private String emailAddress;

    public Staff() {
        super();
    }

    public Staff(String id, String name, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
//        this.address = address;
//        this.phoneNumber = phoneNumber;
//        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmailAddress() {
//        return emailAddress;
//    }
//
//    public void setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress;
//    }

}
