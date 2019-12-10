<html lang="en">
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
    </style>
</head>
<body>
<ul class="navbar">
    <li><a href="/registration">Register</a></li>
    <li><a href="/login">Login</a></li>
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