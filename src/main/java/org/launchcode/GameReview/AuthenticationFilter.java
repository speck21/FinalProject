package org.launchcode.GameReview;

import org.launchcode.GameReview.controllers.AuthenticationController;
import org.launchcode.GameReview.data.UserRepository;
import org.launchcode.GameReview.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class AuthenticationFilter extends HandlerInterceptorAdapter {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");

    //Method for the prehandle to check if a page is whitelisted
    private static boolean isWhiteListed(String path){
        for(String pathroot:whitelist){
            if(path.startsWith(pathroot)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //Don't require login for whitelisted pages
        if(isWhiteListed(request.getRequestURI())){
            return true;
        }
        //get the user from the session (if they exist)
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        //if user is logged in
        if(user != null){
            return true;
        }
        //else if not logged in
        response.sendRedirect("/login");
        return false;
    }
}
