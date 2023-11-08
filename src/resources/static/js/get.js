function get() {
    //let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("GET", '/api/code', false)
    //xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send();

    if (xhr.status == 200) {
        alert("Success!");

        var res = JSON.parse(xhr.response);
        console.log(res);
        console.log(res.date);
        console.log(res.code);

        document.getElementById('load_date').innerHTML = res.date;
        document.getElementById('code_snippet').innerHTML = res.code;
    }
}

(function() {
    get();
})();
