function checkQuantity(object)
{
    var val = parseInt(object.value)
    var min = parseInt(object.min)
    var max = parseInt(object.max)

    if (val > max) {
        object.value = max
    } else if (val < min) {
        object.value = min
    }

    object.form.submit()
}