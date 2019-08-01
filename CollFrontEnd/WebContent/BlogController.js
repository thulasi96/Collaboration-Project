myApp.controller("BlogController",function($scope,$location,$rootScope,$http)
{
	$scope.blogDetail={blogid:'',blogName:'',blogContent:'',username:''};
	
	$scope.checkUser=function()
	{
		console.log($scope.blogDetail);
		
		$location.path("/blog");
	}
	$scope.register=function()
	{
		$scope.blogDetail.date='27-05-2019';
		$scope.blogDetail.status='Y';
		$scope.blogDetail.likes='1';
		$scope.blogDetail.dislikes='1';
		
		console.log($scope.blogDetail);		
	}
});