myApp.controller("JobController",function($scope,$location,$rootScope,$http)
{
	$scope.job={"jobId":0,"jobDesignation":"","companyName":"","ctc":0,"jobLocation":"","lastDate":"","skills":""};
	$scope.applyjob={"applyId":0,"username":"","jobapplied":"","appliedDate":""};
	
	$rootScope.jobdata;
	$rootScope.applyjobdata;
	
	$scope.jobs;
	$scope.applyjob;
	
	$scope.addJob=function()
	{
		
		$http.post('http://localhost:8080/CollMiddleware/addJob',$scope.job)
		.then(function(response)
		{
			
			console.log('job Added');
			alert("job Added Successfully");
			$location.path("/addJob");
		},
		function(errresponse)
		{
		console.log('Error occured to add a job');
		alert("Error occured while adding job");
		$location.path("/addJob");
		});
	}
	
	$scope.applyjob=function()
	{
		$scope.applyjob.username=$rootScope.currentUser.username;
		$http.post('http://localhost:8080/CollMiddleware/applyjob',$scope.applyjob)
		.then(function(response)
		{
			console.log('job applied');
			alert("job Applied Successfully");
			$location.path("/showJob");
		},
		function(errresponse)
		{
		console.log('Error occured in applying a job');
		alert("Error occured while applying job");
		$location.path("/showJob");
		});
	}
	
	$scope.publishJob=function()
	{
		console.log('--- I am in publish job ---');
		
		$http.post('http://localhost:8080/CollMiddleware/publishJob',$scope.job)
		.then(function(response)
				{
					alert("Job published");
					$location.path("/showJob");
				},
				function(errresponse)
				{
					alert("Error Occured While Job publishing");
				});
	}
	
	$scope.deleteJob=function(jobId)
	{
		
		$http.delete('http://localhost:8080/CollMiddleware/deleteJob/'+jobId)
		.then(function(response)
				{
			listJobs();
			alert('job Deleted');
			console.log('JOb Deleted');
			$location.path("/showJob");
				},
				function(errresponse)
				{
					console.log('Error Occured');
					console.log('job Id:'+jobId);
					alert('Error Occured while Deleting Job');
			
				});	
	}
	
	function listJobs()
	{
		console.log('List Job Method');
		
		$http.get('http://localhost:8080/CollMiddleware/getJobs')
		.then(function(response)
				{
			console.log('Showing all the Jobs');
			$scope.jobdata=response.data;
		},
		function(errresponse){
			console.log('Error Occured');
		});
	}
	listJobs();
	
});