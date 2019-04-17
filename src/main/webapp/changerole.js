window.onload = function () {
    var forms = $('form[name="change_role-form"]');
    forms[0].onsubmit = captureData;
};

captureData = function (event) {
    var values = {};
    $.each($('form[name="change_role-form"]').serializeArray(), function (i, field) {
        values[field.name] = field.value;
    });
    
    console.log(JSON.stringify(values));
    $.ajax({
        url: 'https://hidden-display-237214.appspot.com/rest/update/role',
        type: "PUT", // type of action POST || GET
        contentType: "application/json; charset=utf-8",
        dataType: 'json', // data type
        crossDomain: true,
        
        success: function (response) {
            console.log(response);
        },
        error: function (response) {
            console.log(response);
            console.log("deu certo");
            alert("Role de utilizador alterado para AUSER");
            	window.location.href='https://hidden-display-237214.appspot.com/gbopage.html'
        },
        data: JSON.stringify(values) // post data || get data
    });
    
    event.preventDefault();
};