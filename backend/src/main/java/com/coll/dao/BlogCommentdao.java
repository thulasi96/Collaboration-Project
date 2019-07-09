package com.coll.dao;

import java.util.List;
import com.coll.model.BlogComment;

public interface BlogCommentdao {

	public boolean addComment(BlogComment blogComment);
	public boolean deleteComment(BlogComment blogComment);
	
	public List<BlogComment>listBlogComments(int blogId);
	public BlogComment getBlogComment(int blogCommentId);
}
