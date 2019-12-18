<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
        .fields #f1 {
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
        .role {
            display: inline;
            width: 20%;
            background: #4c6ca0;;
            color: #fff;
        }
        .label {
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
    <form action="/admin/update" method="POST">
        <input type="hidden" name="id" value=${user.id}>
        Firstname: <br><input id="f1" type="text" name="firstname" value=${user.firstname}> <br>
        Lastname: <br><input id="f1" type="text" name="lastname" value=${user.lastname}> <br>
        Email: <br><input id="f1" type="email" name="email" value=${user.email}> <br>
        Birthdate: <br><input id="f1" type="date" name="birthdate" value=${user.birthdate}> <br>
        Phone number: <br><input id="f1" type="tel" name="phone" pattern="\+[0-9][0-9]{10}"
                                    value=${user.phone}> <br>
        <label class="label">Role:</label> <br>
        <input class="role" type="checkbox" name="role" value="USER" checked> User
        <input class="role" type="checkbox" name="role" value="ADMIN"> Admin
        <br>
        Password: <br><input id="f1" type="password" name="password" pattern="[A-Za-z0-9$.]{8,}"
                             placeholder="leave blank to save previous password"> <br>
        <input type="hidden" name="previousEmail" value="${user.email}">
        <input type="hidden" name="previousPass" value="${user.password}">
        <input id="button" type="submit" value="Update user">
    </form>
    <form action="/admin/admin_panel" method="POST">
        <input id="button1" type="submit" value="Go back">
    </form>
</section>
</body>
</html>