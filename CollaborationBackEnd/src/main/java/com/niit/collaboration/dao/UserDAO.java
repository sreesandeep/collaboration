package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.User;

@Repository
public interface UserDAO {

		public boolean save(User user);
		public boolean update(User user);
		public boolean delete(User user);
		public List<User> list();
		public User get(String id);
		public User delete(String id);
		public User authenticate(String name, String Password);
		public void setOffLine(String userID);
        public void setOnline(String userID);	
	
}
