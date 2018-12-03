package com.mimacom.prueba.dao;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mimacom.prueba.dao.entity.UserEntity;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager em;

	public void createUser(String username, String first_name, String last_name, String email,String password) {

		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setEmail(email);
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		user.setCreation_date(new java.sql.Date(currentDate.getTime()));
		user.setPassword(password);
		em.persist(user);

	}

	public UserEntity getUser(String id_user) {
		UserEntity user = em.find(UserEntity.class, Integer.valueOf(id_user));
		return user;
	}

	public UserEntity getUserByEmail(String email) {
		UserEntity user = (UserEntity)em.createQuery("SELECT u FROM UserEntity u where u.email = '"+email+"'").getSingleResult();
		return user;
	}

}
