<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
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
    <li><a href="/admin/admin_panel">Go back</a></li>
    <li><a href="/logout">Logout</a></li>
</ul>
<section class="container">
    ${exMessage}
</section>

</body>
</html>