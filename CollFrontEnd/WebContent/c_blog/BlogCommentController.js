    
myApp.controller("BlogCommentController",function($scope,$location,$rootScope,$http)
		{
			$scope.blog={"blogid":0,"blogName":"","blogContent":"","username":"","createDate":"","status":"","likes":0,"dislikes":0};
			
			$scope.blogComment;
			
			$rootScope.blogid;
			
			$scope.comment={"commentId":0,"blogId":0,"blogComment":"","commentDate":"","username":""};		
			
			function loadBlog()
			{
				console.log('getting a Blog');
				$http.get('http://localhost:8080/CollMiddleware/getBlog/'+$rootScope.blogid)
				.then(function(response)
						{
							
							$scope.blog=response.data;
						});
			}
			
			function loadBlogComments()
			{
				$http.get('http://localhost:8080/CollMiddleware/showAllBlogComments')
				.then(function(response)
						{
							
							$scope.blogComment=response.data;
						});
			}
			$scope.addComment=function()
			{
				console.log('I am in add comment');
				$scope.comment.blogid=$scope.blog.blogid;
				$scope.comment.username=$rootScope.currentUser.username;
				$http.post('http://localhost:8080/CollMiddleware/addBlogComment',$scope.comment)
				.then(function(response)
						{
							loadBlogComments();
							alert("comment added");
						},
						function(errresponse)
						{
							
						alert("Error occured");
						});
			}
			loadBlog();
			loadBlogComments();
		});