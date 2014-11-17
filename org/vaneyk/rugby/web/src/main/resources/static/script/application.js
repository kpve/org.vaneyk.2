
var app = angular.module( "app", [] );

var helloController = function( $scope )
{
    $scope.helloTo = {};
    $scope.helloTo.title = "World, AngularJS";
};


//var stompService = function( $q, $timeout, reconnectTimeoutValue, socketUrlValue, brokerPathValue, topicPathValue )
//{
//	var listener = $q.defer();
//	
//	var config =  {
//	    reconnectTimeout : reconnectTimeout;
//	    socketUrl : socketUrl;
//	    brokerPath : brokerPath;
//	    topicPath : topicPath;
//	};
//	
//	var socket = 
//	{
//	    client: null,
//	    stomp: null
//	};
//	
//	var messageIds = [];
//	   
//    this.receive = function()
//	{
//	    return listener.promise;
//	};
//	   
//    service.send = function (message )
//    {
//        var id = Math.floor( Math.random() * 1000000 );
//        socket.stomp.send(service.CHAT_BROKER, {
//        priority: 9
//      }, JSON.stringify({
//        message: message,
//        id: id
//      }));
//      messageIds.push(id);
//    };
//	   
//    var reconnect = function() {
//	      $timeout(function() {
//	        initialize();
//	      }, this.RECONNECT_TIMEOUT);
//	    };
//	   
//	    var getMessage = function(data) {
//	      var message = JSON.parse(data), out = {};
//	      out.message = message.message;
//	      out.time = new Date(message.time);
//	      if (_.contains(messageIds, message.id)) {
//	        out.self = true;
//	        messageIds = _.remove(messageIds, message.id);
//	      }
//	      return out;
//	    };
//	   
//	    var startListener = function() {
//	      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
//	        listener.notify(getMessage(data.body));
//	      });
//	    };
//	   
//	    var initialize = function()
//	    {
//	        socket.client = new SockJS( socketUrl );
//	        socket.stomp = Stomp.over( socket.client );
//	        socket.stomp.connect( {}, startListener);
//	        socket.stomp.onclose = reconnect;
//	    };
//	   
//	    initialize();
//	    return service;
//	  } );
//
//	  
//var stompServiceFactory = function( $q, $timeout )
//{
//	return stompService : stompService( $q, $timeout, );
//	
}

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
    this.STORY_API_URL     = "http://localhost:8080/story/";
    this.MESSAGING_TIMEOUT = 30000;
    this.MESSAGING_TOPIC   = "/topic/all";
    
    this.postStory = function( story )
    {
    	alert( "putting story: " + story );
    	var result = $http.post( storyApiUrl, story );
    	result.success( function( data, status, headers, config )
	    {
		    alert( "put story: " + JSON.stringify( data ) );
	    } );
    	result.error(function(data, status, headers, config)
	    {
		    alert( "failed to put story :(   cause: " +  JSON.stringify( { data: data } ) );
	    } );
    }
    
    
    service.receive = function()
    {
        return listener.promise;
      };
    
    this.updateAllStories = function()
    {
    	var result = $http.get( storyApiUrl );
    };
}

var storyController = function( $scope, $http, storyService )
{
	this.allStories = "empty";
    this.newStory = "emptyyyyy";
    var storyServiceReference = storyService;
   
    this.getAllStories = function()
    {
    	return this.allStories;
    }
    
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