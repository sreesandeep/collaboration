package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.User;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@Repository("userDAO")

public class UserDAOImpl implements UserDAO {
	
	private static final Logger Logger = LoggerFactory.getLogger(UserDAOImpl.class);

		@Autowired
		private SessionFactory sessionFactory;
		public UserDAOImpl(SessionFactory sessionFactory)
		{
			this.sessionFactory=sessionFactory;
		}
		
		@Transactional
		public boolean save(User userDetails){	
			
			try{
			  sessionFactory.getCurrentSession().save(userDetails);
		return true;
			}catch (HibernateException e ){
				//TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}	
		@Transactional
		public boolean update(User user){
			
			try{
				sessionFactory.getCurrentSession().update(user);
		return true;
			} catch (HibernateException e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}
		@Transactional
		public boolean delete(User user){
			try{
		       sessionFactory.getCurrentSession().delete(user);
		return true;
			} catch (HibernateException e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}//To list the UserDetails items.
		
		@SuppressWarnings("unchecked")
		@Transactional
		public List<User> list()
		{
			
			String hql = "from User";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		
		List<User> listUser = query.list();
		if(listUser == null  || listUser.isEmpty())
		{
			 return null;
			 
		}
		return query.list();
		}
	
		@Transactional
		public User get(String id) 
		{
			
			String hql="from User where id = " + "'" + id + "'";
	
			@SuppressWarnings({ "rawtypes" })
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			@SuppressWarnings({ "unchecked" })
			List<User> list=query.list();
			if(list==null || list.isEmpty())
			{
				
				return null;
			}
			else
			{
				return list.get(0);
			}
		}

		@Transactional
		public User delete(String id) 
		{
			User UserToDelete = new User();
			UserToDelete.setId(id);;
			sessionFactory.getCurrentSession().delete(UserToDelete);
			return null;
		}

		@Transactional
		public User authenticate(String name, String password) 
		{
			String hql="from User where name = " + "'" + name + "'and " + " password='" + password+"'";
			@SuppressWarnings({ "rawtypes" })
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			if(list!=null  && !list.isEmpty())
			{
				return list.get(0);
			}
			return null;
		}
		
		@Transactional
		public void setOffLine(String loggedInUserID) {
			Logger.debug("Starting of the method setOnline");
			String hql = "UPDATE User SET isOnline = 'N' where userID ='" + loggedInUserID + "'";
			Logger.debug("hql: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOnline");
		}
		

		@Transactional
		public void setOnline(String loggedInUserID) {
			Logger.debug("Starting of the method setOffline");
			String hql = "UPDATE User SET isOnline = 'Y' where userID = '" + loggedInUserID + "'";
			Logger.debug("hql: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOffline");
	
		}
		
}
