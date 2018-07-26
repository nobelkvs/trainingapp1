package com.task.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.task.controller.CreateTaskServlet;
import com.task.dao.TaskDAO;
import com.task.model.Task;


public class TaskService {

	String status = "";
	TaskDAO taskDAO = null;
	String deleteStatus = null;
	static final Logger log=Logger.getLogger(TaskService.class);

	//passes the task object to DAO for task creation
	public String createTask(Task task) throws ClassNotFoundException, SQLException {

		log.info("inside create task method of Task Service");
		
		taskDAO = new TaskDAO();

		status = taskDAO.createTask(task);

		return status;
	}

	//passes the searching string to dao for tasks retieval
	public List<Task> retrieveTasks(String searchString) throws ClassNotFoundException, SQLException {

		log.info("inside retrieve task method of Task Service");
		
		taskDAO = new TaskDAO();

		List<Task> taskList = taskDAO.retrieveTasks(searchString);

		return taskList;

	}

	//passess the taskId to dao for task deletion
	public String deleteTask(Integer taskId) throws ClassNotFoundException, SQLException {

		log.info("inside delete task method of Task Service");
		
		taskDAO = new TaskDAO();

		deleteStatus = taskDAO.deleteTask(taskId);

		return deleteStatus;
	}

}
