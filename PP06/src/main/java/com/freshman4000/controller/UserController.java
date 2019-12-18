package com.freshman4000.controller;

import com.freshman4000.validators.UDValidator;
import com.freshman4000.model.Role;
import com.freshman4000.model.User;
import com.freshman4000.service.ClientService;
import com.freshman4000.utility.CustomException;
import com.freshman4000.utility.FormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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
    @Qualifier("emailValidator")
    private UDValidator<User> udValidator;

    /**
     * All authenticated users going for slash-urls other than those, mentioned in HttpSecurity,
     * will be redirected to info.jsp by this controller.
     * @return standard bulk view - info.jsp.
     */
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String rootPage() {
        return "info";
    }

    /**
     * This controller is resp for redirecting to login view.
     * @return login view - login.jsp.
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * Controller for users with USER & ADMIN roles. It gets info about current user from
     * SecurityContextHolder, retrieving Authentication and hence retrieving 'username' from it.
     * @param model Spring model.
     * @return user view - hello.jsp.
     */
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = clientService.getUserByUserName(currentPrincipalName);
        model.addAttribute("user", user.toString());
        return "hello";
    }

    /**
     * This controller takes us to admin panel, gets all users from DB and renders them.
     * @param model Spring model.
     * @return standard starting view for admin - index.jsp.
     */
    @RequestMapping("/admin/admin_panel")
    public String goToIndexPage(Model model) {
        List<User> userList = clientService.showAllUsers();
        model.addAttribute("message", userList.isEmpty() ? "DB is empty" : FormGenerator.getHTML(userList));
        return "admin/index";
    }

    /**
     * Transfer controller to form where we add new user.
     * @return admin new user form addition view.
     */
    @RequestMapping("/admin/add_user_form")
    public String addUserForm() {
        return "admin/registration";
    }

    /**
     * This controller get meta from user addition form.
     * @param checkboxValue - array of values retrieved from checkboxes. Can't be not filled, cos exception will be thrown in this case.
     * @param user user object, pre-filled by @ModelAtt.
     * @return redirection to admin panel url and from there directly to index.page where changed users info will be rendered.
     * @throws CustomException - exception that can be thrown by udValidator method, in case that new user email - exists in DB.
     */
    @RequestMapping("/admin/add_user")
    public String addUser(@RequestParam("role")String[] checkboxValue, @ModelAttribute User user) throws CustomException {
        udValidator.validate(user);
        List<Role> userRoles = new ArrayList<>();
        Arrays.stream(checkboxValue).forEach(x -> userRoles.add(new Role(x)));
        user.setRoles(userRoles);
        clientService.addUser(user);
        return "redirect:/admin/admin_panel";
    }

    /**
     * Custom exception handler controller.
     * @param ex - object of CustomException.
     * @return ModelAndView object with added view (error.jsp) and exception message.
     */
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleResourceNotFoundException(CustomException ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exMessage", ex.getMessage());
        mv.setViewName("/admin/error");
        return mv;
    }

    /**
     * Controller that is resp for deletion of the user.
     * @param user - user - needed to be deleted.
     * @return redirection to admin_panel url and then to index.page with changed DB users info.
     */
    @RequestMapping("/admin/delete")
    public String deleteUser(@ModelAttribute User user) {
        clientService.deleteUser(user);
        return "redirect:/admin/admin_panel";
    }

    /**
     * This controller transfers user to update_view.
     * @param user - pre-filled by @ModelAttribute annotation user object, that is passed to ModelAndVIew object and
     *             further used for pre-filling user update form with existing values.
     * @return MAV object with defined view and object of user to be updated.
     */
    @RequestMapping("/admin/update_user_form")
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
     * @param checkboxValue - array of checkbox values.
     * @param previousEmail - previously settled user email.
     * @param user - new user pre-filled by @ModelAtt annotation.
     * @return redirection to admin panel controller and then to index view with changed DB of users.
     * @throws CustomException - exception that is handled by exception handler controller.
     */
    @RequestMapping("/admin/update")
    public String updateUser(@RequestParam("role") String[] checkboxValue,
                             @RequestParam("previousEmail") String previousEmail,
                             @ModelAttribute User user) throws CustomException {
        if (!previousEmail.equals(user.getEmail())) {
            udValidator.validate(user);
        }
        List<Role> userRoles = new ArrayList<>();
        Arrays.stream(checkboxValue).forEach(x -> userRoles.add(new Role(x)));
        user.setRoles(userRoles);
        clientService.updateUser(user);
        return "redirect:/admin/admin_panel";
    }
}