package com.task.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.task.model.Task;
import com.task.utils.DBConnection;

public class TaskDAO {

	String status = "";
	Task task = null;
	List<Task> taskList = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String deleteStatus = null;

	private static final String INSERT_INTO_TASK = "insert into tbl_tasks(taskName,category,dueDate,time,owner,priority,status,relatedTo,relatedDeals) values(?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_FROM_TASK = "delete from tbl_tasks where id=?";

	//creates the task inside the database
	public String createTask(Task task) throws ClassNotFoundException, SQLException {

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
	//this method retrieves the task from database using any field from the database(taskName,category,owner,priority,dueDate)
	public List<Task> retrieveTasks(String searchString) throws ClassNotFoundException, SQLException {
		ResultSet result = null;

		PreparedStatement preparedStatement1 = null, preparedStatement2 = null, preparedStatement3 = null,
				preparedStatement4 = null, preparedStatement5 = null;
		connection = DBConnection.getConnection();

		taskList = new ArrayList<>();

		String query1 = "select * from tbl_tasks where taskName='" + searchString + "'";
		preparedStatement1 = connection.prepareStatement(query1, ResultSet.TYPE_SCROLL_INSENSITIVE);
		result = preparedStatement1.executeQuery();

		if (result.next()) {
			result.beforeFirst();
		} else {
			String query2 = "select * from tbl_tasks where category='" + searchString + "'";
			preparedStatement2 = connection.prepareStatement(query2);
			result = preparedStatement2.executeQuery();
			if (result.next()) {
				result.beforeFirst();
			} else {
				String query3 = "select * from tbl_tasks where owner='" + searchString + "'";
				preparedStatement3 = connection.prepareStatement(query3);

				result = preparedStatement3.executeQuery();
				if (result.next()) {
					result.beforeFirst();
				} else {
					String query4 = "select * from tbl_tasks where priority='" + searchString + "'";
					preparedStatement4 = connection.prepareStatement(query4);
					result = preparedStatement4.executeQuery();
					if (result.next()) {
						result.beforeFirst();
					} else {
						String query5 = "select * from tbl_tasks where dueDate='" + searchString + "'";
						preparedStatement5 = connection.prepareStatement(query5);
						result = preparedStatement5.executeQuery();

					}
				}

			}

		}
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

	//this method deletes the task inside database using task id
	public String deleteTask(Integer taskId) throws ClassNotFoundException, SQLException {

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

