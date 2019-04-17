window.onload = function () {
    var forms = $('form[name="update-form"]');
    forms[0].onsubmit = captureData;
};
var uName = localStorage.getItem('username');
var rName = localStorage.getItem('role');
//console.log(uName);
captureData = function (event) {
    var values = {};
    $.each($('form[name="update-form"]').serializeArray(), function (i, field) {
        values[field.name] = field.value;
    });
    values["username"] = uName;
    values["role"] = rName;
    console.log(JSON.stringify(values));
    $.ajax({
        url: 'https://hidden-display-237214.appspot.com/rest/update/',
        type: "PUT", // type of action POST || GET
        contentType: "application/json; charset=utf-8",
        dataType: 'json', // data type
        crossDomain: true,
        
        success: function (response) {
            console.log(response);
        },
        error: function (response) {
            console.log(response);
            alert("Atualização feita com sucesso.");
            window.location.href='https://hidden-display-237214.appspot.com/userpage.html';
        },
        data: JSON.stringify(values) // post data || get data
    });
    
    event.preventDefault();
};