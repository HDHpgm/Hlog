function loginRequest() {
    $.ajax({
        type: 'POST',
        url: '/login-proc',
        contentType: "application/json",
        data: JSON.stringify({
            username:$('#username').val(),
            password:$('#password').val()
        }),
        success: function (response) {
        },
        error: function (request, error) {
            var text = JSON.parse(request.responseText);
            alert(text.message);
            console.log("code:"+request.status+"\n"+ text);
        }
    })
}