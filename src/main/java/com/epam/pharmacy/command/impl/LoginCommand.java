package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    public static final String FORWARD_LOGIN_PAGE = "/view/jsp/login.jsp";
    private static final String REDIRECT_VIEW_HOMEPAGE = "pharmacy?command=viewHomePage";
    public static final String REDIRECT_LOGIN_PAGE = "pharmacy?command=login";
    private static final String INVALID_LOGIN_OR_PASSWORD = "Invalid login or password.";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response){
        return FORWARD_LOGIN_PAGE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        try (ServiceFactory factory = new ServiceFactory()) {
            factory.startTransaction();
            UserService userService = factory.getUserService();
            String login = request.getParameter(User.LOGIN);
            String password = request.getParameter(User.PASSWORD);
            Optional<User> user = userService.login(login, password);
            HttpSession session = request.getSession();
            if(user.isPresent()){
                session.setAttribute(User.USER, user.get());
            } else {
                session.setAttribute("error", INVALID_LOGIN_OR_PASSWORD);
                factory.rollback();
                return REDIRECT_LOGIN_PAGE;
            }
            factory.commit();
        }
        return REDIRECT_VIEW_HOMEPAGE;
    }
}
