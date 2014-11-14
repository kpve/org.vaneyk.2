

angular.module( "app", [] )
       .controller( "HelloController", function( $scope )
       {
           $scope.helloTo = {};
           $scope.helloTo.title = "World, AngularJS";
       } );
