package com.task.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.task.model.Task;
import com.task.service.*;

/*
 * this servlet retrieves the data based upon any field inside db
 */
public class RetrieveTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PrintWriter out = null;
	List<Task> taskList = null;
	TaskService taskService = null;
	Task task = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		out = response.getWriter();

		Gson obj = new Gson();

		taskService = new TaskService();

		String searchString = request.getParameter("searchString");

		try {

			taskList = taskService.retrieveTasks(searchString);
			String obj2 = obj.toJson(taskList);
			out.println(obj2);
		} catch (Exception e) {
			out.println(e);
		}
	}

}
