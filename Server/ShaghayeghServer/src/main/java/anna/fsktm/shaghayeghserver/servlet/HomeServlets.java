package anna.fsktm.shaghayeghserver.servlet;

import anna.fsktm.shaghayeghserver.ejb.AdminBean;
import anna.fsktm.shaghayeghserver.ejb.ContactBean;
import anna.fsktm.shaghayeghserver.entity.Admin;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ehsun Behravesh
 */
@WebServlet(urlPatterns = {"/"})
public class HomeServlets extends HttpServlet {

    @Inject
    private AdminBean adminBean;

    @Inject
    private ContactBean contactBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("admin") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else {
            Admin admin = (Admin) session.getAttribute("admin");
            req.setAttribute("adminName", admin.getName());
            req.setAttribute("groups", contactBean.getAllGroups());
            
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin admin = adminBean.read(username, password);

        if (admin != null) {
            req.getSession().setAttribute("admin", admin);
        } else if (username.equals("admin")) {
            createAdmin();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
        //resp.sendRedirect("/");
    }

    private void createAdmin() {
        Admin admin = adminBean.read("admin", "123");
        if (admin == null) {
            admin = new Admin();
            admin.setUsername("admin");
            admin.setName("Administrator");
            admin.setPassword("123");
            adminBean.insert(admin);
        }
    }

}
