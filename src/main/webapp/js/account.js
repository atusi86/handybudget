

function getAccountList() {

    var token = document.getElementById('token').value;

    var xmlHttp = createHttpRequest();
    var URL = '/api/AccountActions?operation=getlist';
    var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        hideAllScreens();
        var account_screen = document.getElementById('account_screen');
        account_screen.style.display = 'inline-block';


        var account_table_body = document.getElementById('account_table_body');
        account_table_body.innerHTML = '';

        var accountArray = json.accounts;


        for (var i = 0; i < accountArray.length; i++) {

            var tr = document.createElement('tr');

            var id = accountArray[i].id;
            var name = accountArray[i].name;
            var desc = accountArray[i].desc;
            var created = accountArray[i].created;

            var th = document.createElement('th');
            th.scope = 'row';
            th.innerHTML = i + 1;

            var tdId = document.createElement('td');
            tdId.innerHTML = id;
            tdId.style.display = 'none';
            var tdName = document.createElement('td');
            tdName.innerHTML = name;
            tdName.className = 'pointerCursor';
            var tdDesc = document.createElement('td');
            tdDesc.innerHTML = desc;
            tdDesc.className = 'pointerCursor';
            var tdCreated = document.createElement('td');
            tdCreated.innerHTML = created;
            tdCreated.className = 'pointerCursor';


            var iEdit = document.createElement('i');
            iEdit.id = "edit_" + id;
            iEdit.className = 'far fa-edit editButton';

            setOnclickForEdit(iEdit, id);

            var tdEdit = document.createElement('td');
            tdEdit.appendChild(iEdit);

            var iRemove = document.createElement('i');
            iRemove.id = "remove_" + id;
            iRemove.className = 'far fa-trash-alt removeButton';

            setOnclickForRemove(iRemove, id);

            var tdRemove = document.createElement('td');
            tdRemove.appendChild(iRemove);

            tr.appendChild(th);
            tr.appendChild(tdId);
            tr.appendChild(tdName);
            tr.appendChild(tdDesc);
            tr.appendChild(tdCreated);
            tr.appendChild(tdEdit);
            tr.appendChild(tdRemove);

            setOnclickForCell(tdName, id);
            setOnclickForCell(tdDesc, id);
            setOnclickForCell(tdCreated, id);

            account_table_body.appendChild(tr);
        }

        document.getElementById('loadingScreen').style.display = 'none';
    });


}

function setOnclickForEdit(object, id) {
    object.onclick = function () { showUpdateAccount(id); };
}

function setOnclickForRemove(object, id) {
    object.onclick = function () { deleteAccount(id); };
}

function setOnclickForCell(object, id) {
    object.onclick = function () { getTransactionList(id) }
}

/*function showAccountList() {

    getAccountList();

    hideAllScreens();

    var account_screen = document.getElementById('account_screen');
    account_screen.style.display = 'inline-block';

}*/


function showAddAccount() {

    var account_editor = document.getElementById('account_editor');
    account_editor.style.display = 'inline-block';

    document.getElementById('accountName').value = '';
    document.getElementById('accountDescription').value = '';

    var accountEditorButton = document.getElementById('account_editor_button');
    accountEditorButton.onclick = '';
    accountEditorButton.onclick = function () { addAccount(); };
    accountEditorButton.innerHTML = 'Add';

}


function showUpdateAccount(accountId) {

    var token = document.getElementById('token').value;

    var URL = "/api/AccountActions?operation=getdetails&id=" + accountId;

    var xmlHttp = createHttpRequest();
    var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        var account_editor = document.getElementById('account_editor');
        account_editor.style.display = 'inline-block';

        document.getElementById('accountName').value = json.name;
        document.getElementById('accountDescription').value = json.desc;

        var accountEditorButton = document.getElementById('account_editor_button');
        accountEditorButton.onclick = '';
        accountEditorButton.onclick = function () { updateAccount(accountId); };
        accountEditorButton.innerHTML = 'Update';

        document.getElementById('loadingScreen').style.display = 'none';
    });


}

function addAccount() {

    var token = document.getElementById('token').value;

    var account_editor = document.getElementById('account_editor');
    account_editor.style.display = 'none';

    var accountName = document.getElementById('accountName').value;
    var accountDescription = document.getElementById('accountDescription').value;

    var URL = "/api/AccountActions?operation=add";
    var jsonData = { "name": accountName.trim(), "desc": accountDescription.trim() };
    //var json = JSON.parse(syncronJsonPostRequest(URL, JSON.stringify(jsonData), token).response);

    var xmlHttp = createHttpRequest();
    var json = asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        getAccountList();




    });



}



function updateAccount(accountId) {

    var token = document.getElementById('token').value;

    var account_editor = document.getElementById('account_editor');
    account_editor.style.display = 'none';

    var accountName = document.getElementById('accountName').value;
    var accountDescription = document.getElementById('accountDescription').value;

    var URL = "/api/AccountActions?operation=update&id=" + accountId;
    var jsonData = { "name": accountName.trim(), "desc": accountDescription.trim() };
    //var json = JSON.parse(syncronJsonPostRequest(URL, JSON.stringify(jsonData), token).response);

    var xmlHttp = createHttpRequest();
    var json = asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);


        getAccountList();




    });


}

function deleteAccount(accountId) {

    var token = document.getElementById('token').value;

    var URL = '/api/AccountActions?operation=delete&id=' + accountId;
    //var json = JSON.parse(syncronJsonGetRequest(URL, token).response);

    var xmlHttp = createHttpRequest();
    var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        getAccountList();


    });


}

function hideAllScreens() {

    var account_screen = document.getElementById('account_screen');
    account_screen.style.display = 'none';
    var account_editor = document.getElementById('account_editor');
    account_editor.style.display = 'none';
    var account_screen = document.getElementById('transaction_screen');
    account_screen.style.display = 'none';
    var transaction_editor = document.getElementById('transaction_editor');
    transaction_editor.style.display = 'none';



}