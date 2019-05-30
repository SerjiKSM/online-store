$(document).ready(function(){

    $('.modal').modal('show');

    //add more file components
    $('#addFile').click(function() {
        // var fileIndex = $('#fileTable tr').children().length - 1;
        var fileIndex = $('#fileTable tr').children().length;
        $('#fileTable').append(
            '<tr><td>'+
            '	<input type="file" name="files['+ fileIndex +']" />'+
            '</td></tr>');
    });

});

