/*
function showTransactionList(id) {

    document.getElementById('selected_account').value = id;

    hideAllScreens();

    var account_screen = document.getElementById('transaction_screen');
    account_screen.style.display = 'inline-block';


    getTransactionList();

}
*/

function showAddTransaction(type) {

    var token = document.getElementById('token').value;



    var transaction_editor_button = document.getElementById('transaction_editor_button');
    transaction_editor_button.innerHTML = 'Add transaction';
    transaction_editor_button.onclick = '';
    transaction_editor_button.onclick = function () { addTransaction(); };

    document.getElementById('transaction_typeList').innerHTML = '';
    document.getElementById('transaction_categoryList').innerHTML = '';
    document.getElementById('transaction_amount').value = '';
    document.getElementById('transaction_desc').value = '';
    document.getElementById('transaction_duedate').value = '';
    document.getElementById('transaction_additional_fields').style.display = 'none';


    var URL = "/api/TransactionTypeActions?operation=getlist";
    var xmlHttp = createHttpRequest();
    var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        var transaction_editor = document.getElementById('transaction_editor');
        transaction_editor.style.display = 'inline-block';

        var transaction_typeList = document.getElementById('transaction_typeList');
        transaction_typeList.innerHTML = '';

        var transaction_categoryList = document.getElementById('transaction_categoryList');
        transaction_categoryList.innerHTML = '';

        var types = json.types;

        var option = document.createElement('option');
        option.selected = true;
        option.disabled = true;
        option.hidden = true;
        option.innerHTML = 'Select type';
        transaction_typeList.appendChild(option);

        for (var i = 0; i < types.length; i++) {

            var option = document.createElement('option');
            var id = types[i].id;
            var name = types[i].name;


            if (name.toLowerCase().includes(type)) {
                option.id = 'type_' + id;
                option.innerHTML = name;
                transaction_typeList.appendChild(option);
            }
        }

        document.getElementById('loadingScreen').style.display = 'none';
    });

}

function getTransactionCategoryList() {


    var token = document.getElementById('token').value;
    var transaction_typeList = document.getElementById('transaction_typeList');

    var selectedType = transaction_typeList.options[transaction_typeList.selectedIndex].id;


    var URL = "/api/TransactionCategoryActions?operation=getlist&typeId=" + selectedType.replace('type_', '');
    var xmlHttp = createHttpRequest();
    var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        var transaction_categoryList = document.getElementById('transaction_categoryList');
        transaction_categoryList.innerHTML = '';

        var categories = json.categories;

        for (var i = 0; i < categories.length; i++) {

            var option = document.createElement('option');
            var id = categories[i].id;
            var name = categories[i].name;

            option.id = 'category_' + id;
            option.innerHTML = name;
            transaction_categoryList.appendChild(option);
        }

        var selectedTypeName = transaction_typeList.options[transaction_typeList.selectedIndex].innerHTML;

        var transaction_additional_fields = document.getElementById('transaction_additional_fields');

        if (selectedTypeName === 'Regular expense') {
            transaction_additional_fields.style.display = 'inline-block';
            document.getElementById('transaction_duedate').value = '';
        } else {
            transaction_additional_fields.style.display = 'none';
            document.getElementById('transaction_duedate').value = '';
        }


        document.getElementById('loadingScreen').style.display = 'none';
    });

}

function addTransaction() {

    var token = document.getElementById('token').value;
    var selectedAccount = document.getElementById('selected_account').value;

    var transaction_editor = document.getElementById('transaction_editor');
    transaction_editor.style.display = 'none';

    var transaction_categoryList = document.getElementById('transaction_categoryList');
    var selectedCategory = transaction_categoryList.options[transaction_categoryList.selectedIndex].id.replace('category_', '');

    var transaction_amount = document.getElementById('transaction_amount').value;
    var transaction_desc = document.getElementById('transaction_desc').value;
    var transaction_duedate = document.getElementById('transaction_duedate').value;

    var URL = "/api/TransactionActions?operation=add";
    var jsonData = { "accountId": selectedAccount, "categoryId": selectedCategory, "amount": transaction_amount, "desc": transaction_desc.trim(), "duedate": transaction_duedate };
    //var json = JSON.parse(syncronJsonPostRequest(URL, JSON.stringify(jsonData), token).response);


    var xmlHttp = createHttpRequest();
    var json = asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);



        getTransactionList();

    });


}

