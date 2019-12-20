<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User addition</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .fields {
            background: #f4f4f4;
            width: 200px;
            margin-top: 20px;
            margin-left: 30px;
        }

        .fields form {
            margin: 15px 15px;
        }

        .fields input {
            margin: 5px 0px;
            width: 100%;
            height: 20px;
            border: none;
            border-radius: 5px;
        }

        #button, #button1 {
            background: #4c6ca0;;
            color: #fff;
            width: 100%;
            height: 25px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        #button:hover, #button1:hover {
            background: #446190;
        }

        #role {
            background: #4c6ca0;;
            color: #fff;
        }

        .label {
            color: red;
        }
    </style>
</head>
<body>
<section class="fields">
    <form action="/admin/setUserPass" method="POST">
        Id: <br><input type="id" name="id" value="<%=request.getParameter("id")%>"> <br>
        Password: <br><input type="password" name="password"> <br>
        <input id="button" type="submit" value="Set password">
    </form>
    <form action="/admin/admin_panel.jsp" method="GET">
        <input id="button1" type="submit" value="Go back">
    </form>
</section>
</body>
</html>