package com.coll.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coll.model.ProfilePicture;

@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureDAOimpl implements ProfilePictureDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean save(ProfilePicture profilePicture) {
	    
		try {
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(profilePicture);
			session.flush();
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}

	
	public ProfilePicture getProfilePicture(String username) {
		Session session=sessionFactory.openSession();
		ProfilePicture profilePicture=session.get(ProfilePicture.class,username);
		session.close();
		return profilePicture;
		}
}
