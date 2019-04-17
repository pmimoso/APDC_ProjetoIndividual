window.onload = function () {
    var forms = $('form[name="delete-form"]');
    forms[0].onsubmit = captureData;
};
var uName = localStorage.getItem('username');
captureData = function (event) {
    var values = {};
    $.each($('form[name="delete-form"]').serializeArray(), function (i, field) {
        values[field.name] = field.value;
    });
    values["username"] = uName;
    console.log(JSON.stringify(values));
    $.ajax({
        url: 'https://hidden-display-237214.appspot.com/rest/delete/',
        type: "DELETE", // type of action POST || GET
        contentType: "application/json; charset=utf-8",
        dataType: 'json', // data type
        crossDomain: true,
        
        success: function (response) {
            console.log(response);
        },
        error: function (response) {
            console.log(response);
            window.location.href='https://hidden-display-237214.appspot.com/loginpage.html';
        },
        data: JSON.stringify(values) // post data || get data
    });
    
    event.preventDefault();
};