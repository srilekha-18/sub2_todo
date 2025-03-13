<%@ page import="beans.Task, java.util.List, dao.ToDoDAOImpl, dao.ToDoDAOIntf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Task Management</title>
</head>
<body>

<%
    ToDoDAOIntf dao = ToDoDAOImpl.getInstance();
    int regId = Integer.parseInt(session.getAttribute("regId").toString());
    String fname = dao.getFnameByRegId(regId);
%>

<p>Welcome, <%= fname %> | <a href="./LogoutServlet">Logout</a></p>

<h2>Add Task</h2>
<form method="post" action="./AddTaskServlet">
    Task Name: <input type="text" name="taskName"><br>
    Task Date: <input type="text" name="taskDate" placeholder="dd-mm-yyyy"><br>
    Task Status:
    <select name="taskStatus">
        <option value="1">Not Started</option>
        <option value="2">In Progress</option>
        <option value="3">Completed</option>
    </select><br>
    <input type="submit" value="Add Task">
</form>

<hr>

<h2>Your Tasks</h2>
<table border="1">
    <tr>
        <th>TaskID</th>
        <th>Task Name</th>
        <th>Task Date</th>
        <th>Task Status</th>
        <th>Action</th>
    </tr>

    <%
        List<Task> taskList = dao.findAllTasksByRegId(regId);
        for (Task task : taskList) {
            int taskId = task.getTaskId();
            String taskName = task.getTaskName();
            String taskDate = task.getTaskDate();
            int taskStatus = task.getTaskStatus();
    %>
    <tr <%= taskStatus == 3 ? "style='text-decoration:line-through;'" : "" %>>
        <td><%= taskId %></td>
        <td><%= taskName %></td>
        <td><%= taskDate %></td>
        <td><%= taskStatus %></td>
        <td>
            <% if (taskStatus != 3) { %>
                <a href="./MarkTaskCompletedServlet?regId=<%= regId %>&taskId=<%= taskId %>">Mark as Completed</a>
            <% } else { %>
                Completed
            <% } %>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>
