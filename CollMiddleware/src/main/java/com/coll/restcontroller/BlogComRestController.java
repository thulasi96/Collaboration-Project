package com.coll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.BlogCommentdao;
import com.coll.model.BlogComment;
@RestController
public class BlogComRestController {

	@Autowired
	BlogCommentdao blogcommentDAO;
	
	@GetMapping("/getBlogComments") 
	public ResponseEntity<List<BlogComment>> listBlogComments() 
	{
		List<BlogComment> listBlogComments=blogcommentDAO.getBlogComments();
		if(listBlogComments.size()>0) {
			return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getBlogComments/{blogId}") 
	public ResponseEntity<List<BlogComment>> getBlogCommentswithId(@PathVariable("blogId") int blogId) 
	{
		List<BlogComment> listBlogComments=blogcommentDAO.getBlogComments(blogId);
		if(listBlogComments.size()>0) {
			return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getBlogComment/{commentId}") 
	public ResponseEntity<BlogComment> getBlogComment(@PathVariable("commentId") int commentId)
	{
		BlogComment blogcomment=blogcommentDAO.getBlogComment(commentId);
		return new ResponseEntity<BlogComment>(blogcomment,HttpStatus.OK);
	}
	
	@PostMapping(value="/addBlogComment",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogcomment)
	{
		blogcomment.setCommentDate(new java.util.Date());
		if(blogcommentDAO.addBlogComment(blogcomment))
		{
			return new ResponseEntity<String>("BlogComment added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error adding blogcomment",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/updateBlogComment",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateBlogComment(@RequestBody BlogComment blogcomment)
	{
	BlogComment blogcomment1=blogcommentDAO.getBlogComment(blogcomment.getCommentId());
	blogcomment1.setCommentText(blogcomment.getCommentText());
	
		if(blogcommentDAO.updateBlogComment(blogcomment1)) 
		{
			return new ResponseEntity<String>("BlogComment updated",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error updating blogcomment",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping(value="/deleteBlogComment/{commentId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBlogComment(@PathVariable("commentId") int commentId)
	{
		BlogComment blogcomment=blogcommentDAO.getBlogComment(commentId);
		if(blogcommentDAO.deleteBlogComment(blogcomment)) 
		{
			return new ResponseEntity<String>("Blogcomment deleted",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error deleting blogcomment",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
