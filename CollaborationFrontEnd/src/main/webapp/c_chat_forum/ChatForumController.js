app.controller("ChatForumController" , function($scope, ChatForumService) {
	$scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    
    $scope.addMessage = function() {
    	console.log("addMessage")
    ChatForumService.send($scope.message);
    	$scope.message = "";
    };
    
    ChatForumService.recieve().then(null , null, function(message) {
         console.log("recieve") 
         
       $scope.messages.push(message);  
    });
}); 