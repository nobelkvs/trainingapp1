package com.task.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.task.service.TaskService;

/*
 * This servlet deletes the task inside the database based on taskId
 */
public class DeleteTaskServlet extends HttpServlet {

	TaskService taskService = null;
	String deleteStatus = null;
	PrintWriter out = null;
	static final Logger log = Logger.getLogger(DeleteTaskServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("inside delte servlet");

		Integer taskId = Integer.parseInt(request.getParameter("taskId"));

		out = response.getWriter();
		taskService = new TaskService();

		try {
			deleteStatus = taskService.deleteTask(taskId);
			if (deleteStatus != null)
				out.println(deleteStatus);
			else
				out.println("deleteStatus is empty");
		} catch (Exception e) {
			out.print(e);
		}

	}

}
