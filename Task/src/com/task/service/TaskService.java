package com.task.service;

import java.sql.SQLException;
import java.util.List;

import com.task.dao.TaskDAO;
import com.task.model.Task;


public class TaskService {

	String status = "";
	TaskDAO taskDAO = null;
	String deleteStatus = null;

	//passes the task object to dao fro task creation
	public String createTask(Task task) throws ClassNotFoundException, SQLException {

		taskDAO = new TaskDAO();

		status = taskDAO.createTask(task);

		return status;
	}

	//passes the searching string to dao for tasks retieval
	public List<Task> retrieveTasks(String searchString) throws ClassNotFoundException, SQLException {

		taskDAO = new TaskDAO();

		List<Task> taskList = taskDAO.retrieveTasks(searchString);

		return taskList;

	}

	//passess the taskId to dao for task deletion
	public String deleteTask(Integer taskId) throws ClassNotFoundException, SQLException {

		taskDAO = new TaskDAO();

		deleteStatus = taskDAO.deleteTask(taskId);

		return deleteStatus;
	}

}
