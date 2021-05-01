var radio = document.getElementsByName("team");

for (var i=0; i<radio.length; i++) {
    radio[i].onchange = testRadio;
}

function testRadio() {
    console.log(this.value);
}

document.getElementById("cl").onclick = checkRadio;

function checkRadio() {
    for (var i=0; i<radio.length; i++) {
        if(radio[i].checked) {
            alert(radio[i].value);
            break;
        }
    }
}
