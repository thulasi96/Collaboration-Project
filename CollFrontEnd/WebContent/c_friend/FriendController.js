myApp.controller("FriendController",function($scope,$location,$rootScope,$http)
{
	$scope.friend={friendId:0, username:'', friendName:'', status:''};
	 $scope.showFriends;
	    $scope.friendRequests;
	    $scope.suggestedFriends;
		
	    function showFriends() {
	    	 $http.get('http://localhost:8080/CollMiddleware/showAllFriends/'+$rootScope.currentUser.username)
	    	 .then(function(response) {
	    		 console.log("Showing Friends");
	    		 $scope.showFriends=response.data;
	    	 },
	    	 function(errresponse) {
	    		 console.log("Error showing Friends");
	    		 $scope.showFriends=errresponse.data;
	    	 }); 			 
	    }
	    
	    function friendRequests() {
	    	 $http.get('http://localhost:8080/CollMiddleware/showPendingFriendList/'+$rootScope.currentUser.username)
	    	 .then(function(response) {
	    		 console.log("Showing Friend Requests");
	    		 $scope.friendRequests=response.data;
	    	 },
	    	 function(errresponse) {
	    		 console.log("Error showing Friend Requests");
	    		 $scope.friendRequests=errresponse.data;
	    	 }); 			 
	    }
	    
	    function suggestedFriends() {
	    	 $http.get('http://localhost:8080/CollMiddleware/showSuggestedFriendList/'+$rootScope.currentUser.username)
	    	 .then(function(response) {
	    		 console.log("Showing Suggested Friends");
	    		 $scope.suggestedFriends=response.data;
	    	 },
	    	 function(errresponse) {
	    		 console.log("Error showing Suggested Friends");
	    		 $scope.suggestedFriends=errresponse.data;
	    	 }); 		
	    }
	    
	    $scope.acceptRequest=function(friendId) 
	    {
	      	 $http.get('http://localhost:8080/CollMiddleware/acceptFriendRequest/'+friendId)
	      	 .then(function(response)
	      	{
	      		 console.log("Friend accepted");
	      		 friendRequests();
	      		 $location.path("/friend");
	      	 },
	      	 function(errresponse)
	      	 {
	      		 console.log("Error accepting Friend");
	      	 }); 		
	      }
	    
	   
	    
	    $scope.unfriend=function(friendId)
	    {
	   	 $http.get('http://localhost:8080/CollMiddleware/deleteFriendRequest/'+friendId)
	   	 .then(function(response) {
	   		 console.log("Friend removed");
	   		 friendRequests();
	   		 $location.path("/friendRequests");
	   	 },
	   	 function(errresponse) {
	   		 console.log("Error removing Friend");
	   	 }); 		
	   }
	   
	    $scope.deleteFriend=function(friendId) {
	    	 $http.get('http://localhost:8080/CollMiddleware/deleteFriendRequest/'+friendId)
	    	 .then(function(response) {
	    		 console.log("Friend removed");
	    		 showFriends();
	    		 $location.path("/friend");
	    	 },
	    	 function(errresponse) {
	    		 console.log("Error removing Friend");
	    	 }); 		
	    }
	    
	    $scope.sendRequest=function(username) {
	    	 $scope.friend.username=$rootScope.currentUser.username;
	    	
	    	 $scope.friend.friendusername=username;
	    	 
	    	 $scope.friend.status="NA";
	    	 
	    	 $http.post('http://localhost:8080/CollMiddleware/sendFriendRequest',$scope.friend)
	    	 .then(function(response) {
	    		 console.log("Friend request sent");
	    		 alert('Request sent');
	    		 suggestedFriends();
	    		 $location.path("/friend");
	    	 },
	    	 function(errresponse) {
	    		 console.log("Error sending Friend request");
	    	 }); 		
	    }
	    
	    showFriends();
	    friendRequests();
	    suggestedFriends();
});