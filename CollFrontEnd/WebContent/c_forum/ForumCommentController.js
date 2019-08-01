myApp.controller("ForumCommentController",function($scope,$location,$rootScope,$http)
		{
			$scope.forum={"forumId":0,"forumName":"","forumContent":"","username":"","createDate":"","status":"","likes":0,"dislikes":0};
			
			$scope.forumComment;
			
			$rootScope.forumId;
			
			$scope.comment={"commentId":0,"forumId":0,"forumComment":"","commentDate":"","username":""};		
			
			function loadForum()
			{
				console.log('getting a Forum');
				$http.get('http://localhost:8080/CollMiddleware/getForum/'+$rootScope.forumId)
				.then(function(response)
						{
							
							$scope.forum=response.data;
						});
			}
			
			function showAllForumComments()
			{
				console.log('Listing using forumId');
				$http.get('http://localhost:8080/CollMiddleware/listForumComments/'+$rootScope.forumId)
				.then(function(response)
						{	
					$scope.forumComment=response.data;
							
						},
						function(errresponse){
							console.log('Error Occured');
						}
						);
			}
			$scope.addComment=function()
			{
				console.log('I am in add comment');
				$scope.comment.forumId=$scope.forum.forumId;
				$scope.comment.username=$rootScope.currentUser.username;
				$http.post('http://localhost:8080/CollMiddleware/addForumComment',$scope.comment)
				.then(function(response)
						{
					showAllForumComments();
							alert("comment added");
						},
						function(errresponse)
						{
							
						alert("Error occured");
						});
			}
			$scope.deleteForumComment=function(commentId)
			{
				console.log('Forum Comment Deleted');
				$http.delete('http://localhost:8080/CollMiddleware/deleteForumComment/'+commentId)
				.then(function(response)
				{
					showAllForumComments();
					alert('Forum Comment Deleted');
					$location.path("/showForumComment");
				},
				function(errresponse)
				{
					console.log('Error Occured');
					alert('Error Occured while Deleting Forum comment');
			
				});
			}
			function listForums()
			{
				console.log('List Forum Method');
				
				$http.get('http://localhost:8080/CollMiddleware/showAllForums')
				.then(function(response)
						{
					console.log('Showing all the Forum');
					$scope.forumdata=response.data;
				},
				function(errresponse){
					console.log('Error Occured');
				});
			}
			
			
			
			$scope.incrementLikes=function(forumId)
			{
				console.log('Incremented Likes');
				$http.get('http://localhost:8080/CollMiddleware/incrementLikes/'+forumId)
				.then(function(response)
				{
					listForums();
					$location.path("/showForumComment");
				},
				function(errresponse){
					console.log('Error Occured');
				});
				
			}
			$scope.incrementDisLikes=function(forumId)
			{
				console.log('Incremented dislikes');
				$http.get('http://localhost:8080/CollMiddleware/incrementDisLikes/'+forumId)
				.then(function(response)
				{
					listForums();
					$location.path("/showForumComment");
				},
				function(errresponse){
					console.log('Error Occured');
				});
				
			}
			$scope.deleteForum=function(forumId)
			{
				console.log('Forum Deleted');
				$http.get('http://localhost:8080/CollMiddleware/deleteForum/'+forumId)
				.then(function(response)
						{
					listForums();
					alert('Forum Deleted');
					$location.path("/showForumComment");
						},
						function(errresponse)
						{
							console.log('Error Occured');
							alert('Error Occured while Deleting Forum');
					
						});	
			}
			$scope.editForum=function(forumId)
			{
				console.log('Editing a Forum');
				$rootScope.forumId=forumId;
				$location.path("/updateForum");
			}
			$scope.updateForum=function()
			{
				console.log('I am in update forum');
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
			loadForum();
			showAllForumComments();
			listForums();
			
		});