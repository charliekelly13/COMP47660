package universityWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "blacklist")
@NamedQuery(name= "Blacklist.findAttemptsByIP",
        query= "select a.attempts from Blacklist a where a.ip =?1")
public class Blacklist {
    @Id
    private String ip;

    @NotNull
    private int attempts;

    public Blacklist() {
        super();
    }

    public Blacklist(String ip, int attempts) {
        super();
        this.ip = ip;
        this.attempts = attempts;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}