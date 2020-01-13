function adminPanel() {
    $.ajax({
        url : 'http://localhost:8080/api/admin/show_all_users',
        type : 'GET',
        dataType: 'json',
        success : function(list) {
            $('#div3').empty();
            $.each(list, function (i) {
                var rolesList = [];
                for (var j = 0; j < list[i].roles.length; j++) {
                    rolesList.push(list[i].roles[j].name);
                }
                $("#div3").append("<tr>" +
                    "<td>" + list[i].id + "</td>" +
                    "<td>" + rolesList + "</td>" +
                    "<td>" + list[i].firstname + "</td>" +
                    "<td>" + list[i].lastname + "</td>" +
                    "<td>" + list[i].email + "</td>" +
                    "<td>" + list[i].birthdate + "</td>" +
                    "<td>" + list[i].phone + "</td>" +
                    "<td>" +

                    "<a type=\"button\" class=\"btn btn-info btn-sm\" rel=\"modal:open\" href=\"#ex" + list[i].id + "\">" +
                    "edit" +
                    "</a>"
                    + "</td>" +
                    "<td>" +
                    "<button class='btn btn-sm btn-outline-danger' onclick='deleteUser(" + list[i].id + ")'>" +
                    'delete' +
                    "</button>"
                    + "</td>" + "<td>" +
                    "<form class=\"form-group\" enctype=\"multipart/form-data\" method='post'>" +
                    "<div id=\"ex" + list[i].id + "\" class=\"modal\">" +
                    "<p class='font-weight-bold border-bottom'> Editing user " + list[i].email + "</p>" +
                    "<div class=\"group-item \"><span class=\"font-weight-bold\">ID</span>" +
                    "<input type=\"text\" class=\"form-control\" id=\"idA" + list[i].id + "\" readonly required value=\"" + list[i].id + "\">" +
                    "</div>" +
                    "<div class=\"group-item \"><span class=\"font-weight-bold\">First name</span>" +
                    "<input type=\"text\" class=\"form-control\" id=\"firstnameA" + list[i].id + "\" required value=\"" + list[i].firstname + "\">" +
                    "</div>" +
                    "<div class=\"group-item \"><span class=\"font-weight-bold\">Last name</span>" +
                    "<input type=\"text\" class=\"form-control\" id=\"lastnameA" + list[i].id + "\" required value=\"" + list[i].lastname + "\">" +
                    "</div>" +
                    "<div class=\"group-item \"><span class=\"font-weight-bold\">Email</span>" +
                    "<input type=\"email\" class=\"form-control\" id=\"emailA" + list[i].id + "\" required value=\"" + list[i].email + "\">" +
                    "</div>" +
                    "<div class=\"group-item \"><span class=\"font-weight-bold\">Birthdate</span>" +
                    "<input type=\"date\" class=\"form-control\" id=\"birthdateA" + list[i].id + "\" required value=\"" + list[i].birthdate + "\">" +
                    "</div>" +
                    "<div class=\"group-item \"><span class=\"font-weight-bold\">Phone</span>" +
                    "<input type=\"text\" class=\"form-control\" id=\"phoneA" + list[i].id + "\" pattern='\+[0-9][0-9]{10}' required value=\"" + list[i].phone + "\">" +
                    "</div>" +
                    "<div class=\"group-item\">" +
                    "<span class=\"font-weight-bold\">Role</span>" +
                    "<div class=\"form-control form-checkbox\">" +
                    "<input type=\"checkbox\" class=\"form-control-input\" id=\"customCheck1" + list[i].id + "\" name=\"role\" checked value=\"ADMIN\">" +
                    "<label class=\"form-control-label\">ADMIN</label>" +
                    "</div>" +
                    "<div class=\"form-control form-checkbox\">" +
                    "<input type=\"checkbox\" class=\"form-control-input\" id=\"customCheck2" + list[i].id + "\" name=\"role\" checked value=\"USER\">" +
                    "<label class=\"form-control-label\">USER</label>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"group-item\"><span class=\"font-weight-bold\">Password</span>" +
                    "<input type=\"password\" class=\"form-control\" id=\"passwordA" + list[i].id + "\" pattern=\"[A-Za-z0-9$.]{8,}\"" +
                " placeholder=\"Leave blank for no change\">" +
                    "</div>" +
                    "<input type=\"hidden\" id=\"previousEmailA" + list[i].id + "\" value=\"" + list[i].email + "\">" +
                    "<input type=\"hidden\" id=\"previousPassA" + list[i].id + "\" value=\"" + list[i].password + "\">" +
                    "<div class=\"modal-footer mt-0 pt-2\">" +
                    "<a type=\"button\" class=\"btn btn-outline-info\" href=\"#\" rel=\"modal:close\">Close</a>" +
                    "<button id=\"btn-updateA\" type=\"submit\" onclick=\"updateUser(" + list[i].id + ")\" class=\"btn btn-info\">Update user</button>" +
                "</div>" +
                    "</div>" +
                    "</form>" +
                    "</td>" +
                    "</tr>"
            )
            })
        }
    });
}

function updateUser(id) {
    var roleArrA = [];
    if (document.getElementById("customCheck1".concat(id)).checked === true) {
        roleArrA.push(document.getElementById("customCheck1".concat(id)).value);
    }

    if (document.getElementById("customCheck2".concat(id)).checked === true) {
        roleArrA.push(document.getElementById("customCheck2".concat(id)).value);
    }
    var dataA = {
        "id": $("#idA".concat(id)).val(),
        "firstname": $("#firstnameA".concat(id)).val(),
        "lastname": $("#lastnameA".concat(id)).val(),
        "email": $("#emailA".concat(id)).val(),
        "birthdate": $("#birthdateA".concat(id)).val(),
        "phone": $("#phoneA".concat(id)).val(),
        "roles": roleArrA,
        "password": $("#passwordA".concat(id)).val(),
        "prevEmail": $('#previousEmailA'.concat(id)).val(),
        "prevPass": $('#previousPassA'.concat(id)).val()
    };
    alert(JSON.stringify(dataA));
    $.ajax({
        method: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/admin/update_user',
        data: JSON.stringify(dataA),
        success: function() {
            window.location.href = "http://localhost:8080/admin/admin_panel";
        },
        error: function (e) {
            alert("error");
        }
    });
}

function deleteUser(id) {
    $.ajax({url: 'http://localhost:8080/api/admin/delete/' + id, method: 'POST'});
    location.reload();
}

$('#btn-save').click(function (e) {
    e.preventDefault();
    var roleArr = [];
    if (document.getElementById("role").checked === true) {
        roleArr.push($("#role").val());
    }
    if (document.getElementById("role1").checked === true) {
        roleArr.push($("#role1").val());
    }
    var data = {"firstname":  $("#firstname").val(), "lastname": $("#lastname").val(), "email": $("#email").val(), "birthdate": $("#birthdate").val(), "phone": $("#phone").val(),
        "roles": roleArr, "password": $("#password").val()};

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/admin/add_user',
        data: JSON.stringify(data),
        success: function() {
            window.location.href = "http://localhost:8080/admin/admin_panel";
        },
        error: function() {
            alert('user exists');
        }
    });
});