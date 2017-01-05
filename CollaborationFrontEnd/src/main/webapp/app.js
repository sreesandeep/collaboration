var app=angular.module('myApp',['ngRoute', 'ngCookies']); //myApp is the name of the module. [] is where you inject the dependencies.

app.config(function($routeProvider){ //The config() takes a function which takes the $routeProvider as parameter 
	                                 //and the routing configuration goes inside the function
	$routeProvider
	
	.when('/',{ 
		templateUrl : 'c_home/home.html',
		controller : 'HomeController'
	})
	
	.when('/manage_users',{  //when() method takes a pathand a route as parameters.
		templateUrl : 'c_admin/manage_users.html',
		controller : 'UserController'
	})
	
	.when('/manage_jobs',{ //when() method takes a panthad a route as parameters.
		templateUrl  :  'c_admin/manage_jobs.html',
		controller   :  'UserController'
	})
	
	.when('/manage_friends',{//when() method takes a panthad a route as parameters.
		templateUrl  : 'c_admin/manage_friends.html',
		controller   :  'UserController'
	})
	
	.when('/event',{  //path is a part of the URL after the # symbol.
		templateUrl : 'c_upload/upload.html', //route contains two properties - templateUrl and controller
		controller : 'FileUploadController'
	})
	
	.when('/about',{
		templateUrl : 'c_about/about.html',
			controller : 'AboutController'
	})
	
    .when('/job_opportunities',{
	templateUrl : 'c_job/job.html',
	controller : 'JobController'
   })
   
   /*.when('/event',{
	templateUrl : 'c_event/event.html',
	controller : 'EventController'
   })*/
   
   .when('/about',{
	templateUrl : 'c_about/about.html',
	controller : 'AboutController'
   })
   
  .when('/adminhome',{
	templateUrl : 'c_admin/adminhome.html'
    })

   .when('/login',{
	templateUrl : 'c_user/login.html',
	controller : 'UserController'
    })
    
   .when('/logout',{
	templateUrl : 'c_home/logout.html',
	controller : 'HomeController'
    })
    
   .when('/register',{
    templateUrl : 'c_user/register.html',
    controller : 'UserController'
    })

   .when('/myprofile',{
	templateUrl : 'c_user/my_profile.html',
	controller  : 'UserController'
    })

/********************* Blog Related***************************/
   .when('/create_blog',{
	templateUrl : 'c_blog/create_blog.html',
	controller : 'BlogController'
	})
	
   .when('/list_blog',{
	templateUrl : 'c_blog/list_blog.html',
		controller : 'BlogController'	
   })

   .when('/view_blog',{
	templateUrl : 'c_blog/view_blog.html',
	controller : 'BlogController'
	})
/******************Job related*******************************/
  .when('/view_applied_jobs',{
	templateUrl : 'c_job/view_applied_jobs.html',
	controller : 'JobController'
	})
	
   .when('/post_job',{
	templateUrl : 'c_job/post_job.html',
		controller : 'JobController'	
    })
    
   .when('/view_job_details',{
	templateUrl : 'c_job/view_job_details.html',
	controller : 'JobController'
	})
	
   .when('/search_job',{
	templateUrl : 'c_job/search_job.html',
	controller : 'JobController'	
    })

/*******************Friends related mapping*******************/
    .when('/add_friend',{
	templateUrl : 'c_friend/add_friend.html',
	controller : 'FriendController'
	})
	
	.when('/search_friend',{
		templateUrl : 'c_friend/search_friend.html',
		controller : 'FriendController'
	})

	.when('/view_friend',{
		templateUrl : 'c_friend/view_friend.html',
		controller : 'FriendController'
	})
	
    .when('/chat',{
	templateUrl : 'c_chat/chat.html',
	controller : 'ChatController'
   })

   .when('/chat_forum',{
	templateUrl : 'c_chat_forum/chat_forum.html',
	controller : 'ChatForumController'
   })
   
   .otherwise({redirectTo: '/'});
   })

/****************Security Related*************************/
	app.run(function($rootScope, $location, $cookieStore, $http){
	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		  //http://localhost:8081/Collaboration/addjob
	         //redirect to login page if not logged in and trying to access a restricted page
		  
		 var restrictedPage=$.inArray($location.path(),['//','/','/search_job','/view_blog','/register','/list_blog'])=== -1;
		// -1 ----> non-restricted pages are more and for restricted pages ----> 1 ;
		 console.log("restrictedPage:" +restrictedPage)
	     var loggedIn = $rootScope.currentUser.id;
	     
		 console.log("loggedIn:"+loggedIn)
/*		 if(restrictedPage & !loggedIn){
			console.log("Navigating to login page:")
			alert("You are not logged and so youcant apply for the job")
			$location.path('/login');
		 }
	 });
*/	 if (!loggedIn) 
     {
    	 
    	 if(restrictedPage) {
    		 console.log("Navigating to login page:")
    		 
    		 $location.path('/login');
    	 }
    	  
     }
     else //logged in
    	 {
    	 
    	 var role = $rootScope.currentUser.role;
    	 var userRestrictedPage = $.inArray($location.path(), ['/post_job','/adminhome']) ===0;
    	 
    	 if (userRestrictedPage && role!='admin')
    		 {
    		 alert("You cannot do this operation as you are not logged in as:"+role)
    		 $location.path('/login');
    		 }
    	 }
    	 
});
		
	   //keep user logged in after page refresh

	 $rootScope.currentUser = $cookieStore.get('currentUser') ||{};
	 if($rootScope.currentUser){
		 $http.defaults.headers.common['Authorization']='Basic'+$rootScope.currentUser;
		 }
});





