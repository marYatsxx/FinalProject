package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.entity.UserRole;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.ClientService;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class EditProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditProfileCommand.class);
    private static final String FORWARD_EDIT_PROFILE = "/view/jsp/editProfile.jsp";
    private static final String REPEAT_PASSWORD = "repeat_password";
    private static final String USER_REGISTRATION_ERROR = "Can not register user. Login already exists";
    private static final String PASSWORD_ERROR = "Password and repeated password not equal";
    private static final String REDIRECT_VIEW_PROFILE = "pharmacy?command=viewProfile";
    private static final String REDIRECT_EDIT_PROFILE = "pharmacy?command=editProfile";


    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        return FORWARD_EDIT_PROFILE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
            factory.startTransaction();
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(User.USER);
            String login = request.getParameter(User.LOGIN);
            String password = request.getParameter(User.PASSWORD);
            String repeatedPassword = request.getParameter(REPEAT_PASSWORD);
            if(!password.equals(repeatedPassword)){
                session.setAttribute("password_error", PASSWORD_ERROR);
                LOGGER.info("Error: password and repeated password not equal.");
                factory.rollback();
                return REDIRECT_EDIT_PROFILE;
            } else {
                session.removeAttribute("password_error");
            }
            String name = request.getParameter(User.NAME);
            String surname = request.getParameter(User.SURNAME);

            User updatedUser = new User(user.getId(), login, password, name, surname, user.getRole());
            UserService userService = factory.getUserService();
            if(!userService.create(updatedUser)){
                LOGGER.info("Error: can not update user. User with login " + user.getLogin() + " already exists");
                session.setAttribute("login_error", USER_REGISTRATION_ERROR);
                factory.rollback();
                return REDIRECT_EDIT_PROFILE;
            } else {
                session.removeAttribute("login_error");
            }
            factory.commit();
            session.setAttribute(User.USER, updatedUser);
        }
        return REDIRECT_VIEW_PROFILE;
    }
}
