package com.coll.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coll.model.Job;
@Repository("jobDAO")
@Transactional
public class JobDAOimpl implements JobDAO{
	@Autowired
	SessionFactory sessionFactory;

	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			System.out.println("Job Data created");
			return true;
			}
		catch(Exception e)
		{
			return false;
		}
	}

	public Job getJob(int jobId) {
		Session session=sessionFactory.openSession();
		Job job=session.get(Job.class,jobId);
		session.close();
		return job;
	}

	public List<Job> getJobs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job");
		List<Job> listJobs=query.list();
		return listJobs;
	}

	public boolean publishJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			System.out.println("Job Data created");
			return true;
			}
		catch(Exception e)
		{
			return false;
		}
	}

	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			System.out.println("Job Data deleted");
			return true;
			}
		catch(Exception e)
		{
			return false;
		}
	}
	

}