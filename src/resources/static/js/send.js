function send(e) {
    let object = {
        "views_restriction": document.getElementById("views_restriction").value,
        "time_restriction": document.getElementById("time_restriction").value,
        "code": document.getElementById("code_snippet").value
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
      alert("Success!");
    }
}