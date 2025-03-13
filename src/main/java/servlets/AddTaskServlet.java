package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Task;
import dao.ToDoDAOImpl;
import dao.ToDoDAOIntf;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;  // ✅ Add this line

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        // Get task details from request
        String taskName = request.getParameter("taskName").trim();
        String taskDate = request.getParameter("taskDate").trim();
        int taskStatus = Integer.parseInt(request.getParameter("taskStatus").trim());
        int regId = Integer.parseInt(session.getAttribute("regId").toString());

        Task task = new Task(0, taskName, taskDate, taskStatus, regId);

        // Add task to database
        ToDoDAOIntf dao = ToDoDAOImpl.getInstance();
        int taskId = dao.addTask(regId, task);
        if (taskId > 0) {
            response.sendRedirect("./ViewTasks.jsp");
        }
    }
}
