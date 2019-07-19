package com.coll.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.JobDAO;
import com.coll.model.Job;

public class JobDaoTest {

static JobDAO jobDAO;
	
	@BeforeClass
	public static void executefirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		jobDAO=(JobDAO)context.getBean("jobDAO");
	}
	
	@Test
	public void addjobtest() {
		Job job=new Job();
		job.setDesignation("Analyst");
		job.setCompanyName("cognizant");
		job.setCtc(220000);
	    job.setLocation("Chennai");
	    assertTrue("problem in adding job",jobDAO.addJob(job));
	}

	@Ignore
	@Test
	public void getjobtest() {
		assertNotNull("problem in getting job",jobDAO.getJobDetails(501));
	}
	@Test 
	public void getjobstest() {
		List<Job> listJobs=jobDAO.displayJob();
	    for(Job job:listJobs) {
	    	System.out.println("jobid:"+job.getJobId());
	    }
	}
	@Ignore
	@Test
	public void deletejobtest() {
		Job job=jobDAO.getJobDetails(501);
		assertTrue("problem in adding job",jobDAO.deleteJob(job));
	}
}
