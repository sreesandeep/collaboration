package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

@Repository
public interface JobDAO {

		public boolean save(Job job);
		public boolean save(JobApplication jobApplication);
		//public boolean delete(Job job);
		public List<Job> list();
	   public List<JobApplication> listJobApplication();
		public Job getJobDetails(int JobID);
		
	public boolean postJob(Job job);
	public boolean update(Job job);
	public List<Job> getAllVacantJobs();
	public List<Job> getAllJobs();
	
	public boolean applyForJob(JobApplication jobApplication);
	public boolean updateJobApplication(JobApplication jobApplication);
	public JobApplication get(String userID, int JobID);
	public JobApplication getMyAppliedJobs(String userID);
	public JobApplication getJobApplication(int id);

	
}
