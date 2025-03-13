package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ToDoDAOImpl;
import dao.ToDoDAOIntf;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        // Read login details
        String email = request.getParameter("email").trim();
        String pass = request.getParameter("pass").trim();

        // Verify login in database
        ToDoDAOIntf dao = ToDoDAOImpl.getInstance();
        int regId = dao.login(email, pass);

        if (regId > 0) {
            session.setAttribute("regId", regId);
            response.sendRedirect("./ViewTasks.jsp");
        } else {
            request.setAttribute("loginError", "Invalid Credentials");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }
}
