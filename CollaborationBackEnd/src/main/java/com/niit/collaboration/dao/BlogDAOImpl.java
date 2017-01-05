package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.User;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@Repository("blogDAO")

public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean save(Blog blog){	
		
		try{
		  sessionFactory.getCurrentSession().save(blog);
	return true;
		}catch (Exception e ){
			//TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
	@Transactional
	public boolean update(Blog blog){
		
		try{
			sessionFactory.getCurrentSession().update(blog);
	return true;
		} catch (Exception e){
			//TODO Auto-generated catch block
	       e.printStackTrace();
	       return false;
		}
	}
	@Transactional
	public boolean delete(Blog blog){
		try{
	       sessionFactory.getCurrentSession().delete(blog);
	return true;
		} catch (Exception e){
			//TODO Auto-generated catch block
	       e.printStackTrace();
	       return false;
		}
	}//To list the forumitems.
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Blog> list(){
		
		String hql = "from Blog";
	@SuppressWarnings("rawtypes")
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	
	List<Blog> listBlog = query.list();
	if(listBlog == null  || listBlog.isEmpty())
	{
		 return null;
		 
	}
	return query.list();
	}

	@Transactional
	public Blog get(int id) {

		String hql="from Blog where id = " + "'" + id + "'";

		@SuppressWarnings({ "rawtypes" })
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<Blog> list=query.list();
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
	public Blog delete(int id) 
	{
		Blog BlogToDelete = new Blog();
		BlogToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(BlogToDelete);
		return null;
	}

	
}
