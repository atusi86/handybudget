function showMeasuringScreen() {

    var token = document.getElementById('token').value;
    hideAllScreens();

    var measuring_screen = document.getElementById('measuring_screen');
    measuring_screen.style.display = 'inline-block';


    var measuring_table_body = document.getElementById('measuring_table_body');
    measuring_table_body.innerHTML = '';

    /*
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
    */

}