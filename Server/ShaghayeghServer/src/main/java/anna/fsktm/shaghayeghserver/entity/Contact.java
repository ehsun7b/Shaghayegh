package anna.fsktm.shaghayeghserver.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ehsun Behravesh
 */
@XmlRootElement(name = "contact")
@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serverId;

    private String id;
    private String name;

    @ElementCollection
    private List<String> numbers;

    @ElementCollection
    private List<String> emails;

    private Long regTime;

    public Contact() {
    }

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;

        this.numbers = new ArrayList<>();
        this.emails = new ArrayList<>();
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

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    public Date getTime() {
        return new Date(regTime);
    }
}
