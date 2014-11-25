$('#cpf').mask("99999999999");

$("#todos").click( function() {
    $("input[name='id']").each( function() {
        if($('#todos').is(':checked')){
            $(this).attr('checked',true);
        } else {
            $(this).attr('checked',false);
        }

    })
})
