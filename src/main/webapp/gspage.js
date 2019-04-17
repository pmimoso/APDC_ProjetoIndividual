window.onload = function () {
    var forms = $('form[name="gs-form"]');
    forms[0].onsubmit = captureData;
};

captureData = function (event) {
    var values = {};
    //values["role"] = "user"; //user_type
    $.each($('form[name="gs-form"]').serializeArray(), function (i, field) {
        values[field.name] = field.value;
    });
    console.log(JSON.stringify(values));
    $.ajax({
        url: 'https://hidden-display-237214.appspot.com/rest/register/gbo',
        type: "POST", // type of action POST || GET
        contentType: "application/json; charset=utf-8",
        dataType: 'json', // data type
        crossDomain: true,
        
        success: function (response) {
            console.log(response);
        },
        error: function (response) {
        		alert("Registo efetuado com sucesso.");
        },
        data: JSON.stringify(values) // post data || get data
    });
    
    event.preventDefault();
};