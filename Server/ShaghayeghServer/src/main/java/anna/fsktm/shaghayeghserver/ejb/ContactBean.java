package anna.fsktm.shaghayeghserver.ejb;

import anna.fsktm.shaghayeghserver.entity.Contact;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    public List<Date> getAllGroups() {
        TypedQuery<Long> query = em.createQuery("select c.regTime from Contact c group by c.regTime", Long.class);
        List<Long> longs = query.getResultList();
        List<Date> result = new ArrayList<>(longs.size());
        
        for (Long lon: longs) {
            result.add(new Date(lon));
        }
        return result;
    }
    
    public List<Contact> getAll(Long time) {
        TypedQuery<Contact> query = em.createQuery("select c from Contact c where c.regTime = :time", Contact.class);
        query.setParameter("time", time);
        List<Contact> result = query.getResultList();
        return result;
    }
}
