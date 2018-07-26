package com.task.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.task.controller.CreateTaskServlet;
import com.task.model.Task;
import com.task.utils.DBConnection;

public class TaskDAO {

	String status = "";
	Task task = null;
	List<Task> taskList = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String deleteStatus = null;

	static final Logger log = Logger.getLogger(TaskDAO.class);

	private static final String INSERT_INTO_TASK = "insert into tbl_tasks(taskName,category,dueDate,time,owner,priority,status,relatedTo,relatedDeals) values(?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_FROM_TASK = "delete from tbl_tasks where id=?";

	// creates the task inside the database
	public String createTask(Task task) throws ClassNotFoundException, SQLException {

		log.info("inside create task method of TaskDAO");

		connection = DBConnection.getConnection();

		preparedStatement = connection.prepareStatement(INSERT_INTO_TASK);

		preparedStatement.setString(1, task.getTaskName());
		preparedStatement.setString(2, task.getCategory());
		preparedStatement.setString(3, task.getDueDate());
		preparedStatement.setString(4, task.getTime());
		preparedStatement.setString(5, task.getOwner());
		preparedStatement.setString(6, task.getPriority());
		preparedStatement.setString(7, task.getStatus());
		preparedStatement.setString(8, task.getRelatedTo());
		preparedStatement.setString(9, task.getRelatedDeals());

		int result = preparedStatement.executeUpdate();
		if (result == 1)
			status = "New Task Created Successfully";
		else
			status = "please try again";

		return status;

	}

	// this method retrieves the task from database using any field from the
	// database(taskName,category,owner,priority,dueDate)
	public List<Task> retrieveTasks(String searchString) throws ClassNotFoundException, SQLException {
		
		log.info("inside retrieve task method of TaskDAO");
		
		ResultSet result = null;

		connection = DBConnection.getConnection();

		taskList = new ArrayList<>();

		// this query will retrieves the data based upon any field regarding the
		// database
		String query1 = "select * from tbl_tasks where taskName='" + searchString + "' or category='" + searchString
				+ "' or owner='" + searchString + "' or priority='" + searchString + "' or dueDate='" + searchString
				+ "' ";

		preparedStatement = connection.prepareStatement(query1);
		result = preparedStatement.executeQuery();

		while (result.next()) {

			task = new Task();
			task.setTaskId(result.getInt(1));
			task.setTaskName(result.getString(2));
			task.setCategory(result.getString(3));
			task.setDueDate(result.getString(4));
			task.setTime(result.getString(5));
			task.setOwner(result.getString(6));
			task.setPriority(result.getString(7));
			task.setStatus(result.getString(8));
			task.setRelatedTo(result.getString(9));
			task.setRelatedDeals(result.getString(10));

			taskList.add(task);

		}

		return taskList;

	}

	// this method deletes the task inside database using task id
	public String deleteTask(Integer taskId) throws ClassNotFoundException, SQLException {
		
		log.info("inside delete task method of TaskDAO");

		connection = DBConnection.getConnection();

		preparedStatement = connection.prepareStatement(DELETE_FROM_TASK);

		preparedStatement.setInt(1, taskId);

		Integer result = preparedStatement.executeUpdate();

		if (result >= 1)
			deleteStatus = "Successfully deleted";
		else
			deleteStatus = "failed to delete....please try again";

		return deleteStatus;
	}
}
