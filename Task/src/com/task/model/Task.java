package com.task.model;


//This calss holds all the variables regarding the Task

public class Task {

	private Integer taskId;
	private String taskName;
	private String category;
	private String dueDate;
	private String time;
	private String owner;
	private String priority;
	private String status;
	private String relatedTo;
	private String relatedDeals;

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", category=" + category + ", dueDate=" + dueDate
				+ ", time=" + time + ", owner=" + owner + ", priority=" + priority + ", status=" + status
				+ ", relatedTo=" + relatedTo + ", relatedDeals=" + relatedDeals + "]";
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}

	public String getRelatedDeals() {
		return relatedDeals;
	}

	public void setRelatedDeals(String relatedDeals) {
		this.relatedDeals = relatedDeals;
	}

}
