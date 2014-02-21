

function submit() {
    //alert($("#id_textarea").val());
    //console.log($("#id_textarea").val());
    $.ajax({
	url: "api/v0/geotag",
	type: "POST",
	contentType: "text/plain",
	data: $("#id_textarea").val()
    })
	.done(function(data) {
	    //console.log(data);
	    $("#id_results").text(JSON.stringify(data));

	    // add results to map
	    var bounds = [];
	    $.each(data.resolvedLocations, (function (idx, val) {
		//console.log(val);
		bounds.push([val.geoname.latitude, val.geoname.longitude]);
		L.marker([val.geoname.latitude, val.geoname.longitude]).addTo(map)
		    .bindPopup(val.geoname.name);
	    }));
	    
	    // zoom to bounds of markers
	    map.fitBounds(bounds);
		
	});
}





