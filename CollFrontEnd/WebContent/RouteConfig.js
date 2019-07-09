var myApp=angular.module("myApp",['ngRoute']);

myApp.config(function($routeProvider){
	
	alert("I am in Route Provider");
	
	$routeProvider.when("/login",{templateUrl:"c_user/Login.html"})
    					.when("/register",{templateUrl:"c_user/Register.html"})
    					.when("/aboutus",{templateUrl:"c_user/AboutUs.html"});
    
});