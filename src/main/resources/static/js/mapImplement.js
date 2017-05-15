
    var map, infoWindow,i,obj,latt,lann,pos,marker,content,infowindow,location;
    function initMap() {
      map = new google.maps.Map(document.getElementById('map_canvas'), {
        center: {lat: 51, lng: 19},
        zoom: 5
      });
      infoWindow = new google.maps.InfoWindow;
      
      // HTML5 geolocation.
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
      	
      	 
      	  
          pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          map.setCenter(pos);
          
        }, function() {
          handleLocationError(true, infoWindow, map.getCenter());
        });
      } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
      }
    }

    function handleLocationError(browserHasGeolocation, infoWindow, pos) {
      infoWindow.setPosition(pos);
      infoWindow.setContent(browserHasGeolocation ?
                            'Error: The Geolocation service failed.' :
                            'Error: Your browser doesn\'t support geolocation.');
      infoWindow.open(map);
        
      
      }
    
   
    function mapRajesh()
    {
    	initMap();
    	
    	$(document).ready(function() {
    		$("#a").click(function(){
    	  		alert("Request sent.!");
    	  		});
    		
    		
        	var i;
    		
    	  	var jsonData = $("#jsonData").val();
    	  	 
    	  	  
    	  	  console.log(jsonData);
    	  	  obj = JSON.parse(jsonData);
    	  	  var len = obj.length;

    	  	  infowindow = new google.maps.InfoWindow();
    		  
    		  for(i=0; len; i++)
    			  {
    			  displayLocation(obj[i]);
    			  }
    	  		
    	    });
    }
    
    
    function displayLocation(location) {
        
    	 var content = 	'<div class="infoWindow"><strong>' 	+location.name + '</strong>'
	  		+ '<br/>' 	+ location.bloodgroup
	  		+ '<br/>' 	+ location.city
         + '<br/>' 	+ location.email + '</div>'
         + '<br/>' 	+ location.phone + '</div>';
         console.log("This  content is now printing "+content); 
        var lat = location.latitude;
        var lon = location.longitude;
        var  latLng = new google.maps.LatLng(lat,lon);
        
        if (parseInt(location.latitude) == 0) {
            geocoder.geocode( { 'address': location.city }, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    
                    var marker = new google.maps.Marker({
                        map: map, 
                        position: latLng,
                        title: location.name
                    });
                    
                    google.maps.event.addListener(marker, 'mouseover', function() {
                        infowindow.setContent(content);
                        infowindow.open(map,marker);
                    });
                    
                }
            });
        } else {
            
            var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
            var position = new google.maps.LatLng(parseFloat(location.latitude), parseFloat(location.longitude));
            var marker = new google.maps.Marker({
                map: map, 
                position: position,
                title: location.name
                //icon: iconBase + 'schools_maps.png'
            });
            
            google.maps.event.addListener(marker, 'mouseover', function() {
                infowindow.setContent(content);
                infowindow.open(map,marker);
            });
        }
	}