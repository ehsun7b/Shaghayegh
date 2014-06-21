package anna.fsktm.shaghayeghserver.service;

import anna.fsktm.shaghayeghserver.entity.Contact;
import java.util.ArrayList;
import java.util.List;
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
@Path("contact")
public class ContactService {
    
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
    public Response save(List<Contact> contacts) {
        System.out.println(contacts.size());
        
        for (Contact contact: contacts) {
            System.out.println("Name: " + contact.getName());
        }
        
        return Response.ok("{\"success\": true}", MediaType.APPLICATION_JSON).build();
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
        return "test";
    }
}
