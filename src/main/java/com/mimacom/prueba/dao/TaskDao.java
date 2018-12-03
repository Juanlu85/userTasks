package com.mimacom.prueba.dao;

import java.util.List;

import com.mimacom.prueba.dao.entity.TaskEntity;
import com.mimacom.prueba.dao.entity.UserEntity;

public interface TaskDao {

	public void createTask(UserEntity user, String title, String detail);

	public List<TaskEntity> getAll(UserEntity user);

	public TaskEntity getTaskById(String id_task);

	public void deleteTask(String id_task);

	public TaskEntity completeTask(String id_task);

	public void updateTask(TaskEntity task);
	
}
