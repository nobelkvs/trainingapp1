package com.task.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.task.model.Task;
import com.task.service.TaskService;
/*
 * This servlet is used to create the task in the database by getting the data from ajax call and sending to service class
 * 
 */
public class CreateTaskServlet extends HttpServlet {

	String insertStatus = "";//used to hold the insert status of task in the database
	
	/*
	 *  this method recieves the data from the ajax and binds to task object and  sends the object to service 
	 */
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String taskName = request.getParameter("taskName");
		String category = request.getParameter("category");
		String dueDate = request.getParameter("dueDate");
		String time = request.getParameter("time");
		String owner = request.getParameter("owner");
		String priority = request.getParameter("priority");
		String status = request.getParameter("status");
		String relatedTo = request.getParameter("relatedTo");
		String relatedDeals = request.getParameter("relatedDeals");

		Task task = new Task();

		task.setCategory(category);
		task.setOwner(owner);
		task.setPriority(priority);
		task.setRelatedDeals(relatedDeals);
		task.setRelatedTo(relatedTo);
		task.setStatus(status);
		task.setTaskName(taskName);
		task.setDueDate(dueDate);
		task.setTime(time);

		TaskService taskService = new TaskService();

		try {
			insertStatus = taskService.createTask(task);
		} catch (Exception e) {
			out.println(e);
		}

		out.println(insertStatus);
		
	}

}
