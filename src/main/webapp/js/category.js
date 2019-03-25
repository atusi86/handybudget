function showTransactionCategories() {

    var input = document.getElementById("categoryFilterInput");
    input.addEventListener("keyup", function () {
        categoryFilter();
    });
    input.addEventListener("keyup", function () {
        highlightCategory();
    });

    var token = document.getElementById('token').value;

    var xmlHttp = createHttpRequest();
    var URL = '/api/TransactionCategoryActions?operation=getallcategories';
    asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        hideAllScreens();
        var category_screen = document.getElementById('category_screen');
        category_screen.style.display = 'inline-block';


        var category_table_body = document.getElementById('category_table_body');
        category_table_body.innerHTML = '';

        var categoryArray = json.categories;


        for (var i = 0; i < categoryArray.length; i++) {

            var tr = document.createElement('tr');

            var id = categoryArray[i].id;
            var type = categoryArray[i].type;
            var name = categoryArray[i].name;
            var desc = categoryArray[i].desc;

            var th = document.createElement('th');
            th.scope = 'row';
            th.innerHTML = i + 1;


            var tdType = document.createElement('td');
            tdType.innerHTML = type;
            var tdId = document.createElement('td');
            tdId.innerHTML = id;
            tdId.style.display = 'none';
            var tdName = document.createElement('td');
            tdName.innerHTML = name;
            var tdDesc = document.createElement('td');
            tdDesc.innerHTML = desc;


            var iEdit = document.createElement('i');
            iEdit.id = "edit_" + id;
            iEdit.className = 'far fa-edit editButton';

            setOnclickForCategoryEdit(iEdit, id);

            var tdEdit = document.createElement('td');
            tdEdit.appendChild(iEdit);

            var iRemove = document.createElement('i');
            iRemove.id = "remove_" + id;
            iRemove.className = 'far fa-trash-alt removeButton';

            setOnclickForCategoryRemove(iRemove, id);

            var tdRemove = document.createElement('td');
            tdRemove.appendChild(iRemove);

            tr.appendChild(th);
            tr.appendChild(tdId);
            tr.appendChild(tdType);
            tr.appendChild(tdName);
            tr.appendChild(tdDesc);
            tr.appendChild(tdEdit);
            tr.appendChild(tdRemove);


            category_table_body.appendChild(tr);
        }

        document.getElementById('loadingScreen').style.display = 'none';
    });


}


function showAddCategory(typeId) {

    var token = document.getElementById('token').value;


    document.getElementById('category_name').value = '';
    document.getElementById('category_desc').value = '';

    var category_editor_button = document.getElementById('category_editor_button');
    category_editor_button.onclick = '';
    category_editor_button.onclick = function () { addCategory(typeId); };
    category_editor_button.innerHTML = 'Add';


    var URL = "/api/TransactionTypeActions?operation=getlist";
    var xmlHttp = createHttpRequest();
    asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        var category_editor = document.getElementById('category_editor');
        category_editor.style.display = 'table';

        var category_typeList = document.getElementById('category_typeList');
        category_typeList.innerHTML = '';

        var types = json.types;

        if (typeId != undefined) {

            for (var i = 0; i < types.length; i++) {

                var option = document.createElement('option');
                var id = types[i].id;
                var name = types[i].name;

                option.id = 'type_' + id;
                option.innerHTML = name;

                if (id == typeId) {
                    option.selected = true;
                } else {
                    option.disabled = true;
                }

                category_typeList.appendChild(option);

            }


        } else {

            var option = document.createElement('option');
            option.selected = true;
            option.disabled = true;
            option.hidden = true;
            option.innerHTML = 'Select type';
            category_typeList.appendChild(option);

            for (var i = 0; i < types.length; i++) {

                var option = document.createElement('option');
                var id = types[i].id;
                var name = types[i].name;

                option.id = 'type_' + id;
                option.innerHTML = name;
                category_typeList.appendChild(option);

            }
        }

        document.getElementById('loadingScreen').style.display = 'none';
    });



}

