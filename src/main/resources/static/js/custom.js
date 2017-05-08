/**
 * Profile page js
 */
$(document).ready(function() {
$(".btn-pref .btn").click(function () {
    $(".btn-pref .btn").removeClass("btn-primary").addClass("btn-default");
    // $(".tab").addClass("active"); // instead of this do the below 
    $(this).removeClass("btn-default").addClass("btn-primary");   
});


//latitude and longitude 
	
var latf = $("#lat"), longf = $("#lon");

$("#city").keyup(function(){
	 var xx = $("#city").val();

	var geocoder = new google.maps.Geocoder();
	var address = xx;

	geocoder.geocode( { 'address': address}, function(results, status) {

	if (status == google.maps.GeocoderStatus.OK) {
	    var latitude = results[0].geometry.location.lat();
	    var longitude = results[0].geometry.location.lng();

	     if(address =='')
	    	{
	    	 latf.val("");
	 	     longf.val("");
	    	}
	     else
	    	 {
	    	 latf.val(latitude);
	 	     longf.val(longitude);
	    	 }
	     
	    
	    
	    } 
	 }); 

});

	$("#city").keydown(function(){
		$("#city").css("background-color", "yellow");
	});
	
	
});