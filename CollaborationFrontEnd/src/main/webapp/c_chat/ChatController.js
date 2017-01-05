app.controller("ChatController" , function($scope, ChatService) {
	$scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    
    $scope.addMessage = function() {
    	console.log("addMessage")
    ChatService.send($scope.message);
    	$scope.message = "";
    };
    
    ChatService.recieve().then(null , null, function(message) {
         console.log("recieve") 
         
       $scope.messages.push(message);  
    });
}); 