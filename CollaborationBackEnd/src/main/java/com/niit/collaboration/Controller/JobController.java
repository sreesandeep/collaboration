package com.niit.collaboration.Controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.User;

@RestController
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	Job job;
	
	@Autowired
	JobApplication jobApplication;
	
	@RequestMapping(value = "/getAllJobs/" , method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getjobs()
	{
		List<Job> jobs = jobDAO.list();
		if(jobs == null)
		{
			job = new Job();
			 job.setErrorCode("404");
       	  job.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/getAllJobsApplication/" , method = RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> getjobsapplied()
	{
		List<JobApplication> jobapplied = jobDAO.listJobApplication();
		if(jobapplied == null)
		{
			job = new Job();
			 job.setErrorCode("404");
       	  job.setErrorMessage("No jobapplied are available");
       	  return new ResponseEntity<List<JobApplication>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<JobApplication>>(jobapplied, HttpStatus.OK);
         }
	}
	
	/*@RequestMapping(value="/getMyAppliedJobs/" , method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		logger.debug("Starting of the method getMyAppliedJobs");
		String loggedInUserID =  (String) httpSession.getAttribute("loggedInUserID");
		
		List<Job> job = jobDAO.getAllJobsApplication();
		List<Job> jobapplication = (List<Job>) jobDAO.getAllJobsApplication();
		return new ResponseEntity<List<Job>>(job , HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/applyForJob/{jobID}", method = RequestMethod.POST)
	public ResponseEntity<Job> applyforJob(@PathVariable("jobID") int jobID, HttpSession httpSession)
	{
		logger.debug("Starting of the method getMyAppliedJobs");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		
		/*jobApplication = jobDAO.getJobApplication(jobID);*/
		jobApplication.setUserID(loggedInUserID);
	    jobApplication.setJobID(jobID);
		jobApplication.setStatus('N');
		jobApplication.setDateTime(null);
		if (jobDAO.save(jobApplication))
		{
			job.setErrorCode("404");
			job.setErrorMessage("Not able to apply for the job");
			
		}
		 return new ResponseEntity<Job> (job , HttpStatus.OK);
	}
	
	

	
	
	
	@RequestMapping(value="/getJobDetails/{JobID}", method = RequestMethod.GET)
	public ResponseEntity<Job> getJobDetails(@PathVariable("JobID") int JobID) {
		logger.debug("Starting of the method getJobDetails");
		Job job= jobDAO.getJobDetails(JobID);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getAllJobDetails/{jobID}", method  = RequestMethod.PUT)
	public ResponseEntity<Job> getAllJobDetails(@RequestParam("jobID")int jobID, HttpSession httpSession){
		logger.debug("Starting of the method getAllJobDetails");
		String loggedInUserID =  (String) httpSession.getAttribute("loggedInUserID");
		
		jobApplication = jobDAO.getJobApplication(jobID);
		jobApplication.setUserID(loggedInUserID);
		jobApplication.setStatus('N');
		if(jobDAO.save(jobApplication)) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to apply for the job");
			logger.debug("Not able to apply for the job");
		}
		return new ResponseEntity<Job>(job , HttpStatus.OK);
	}
	
	@RequestMapping(value="/selectUser/{userID}/{jobID}", method = RequestMethod.PUT)
	public ResponseEntity<Job> selectUser(@RequestParam("userID")int userID,@RequestParam("jobID")int jobID){
		logger.debug("Starting of the method selectUser");
		jobApplication.setStatus('S');
		if(jobDAO.save(jobApplication)) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to change the application status as 'selected'");
			logger.debug("Not able to change the application status as 'selected'");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@RequestMapping(value="/canCallForInterview/{userID}/{jobID}",method = RequestMethod.PUT)
	public ResponseEntity<Job> callForInterview(@PathVariable("userID")String userID, @PathVariable("jobID")Long jobID){
		logger.debug("Starting of the method canCallForInterview");
		jobApplication.setStatus('C');
		if(jobDAO.save(jobApplication)){
			job.setErrorCode("404");
			job.setErrorMessage("Not able to change the job application status 'Call for Interview'");
			logger.debug("Not able to change the job application status 'Call for Interview'");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectJobApplcation/{userID}/{jobID}",method= RequestMethod.PUT)
	public ResponseEntity<Job> rejectJobApplication(@PathVariable("userID")int userID , @PathVariable("jobID")int jobID){
		logger.debug("Starting of the method rejectJobApplication");
		//jobApplication = jobDAO.getJobApplication(userID, jobID);
		
		jobApplication.setStatus('R');
		if(jobDAO.save(jobApplication) ==false) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to reject the application");
			logger.debug("Not able to reject the application");
		}
		else
		{
			job.setErrorCode("200");
			job.setErrorMessage("Successfully updated the status as Rejected");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@RequestMapping(value="/postAJob/", method = RequestMethod.POST)
	public ResponseEntity<Job> postAJob(@RequestBody Job job, HttpSession httpsession) {
		logger.debug("Starting of the method postAJob");
		
		String loggedInuserID =  (String) httpsession.getAttribute("loggedInUserID");
		
		job.setUserID(loggedInuserID);
		job.setStatus("N");
		
		jobDAO.save(job);
		
		/*if(jobDAO.save(job) == false) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to post a job");
			logger.debug("Not able to post a job");
		}*/
		return new ResponseEntity<Job>(job , HttpStatus.OK);
	}	
	
	
	 @RequestMapping(value="/jobaccept/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<JobApplication> jobaccept(@PathVariable("id") int id, @RequestBody JobApplication jobApplication ) 
	   {
		 jobApplication = jobDAO.getJobApplication(jobApplication.getId());  
		 
		  if(jobApplication==null)
		  {
			 
			  jobApplication = new JobApplication();
			   return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.NOT_FOUND);
		  }
		  
		  jobApplication.setStatus('A');
		  jobApplication.setRemarks("C");
		  jobDAO.updateJobApplication(jobApplication); 
		  
		   
		   return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	   }

}
