package anna.fsktm.shaghayeghserver.servlet;

import anna.fsktm.shaghayeghserver.ejb.ContactBean;
import anna.fsktm.shaghayeghserver.entity.Contact;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ehsun Behravesh
 */
@WebServlet(urlPatterns = {"/group"})
public class GroupServlet extends HttpServlet {

    
    @Inject
    private ContactBean contactBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strTime = req.getParameter("time");
        
        if (strTime != null) {
            Long time = Long.parseLong(strTime);
            List<Contact> contacts = contactBean.getAll(time);
            req.setAttribute("contacts", contacts);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/group.jsp").forward(req, resp);
    }
    
}
