
var app = angular.module( "app", [] );

var helloController = function( $scope )
       {
           $scope.helloTo = {};
           $scope.helloTo.title = "World, AngularJS";
       };

app.controller( "HelloController", helloController );



HEY LOOK A THIS LINK TO START:  http://g00glen00b.be/spring-angular-sockjs/