myApp.controller("ForumController",function($scope,$location,$rootScope,$http)
{
	$scope.forum={"forumId":0,"forumName":"","forumContent":"","username":"","createDate":"","status":""};
	
	$rootScope.forumdata;
	
	$rootScope.forumId;
	
	$scope.addForum=function()
	{
		$scope.forum.username=$rootScope.currentUser.username;
		$http.post('http://localhost:8080/CollMiddleware/addForum',$scope.forum)
		.then(function(response)
		{
			console.log('forum Added');
			alert("forum Added Successfully");
			$location.path("/showForum");
		},
		function(errresponse)
		{
		console.log('Error occured to add a forum');
		alert("Error occured while adding forum");
		$location.path("/addForum");
		});
	}
	
	function listForums()
	{
		console.log('List Forum Method');
		
		$http.get('http://localhost:8080/CollMiddleware/showAllForum')
		.then(function(response)
				{
			console.log('Showing all the Forum');
			$scope.forumdata=response.data;
			console.log(' Forum Data');
		},
		function(errresponse){
			console.log('Error Occured');
		});
	}
	
	$scope.deleteForum=function(forumId)
	{
		
		$http.delete('http://localhost:8080/CollMiddleware/deleteForum/'+forumId)
		.then(function(response)
				{
			listForums();
			alert('Forum Deleted');
			console.log('Forum Deleted');
			$location.path("/showForum");
				},
				function(errresponse)
				{
					console.log('Error Occured');
					console.log('forum id:'+forumId);
					alert('Error Occured while Deleting Forum');
			
				});	
	}
	$scope.approve=function(forumId)
	{
		
		$http.get('http://localhost:8080/CollMiddleware/approveForum/'+forumId)
		.then(function(response)
				{
			listForums();
			alert('Forum Approved');
			console.log('Forum Approved');
			$location.path("/adminForum");
				},
				function(errresponse)
				{
					console.log('forum id:'+forumId);
					console.log('Error Occured');					
					alert('Error Occured while Approving Forum');
			
				});	
	}
	
	$scope.reject=function(forumId)
	{
		
		$http.get('http://localhost:8080/CollMiddleware/rejectForum/'+forumId)
		.then(function(response)
				{
			listForums();
			alert('Forum Rejected');
			console.log('Forum Rejected');
			$location.path("/adminForum");
				},
				function(errresponse)
				{
					console.log('Error Occured');
					alert('Error Occured while Rejecting Forum');
			
				});	
	}
	$scope.editForum=function(forumId)
	{
		console.log('Editing a Forum');
		$rootScope.forumId=forumId;
		$location.path("/updateForum");
	}
	
	function getForum()
	{
		console.log('getting a Forum');
		$http.get('http://localhost:8080/CollMiddleware/getForum/'+$rootScope.forumId)
		.then(function(response)
				{
					
					$scope.forum=response.data;
				});
	}
	$scope.updateForum=function()
	{
		console.log('I am in update Forum');
		$http.post('http://localhost:8080/CollMiddleware/updateForum',$scope.forum)
		.then(function(response)
				{
					alert("Forum is updated");
				},
				function(errresponse)
				{
					alert("Error Occured");
				});
	}
	$scope.showComment=function(forumId)
	{
		console.log('I am in show comment');
		$rootScope.forumId=forumId;
		$location.path("/showForumComment");
	}
	getForum();
	
	listForums();
	});