package com.freshman4000.controllers;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.utility.AuthGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping({"/","/info"})
    public String rootPage() {
        return "info";
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/error")
    public String errorPage() {
        return "admin/error";
    }

    @GetMapping("/hello")
    public String printWelcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = restTemplate.postForObject("http://localhost:8090/api/getAuth/" + currentPrincipalName, AuthGetter.getEntity("jsonObject"), User.class);
        List<Role> list = new ArrayList<>(user.getRoles());
        model.addAttribute("list", list);
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping("/admin/admin_panel")
    public String goToIndexPage() {
        return "admin/admin_panel";
    }

    @GetMapping("/admin/add_user_form")
    public String addUserForm() {
        return "admin/add_new_user";
    }


    @GetMapping("/admin/update_user_form")
    public ModelAndView updateUserForm(@ModelAttribute User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/admin_panel");
        mv.addObject(user);
        return mv;
    }
}