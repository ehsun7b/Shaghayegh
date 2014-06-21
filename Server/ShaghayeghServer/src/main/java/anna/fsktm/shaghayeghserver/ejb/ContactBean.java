package anna.fsktm.shaghayeghserver.ejb;

import anna.fsktm.shaghayeghserver.entity.Contact;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ehsun Behravesh
 */
@Stateless
public class ContactBean {

    @PersistenceContext
    private EntityManager em;
    
    public void insert(final Contact contact) {
        em.persist(contact);
    }
    
    //public List<Con
}
