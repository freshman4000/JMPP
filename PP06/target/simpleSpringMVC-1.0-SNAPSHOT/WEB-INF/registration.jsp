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
    <form action="/add_user" method="POST">
        Firstname: <br><input type="text" name="username" required> <br>
        Lastname: <br><input type="text" name="lastname" required> <br>
        Email: <br><input type="email" name="email" required> <br>
        Birthdate: <br><input type="date" name="birthdate" required> <br>
        Phone number: <br><input type="text" name="phone" pattern="\+[0-9][0-9]{10}"
                                 required placeholder="+9(999)999-99-99 format"> <br>
        <label for="role" class="label">Role:</label> <br>  <select name="role" id="role">
        <option value="user" selected>User</option>
        <option value="admin">Admin</option>
    </select> <br>
        Password: <br><input type="password" name="password" pattern="[A-Za-z0-9]{8}"
                                 required placeholder="Text/numbers 8 characters"> <br>
        <input id="button" type="submit" value="Add user">
    </form>
    <form action="/" method="GET">
        <input id="button1" type="submit" value="Go back">
    </form>
</section>

<section class="container">
    <%
        if (request.getAttribute("message") != null) {
            out.println(request.getAttribute("message"));
        }
    %>
</section>

</body>
</html>