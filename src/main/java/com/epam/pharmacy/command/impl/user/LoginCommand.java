package com.epam.pharmacy.command.impl.user;

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

/**
 * Invoked when user tries to log in
 *
 * @author Marina Yatsushkevich
 */

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    public static final String FORWARD_LOGIN_PAGE = "/view/jsp/login.jsp";
    private static final String REDIRECT_VIEW_HOMEPAGE = "pharmacy?command=viewHomePage";
    public static final String REDIRECT_LOGIN_PAGE = "pharmacy?command=viewLoginPage";
    private static final String INVALID_LOGIN_OR_PASSWORD = "error.login";
    private static final String DENIED_ACCESS = "error.denied";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        UserService userService = factory.getUserService();
        String login = request.getParameter(User.LOGIN);
        String password = request.getParameter(User.PASSWORD);
        Optional<User> user = userService.login(login, password);
        HttpSession session = request.getSession();
        if (user.isPresent()) {
            if(user.get().isBlocked()){
                session.setAttribute("error", DENIED_ACCESS);
                return REDIRECT_LOGIN_PAGE;
            } else {
                session.setAttribute(User.USER, user.get());
                session.removeAttribute("error");
            }
        } else {
            session.setAttribute("error", INVALID_LOGIN_OR_PASSWORD);
            return REDIRECT_LOGIN_PAGE;
        }
        return REDIRECT_VIEW_HOMEPAGE;
    }
}
