'use strict';

app.controller("FriendController", ['UserService','$scope','FriendService','$location','$rootScope',   
                                    function(UserService,$scope,FriendService,$location,$rootScope){
	console.log("FriendController...")
  	var self = this;
	self.friend ={
			id : '',
			userID : '',
			friendID : '',
			status : '',
			isOnline : '',
			errorMessage : '',
			errorCode : ''
	};
	self.friends = [];
	
	
	self.user = {
			id : '',
			name :'',
			email : '',
			password : '',
			mob_no : '',
			dob : '',
			gender : '',
			role : '',
			errorMessage : '',
			errorCode : ''
	};
	self.users = [];
	
	self.sendFriendRequest=sendFriendRequest
	function sendFriendRequest(friendID)
	{
		console.log("->sendFriendRequest :"+friendID);
		FriendService.sendFriendRequest(friendID)
		.then
		(function(d){
			self.friend = d;
			alert("Friend request sent")
		},
		  function(errResponse){
			console.error('Error while sending friend request');
		}
		);
	}
	
	self.getMyFriends = function(){
		console.log("Getting my friends");
		FriendService.getMyFriends()
		.then(
		     function(d)	{
		    	 self.friends = d;
					alert("Got the friend request")
		     },
		     function(errResponse){
					console.error('Error while sending friend request');
				}
		);
	};
	
	self.updateFriendRequest = function(friend, id){
		FriendService.updateFriendRequest(friend, id)
		.then(
				self.fetchAllFriends,
			     function(errResponse){
						console.error('Error while updating friend');
					} 		
		);
	};
	
	self.deleteFriend = function(id){
		FriendService.deleteFriend(id)
		.then(
				self.fetchAllFriends,
			     function(errResponse){
						console.error('Error while deleting friend');
					} 		
		);
	};
	
	self.fetchAllUsers = function(id){
		UserService.fetchAllUsers().then(function(d){
			self.users = d;
		},
			     function(errResponse){
						console.error('Error while feteching users');
					} 		
		);
	};
	
	self.fetchAllUsers();
	
	self.getMyFriends();
	
}])
  

	
	