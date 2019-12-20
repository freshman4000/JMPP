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
    </style>
</head>
<body>
<section class="fields">
    <form action="/login" method="POST">
        Email: <br><input type="email" name="email"> <br>
        Password: <br><input type="password" name="password"> <br>
        <input id="button" type="submit" value="Login">
    </form>
</section>
</body>
</html>
