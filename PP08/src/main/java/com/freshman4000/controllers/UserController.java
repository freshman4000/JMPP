package com.freshman4000.controllers;

import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.validators.UDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private UDValidator<User> udValidator;

    /**
     * All authenticated users going for slash-urls other than those, mentioned in HttpSecurity,
     * will be redirected to info.jsp by this controller.
     *
     * @return standard bulk view - info.jsp.
     */
    @GetMapping("/")
    public String rootPage() {
        return "info";
    }

    /**
     * This controller is resp for redirecting to login view.
     *
     * @return login view - login.jsp.
     */
    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    /**
     * Controller for users with USER & ADMIN roles. It gets info about current user from
     * SecurityContextHolder, retrieving Authentication and hence retrieving 'username' from it.
     *
     * @param model Spring model.
     * @return user view - hello.jsp.
     */
    @GetMapping("/hello")
    public String printWelcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = clientService.getUserByUserName(currentPrincipalName);
        model.addAttribute("user", user);
        return "hello";
    }

    /**
     * This controller takes us to admin panel, gets all users from DB and renders them.
     *
     * @param model Spring model.
     * @return standard starting view for admin - index.jsp.
     */
    @GetMapping("/admin/admin_panel")
    public String goToIndexPage(Model model) {
        model.addAttribute("users", clientService.showAllUsers());
        return "admin/index";
    }

    /**
     * Transfer controller to form where we add new user.
     *
     * @return admin new user form addition view.
     */
    @GetMapping("/admin/add_user_form")
    public String addUserForm() {
        return "admin/registration";
    }

    /**
     * This controller get meta from user addition form.
     *
     * @param checkboxValue - array of values retrieved from checkboxes. Can't be not filled, cos exception will be thrown in this case.
     * @param user          user object, pre-filled by @ModelAtt.
     * @return redirection to admin panel url and from there directly to index.page where changed users info will be rendered.
     */
    @PostMapping("/admin/add_user")
    public String addUser(@RequestParam("role") String[] checkboxValue, @ModelAttribute User user, Model model) {
        if (!udValidator.validate(user)) {
            model.addAttribute("messageEx", "Email is registered already!");
            return "/admin/error";
        } else {
            List<Role> userRoles = new ArrayList<>();
            Arrays.stream(checkboxValue).forEach(x -> userRoles.add(new Role(x)));
            user.setRoles(userRoles);
            clientService.addUser(user);
            return "redirect:/admin/admin_panel";
        }
    }

    /**
     * Controller that is resp for deletion of the user.
     *
     * @param user - user - needed to be deleted.
     * @return redirection to admin_panel url and then to index.page with changed DB users info.
     */
    @PostMapping("/admin/delete")
    public String deleteUser(@ModelAttribute User user) {
        clientService.deleteUser(user);
        return "redirect:/admin/admin_panel";
    }

    /**
     * This controller transfers user to update_view.
     *
     * @param user - pre-filled by @ModelAttribute annotation user object, that is passed to ModelAndVIew object and
     *             further used for pre-filling user update form with existing values.
     * @return MAV object with defined view and object of user to be updated.
     */
    @GetMapping("/admin/update_user_form")
    public ModelAndView updateUserForm(@ModelAttribute User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/update_user");
        mv.addObject(user);
        return mv;
    }

    /**
     * This controller retrieves checkbox values via forming array of strings. It also retrieves previous email param
     * in sake of validation logic, and, finally, forms updated user object, using @ModelAttribute annotation. The tricky thing is
     * that when we want to update existing user email via using udValidator for email validation - - it checks for email value in
     * DB and throws exception if it exists in order to prevent duplicate values. So, before validation, we check, if the previous
     * email equals to new email. If they equal - we understand, that user didn't update email and we can skip validation, and in
     * opposite case - we do validation, so that user couldn't use already existing email from DB.
     *
     * @param checkboxValue - array of checkbox values.
     * @param previousEmail - previously settled user email.
     * @param user          - new user pre-filled by @ModelAtt annotation.
     * @return redirection to admin panel controller and then to index view with changed DB of users.
     */
    @PostMapping("/admin/update")
    public String updateUser(@RequestParam("role") String[] checkboxValue,
                             @RequestParam("previousEmail") String previousEmail,
                             @RequestParam("previousPass") String previousPass,
                             @ModelAttribute User user,
                             Model model) {
        boolean validated = true;
        if (!previousEmail.equals(user.getEmail())) {
            validated = udValidator.validate(user);
        }
        if (validated) {
            List<Role> userRoles = new ArrayList<>();
            Arrays.stream(checkboxValue).forEach(x -> userRoles.add(new Role(x)));
            user.setRoles(userRoles);
            if (user.getPassword().equals("")) {
                user.setPassword(previousPass.concat("same"));
            }
            clientService.updateUser(user);
            return "redirect:/admin/admin_panel";
        } else {
            model.addAttribute("messageEx", "Email is registered already!");
            return "/admin/error";
        }
    }
}