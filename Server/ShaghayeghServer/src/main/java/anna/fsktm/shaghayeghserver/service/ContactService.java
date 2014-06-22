package anna.fsktm.shaghayeghserver.service;

import anna.fsktm.shaghayeghserver.ejb.ContactBean;
import anna.fsktm.shaghayeghserver.entity.Contact;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ehsun Behravesh
 */
@Stateless
@Path("contact")
public class ContactService {

    @Inject
    private ContactBean contactBean;

    @GET
    @Path("log/{name}/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showInLog(@PathParam("name") String name,
            @PathParam("number") String number) {

        Contact contact = new Contact(name, number);

//        String json = new Gson().toJson(contact);
        //      System.out.println(json);
        return Response.ok("ok", MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(List<Contact> contacts) {
        //System.out.println(contacts.size());
        boolean success = true;
        String error = null;
        try {
            long regTime = System.currentTimeMillis();
            for (Contact contact : contacts) {
                contact.setRegTime(regTime);
                contactBean.insert(contact);
            }
        } catch (Exception ex) {
            success = false;
            error = ex.getMessage();
        }

        if (success) {
            return Response.ok("{\"success\": true}", MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok("{\"success\": false, \"error\": \"" + error + "\"}", MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("sample")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sample() {
        List<Contact> contacts = new ArrayList<>();

        Contact contact = new Contact("id1", "John");
        contacts.add(contact);

        return Response.ok(contacts, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("test")
    public String test() {
        Contact c = new Contact("1", "haha");
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        c.setRegTime(System.currentTimeMillis());
        emails.add("name@hyee.org");
        numbers.add("456786");
        numbers.add("3453434");
        c.setNumbers(numbers);
        c.setEmails(emails);
        contactBean.insert(c);
        return "test";
    }
}
