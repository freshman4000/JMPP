package com.freshamn4000.utility;

import com.freshamn4000.models.User;

public class FormGenerator {
    public static String getUpdateForm(User user) {
        return  "<form id=\"inline\" action=\"/update\" method=\"GET\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "<input type=\"hidden\" name=\"name\" value=\"" + user.getFirstName() + "\">" +
                "<input type=\"hidden\" name=\"lastname\" value=\"" + user.getLastName() + "\">" +
                "<input type=\"hidden\" name=\"email\" value=\"" + user.getEmail() + "\">" +
                "<input type=\"hidden\" name=\"birthdate\" value=\"" + user.getBirthDate() + "\">" +
                "<input type=\"hidden\" name=\"phone\" value=\"" + user.getPhoneNumber() + "\">" +
                "<input id=\"sub\" type=\"submit\" value=\"update\"></form>";
    }
    public static String getDeleteForm(User user) {
        return  "<form id=\"inline\" action=\"/deleteUser\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "<input type=\"submit\" value=\"delete\"></form>";
    }
}
