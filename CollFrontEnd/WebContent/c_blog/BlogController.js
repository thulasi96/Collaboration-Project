myApp.controller("BlogController",function($scope,$location,$rootScope,$http)
{
	$scope.blog={"blogid":0,"blogName":"","blogContent":"","username":"","createDate":"","status":"","likes":0,"dislikes":0};
	
	$rootScope.blogdata;
	
	$rootScope.blogid;
	
	$scope.addBlog=function()
	{
		$scope.blog.username=$rootScope.currentUser.username;
		$http.post('http://localhost:8080/CollMiddleware/addBlog',$scope.blog)
		.then(function(response)
		{
			console.log('Blog Added');
			alert("Blog Added Successfully");
			$location.path("/addBlog");
		},
		function(errresponse)
		{
		console.log('Error occured to add a blog');
		alert("Error occured while adding Blog");
		$location.path("/addBlog");
		});
	}
	
	function listBlogs()
	{
		console.log('List Blog Method');
		
		$http.get('http://localhost:8080/CollMiddleware/showAllBlogs')
		.then(function(response)
				{
			console.log('Showing all the Blog');
			$scope.blogdata=response.data;
		},
		function(errresponse){
			console.log('Error Occured');
		});
	}
	
	
	
	$scope.incrementLikes=function(blogid)
	{
		$http.get('http://localhost:8080/CollMiddleware/incrementLikes/'+blogid)
		.then(function(response) {
			 console.log("Incremented Likes");
			 listBlogs();
			 $location.path("/showBlog");
		 },
		 function(errresponse) {
			 console.log("Error Incrementing Likes");
		 });
		
	}
	$scope.incrementDisLikes=function(blogid)
	{
		$http.get('http://localhost:8080/CollMiddleware/incrementDisLikes/'+blogid)
		.then(function(response) {
			 console.log("Incremented Dislikes");
			 listBlogs();
			 $location.path("/showBlog");
		 },
		 function(errresponse) {
			 console.log("Error Incrementing Dislikes");
		 });
		
	}
	$scope.deleteBlog=function(blogid)
	{
		$http.get('http://localhost:8080/CollMiddleware/deleteBlog/'+blogid)
		.then(function(response) {
			 console.log("Blog deleted");
			 listBlogs();
			 $location.path("/showBlog");
		 },
		 function(errresponse) {
			 console.log("Error deleting Blog");
		 });
	}
	$scope.approve=function(blogid)
	{
		$http.get('http://localhost:8080/CollMiddleware/approveBlog/'+blogid)
		 .then(function(response) {
			 console.log("Blog approved");
			 listBlogs();
			 $location.path("/manageBlog");
		 },
		 function(errresponse) {
			 console.log("Error approving Blog");
		 });
	}
	
	$scope.reject=function(blogid)
	{
		$http.get('http://localhost:8080/CollMiddleware/rejectBlog/'+$rootScope.blogid)
		.then(function(response) {
			 console.log("Blog rejected");
			 listBlogs();
			 $location.path("/manageBlog");
		 },
		 function(errresponse) {
			 console.log("Error rejecting Blog");
		 });
	}
	$scope.editBlog=function(blogid)
	{
		console.log('Editing a Blog');
		$rootScope.blogid=blogid;
		$location.path("/updateBlog");
	}
	
	function getBlog()
	{
		$http.get('http://localhost:8080/CollMiddleware/getBlog/'+$rootScope.blogid)
		.then(function(response) {
			$scope.blog=response.data;
	console.log($scope.blog);		
			console.log("getting blog");
		},
		function(errresponse)
		{
			console.log("error getting blog");
		});
	}
	
	$scope.updateBlog=function()
	{
		console.log('I am in update blog');
		$http.post('SupdateBlog',$scope.blog)
		.then(function(response)
				{
					alert("Blog is updated");
				},
				function(errresponse)
				{
					alert("Error Occured");
				});
	}
	$scope.showComment=function(blogid)
	{
		console.log('I am in show comment');
		$rootScope.blogid=blogid;
		$location.path("/blogcom");
	}
	getBlog();
	
	listBlogs();
	});