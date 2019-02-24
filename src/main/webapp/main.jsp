<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<% String token = (String)request.getAttribute("token");%>
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
    
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
        integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <script type="text/javascript" src="/js/common.js?version=1"></script>
        <script type="text/javascript" src="/js/main.js?version=6"></script>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">HandyBudget</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav mr-auto"></ul>

                <ul class="nav justify-content-end">
                    <li class="nav-item"><a class="nav-link" href="/api/logout">Log out <i
                                class="fas fa-sign-out-alt"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link" href="#"><i class="fas fa-plus-square"></i> Add single charge</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#"><i class="fas fa-plus-square"></i> Add single income</a>
        </li>
    </ul>

    <div class="container">
        <div class="row">
            <div class="col-3">
                <nav class="nav flex-column">
                    <a class="nav-link active" href="#" onclick="getAccountList()"><i class="fas fa-table"></i> Accounts</a>
                    <a class="nav-link active" href="#"><i class="fas fa-bars"></i> Transaction categories</a>
                    <a class="nav-link active" href="#"><i class="fas fa-weight"></i> Measuring devices</a>
                </nav>
            </div>
            <div class="col-9">
                <table id="account_table" class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Created Date</th>
                        </tr>
                    </thead>
                    <tbody id="account_table_body">
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <input id="token" type="hidden" value=<%=token%>>

</body>

</html>