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
<% if (session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
    request.setAttribute("message", "You have no rights to view this page!");
    request.getRequestDispatcher("/info.jsp").forward(request, response);
}%>

<section class="fields">
    <form action="/admin/update" method="POST">
        <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
        Firstname: <br><input type="text" name="username" placeholder="<%=request.getParameter("name")%>"> <br>
        Lastname: <br><input type="text" name="lastname" placeholder="<%=request.getParameter("lastname")%>"> <br>
        Email: <br><input type="email" name="email" placeholder="<%=request.getParameter("email")%>"> <br>
        Birthdate: <br><input type="date" name="birthdate" placeholder="<%=request.getParameter("birthdate")%>"> <br>
        Phone number: <br><input type="tel" name="phone" pattern="\+[0-9][0-9]{10}"
                                    placeholder="<%=request.getParameter("phone")%>"> <br>
        <label for="role" class="label">Role:</label> <br>  <select name="role" id="role">
                <option value="user" selected>User</option>
                <option value="admin">Admin</option>
                    </select> <br>
        <input id="button" type="submit" value="Update user">
    </form>
    <form action="/admin_panel.jsp" method="POST">
        <input id="button1" type="submit" value="Go back">
    </form>
</section>
</body>
</html>