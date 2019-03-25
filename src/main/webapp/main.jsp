<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<% String token = (String)request.getAttribute("token");
   String randomString = (String)request.getAttribute("randomString");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta http-equiv="cache-control" content="max-age=0">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="-1">
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 11:00:00 GMT">
    <meta http-equiv="pragma" content="no-cache">

    <title>Handy Budget</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
        integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script type="text/javascript" src="/js/common.js?<%=randomString%>"></script>
    <script type="text/javascript" src="/js/account.js?<%=randomString%>"></script>
    <script type="text/javascript" src="/js/transaction.js?<%=randomString%>"></script>
    <script type="text/javascript" src="/js/category.js?<%=randomString%>"></script>
    <script type="text/javascript" src="/js/search.js?<%=randomString%>"></script>
    <script type="text/javascript" src="/js/measuring.js?<%=randomString%>"></script>
    <script type="text/javascript" src="/js/analysis.js?<%=randomString%>"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mark.js/8.11.1/mark.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/main.css?<%=randomString%>">
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">HandyBudget</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav mr-auto"></ul>

                <ul class="nav justify-content-end">
                    <li class="nav-item"><a class="nav-link" href="/">Settings <i class="fas fa-user-cog"></i></a></li>
                    <li class="nav-item"><a class="nav-link" href="/">Log out <i class="fas fa-sign-out-alt"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>



    <div class="container-fluid">
        <div class="row">
            <div id="sidebar" class="col-2">
                <nav class="nav flex-column">
                    <a class="nav-link active" href="#" onclick="getAccountList()"><i class="fas fa-table"></i> Accounts</a>
                    <a class="nav-link active" href="#" onclick="showTransactionCategories()"><i class="fas fa-bars"></i> Transaction categories</a>
                    <a class="nav-link active" href="#" onclick="showSearchScreen()"><i class="fas fa-search"></i> Search</a>
                    <a class="nav-link active" href="#" onclick="showMeasuringScreen()"><i class="fas fa-weight"></i> Measuring devices</a>
                    <a class="nav-link active" href="#" onclick="showAnalysisScreen()"><i class="fas fa-chart-pie"></i> Analysis</a>
                </nav>
            </div>
            <div id="account_screen" class="col-4" style="display: none">
                <div class="table-responsive">
                    <table id="account_table" class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Created Date</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody id="account_table_body">
                        </tbody>
                    </table>
                </div>

                <div class="container">
                    <button type="button" class="btn btn-outline-primary" onclick="showAddAccount()"><i
                            class="fas fa-plus-square"></i> Add account</button>
                </div>

            </div>
            <div id="transaction_screen" class="col-9" style="display: none">
                <div class="container-fluid">
                    <div class="row">
                        <div id="monthsListDiv" class="container" style="display: none">
                            <ul class="nav nav-tabs monthsList" id="monthsList" role="tablist">
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div id="regular_income" class="col-6">
                            <h3>Regular incomes</h3>
                            <div>
                                <table id="table_regular_income" class="table table-sm table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Category</th>
                                            <th scope="col">Description</th>
                                            <th scope="col">Amount</th>
                                            <th scope="col">Credited</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_regular_income_body">
                                    </tbody>
                                    <tfoot id="table_regular_income_foot">
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                        <div id="regular_expense" class="col-6">
                            <h3>Regular expenses</h3>
                            <table id="table_regular_expense" class="table table-sm table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Due Date</th>
                                        <th scope="col">Amount</th>
                                        <th scope="col">Charged</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody id="table_regular_expense_body">
                                </tbody>
                                <tfoot id="table_regular_expense_foot">
                                </tfoot>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div id="transactions" class="col-6">
                            <h3>Ad-hoc transactions</h3>
                            <table id="table_adhoc_transactions" class="table table-sm table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Create date</th>
                                        <th scope="col">Amount</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody id="table_adhoc_transactions_body">
                                </tbody>
                                <tfoot id="table_adhoc_transactions_foot">
                                </tfoot>
                            </table>
                        </div>
                        <div id="transactions" class="col-6 align-self-center">
                            <span id="prevbalance"></span><br>
                            <span id="currentbalance"></span><br>
                            <span id="aggregatedbalance"></span>
                            <button type="button" class="btn btn-outline-primary btn-lg btn-block"
                                onclick="showAddTransaction('ad-hoc')">Create new ad-hoc transaction</button>
                            <button type="button" class="btn btn-outline-primary btn-lg btn-block"
                                onclick="showAddTransaction('regular')">Create new regular transaction</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="category_screen" class="col-4" style="display: none">
                    <div class="table-responsive">
                        <input type="text" id="categoryFilterInput" class="form-control categoryFilter" placeholder="Search for name..">
                        <table id="category_table" class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Type</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Description</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody id="category_table_body">
                            </tbody>
                        </table>
                    </div>
    
                    <div class="container">
                        <button type="button" class="btn btn-outline-primary" onclick="showAddCategory()"><i
                                class="fas fa-plus-square"></i> Add category</button>
                    </div>
            </div>
            <div id="search_screen" class="col-9" style="display: none">
                    <div class="table-responsive">
                        <div class="container-fluid searchScreenFilter">
                            <div class="row">
                                <div class="col-3 align-self-center">
                                    <label for="searchFilterInput_name">Category Name</label>
                                    <input type="text" id="searchFilterInput_name" class="form-control searchScreenFilter" placeholder="Search for category name..">
                                    <label for="searchFilterInput_desc">Description</label>
                                    <input type="text" id="searchFilterInput_desc" class="form-control searchScreenFilter" placeholder="Search for description..">
                                </div>
                                <div class="col-3 align-self-center">
                                    <label for="searchScreen_startDate">Start Date</label>
                                    <input type="date" id="searchScreen_startDate" class="form-control searchScreenFilter" placeholder="Start Date">
                                    <label for="searchScreen_endDate">End Date</label>
                                    <input type="date" id="searchScreen_endDate" class="form-control searchScreenFilter" placeholder="End Date">
                                </div>
                                <div class="col-3 align-self-center">
                                        <button type="button" class="btn btn-outline-primary searchScreenDateButton" onclick="showSearchScreen()">Filter by Date</button><br>
                                        <button type="button" class="btn btn-outline-primary searchScreenDateButton" onclick="resetSearchScreen()">Reset</button>
                                </div>
                            </div>
                        </div>
                        <table id="search_table" class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Account Name</th>
                                    <th scope="col">Type</th>
                                    <th scope="col">Category Name</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Created Date</th>
                                    <th scope="col">Due Date</th>
                                    <th scope="col">Amount</th>
                                    <th scope="col">Charged / Credited</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody id="search_table_body">
                            </tbody>
                        </table>
                    </div>
            </div>
            <div id="measuring_screen" class="col-6" style="display: none">
                    <div class="table-responsive">
                            <table id="measuring_table" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Type</th>
                                        <th scope="col">Identifier</th>
                                        <th scope="col">Description</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody id="measuring_table_body">
                                </tbody>
                            </table>
                    </div>
                    <div class="table-responsive">
                            <table id="position_table" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Identifier</th>
                                        <th scope="col">Position</th>
                                        <th scope="col">Consumption</th>
                                        <th scope="col">Created Date</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody id="position_table_body">
                                </tbody>
                            </table>  
                    </div> 
            </div>
            <div id="analysis_screen" class="col-6" style="display: none">
                <div id="canvas-holder" style="width:40%">
                    <canvas id="chart-area" width="400" height="400"></canvas>
                </div>
            </div>
        </div>
    </div>

    <div id="account_editor" class="editorScreen" style="display: none">
        <div class="editorScreenMiddle">
            <div class="editorScreenInner">
                <div class="form-group">
                    <label for="accountName">Name</label>
                    <input type="text" class="form-control" id="accountName" placeholder="Account name" required>
                </div>
                <div class="form-group">
                    <label for="accountDescription">Description</label>
                    <input type="text" class="form-control" id="accountDescription" placeholder="Account description">
                </div>
                <button id="account_editor_button" type="button" class="btn btn-outline-primary"></button>
                <button type="button" class="btn btn-outline-primary" onclick="backButton('account_editor')">Cancel</button>
            </div>
        </div>
    </div>

    <div id="transaction_editor" class="editorScreen" style="display: none">
        <div class="editorScreenMiddle">
            <div class="editorScreenInner">
                <div class="form-group">
                    <label for="transaction_typeList">Type</label>
                    <select id="transaction_typeList" class="form-control"
                        onchange="getTransactionCategoryList()"></select>
                </div>
                <div class="form-group">
                    <label for="transaction_categoryList">Category</label><i id="categoryAddButton" class="fas fa-plus-square categoryAdd" style="display: none"></i>
                    <select id="transaction_categoryList" class="form-control"></select>
                </div>
                <div class="form-group">
                    <label for="transaction_amount">Amount</label>
                    <input type="number" class="form-control" id="transaction_amount" placeholder="Amount">
                </div>
                <div class="form-group">
                    <label for="transaction_desc">Description</label>
                    <input type="text" class="form-control" id="transaction_desc" placeholder="Description">
                </div>
                <div id="transaction_additional_fields" style="display: none">
                    <div class="form-group">
                        <label for="transaction_duedate">Due date</label>
                        <input type="date" class="form-control" id="transaction_duedate" placeholder="Due date">
                    </div>
                </div><br>
                <button id="transaction_editor_button" type="button" class="btn btn-outline-primary"></button>
                <button type="button" class="btn btn-outline-primary" onclick="backButton('transaction_editor')">Cancel</button>
            </div>
        </div>
    </div>

    <div id="category_editor" class="editorScreen" style="display: none">
        <div class="editorScreenMiddle">
            <div class="editorScreenInner">
                <div class="form-group">
                    <label for="category_typeList">Type</label>
                    <select id="category_typeList" class="form-control"></select>
                </div>
                <div class="form-group">
                    <label for="category_name">Category Name</label>
                    <input type="text" class="form-control" id="category_name" placeholder="Category Name">
                </div>
                <div class="form-group">
                    <label for="category_desc">Description</label>
                    <input type="text" class="form-control" id="category_desc" placeholder="Description">
                </div><br>
                <button id="category_editor_button" type="button" class="btn btn-outline-primary"></button>
                <button type="button" class="btn btn-outline-primary" onclick="backButton('category_editor')">Cancel</button>
            </div>
        </div>
    </div>


    <div id="before_remove" class="editorScreen" style="display: none">
            <div class="editorScreenMiddle">
                <div class="editorScreenInner">
                   <div class="deleteQuestion">
                        <span>Are you sure you want to delete?</span><br>
                    </div>
                    <button id="before_remove_button" type="button" class="btn btn-outline-primary">Yes</button>
                    <button type="button" class="btn btn-outline-primary" onclick="backButton('before_remove')">Cancel</button>
                </div>
            </div>
    </div>



    <div id="loadingScreen" class="loadingScreen"></div>


    <input id="token" type="hidden" value=<%=token%>>
    <input id="selected_account" type="hidden">
    <input id="selected_month" type="hidden">

</body>

</html>