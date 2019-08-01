package com.coll.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.coll.dao.ProfilePictureDAO;
import com.coll.model.ProfilePicture;
import com.coll.model.UserDetail;
@RestController
public class FileUploadRestController {
	@Autowired
	ProfilePictureDAO profilePictureDAO;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam(value="file") CommonsMultipartFile fileUpload,HttpSession session)
	{
		UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		
		if(userDetail==null)
		{
			return new ResponseEntity<String>("Un Authorised user",HttpStatus.NOT_FOUND);
		}
		else
		{
			ProfilePicture profilePicture= new ProfilePicture();
			profilePicture.setUsername(userDetail.getUsername());
			profilePicture.setImage(fileUpload.getBytes());
			System.out.println("Save method");
			profilePictureDAO.save(profilePicture);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{username}")
	public @ResponseBody byte[] getProfilePicture(@PathVariable("username")String username,HttpSession session)
	{
		UserDetail userDetail =(UserDetail)session.getAttribute("userDetail");
		
		if(userDetail==null)
		{
			return null;
		}
		else
		{
			ProfilePicture profilePicture=profilePictureDAO.getProfilePicture(userDetail.getUsername());
			
			if(profilePicture!=null)
			{
				return profilePicture.getImage();
			}
			else
			{
				return null;
			}
		}
	}

}