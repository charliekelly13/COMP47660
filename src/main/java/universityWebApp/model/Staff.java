package universityWebApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private String nationality;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

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

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

