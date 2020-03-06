package universityWebApp.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class Staff {
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private String nationality;

    public Staff() {
        super();
    }

    public Staff(String id, String name, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
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
}
