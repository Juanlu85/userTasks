package com.mimacom.prueba.dao;

import com.mimacom.prueba.dao.entity.UserEntity;

public interface UserDao {

	public UserEntity getUser(String id_user);
	public void createUser(String username, String first_name, String last_name, String email,String password);
	public UserEntity getUserByEmail(String email);
	
}