function getTransactionList(accountId, date) {

    if (accountId != undefined) {
        document.getElementById('selected_account').value = accountId;
    }




    var token = document.getElementById('token').value;
    var selectedAccount = document.getElementById('selected_account').value;

    var json;

    if (date == undefined) {
        var URL = '/api/TransactionActions?operation=getlist&accountId=' + selectedAccount;


        var xmlHttp = createHttpRequest();
        var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
            if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
                return;
            }
            var json = JSON.parse(xmlHttp.responseText);

            hideAllScreens();

            var account_screen = document.getElementById('transaction_screen');
            account_screen.style.display = 'inline-block';

            processTransactions(json);

            document.getElementById('loadingScreen').style.display = 'none';
        });

    } else {
        var URL = '/api/TransactionActions?operation=getlist&accountId=' + selectedAccount;
        var jsonData = { "date": date };


        var xmlHttp = createHttpRequest();
        var json = asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
            if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
                return;
            }
            var json = JSON.parse(xmlHttp.responseText);

            hideAllScreens();

            var account_screen = document.getElementById('transaction_screen');
            account_screen.style.display = 'inline-block';

            processTransactions(json);

            document.getElementById('loadingScreen').style.display = 'none';
        });

    }


}

function processTransactions(json) {


    var regularExpensesArray = json.regularExpenses;
    var regularIncomesArray = json.regularIncomes;
    var adHocTransactionsArray = json.adHocTransactions;

    var incomesSum = json.incomesSum;
    var expensesSum = json.expensesSum;
    var adhocSum = json.adhocSum;
    var balance = json.balance;

    var table_regular_income = document.getElementById('table_regular_income_body');
    table_regular_income.innerHTML = '';
    var table_regular_expense = document.getElementById('table_regular_expense_body');
    table_regular_expense.innerHTML = '';
    var table_adhoc_transactions = document.getElementById('table_adhoc_transactions_body');
    table_adhoc_transactions.innerHTML = '';


    var table_regular_income_foot = document.getElementById('table_regular_income_foot');
    table_regular_income_foot.innerHTML = '';
    var table_regular_expense_foot = document.getElementById('table_regular_expense_foot');
    table_regular_expense_foot.innerHTML = '';
    var table_adhoc_transactions_foot = document.getElementById('table_adhoc_transactions_foot');
    table_adhoc_transactions_foot.innerHTML = '';


    //REGULAR EXPENSES

    for (var i = 0; i < regularExpensesArray.length; i++) {

        var tr = document.createElement('tr');

        var id = regularExpensesArray[i].id;
        var category = regularExpensesArray[i].category;
        var desc = regularExpensesArray[i].desc;
        var amount = regularExpensesArray[i].amount;
        var createDate = regularExpensesArray[i].createDate;

        var dueDate = regularExpensesArray[i].dueDate;
        var isPaid = regularExpensesArray[i].isPaid;

        var th = document.createElement('th');
        th.scope = 'row';
        th.innerHTML = i + 1;

        var tdId = document.createElement('td');
        tdId.innerHTML = id;
        tdId.style.display = 'none';
        var tdCategory = document.createElement('td');
        tdCategory.innerHTML = category;
        var tdDesc = document.createElement('td');
        tdDesc.innerHTML = desc;
        var tdAmount = document.createElement('td');
        tdAmount.innerHTML = amount;
        var tdDueDate = document.createElement('td');
        tdDueDate.innerHTML = dueDate;
        var tdIsPaid = document.createElement('td');
        var isPaidCheckBox = document.createElement('input');
        isPaidCheckBox.type = 'checkbox';
        if (isPaid) {
            isPaidCheckBox.checked = true;
        }
        tdIsPaid.appendChild(isPaidCheckBox);


        var iEdit = document.createElement('i');
        iEdit.id = "edit_" + id;
        iEdit.className = 'far fa-edit editButton';

        setOnclickForTransactionEdit(iEdit, id);

        var tdEdit = document.createElement('td');
        tdEdit.appendChild(iEdit);

        var iRemove = document.createElement('i');
        iRemove.id = "remove_" + id;
        iRemove.className = 'far fa-trash-alt removeButton';

        setOnclickForTransactionRemove(iRemove, id);

        var tdRemove = document.createElement('td');
        tdRemove.appendChild(iRemove);

        tr.appendChild(th);
        tr.appendChild(tdId);
        tr.appendChild(tdCategory);
        tr.appendChild(tdDesc);
        tr.appendChild(tdDueDate);
        tr.appendChild(tdAmount);
        tr.appendChild(tdIsPaid);
        tr.appendChild(tdEdit);
        tr.appendChild(tdRemove);

        table_regular_expense.appendChild(tr);
    }

    //EXPENSES SUM
    var tr = document.createElement('tr')
    tr.className = 'sumRow';
    var th = document.createElement('th');
    th.scope = 'row';
    th.innerHTML = '&#931;';
    tr.appendChild(th);
    for (var i = 0; i < 3; i++) {
        var td = document.createElement('td');
        tr.appendChild(td);
    }
    var tdSum = document.createElement('td');
    tdSum.innerHTML = expensesSum;
    tr.appendChild(tdSum);
    for (var i = 0; i < 3; i++) {
        var td = document.createElement('td');
        tr.appendChild(td);
    }
    table_regular_expense_foot.appendChild(tr);


    //REGULAR INCOMES

    for (var i = 0; i < regularIncomesArray.length; i++) {

        var tr = document.createElement('tr');

        var id = regularIncomesArray[i].id;
        var category = regularIncomesArray[i].category;
        var desc = regularIncomesArray[i].desc;
        var amount = regularIncomesArray[i].amount;

        var th = document.createElement('th');
        th.scope = 'row';
        th.innerHTML = i + 1;

        var tdId = document.createElement('td');
        tdId.innerHTML = id;
        tdId.style.display = 'none';
        var tdCategory = document.createElement('td');
        tdCategory.innerHTML = category;
        var tdDesc = document.createElement('td');
        tdDesc.innerHTML = desc;
        var tdAmount = document.createElement('td');
        tdAmount.innerHTML = amount;


        var iEdit = document.createElement('i');
        iEdit.id = "edit_" + id;
        iEdit.className = 'far fa-edit editButton';

        setOnclickForTransactionEdit(iEdit, id);

        var tdEdit = document.createElement('td');
        tdEdit.appendChild(iEdit);

        var iRemove = document.createElement('i');
        iRemove.id = "remove_" + id;
        iRemove.className = 'far fa-trash-alt removeButton';

        setOnclickForTransactionRemove(iRemove, id);

        var tdRemove = document.createElement('td');
        tdRemove.appendChild(iRemove);

        tr.appendChild(th);
        tr.appendChild(tdId);
        tr.appendChild(tdCategory);
        tr.appendChild(tdDesc);
        tr.appendChild(tdAmount);
        tr.appendChild(tdEdit);
        tr.appendChild(tdRemove);

        table_regular_income.appendChild(tr);
    }

    //INCOMES SUM
    var tr = document.createElement('tr')
    tr.className = 'sumRow';
    var th = document.createElement('th');
    th.scope = 'row';
    th.innerHTML = '&#931;';
    tr.appendChild(th);
    for (var i = 0; i < 2; i++) {
        var td = document.createElement('td');
        tr.appendChild(td);
    }
    var tdSum = document.createElement('td');
    tdSum.innerHTML = incomesSum;
    tr.appendChild(tdSum);
    for (var i = 0; i < 2; i++) {
        var td = document.createElement('td');
        tr.appendChild(td);
    }
    table_regular_income_foot.appendChild(tr);


    //AD-HOC TRANSACTIONS


    for (var i = 0; i < adHocTransactionsArray.length; i++) {

        var tr = document.createElement('tr');

        var id = adHocTransactionsArray[i].id;
        var category = adHocTransactionsArray[i].category;
        var desc = adHocTransactionsArray[i].desc;
        var createDate = adHocTransactionsArray[i].createDate;
        var amount = adHocTransactionsArray[i].amount;

        var th = document.createElement('th');
        th.scope = 'row';
        th.innerHTML = i + 1;

        var tdId = document.createElement('td');
        tdId.innerHTML = id;
        tdId.style.display = 'none';
        var tdCategory = document.createElement('td');
        tdCategory.innerHTML = category;
        var tdDesc = document.createElement('td');
        tdDesc.innerHTML = desc;
        var tdCreateDate = document.createElement('td');
        tdCreateDate.innerHTML = createDate;
        var tdAmount = document.createElement('td');
        tdAmount.innerHTML = amount;


        var iEdit = document.createElement('i');
        iEdit.id = "edit_" + id;
        iEdit.className = 'far fa-edit editButton';

        setOnclickForTransactionEdit(iEdit, id);

        var tdEdit = document.createElement('td');
        tdEdit.appendChild(iEdit);

        var iRemove = document.createElement('i');
        iRemove.id = "remove_" + id;
        iRemove.className = 'far fa-trash-alt removeButton';

        setOnclickForTransactionRemove(iRemove, id);

        var tdRemove = document.createElement('td');
        tdRemove.appendChild(iRemove);

        tr.appendChild(th);
        tr.appendChild(tdId);
        tr.appendChild(tdCategory);
        tr.appendChild(tdDesc);
        tr.appendChild(tdCreateDate);
        tr.appendChild(tdAmount);
        tr.appendChild(tdEdit);
        tr.appendChild(tdRemove);

        table_adhoc_transactions.appendChild(tr);
    }

    //ADHOC SUM
    var tr = document.createElement('tr')
    tr.className = 'sumRow';
    var th = document.createElement('th');
    th.scope = 'row';
    th.innerHTML = '&#931;';
    tr.appendChild(th);
    for (var i = 0; i < 3; i++) {
        var td = document.createElement('td');
        tr.appendChild(td);
    }
    var tdSum = document.createElement('td');
    tdSum.innerHTML = adhocSum;
    tr.appendChild(tdSum);
    for (var i = 0; i < 2; i++) {
        var td = document.createElement('td');
        tr.appendChild(td);
    }
    table_adhoc_transactions_foot.appendChild(tr);

    //BALANCE

    var balanceText = document.getElementById('balance');
    balanceText.innerHTML = 'Balance: ' + balance;
    balanceText.className = 'balanceAmount';
    if (balance >= 0) {
        balanceText.style.color = 'green';
    } else {
        balanceText.style.color = 'red';
    }

}

