var form = document.getElementById('method_of_receiving');
var address = document.getElementById('address');

form.addEventListener('change', function () {
    if(form.value === "delivery") {
        address.style.visibility = "visible"
        address.required = true

        var addressPrompt = document.createElement('h4')
        addressPrompt.className = 'addressPrompt'
        addressPrompt.style.textAlign = 'left'
        addressPrompt.style.marginBottom = '1px'
        addressPrompt.innerHTML = 'Enter address'
        address.parentElement.insertBefore(addressPrompt, address)
    } else {
        address.style.visibility = "hidden"
        address.required = false

        document.querySelector('.addressPrompt').remove()
    }
})