
var app = angular.module( "app", [] );

var helloController = function( $scope )
{
    $scope.helloTo = {};
    $scope.helloTo.title = "World, AngularJS";
};

var story = function()
{
	var content = "please provide story data in json format"
	
	this.setContent = function( newContent )
	{
		this.content = newContent;
	}
	
	this.getContent = function()
	{
		return this.content;
	}
};

//var StoryService = function( $http )
var storyService = function( $http )
{
    var storyApiUrl = "http://localhost:8080/story/";

    this.postStory = function( story )
    {
    	alert( "putting story: " + story );
    	var res = $http.put( storyApiUrl, story );
	    res.success( function( data, status, headers, config )
	    {
		    alert( "put story!" );
	    } );
	    res.error(function(data, status, headers, config)
	    {
		    alert( "failed to put story :(   cause: " +  JSON.stringify( { data: data } ) );
	    } );
    }
}

var storyController = function( $scope, $http, storyService )
{
    var newStory;
    var storyServiceReference = storyService;
    	
    $scope.newStory = this.newStory;
    this.addNewStory = function()
    {
    	storyServiceReference.postStory( this.newStory );
    }
    
    $scope.storyController = this;
};

// TODO revisit
//var initApplication = function( app  )
//{
	app.service( "storyService", [ "$http", storyService	 ] );
	app.controller( "helloController", helloController );
	app.controller( "storyController", [ "$scope", "$http", "storyService", storyController ] );
//};

//initApplication( app ); 


//HEY LOOK A THIS LINK TO START:  http://g00glen00b.be/spring-angular-sockjs/