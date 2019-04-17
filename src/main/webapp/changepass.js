window.onload = function () {
    var forms = $('form[name="change_pass-form"]');
    forms[0].onsubmit = captureData;
};

captureData = function (event) {
    var values = {};
    $.each($('form[name="change_pass-form"]').serializeArray(), function (i, field) {
        values[field.name] = field.value;
    });
    
    console.log(JSON.stringify(values));
    $.ajax({
        url: 'https://hidden-display-237214.appspot.com/rest/update/password',
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
            alert("Palavra-passe alterada com sucesso!");
            	window.location.href='https://hidden-display-237214.appspot.com/userpage.html'
        },
        data: JSON.stringify(values) // post data || get data
    });
    
    event.preventDefault();
};