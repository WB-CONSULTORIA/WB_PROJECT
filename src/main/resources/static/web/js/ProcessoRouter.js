
for (const iterator of document.getElementsByTagName("form")) {
    if(iterator.className!="ProcessoForm" || !iterator.className){
        continue
    }
    iterator.addEventListener("submit",(e)=>{
        e.preventDefault();
        let data = {}
        const inputNodes = e.target.children
        console.log(e)
        for (const input of inputNodes) {
            if(input.id!="submit"){
                data[input.id]=input.value
            }
        }
        Redirect(data,data._method ?? e.target.method)
    })
}
function Redirect(data,method) {
    const myHeaders = new Headers();
    let id;
    myHeaders.append("Content-Type", "application/json");
    let requestOptions = {
        method: method,
        headers: myHeaders,
        redirect: "follow"
        };
    if(method!='get'){
        const raw = JSON.stringify(data);
        requestOptions["body"]=raw
    }
    switch(method){
        case 'get':
            id= '/most-recent'
        break;
        case 'post':
        if(data.numeroProcesso){
            id=''
        }
        break;
    }

        fetch(`http://localhost:8080/processos${id}`, requestOptions)
            .then((response) => response.text())
            .then((result) => console.log(result))
            .catch((error) => console.error(error));
}