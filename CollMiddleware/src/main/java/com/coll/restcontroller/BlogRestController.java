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

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

@RestController
public class BlogRestController {

	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/listBlogs")
	public ResponseEntity<List<Blog>> getBlogs() 
	{
		List<Blog> listBlogs=blogDAO.listBlogs();
		if(listBlogs.size()>0)
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=blogDAO.getBlog(blogId);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@PostMapping(value="/addBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlog(@RequestBody Blog blog)
	{
		blog.setUsername("Arjun");
		blog.setCreateDate(new java.util.Date());
		blog.setStatus("NA");
		blog.setLikes(1);
		blog.setDislikes(0);
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("Blog added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error adding blog",HttpStatus.NOT_FOUND);
		}
}
	

	@PostMapping(value="/updateBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
	{
		Blog blog1=blogDAO.getBlog(blog.getBlogId());
		blog1.setBlogName(blog.getBlogName());
		blog1.setBlogContent(blog.getBlogContent());
		if(blogDAO.updateBlog(blog1)) 
		{
			return new ResponseEntity<String>("Blog updated",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error updating blog",HttpStatus.NOT_FOUND);
		}
		
	}
	@GetMapping(value="/deleteBlog/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=blogDAO.getBlog(blogId);
		if(blogDAO.deleteBlog(blog)) 
		{
			return new ResponseEntity<String>("Blog deleted",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error deleting blog",HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value="/approveBlog/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=blogDAO.getBlog(blogId);
		if(blogDAO.approveBlog(blog)) 
		{
			return new ResponseEntity<String>("Blog approved",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error approving blog",HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping(value="/rejectBlog/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=blogDAO.getBlog(blogId);
		if(blogDAO.rejectBlog(blog)) 
		{
			return new ResponseEntity<String>("Blog rejected",HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("Error rejecting blog",HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value="/incrementLikes/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementLikes(@PathVariable("blogId") int blogId)
	{
		if(blogDAO.incrementLikes(blogId)) 
		{
			return new ResponseEntity<String>("incremented Likes",HttpStatus.OK);
		}
		else 
	    {
			return new ResponseEntity<String>("Error incrementing Likes",HttpStatus.NOT_FOUND);
		}
    }

	@GetMapping(value="/incrementDislikes/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementLDislikes(@PathVariable("blogId") int blogId)
	
	{
		if(blogDAO.incrementDislikes(blogId)) 
		{
			return new ResponseEntity<String>("incremented Dislikes",HttpStatus.OK);
		}
		else 
	    {
			return new ResponseEntity<String>("Error incrementing Dislikes",HttpStatus.NOT_FOUND);
		}
    }
}
