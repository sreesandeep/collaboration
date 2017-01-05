'use strict';

$scope.uploadFile = function(files) {
    var fd = new FormData();
    //Take the first selected file
    fd.append("file", files[0]);

    $http.post(uploadUrl, fd, {
        withCredentials: true,
        headers: {'Content-Type': undefined },
        transformRequest: angular.identity
    })
    success.error;

};

self.submit = function(id){
	{
		self.uploadimage(self.id);
	}
}

	var  BASE_URL="http://localhost:8082/CollaboartionBackEnd"
		 self.uploadimage = function(id){
         return (BASE_URL+'/upload')
         .then(
        		 function(response){
        			 return response.data;
        		 },
        		 function(errResponse){
        			 return $q.reject(errResponse);
        		 }
        		 );
 }; 

/*angular.module('myApp', ['ngFileUpload']);

var path = [ '$scope', 'Upload', function($scope, Upload) {
  $scope.onFileSelect = function($files) {
    //$files: an array of files selected, each file has name, size, and type.
    for (var i = 0; i < $files.length; i++) {
      var $file = $files[i];
      Upload.upload({
        url: 'my/upload/url',
        file: $file,
        progress: function(e){}
      }).then(function(data, status, headers, config) {
        // file is uploaded successfully
        console.log(data);
      }); 
    }
  }
}];
*/
