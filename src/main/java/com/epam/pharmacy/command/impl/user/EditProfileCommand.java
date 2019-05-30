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

/**
 * Invoked when user wants to edit personal information
 *
 * @author Marina Yatsushkevich
 */

public class EditProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditProfileCommand.class);

    private static final String REPEAT_PASSWORD = "repeat_password";
    private static final String USER_REGISTRATION_ERROR = "Can not register user. Login already exists";
    private static final String PASSWORD_ERROR = "Password and repeated password not equal";
    private static final String REDIRECT_VIEW_PROFILE = "pharmacy?command=viewProfile";
    private static final String REDIRECT_EDIT_PROFILE = "pharmacy?command=viewEditProfile";

    /**
     *
     * @param request
     * @param response
     * @return Redirect to profile page
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        String login = request.getParameter(User.LOGIN);
        String password = request.getParameter(User.PASSWORD);
        String repeatedPassword = request.getParameter(REPEAT_PASSWORD);
        if (!password.equals(repeatedPassword)) {
            session.setAttribute("password_error", PASSWORD_ERROR);
            LOGGER.info("Error: password and repeated password not equal.");
            return REDIRECT_EDIT_PROFILE;
        } else {
            session.removeAttribute("password_error");
        }
        String name = request.getParameter(User.NAME);
        String surname = request.getParameter(User.SURNAME);

        User updatedUser = new User(user.getId(), login, password, name, surname, user.getRole(), false);
        UserService userService = factory.getUserService();
        if (!userService.create(updatedUser)) {
            LOGGER.info("Error: can not update user. User with login " + user.getLogin() + " already exists");
            session.setAttribute("login_error", USER_REGISTRATION_ERROR);
            return REDIRECT_EDIT_PROFILE;
        } else {
            session.removeAttribute("login_error");
        }
        session.setAttribute(User.USER, updatedUser);
        return REDIRECT_VIEW_PROFILE;
    }
}
