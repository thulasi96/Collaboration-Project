package com.coll.dao;

import java.util.List;
import com.coll.model.BlogComment;

public interface BlogCommentdao {

	public boolean addBlogComment(BlogComment blogcomment);
	public boolean updateBlogComment(BlogComment blogComment);
	
	public boolean deleteBlogComment(BlogComment blogcomment);
	public BlogComment getBlogComment(int commentId);
	
	public List<BlogComment> getBlogComments();
	public List<BlogComment> getBlogComments(int blogId);
}
