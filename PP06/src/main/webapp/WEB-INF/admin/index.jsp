<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Index page</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
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
        #inline {
            Display: inline;
            margin-left: 5px;
            margin-right: 5px;
        }
        .container {
            margin-top: 30px;
        }
        #ufi {
            color: #fff;
            background-color: #4c6ca0;
            border-radius: 2px;
            margin-left: 5px;
            margin-right: 5px;
        }
    </style>

</head>
<body>
<ul class="navbar">
    <li><a href="/admin/add_user_form">Add user</a></li>
    <c:import url="logout.jsp"></c:import>
</ul>

<section class="container">
    ${message}
</section>
</body>
</html>