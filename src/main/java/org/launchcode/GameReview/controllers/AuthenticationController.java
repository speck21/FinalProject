package org.launchcode.GameReview.controllers;

import org.launchcode.GameReview.data.UserRepository;
import org.launchcode.GameReview.models.User;
import org.launchcode.GameReview.models.dto.LoginFormDTO;
import org.launchcode.GameReview.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey="user";

    public User getUserFromSession(HttpSession session){
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if(userId == null){
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("register")
    public String displayRegisterUserForm(Model model){
        model.addAttribute("title", "Register User");
        model.addAttribute(new RegisterFormDTO());
        return "register";
    }

    @PostMapping("register")
    public String processRegisterUserForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO, Errors errors, Model model, HttpServletRequest request){
        if(errors.hasErrors()){
            model.addAttribute("title", "Register User");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if(existingUser!= null){
            errors.rejectValue("username", "username.alreadyexists", "Username already exists.");
            model.addAttribute("title", "Register User");
            return "register";
        }
        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if(!password.equals(verifyPassword)){
            errors.rejectValue("password", "password.mismatch", "Passwords must match");
            model.addAttribute("title", "Register User");
            return "register";
        }
        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return "redirect:";
    }

    @GetMapping("login")
    public String displayLoginForm(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute(new LoginFormDTO());
        return "login";
    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, Model model, HttpServletRequest request){
        if(errors.hasErrors()){
            model.addAttribute("title", "Login");
            return "login";
        }
        User loginUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if(loginUser==null){
            errors.rejectValue("username", "username.dne", "Username does not exist.");
            model.addAttribute("title", "Login");
            return "login";
        }
        String password = loginFormDTO.getPassword();

        if(!loginUser.isMatchingPassword(password)){
            errors.rejectValue("password", "password.invalid", "Invalid Password");
            model.addAttribute("title", "Login");
            return "login";
        }

        setUserInSession(request.getSession(), loginUser);
        return "redirect:/titles";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }


}


















