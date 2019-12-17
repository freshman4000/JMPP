package com.freshman4000.controller;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.utility.FormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String rootPage() {
        return "info";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = clientService.getUserByUserName(currentPrincipalName);
        model.addAttribute("user", user.toString());
        return "hello";
    }
    @RequestMapping("/admin/admin_panel")
    public String goToIndexPage(Model model) {
        List<User> userList = clientService.showAllUsers();
        model.addAttribute("message", userList.isEmpty() ? "DB is empty" : FormGenerator.getHTML(userList));
        return "admin/index";
    }
    @RequestMapping("/admin/add_user_form")
    public String addUserForm() {
        return "admin/registration";
    }

    @RequestMapping("/admin/add_user")
    public String addUser(@RequestParam("role")String[] checkboxValue, @ModelAttribute User user) {
        List<Role> userRoles = new ArrayList<>();
        Arrays.stream(checkboxValue).forEach(x -> userRoles.add(new Role(x)));
        user.setRoles(userRoles);
        clientService.addUser(user);
        return "redirect:/admin/admin_panel";
    }
    @RequestMapping("/admin/delete")
    public String deleteUser(@ModelAttribute User user) {
        clientService.deleteUser(user);
        return "redirect:/admin/admin_panel";
    }
    @RequestMapping("/admin/update_user_form")
    public ModelAndView updateUserForm(@ModelAttribute User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/update_user");
        mv.addObject(user);
        return mv;
    }
    @RequestMapping("/admin/update")
    public String updateUser(@RequestParam("role") String[] checkboxValue, @ModelAttribute User user) {
        List<Role> userRoles = new ArrayList<>();
        Arrays.stream(checkboxValue).forEach(x -> userRoles.add(new Role(x)));
        user.setRoles(userRoles);
        clientService.updateUser(user);
        return "redirect:/admin/admin_panel";
    }
}