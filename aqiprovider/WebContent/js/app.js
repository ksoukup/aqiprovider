(function() {
	var app = angular.module('aqiProvider', []);
	app.controller('MainCtrl', function($http, $scope, $log) {

		$scope.message = "The function is done.";
		
		var onGetComplete = function(response){
			$log.info(response.data);
			$scope.records = response.data;
		}
		
		$http.get("AqiProviderCurrent").then(onGetComplete);
	

	});
}());
