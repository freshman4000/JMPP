<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User addition</title>
    <style>
        /* Navbar Styling */
        .navbar {
            list-style: none;
            margin: 0;
            padding: 0;
            background: #4c6ca0;
            border-radius: 5px;
            overflow: auto;
        }
        .navbar li {
            float: left;
        }
        .navbar li a {
            display: block;
            color: #fff;
            text-decoration: none;
            padding: 15px 20px;
        }
        .navbar li a:hover {
            background: #446190;
            color: #f4f4f4;
        }
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
            font-size: 17px;
            margin: 15px 15px;
        }
        .fields #f1{
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
        .label {
            font-size: 15px;
            color: red;
        }
    </style>
</head>
<body>
<ul class="navbar">
    <li><a href="/admin/admin_panel">Go back</a></li>
    <c:import url="logout.jsp"></c:import>
</ul>
<section class="fields">
    <form action="/admin/add_user" method="POST">
        Firstname: <br><input id = "f1" type="text" name="firstname" required> <br>
        Lastname: <br><input id = "f1" type="text" name="lastname" required> <br>
        Email: <br><input id = "f1" type="email" name="email" required> <br>
        Birthdate: <br><input id = "f1" type="date" name="birthdate" required> <br>
        Phone number: <br><input id = "f1" type="text" name="phone" pattern="\+[0-9][0-9]{10}"
                                 required placeholder="+9(999)999-99-99 format"> <br>
        <label class="label">Role:</label> <br>
        <input class="role" type="checkbox" name="role" value="USER"> User
        <input class="role" type="checkbox" name="role" value="ADMIN"> Admin
        <br>
        Password: <br><input id = "f1" type="password" name="password" pattern="[A-Za-z0-9]{8}"
                                 required placeholder="Text/numbers 8 characters"> <br>
        <input id="button" type="submit" value="Add user">
    </form>
    <form action="/admin/admin_panel" method="GET">
        <input id="button1" type="submit" value="Go back">
    </form>
</section>

<section class="container">

</section>

</body>
</html>