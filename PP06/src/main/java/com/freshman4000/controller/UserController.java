package com.freshman4000.controller;

import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.utility.FormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ClientService clientService;

    @RequestMapping("/")
    public String goToIndexPage(Model model) {
        List<User> userList = clientService.showAllUsers();
        StringBuilder sb = new StringBuilder();
        userList.forEach(x -> sb
                .append(FormGenerator.getDeleteForm(x))
                .append(FormGenerator.getUpdateForm(x))
                .append(x.toString()).append("<br />"));
        String answer = sb.toString();
        model.addAttribute("message", answer.isEmpty() ? "DB is empty" : answer);
        return "index";
    }
    @RequestMapping("/add_user_form")
    public String addUserForm() {
        return "registration";
    }
    @RequestMapping("/add_user")
    public String addUser(@ModelAttribute User user) {
        clientService.addUser(user);
        return "redirect:/";
    }
    @RequestMapping("/delete")
    public String deleteUser(@ModelAttribute User user) {
        clientService.deleteUser(user);
        return "redirect:/";
    }
    @RequestMapping("/update_user_form")
    public ModelAndView updateUserForm(@ModelAttribute User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("update_user");
        mv.addObject(user);
        return mv;
    }
    @RequestMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        clientService.updateUser(user);
        return "redirect:/";
    }
}
