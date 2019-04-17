var failed = 0;
window.onload = function () {
    var forms = $('form[name="login-form"]');
    forms[0].onsubmit = captureData;
};
captureData = function (event) {
    var values = {};
    $.each($('form[name="login-form"]').serializeArray(), function (i, field) {
        values[field.name] = field.value;
    });
    console.log(JSON.stringify(values));
    $.ajax({
        url: 'https://hidden-display-237214.appspot.com/rest/login/v1',
        type: "POST", // type of action POST || GET
        contentType: "application/json; charset=utf-8",
        dataType: 'json', // data type
        crossDomain: true,
        
        success: function (response) {
        	localStorage.setItem('failedAttempts', failed);
            localStorage.setItem('role', response.role);
            localStorage.setItem('username', response.username);
            var r = localStorage.getItem('role');
            var u = localStorage.getItem('username');
            alert("Login feito com sucesso.");
            if(r=="user") {
            window.location.href='https://hidden-display-237214.appspot.com/userpage.html';
            }
            if(r=='gbo') {
            	window.location.href='https://hidden-display-237214.appspot.com/gbopage.html';
            }
            if(r=='gs') {
            	window.location.href='https://hidden-display-237214.appspot.com/gspage.html';
            }
            if(r=='auser') {
            	window.location.href='https://hidden-display-237214.appspot.com/auserpage.html';
            }
        },
        error: function (response) {
        	failed++;
        	alert("Login falhado.")
        },
        data: JSON.stringify(values) // post data || get data
    });
    
    event.preventDefault();
};