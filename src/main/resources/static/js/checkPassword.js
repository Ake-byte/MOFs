$('#password, #confirm_password').on('keyup', function () {
  if ($('#password').val() == $('#confirm_password').val()) {
    $('#message').html('Las contrase&ntilde;as coinciden').css('color', 'green');
  } else 
    $('#message').html('Las NO contrase&ntilde;as coinciden').css('color', 'red');
});