function getAccountList() {

    var token = document.getElementById('token').value;

    var URL = '/api/getAccountList';
    var json=JSON.parse(syncronJsonGetRequest(URL, token).response);

    var account_table_body = document.getElementById('account_table_body');

    var accountArray = json.accounts;

    for(var i = 0; i < accountArray.length; i++){
        
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
        var tdDesc = document.createElement('td');
        tdDesc.innerHTML = desc;
        var tdCreated = document.createElement('td');
        tdCreated.innerHTML = created;

        tr.append(th);
        tr.append(tdId);
        tr.append(tdName);
        tr.append(tdDesc);
        tr.append(tdCreated);

        account_table_body.append(tr);
    }
}