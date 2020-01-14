function adminPanel() {
    $.ajax({
        url: 'http://localhost:8080/api/admin/show_all_users',
        type: 'GET',
        dataType: 'json',
        success: function (list) {
            $('#div3').empty();
            $.each(list, function (i) {
                var rolesList = [];
                for (var j = 0; j < list[i].roles.length; j++) {
                    rolesList.push(list[i].roles[j].name);
                }
                $("#div3").append("<tr id=\"row" + list[i].id + "\">" +
                    "<td id=\"i" + list[i].id + "\">" + list[i].id + "</td>" +
                    "<td id=\"r" + list[i].id + "\">" + rolesList + "</td>" +
                    "<td id=\"f" + list[i].id + "\">" + list[i].firstname + "</td>" +
                    "<td id=\"l" + list[i].id + "\">" + list[i].lastname + "</td>" +
                    "<td id=\"e" + list[i].id + "\">" + list[i].email + "</td>" +
                    "<td id=\"b" + list[i].id + "\">" + list[i].birthdate + "</td>" +
                    "<td id=\"ph" + list[i].id + "\">" + list[i].phone + "</td>" +
                    "<td>" +

                    "<a type=\"button\" class=\"btn btn-info btn-sm\" rel=\"modal:open\" href=\"#ex" + list[i].id + "\">" +
                    "edit" +
                    "</a>"
                    + "</td>" +
                    "<td>" +
                    "<button class='btn btn-sm btn-outline-danger' onclick='deleteUser(" + list[i].id + ")'>" +
                    'delete' +
                    "</button>"
                    + "</td>" + "<td id = \"winCell\">" +
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
                    "<span class=\"font-weight-bold\">Role</span>" + "<div id='rlist" + list[i].id + "'>" + "<div id='rlistinner" + list[i].id + "'>" +
                    // "<div class=\"form-control form-checkbox\">" +
                    // "<input type=\"checkbox\" class=\"form-control-input\" id=\"customCheck1" + list[i].id + "\" name=\"role\" checked value=\"ADMIN\">" +
                    // "<label class=\"form-control-label\">ADMIN</label>" +
                    // "</div>" +
                    // "<div class=\"form-control form-checkbox\">" +
                    // "<input type=\"checkbox\" class=\"form-control-input\" id=\"customCheck2" + list[i].id + "\" name=\"role\" checked value=\"USER\">" +
                    // "<label class=\"form-control-label\">USER</label>" +
                    // "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"group-item\"><span class=\"font-weight-bold\">Password</span>" +
                    "<input type=\"password\" class=\"form-control\" id=\"passwordA" + list[i].id + "\" pattern=\"[A-Za-z0-9$.]{8,}\"" +
                    " placeholder=\"Leave blank for no change\">" +
                    "</div>" +
                    "<input type=\"hidden\" id=\"previousEmailA" + list[i].id + "\" value=\"" + list[i].email + "\">" +
                    "<input type=\"hidden\" id=\"previousPassA" + list[i].id + "\" value=\"" + list[i].password + "\">" +
                    "<div class=\"modal-footer mt-0 pt-2\">" +
                    "<a id=\"closemodal" + list[i].id + "\" type=\"button\" class=\"btn btn-outline-info\" href=\"#\" rel=\"modal:close\">Close</a>" +
                    "<button id=\"btn-updateA\" type=\"submit\" onclick=\"updateUser(" + list[i].id + ")\" class=\"btn btn-info\">Update user</button>" +
                    "</div>" +
                    "</div>" +
                    "</form>" +
                    "</td>" +
                    "</tr>"
                );

                $.ajax({
                    method: 'GET',
                    url: "http://localhost:8080/api/admin/get_roles",
                    dataType: "json",
                    success: function (data) {
                        $('#rlistinner'.concat(list[i].id)).remove();
                        $.each(data, function (k) {
                            $('#rlist'.concat(list[i].id)).append("<div id='rlistinner" + list[i].id + "'>" +
                                "<div class=\"form-control form-checkbox\">" +
                                "<input type=\"checkbox\" class=\"form-control-input rlsmodal" + list[i].id + "\" id=\"customCheck1" + list[i].id + "\" name=\"role\" checked value=\"" + data[k].name +"\">" +
                                "<label class=\"form-control-label\">" + data[k].name +"</label>" +
                                "</div>" + "</div>"
                            );

                        });
                    }
                });
            })
        }
    });
}

function appendRoles() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/admin/get_roles",
        dataType: "json",
        success: function (data) {
            $("#roleList").empty();
            $.each(data, function (i) {
                $("#roleList").append("<div class=\"form-control form-checkbox\">" +
                    "<input type=\"checkbox\" class=\"form-control-input role\" name=\"role\" value=\"" + data[i].name + "\" checked>" +
                    "<label class=\"form-control-label\">" + data[i].name + "</label>" +
                    "</div>")
            });
        }
    });
}

function updateUser(id) {
    var roleArrA = [];
    var rolesList = document.getElementsByClassName('rlsmodal'.concat(id));
    for (var i = 0; i < rolesList.length; i++) {
        if (rolesList[i].checked === true) {
            roleArrA.push(rolesList[i].getAttribute('value'));
        }
    }
    // if (document.getElementById("customCheck1".concat(id)).checked === true) {
    //     roleArrA.push(document.getElementById("customCheck1".concat(id)).value);
    // }
    //
    // if (document.getElementById("customCheck2".concat(id)).checked === true) {
    //     roleArrA.push(document.getElementById("customCheck2".concat(id)).value);
    // }
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
    $.ajax({
        method: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/admin/update_user',
        data: JSON.stringify(dataA),
        success: function () {
            $("#i".concat(id)).text(dataA.id);
            $("#f".concat(id)).text(dataA.firstname);
            $("#l".concat(id)).text(dataA.lastname);
            $("#e".concat(id)).text(dataA.email);
            $("#b".concat(id)).text(dataA.birthdate);
            $("#ph".concat(id)).text(dataA.phone);
            $("#r".concat(id)).text(dataA.roles);
            $("#closemodal".concat(id)).click();
        },
        error: function (e) {
            alert("error");
        }
    });
}

function deleteUser(id) {
    $.ajax({
        url: 'http://localhost:8080/api/admin/delete/' + id,
        method: 'POST',
        success: function () {
            $("#row".concat(id)).remove();
        }
    });
}

$('#btn-save').click(function (e) {
    e.preventDefault();
    var roleArr = [];
    var rol = document.getElementsByClassName('role');
    for (var i = 0; i < rol.length; i++) {
        if (rol[i].checked === true) {
            roleArr.push(rol[i].getAttribute('value'));
        }
    }
    var data = {
        "firstname": $("#firstname").val(),
        "lastname": $("#lastname").val(),
        "email": $("#email").val(),
        "birthdate": $("#birthdate").val(),
        "phone": $("#phone").val(),
        "roles": roleArr,
        "password": $("#password").val()
    };

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/admin/add_user',
        data: JSON.stringify(data),
        success: function () {
            window.location.href = "http://localhost:8080/admin/admin_panel";
        },
        error: function () {
            $('#message').empty().text('Error happened. User exists!');
            $('#uad').empty();
        }
    });
});