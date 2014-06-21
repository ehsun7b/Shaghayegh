package anna.fsktm.shaghayegh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehsun7b on 6/15/14.
 */
public class Contact implements Serializable {
    private String id;
    private String name;
    private List<String> numbers;
    private List<String> emails;


    public Contact(String id, String name) {
        this.id = id;
        this.name = name;

        this.numbers = new ArrayList<String>();
        this.emails = new ArrayList<String>();
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

    public static String toJSON(Contact contact) throws JSONException {
        JSONObject jo = new JSONObject();

        jo.put("id", contact.getId());
        jo.put("name", contact.getName());

        StringBuilder numbers = new StringBuilder();
        numbers.append("[");
        for (int i = 0; i < contact.getNumbers().size(); ++i) {
            String number = contact.getNumbers().get(i);

            numbers.append("\"");
            numbers.append(number);
            numbers.append("\"");

            if (i < contact.getNumbers().size() - 1) {
                numbers.append(",");
            }
        }
        numbers.append("]");

        jo.put("numbers", numbers.toString());

        StringBuilder emails = new StringBuilder();
        emails.append("[");
        for (int i = 0; i < contact.getEmails().size(); ++i) {
            String email = contact.getEmails().get(i);

            emails.append("\"");
            emails.append(email);
            emails.append("\"");

            if (i < contact.getEmails().size() - 1) {
                emails.append(",");
            }
        }
        emails.append("]");

        jo.put("emails", emails.toString());

        return jo.toString();
    }

    public static String toJSON(List<Contact> contacts) throws JSONException {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < contacts.size(); ++i) {
            Contact c = contacts.get(i);

            result.append( toJSON(c));

            if (i < contacts.size() - 1) {
                result.append(",");
            }
        }
        result.append("]");

        return result.toString();
    }
}