function setOnclickForTransactionEdit(object, id) {
    object.onclick = function () { showUpdateTransaction(id); };

}

function setOnclickForTransactionRemove(object, id) {
    object.onclick = function () { deleteTransaction(id); };
}

function deleteTransaction(id) {

    var token = document.getElementById('token').value;

    var URL = '/api/TransactionActions?operation=delete&id=' + id;
    //var json = JSON.parse(syncronJsonGetRequest(URL, token).response);

    var xmlHttp = createHttpRequest();
    var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        getTransactionList();

    });


}

function showUpdateTransaction(id) {

    var token = document.getElementById('token').value;



    var URL = "/api/TransactionActions?operation=getdetails&id=" + id;
    //var json = JSON.parse(syncronJsonGetRequest(URL, token).response);

    var xmlHttp = createHttpRequest();
    asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        var typeId = json.typeId;
        var categoryId = json.categoryId;
        var dueDate = json.dueDate;
        var typeName = json.typeName;


        //Transaction type
        var transaction_typeList = document.getElementById('transaction_typeList');
        transaction_typeList.innerHTML = '';
        var type;

        if (typeName.toLowerCase().includes('ad-hoc')) {
            type = 'ad-hoc';
        } else {
            type = 'regular';
        }

        var URL2 = "/api/TransactionTypeActions?operation=getlist";
        //var json2 = JSON.parse(syncronJsonGetRequest(URL, token).response);

        var xmlHttp2 = createHttpRequest();
        asyncronJsonGetRequest(xmlHttp2, URL2, token, function () {
            if (xmlHttp2 == null || xmlHttp2.readyState != 4 || xmlHttp2.status != 200) {
                return;
            }
            var json2 = JSON.parse(xmlHttp2.responseText);

            var types = json2.types;

            for (var i = 0; i < types.length; i++) {

                var option = document.createElement('option');
                var id2 = types[i].id;
                var name = types[i].name;


                if (name.toLowerCase().includes(type)) {
                    option.id = 'type_' + id2;
                    option.innerHTML = name;
                    if (name == typeName) {
                        option.selected = true;
                    }
                    transaction_typeList.appendChild(option);
                }
            }

            //Transaction category
            var transaction_categoryList = document.getElementById('transaction_categoryList');
            transaction_categoryList.innerHTML = '';

            var URL3 = "/api/TransactionCategoryActions?operation=getlist&typeId=" + typeId;
            //var json3 = JSON.parse(syncronJsonGetRequest(URL, token).response);

            var xmlHttp3 = createHttpRequest();
            asyncronJsonGetRequest(xmlHttp3, URL3, token, function () {
                if (xmlHttp3 == null || xmlHttp3.readyState != 4 || xmlHttp3.status != 200) {
                    return;
                }
                var json3 = JSON.parse(xmlHttp3.responseText);

                var categories = json3.categories;

                for (var i = 0; i < categories.length; i++) {

                    var option = document.createElement('option');
                    var id3 = categories[i].id;
                    var name = categories[i].name;
                    option.id = 'category_' + id3;
                    option.innerHTML = name;
                    if (id3 == categoryId) {
                        option.selected = true;
                    }
                    transaction_categoryList.appendChild(option);
                }



                document.getElementById('transaction_amount').value = json.amount;
                document.getElementById('transaction_desc').value = json.desc;

                if (dueDate != undefined) {
                    document.getElementById('transaction_additional_fields').style.display = 'inline-block';
                    document.getElementById('transaction_duedate').value = json.dueDate;
                } else {
                    document.getElementById('transaction_additional_fields').style.display = 'none';
                    document.getElementById('transaction_duedate').value = '';
                }


                var transaction_editor_button = document.getElementById('transaction_editor_button');
                transaction_editor_button.onclick = '';
                transaction_editor_button.onclick = function () { updateTransaction(id); };
                transaction_editor_button.innerHTML = 'Update';

                var transaction_editor = document.getElementById('transaction_editor');
                transaction_editor.style.display = 'inline-block';

                document.getElementById('loadingScreen').style.display = 'none';
            });

        });

    });


}

function updateTransaction(id) {

    var token = document.getElementById('token').value;

    var transaction_editor = document.getElementById('transaction_editor');
    transaction_editor.style.display = 'none';

    var transaction_categoryList = document.getElementById('transaction_categoryList');
    var selectedCategory = transaction_categoryList.options[transaction_categoryList.selectedIndex].id.replace('category_', '');

    var transaction_amount = document.getElementById('transaction_amount').value;
    var transaction_desc = document.getElementById('transaction_desc').value;
    var transaction_duedate = document.getElementById('transaction_duedate').value;

    var URL = "/api/TransactionActions?operation=update&id=" + id;
    var jsonData = { "categoryId": selectedCategory, "amount": transaction_amount, "desc": transaction_desc.trim(), "duedate": transaction_duedate };
    //var json = JSON.parse(syncronJsonPostRequest(URL, JSON.stringify(jsonData), token).response);

    var xmlHttp = createHttpRequest();
    var json = asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        getTransactionList();



    });



}