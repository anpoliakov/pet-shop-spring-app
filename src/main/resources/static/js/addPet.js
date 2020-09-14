let sub_back = document.getElementById("submit_back");
let sub_send = document.getElementById("submit_send");

submit_back.onclick = () => {
    window.location.href = 'index.html';
}

sub_send.onclick = () => {
    let data_pet = {};
    let fieldsForm = document.getElementsByClassName("field");
    let fieldsRadio = document.getElementsByClassName("fieldRadio");

    for(let i=0; i<fieldsForm.length; i++){
        let name = fieldsForm[i].name;
        let value = fieldsForm[i].value;
        data_pet[name] = value;
    }

    for(let i=0; i<fieldsRadio.length; i++){
        if(fieldsRadio[i].checked){
            let name = fieldsRadio[i].name;
            let value = fieldsRadio[i].value;
            data_pet[name] = value;
            break;
        }
    }

    let jsonDataPet = JSON.stringify(data_pet);
    console.log(jsonDataPet);
    fetch('/pet',{method: 'POST', headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify(jsonDataPet)
                        }).then(result => result.json().then(console.log));
}