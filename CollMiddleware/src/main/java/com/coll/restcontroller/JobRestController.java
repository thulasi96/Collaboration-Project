package com.coll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.JobDAO;
import com.coll.model.Job;

@RestController
public class JobRestController {
	@Autowired
	JobDAO jobDAO;
	
	
	@GetMapping("/getJobs")
	public ResponseEntity<List<Job>> getJobs() 
	{
		List<Job> listJobs=jobDAO.getJobs();
		if(listJobs.size()>0) {
			return new ResponseEntity<List<Job>>(listJobs,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Job>>(listJobs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/addJob",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addJob(@RequestBody Job job)
	{
		if(jobDAO.addJob(job))
		{
			return new ResponseEntity<String>("Job added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error adding job",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable("jobId") int jobId)
	{
		Job job=jobDAO.getJob(jobId);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@PostMapping(value="/publishJob",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> publishJob(@RequestBody Job job)
	{
		if(jobDAO.publishJob(job));
		return new ResponseEntity<String>("Job Published",HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value="/deleteJob/{jobId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteJob(@PathVariable ("jobId")int jobId)
	{
		Job job=(Job)jobDAO.getJob(jobId);
		if(jobDAO.deleteJob(job))
			return new ResponseEntity<String>("Job Deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
}