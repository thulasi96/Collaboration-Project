package com.coll.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

@RestController
public class BlogRestController
{
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/showAllBlogs")
	public ResponseEntity<List<Blog>> showAllBlogs()
	{
		List<Blog> listBlogs=blogDAO.getBlogs();
		
		if(listBlogs.size()>0)
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getBlog/{blogid}")
	
		public ResponseEntity<Blog> getBlog(@PathVariable("blogid")int blogid)
		{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	
	@PostMapping(value="/addBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlog(@RequestBody Blog blog)
	{
		blog.setCreateDate(new java.util.Date());
		
		
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value="/updateBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
	{
		Blog blog1=blogDAO.getBlog(blog.getBlogid());
		blog1.setCreateDate(blog.getCreateDate());
		blog1.setDislikes(blog.getDislikes());
		blog1.setLikes(blog.getLikes());
		blog1.setStatus(blog.getStatus());
		blog1.setUsername(blog.getUsername());
		blog1.setBlogName(blog.getBlogName());
			
		if(blogDAO.updateBlog(blog1)) 
		{
			return new ResponseEntity<String>("Blog updated",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error updating blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value="/deleteBlog/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		
		if(blogDAO.deleteBlog(blog))
		{
			return new ResponseEntity<String>("Blog Deleted",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/approveBlog/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> approveBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		if(blogDAO.approveBlog(blog))
		{
			return new ResponseEntity<String>("Approved",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);				
	}
	
	@GetMapping(value="/rejectBlog/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> rejectBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		
		if(blogDAO.rejectBlog(blog))
		{
			return new ResponseEntity<String>("Approved",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);				
	}
	
	@GetMapping(value="/incrementLikes/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementLikes(@PathVariable("blogid")int blogid)
	
	{
		if(blogDAO.incrementLikes(blogid))
		{
			return new ResponseEntity<String>("Incremented likes",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/incrementDisLikes/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementDisLikes(@PathVariable("blogid")int blogid)
	
	{
		if(blogDAO.incrementDislikes(blogid))
		{
			return new ResponseEntity<String>("Incremented Dislikes",HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}