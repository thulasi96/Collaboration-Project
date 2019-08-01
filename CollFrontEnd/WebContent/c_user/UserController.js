myApp.controller("UserController",function($scope,$location,$rootScope,$http,$cookieStore)
{
	$scope.userDetail={username:'',firstname:'',lastname:'',password:'',emailId:'',role:'',status:'',isOnline:''};
	
	$rootScope.currentUser;
	
	$scope.checkUser=function()
	{
		 $http.post('http://localhost:8080/CollMiddleware/checkuser',JSON.stringify($scope.userDetail))
		.then(function(response)
		{
			console.log('Logged In');
			
			$rootScope.currentUser=response.data;
			console.log($rootScope.currentUser);
			$cookieStore.put('userDetail',response.data);
			$location.path("/blog");
		},
		function(errresponse)
		{
			console.log('Error username or password is incorrect');
			$scope.error="username or password is incorrect";
			$location.path("/login");
		});
		
	}
	
	
	$scope.register=function()
	{
		$scope.userDetail.role='ROLE_USER';
		$scope.userDetail.status='Y';
		$scope.userDetail.isOnline='N';
		
		
		$http.post('http://localhost:8080/CollMiddleware/registerUser',JSON.stringify($scope.userDetail))
		.then(function(response)
		{
			console.log('User is registered');
			console.log(response.data);
			$location.path("/login");
		},
		function(errresponse)
		{
			console.log('Error occured during registration');
			console.log(errresponse.data);
		});
		
	}
	
	$scope.logout=function()
	{
		console.log('Logging out');
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetail');
		alert("Logged Out");
		$location.path("/login");
	}
	});