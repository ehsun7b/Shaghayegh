package anna.fsktm.shaghayeghserver.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ehsun Behravesh
 */
@XmlRootElement(name = "contact")
public class Contact implements Serializable {
    private String id;
    private String name;
    private List<String> numbers;
    private List<String> emails;

    private Long time;
    
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
}
