window.onload = function () {
    var forms = $('form[name="gsupdate-form"]');
    forms[0].onsubmit = captureData;
};

captureData = function (event) {
    var values = {};
    $.each($('form[name="gsupdate-form"]').serializeArray(), function (i, field) {
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
            console.log(response);
            alert("Nova conta criada com sucesso.");
            window.location.href='https://hidden-display-237214.appspot.com/gspage.html';
        },
        data: JSON.stringify(values) // post data || get data
    });
    
    event.preventDefault();
};