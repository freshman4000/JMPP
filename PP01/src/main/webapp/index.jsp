<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Menu styling</title>
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
    <li><a href="add_user.jsp">Add user</a></li>
    <li><a href="/show">Show all users</a></li>
</ul>
<section class="container">
<%
    if (request.getAttribute("message") != null) {
        out.println(request.getAttribute("message"));
    }
%>
</section>
</body>
</html>
