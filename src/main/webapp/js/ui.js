$('#cpf').mask("99999999999");
$('#inicial').mask("99/99/9999");
$('#fim').mask("99/99/9999");

$("#todos").click( function() {
    $("input[name='id']").each( function() {
        if($('#todos').is(':checked')){
            $(this).attr('checked',true);
        } else {
            $(this).attr('checked',false);
        }
    })
});

$( "#inicial" ).datepicker({
    dateFormat: "dd/mm/yy"
});

$( "#fim" ).datepicker({
    dateFormat: "dd/mm/yy"
});
