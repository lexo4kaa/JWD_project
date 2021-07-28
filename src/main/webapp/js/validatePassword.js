var form = document.getElementById('form')
var password = form[name="password"]
var password2 = form[name="password2"]

form.addEventListener('submit', function (event) {
    if(password.value !== password2.value) {
        event.preventDefault()

        var errors = document.querySelectorAll('.error')
        for(var i = 0; i < errors.length; i++) {
            errors[i].remove()
        }
        var error = document.createElement('div')
        error.className = 'error'
        error.style.color = 'red'
        error.style.textAlign = 'center'
        var locale = form[name="locale"].value;
        if(locale === 'ru_RU') {
            error.innerHTML = 'пароли не совпадают'
        } else {
            error.innerHTML = 'password doesn\'t match'
        }
        password2.parentElement.insertBefore(error, password2)
    }
})