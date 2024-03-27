let appName = "Game Station";

function deleteStudio(studioId){
    console.log("Deleting studio with ID:", studioId);

    $.ajax({
        type: 'POST',
        url: '/studios/delete-studio',
        data: {studioId: studioId},
        success: function(response){
            location.reload(); //refreshes page after success
        },
        error: function(xhr, status, error){
            console.error("Error deleting studio: ", error);
            //handle errors
        }
    });
}