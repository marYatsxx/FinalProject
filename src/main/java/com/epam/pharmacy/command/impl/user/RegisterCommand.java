package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.entity.UserRole;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import com.epam.pharmacy.util.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegisterCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final String REPEAT_PASSWORD = "repeat_password";

    private static final String USER_REGISTRATION_ERROR = "error.existing.login";
    private static final String TOO_EASY_PASSWORD = "error.easy.password";
    private static final String PASSWORD_AND_REPEATED_PASSWORD_NOT_EQUAL = "error.repeat";

    private static final String REDIRECT_VIEW_PROFILE = "pharmacy?command=viewProfile";
    private static final String REDIRECT_REGISTRATION_PAGE = "pharmacy?command=viewRegister";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String login = request.getParameter(User.LOGIN);
        String password = request.getParameter(User.PASSWORD);
        if (!Validator.validatePassword(password)) {
            LOGGER.error("Error: password must be at least 4 characters, no more than 8 characters.");
            session.setAttribute("password_error", TOO_EASY_PASSWORD);
            return REDIRECT_REGISTRATION_PAGE;
        } else {
            session.removeAttribute("password_error");
        }
        String repeatedPassword = request.getParameter(REPEAT_PASSWORD);
        if (!password.equals(repeatedPassword)) {
            LOGGER.error("Error: password and repeated password not equal.");
            session.setAttribute("password_error", PASSWORD_AND_REPEATED_PASSWORD_NOT_EQUAL);
            return REDIRECT_REGISTRATION_PAGE;
        } else {
            session.removeAttribute("password_error");
        }
        String name = request.getParameter(User.NAME);
        String surname = request.getParameter(User.SURNAME);
        Optional<String> userRole = Optional.ofNullable(request.getParameter(User.USER_ROLE_ID));
        String role = userRole.orElse(ClientAccount.CLIENT);

        User user = new User(login, password, name, surname, UserRole.valueOf(role.toUpperCase()), false);
        ServiceFactory factory = new ServiceFactory();
        UserService userService = factory.getUserService();
        if (!userService.register(user)) {
            LOGGER.error("Error: can not creat user. User with login " + user.getLogin() + " already exists");
            session.setAttribute("registration_error", USER_REGISTRATION_ERROR);
            return REDIRECT_REGISTRATION_PAGE;
        } else {
            session.removeAttribute("registration_error");

            if(session.getAttribute(User.USER)!=null){
                String result = "#ok";
                return REDIRECT_VIEW_PROFILE + result;
            } else {
                session.setAttribute(User.USER, user);
                return REDIRECT_VIEW_PROFILE;
            }
        }
    }
}
