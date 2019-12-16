package com.freshman4000.utility;

import com.freshman4000.model.User;

import java.util.List;

public class FormGenerator {
    public static String getUpdateForm(User user) {
        return  "<form id=\"inline\" action=\"/update_user_form\" method=\"GET\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "<input type=\"hidden\" name=\"username\" value=\"" + user.getUsername() + "\">" +
                "<input type=\"hidden\" name=\"lastname\" value=\"" + user.getLastname() + "\">" +
                "<input type=\"hidden\" name=\"email\" value=\"" + user.getEmail() + "\">" +
                "<input type=\"hidden\" name=\"birthdate\" value=\"" + user.getBirthdate() + "\">" +
                "<input type=\"hidden\" name=\"phone\" value=\"" + user.getPhone() + "\">" +
                "<input type=\"hidden\" name=\"role\" value=\"" + user.getRole() + "\">" +
                "<input type=\"hidden\" name=\"password\" value=\"" + user.getPassword() + "\">" +
                "<input id=\"sub\" type=\"submit\" value=\"update\"></form>";
    }
    public static String getDeleteForm(User user) {
        return  "<form id=\"inline\" action=\"/delete\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "<input type=\"submit\" value=\"delete\"></form>";
    }
    public static String getSetPassForm(User user) {
        return  "<form id=\"inline\" action=\"/admin/setUserPass\" method=\"GET\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "<input type=\"submit\" value=\"update password\"></form>";
    }
    public static String getHTML(List<User> userList) {
        StringBuilder sb = new StringBuilder();
        userList.forEach(x -> sb
                .append(FormGenerator.getDeleteForm(x))
                .append(FormGenerator.getUpdateForm(x))
                .append(x.toString()).append("<br />"));
        return sb.toString();
    }
}
