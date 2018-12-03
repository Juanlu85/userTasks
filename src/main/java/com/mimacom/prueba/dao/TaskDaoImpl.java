package com.mimacom.prueba.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mimacom.prueba.dao.entity.TaskEntity;
import com.mimacom.prueba.dao.entity.UserEntity;
import com.mimacom.prueba.utils.Constants;

@Repository
@Transactional
public class TaskDaoImpl implements TaskDao {
	@PersistenceContext
	private EntityManager em;

	public void createTask(UserEntity user, String title, String detail) {

		TaskEntity task = new TaskEntity();
		task.setUser(user);
		task.setTitle(title);
		task.setDetail(detail);
		task.setState(Constants.CTE_STATE_PENDING);

		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		task.setCreation_date(new java.sql.Date(currentDate.getTime()));
		task.setModification_date(new java.sql.Date(currentDate.getTime()));
		em.persist(task);

	}
	
	public List<TaskEntity> getAll(UserEntity user) {
		List<TaskEntity> userTasks = em.createQuery("SELECT t FROM UserEntity u, TaskEntity t where u = t.user and u.id_user = "+user.getId_user()).getResultList();
		return userTasks;
	}



	public TaskEntity getTaskById(String id_task) {
		TaskEntity task = em.find(TaskEntity.class, Integer.valueOf(id_task));
		return task;
	}



	public void deleteTask(String id_task) {
		TaskEntity task = this.getTaskById(id_task);
		em.remove(task);
	}



	public TaskEntity completeTask(String id_task) {
		TaskEntity task = this.getTaskById(id_task);
		task.setState(Constants.CTE_STATE_COMPLETED);
		
		em.persist(task);
		return task;
	}

	public void updateTask(TaskEntity task) {
		em.persist(task);
	}
	
	

}
