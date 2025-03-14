package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ToDoDAOImpl;
import dao.ToDoDAOIntf;

@WebServlet("/MarkTaskCompletedServlet")
public class MarkTaskCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int regId = Integer.parseInt(request.getParameter("regId"));
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        ToDoDAOIntf dao = ToDoDAOImpl.getInstance();
        boolean flag = dao.markTaskCompleted(regId, taskId);
        if (flag) {
            response.sendRedirect("./ViewTasks.jsp");
        } else {
            response.getWriter().println("Transaction Failed");
        }
    }
}
