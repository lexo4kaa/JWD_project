var form = document.getElementById('form')
var name_ = form[name="name"]
var surname = form[name="surname"]
var login = form[name="nickname"]
var dob = form[name="dob"]
var email = form[name="email"]
var phone = form[name="phone"]
var password = form[name="password"]
var password2 = form[name="password2"]

form.addEventListener('submit', function ()  {
    sessionStorage.setItem("reg_name", name_.value)
    sessionStorage.setItem("reg_surname", surname.value)
    sessionStorage.setItem("reg_login", login.value)
    sessionStorage.setItem("reg_dob", dob.value)
    sessionStorage.setItem("reg_email", email.value)
    sessionStorage.setItem("reg_phone", phone.value)
    sessionStorage.setItem("reg_password", password.value)
    sessionStorage.setItem("reg_password2", password2.value)
})

let storedName = sessionStorage.getItem("reg_name")
let storedSurname = sessionStorage.getItem("reg_surname")
let storedLogin = sessionStorage.getItem("reg_login")
let storedDob = sessionStorage.getItem("reg_dob")
let storedEmail = sessionStorage.getItem("reg_email")
let storedPhone = sessionStorage.getItem("reg_phone")
let storedPassword = sessionStorage.getItem("reg_password")
let storedPassword2 = sessionStorage.getItem("reg_password2")

if(storedName !== null) {
    name_.value = storedName
}

if(storedSurname !== null) {
    surname.value = storedSurname
}

if(storedLogin !== null) {
    login.value = storedLogin
}

if(storedDob !== null) {
    dob.value = storedDob
}

if(storedEmail !== null) {
    email.value = storedEmail
}

if(storedPhone !== null) {
    phone.value = storedPhone
}

if(storedPassword !== null) {
    password.value = storedPassword
}

if(storedPassword2 !== null) {
    password2.value = storedPassword2
}