function login(){

    var userName = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    var URL='/api/login';
	var jsonData = {"username": userName, "password": password};
    var json=JSON.parse(syncronJsonPostRequest(URL,JSON.stringify(jsonData)).response);

    var resultField = document.getElementById('result');
    resultField.innerHTML = json.msg;
}