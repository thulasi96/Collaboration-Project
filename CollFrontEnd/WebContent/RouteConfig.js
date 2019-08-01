var myApp=angular.module("myApp",['ngRoute','ngCookies']);

myApp.config(function($routeProvider){
	
	
	
	$routeProvider.when("/login",{templateUrl:"c_user/Login.html"})
    					.when("/register",{templateUrl:"c_user/Register.html"})
    					.when("/aboutus",{templateUrl:"c_user/AboutUs.html"})
						.when("/profile",{templateUrl:"c_user/ProfilePicture.html"})
						.when("/chat",{templateUrl:"c_chat/Chat.html"})
	
						.when("/addBlog",{templateUrl:"c_blog/AddBlog.html"})
						.when("/manageBlog",{templateUrl:"c_blog/ManageBlog.html"})
					    .when("/showBlog",{templateUrl:"c_blog/ShowBlog.html"})
					    .when("/updateBlog",{templateUrl:"c_blog/UpdateBlog.html"})
					    .when("/myBlog",{templateUrl:"c_blog/MyBlog.html"})
	
					    .when("/addForum",{templateUrl:"c_forum/AddForum.html"})
					    .when("/manageForum",{templateUrl:"c_forum/ManageForum.html"})
					    .when("/showForum",{templateUrl:"c_forum/ShowForum.html"})
					    .when("/updateForum",{templateUrl:"c_forum/UpdateForum.html"})
					    
					    
					    .when("/forumComment",{templateUrl:"c_forum/ForumComment.html"})
					    .when("/updateForumComment",{templateUrl:"c_forum/UpdateForumComment.html"})
					    
					    .when("/addJob",{templateUrl:"c_job/Job.html"})
					    .when("/showJob",{templateUrl:"c_job/ShowJob.html"})
					    
					    .when("/showFriends",{templateUrl:"c_friend/Friend.html"})
					    
					    
					    
});

myApp.run(function($rootScope,$cookieStore)
		{
		console.log("I am in run function");
		if($rootScope.currentUser==undefined)
			{
			$rootScope.currentUser=$cookieStore.get('userDetail');
			console.log('I am in run Scope');
			}
		});