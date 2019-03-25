function showSearchScreen() {

    var input = document.getElementById('searchFilterInput_name');
    input.addEventListener("keyup", function () {
        searchScreenFilter('searchFilterInput_name', 'search_table_body');
    });
    input.addEventListener("keyup", function () {
        highlightTransaction('searchFilterInput_name', '#search_table_body');
    });

    var input2 = document.getElementById("searchFilterInput_desc");
    input2.addEventListener("keyup", function () {
        searchScreenFilter('searchFilterInput_desc', 'search_table_body');
    });
    input2.addEventListener("keyup", function () {
        highlightTransaction('searchFilterInput_desc', '#search_table_body');
    });



    var token = document.getElementById('token').value;
    hideAllScreens();

    var startDate = document.getElementById('searchScreen_startDate').value;
    var endDate = document.getElementById('searchScreen_endDate').value;

    var URL = '/api/TransactionActions?operation=getalltransactions';
    var jsonData;

    if (startDate != '' && endDate != '') {
        jsonData = { "startDate": startDate, "endDate": endDate };
    }


    var xmlHttp = createHttpRequest();
    asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        hideAllScreens();

        var search_screen = document.getElementById('search_screen');
        search_screen.style.display = 'inline-block';

        var search_table_body = document.getElementById('search_table_body');
        search_table_body.innerHTML = '';

        var transactionArray = json.transactions;

        for (var i = 0; i < transactionArray.length; i++) {

            var tr = document.createElement('tr');

            var id = transactionArray[i].id;
            var categoryName = transactionArray[i].categoryName;
            var typeName = transactionArray[i].typeName
            var desc = transactionArray[i].desc;
            var amount = transactionArray[i].amount;
            var createDate = transactionArray[i].createDate;
            var accountName = transactionArray[i].accountName;

            var dueDate = transactionArray[i].dueDate;
            var isPaid = transactionArray[i].isPaid;

            var th = document.createElement('th');
            th.scope = 'row';
            th.innerHTML = i + 1;

            var tdId = document.createElement('td');
            tdId.innerHTML = id;
            tdId.style.display = 'none';
            var tdAccountName = document.createElement('td');
            tdAccountName.innerHTML = accountName;
            var tdCategory = document.createElement('td');
            tdCategory.innerHTML = categoryName;
            var tdDesc = document.createElement('td');
            tdDesc.innerHTML = desc;
            var tdCreateDate = document.createElement('td');
            tdCreateDate.innerHTML = createDate;
            var tdTypeName = document.createElement('td');
            tdTypeName.innerHTML = typeName;
            var tdAmount = document.createElement('td');
            tdAmount.innerHTML = amount;
            var tdDueDate = document.createElement('td');
            if (dueDate != undefined) {
                tdDueDate.innerHTML = dueDate;
            } else {
                tdDueDate.innerHTML = '-';
            }
            var tdIsPaid = document.createElement('td');
            var isPaidCheckBox = document.createElement('input');
            isPaidCheckBox.type = 'checkbox';
            setOnChangeForIsPaid(isPaidCheckBox, id, 'search_screen');
            if (isPaid) {
                isPaidCheckBox.checked = true;
            }
            tdIsPaid.appendChild(isPaidCheckBox);


            var iEdit = document.createElement('i');
            iEdit.id = "edit_" + id;
            iEdit.className = 'far fa-edit editButton';

            setOnclickForTransactionEdit(iEdit, id, 'search_screen');

            var tdEdit = document.createElement('td');
            tdEdit.appendChild(iEdit);

            var iRemove = document.createElement('i');
            iRemove.id = "remove_" + id;
            iRemove.className = 'far fa-trash-alt removeButton';

            setOnclickForTransactionRemove(iRemove, id, 'search_screen');

            var tdRemove = document.createElement('td');
            tdRemove.appendChild(iRemove);

            tr.appendChild(th);
            tr.appendChild(tdId);
            tr.appendChild(tdAccountName);
            tr.appendChild(tdTypeName);
            tr.appendChild(tdCategory);
            tr.appendChild(tdDesc);
            tr.appendChild(tdCreateDate);
            if (typeName.toLowerCase() === 'regular expense') {
                tr.appendChild(tdDueDate);
            } else {
                var placeholder = document.createElement('td');
                placeholder.innerHTML = '-';
                tr.appendChild(placeholder);
            }
            tr.appendChild(tdAmount);
            if (typeName.toLowerCase() === 'regular expense' || typeName.toLowerCase() === 'regular expense') {
                tr.appendChild(tdIsPaid);
            } else {
                var placeholder = document.createElement('td');
                placeholder.innerHTML = '-';
                tr.appendChild(placeholder);
            }
            tr.appendChild(tdEdit);
            tr.appendChild(tdRemove);

            search_table_body.appendChild(tr);
        }



        document.getElementById('loadingScreen').style.display = 'none';
    });

}

function searchScreenFilter(inputId, tableId) {
    // Declare variables 
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById(inputId);
    filter = input.value.toUpperCase();
    table = document.getElementById(tableId);
    tr = table.getElementsByTagName("tr");

    var searchFilterInput_desc = document.getElementById('searchFilterInput_desc').value;
    var searchFilterInput_name = document.getElementById('searchFilterInput_name').value;

    if(searchFilterInput_name != ''){
        document.getElementById('searchFilterInput_desc').disabled = true;
    }else{
        document.getElementById('searchFilterInput_desc').disabled = false;
    }

    if(searchFilterInput_desc != ''){
        document.getElementById('searchFilterInput_name').disabled = true;
    }else{
        document.getElementById('searchFilterInput_name').disabled = false;
    }


    var columnNo;
    if (inputId === 'searchFilterInput_name') {
        columnNo = 3;
    } else {
        columnNo = 4;
    }

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        
            td = tr[i].getElementsByTagName("td")[columnNo];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        
    }
}

function highlightTransaction(inputId, tableId) {

    var input = document.getElementById(inputId);
    var markInstance = new Mark(document.querySelectorAll(tableId));
    markInstance.unmark();
    markInstance.mark(input.value);


}

function resetSearchScreen() {

    document.getElementById('searchScreen_startDate').value = '';
    document.getElementById('searchScreen_endDate').value = '';
    document.getElementById('searchFilterInput_name').value = '';
    document.getElementById('searchFilterInput_desc').value = '';

    showSearchScreen();

}