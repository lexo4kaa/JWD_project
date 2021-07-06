var password = document.getElementById('password')
var password2 = document.getElementById('password2')

document.addEventListener('submit', function (event) {
    if(password.value != password2.value) {
        event.preventDefault()

        var errors = document.querySelectorAll('.error')
        for(var i = 0; i < errors.length; i++) {
            errors[i].remove()
        }

        var error = document.createElement('div')
        error.className = 'error'
        error.style.color = 'red'
        error.style.textAlign = 'center'
        error.innerHTML = 'password doesn\'t match' /*todo russian lang*/
        password2.parentElement.insertBefore(error, password2)
    }
})