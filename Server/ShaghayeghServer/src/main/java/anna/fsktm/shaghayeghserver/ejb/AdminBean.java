package anna.fsktm.shaghayeghserver.ejb;

import anna.fsktm.shaghayeghserver.entity.Admin;
import anna.fsktm.shaghayeghserver.utils.Utils;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ehsun Behravesh
 */
@Stateless
public class AdminBean {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
/*        Admin admin = read("admin", "123");
        if (admin == null) {
            admin = new Admin();
            admin.setUsername("admin");
            admin.setName("Administrator");
            admin.setPassword("123");
            insert(admin);
        }*/
    }

    public Admin read(final String username, String password) {
        TypedQuery<Admin> query = em.createQuery("select a from Admin a where a.username = :username and a.password = :password", Admin.class);
        query.setParameter("username", username);
        query.setParameter("password", Utils.hashPassword(password));

        Admin result = null;
        try {
            result = query.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {

        }

        return result;
    }

    public void insert(final Admin admin) {
        admin.setPassword(Utils.hashPassword(admin.getPassword()));
        em.persist(admin);
    }

}