function addCategory(typeId) {

    var token = document.getElementById('token').value;

    var category_editor = document.getElementById('category_editor');
    category_editor.style.display = 'none';

    var categoryName = document.getElementById('category_name').value;
    var categoryDescription = document.getElementById('category_desc').value;

    var category_typeList = document.getElementById('category_typeList');
    var selectedTypeId = category_typeList.options[category_typeList.selectedIndex].id;

    var URL = "/api/TransactionCategoryActions?operation=add";
    var jsonData = { "name": categoryName.trim(), "desc": categoryDescription.trim(), "typeId": selectedTypeId.replace('type_', '') };

    var xmlHttp = createHttpRequest();
    asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        if (typeId == undefined) {
            showTransactionCategories();
        } else {

            backButton('category_editor');
            getTransactionCategoryList();

        }




    });
}


function setOnclickForCategoryEdit(object, id) {
    object.onclick = function () { showUpdateCategory(id); };
}

function setOnclickForCategoryRemove(object, id) {
    object.onclick = function () { showQuestionBeforeDelete(id, 'category'); };
}

function deleteCategory(id) {

    var token = document.getElementById('token').value;

    var URL = '/api/TransactionCategoryActions?operation=delete&id=' + id;

    var xmlHttp = createHttpRequest();
    var json = asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        showTransactionCategories();

        document.getElementById('before_remove').style.display = 'none';

    });


}

function updateCategory(id) {

    var token = document.getElementById('token').value;

    var category_editor = document.getElementById('category_editor');
    category_editor.style.display = 'none';

    var categoryName = document.getElementById('category_name').value;
    var categoryDescription = document.getElementById('category_desc').value;

    var category_typeList = document.getElementById('category_typeList');
    var selectedTypeId = category_typeList.options[category_typeList.selectedIndex].id;

    var URL = "/api/TransactionCategoryActions?operation=update&id=" + id;
    var jsonData = { "name": categoryName.trim(), "desc": categoryDescription.trim(), "typeId": selectedTypeId.replace('type_', '') };

    var xmlHttp = createHttpRequest();
    asyncronJsonPostRequest(xmlHttp, URL, token, jsonData, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);

        showTransactionCategories();


    });


}


function showUpdateCategory(categoryId) {

    var token = document.getElementById('token').value;

    var URL = "/api/TransactionCategoryActions?operation=getdetails&id=" + categoryId;

    var xmlHttp = createHttpRequest();
    asyncronJsonGetRequest(xmlHttp, URL, token, function () {
        if (xmlHttp == null || xmlHttp.readyState != 4 || xmlHttp.status != 200) {
            return;
        }
        var json = JSON.parse(xmlHttp.responseText);
        var typeId = json.typeId;

        var category_editor = document.getElementById('category_editor');
        category_editor.style.display = 'table';

        var category_typeList = document.getElementById('category_typeList');
        category_typeList.innerHTML = '';


        document.getElementById('category_name').value = json.name;
        document.getElementById('category_desc').value = json.desc;

        var category_editor_button = document.getElementById('category_editor_button');
        category_editor_button.onclick = '';
        category_editor_button.onclick = function () { updateCategory(categoryId); };
        category_editor_button.innerHTML = 'Update';



        var URL2 = "/api/TransactionTypeActions?operation=getlist";
        var xmlHttp2 = createHttpRequest();
        asyncronJsonGetRequest(xmlHttp2, URL2, token, function () {
            if (xmlHttp2 == null || xmlHttp2.readyState != 4 || xmlHttp2.status != 200) {
                return;
            }
            var json2 = JSON.parse(xmlHttp2.responseText);

            var types = json2.types;

            var category_typeList = document.getElementById('category_typeList');

            for (var i = 0; i < types.length; i++) {

                var option = document.createElement('option');
                var id = types[i].id;
                var name = types[i].name;

                option.id = 'type_' + id;
                option.innerHTML = name;
                if (id == typeId) {
                    option.selected = true;
                }

                category_typeList.appendChild(option);

            }

            document.getElementById('loadingScreen').style.display = 'none';
        });




    });
}


function categoryFilter() {
    // Declare variables 
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("categoryFilterInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("category_table_body");
    tr = table.getElementsByTagName("tr");


    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[2];
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

function highlightCategory() {

    var input = document.getElementById("categoryFilterInput");
    var markInstance = new Mark(document.querySelectorAll("#category_table_body"));
    markInstance.unmark();
    markInstance.mark(input.value);


}