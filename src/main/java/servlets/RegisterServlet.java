package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Register;
import dao.ToDoDAOImpl;
import dao.ToDoDAOIntf;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Reading form data
        String fname = request.getParameter("fname").trim();
        String lname = request.getParameter("lname").trim();
        String email = request.getParameter("email").trim();
        String pass = request.getParameter("pass").trim();
        long mobile = Long.parseLong(request.getParameter("mobile").trim());
        String address = request.getParameter("address").trim();

        // Creating Register object
        Register reg = new Register(0, fname, lname, email, pass, mobile, address);

        // Register user in database
        ToDoDAOIntf dao = ToDoDAOImpl.getInstance();
        int regId = dao.register(reg);
        if (regId > 0) {
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        } else {
            out.println("Registration Failed");
        }
    }
}
