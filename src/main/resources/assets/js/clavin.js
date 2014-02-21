

function submit() {
    //alert($("#id_textarea").val());
    console.log($("#id_textarea").val());
    $.ajax({
	url: "api/v0/geotag",
	type: "POST",
	contentType: "text/plain",
	data: $("#id_textarea").val()
    })
	.done(function(data) {
	    //console.log(data);
	    $("#id_results").text(JSON.stringify(data));
	});
}



