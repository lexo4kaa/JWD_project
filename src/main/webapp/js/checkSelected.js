var form = document.getElementById('radio');

form.addEventListener('submit', function (event) {
    var flag = true;
    var teams = form.querySelectorAll('input[name="team"]');
    for (var team of teams) {
        if (team.checked) {
            flag = false;
        }
    }

    if(flag) {
        event.preventDefault()

        var errors = document.querySelectorAll('.error')
        for(var i = 0; i < errors.length; i++) {
            errors[i].remove()
        }
        var error = document.createElement('div')
        error.className = 'error'
        error.style.color = 'red'
        error.style.textAlign = 'left'
        var locale = form[name="locale"].value;
        if(locale === 'en_US') {
            error.innerHTML = 'choose one of teams'
        } else {
            error.innerHTML = 'выберите одну из команд'
        }
        form.appendChild(error)
    }
})