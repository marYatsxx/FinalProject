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

public class RegisterCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final String REPEAT_PASSWORD = "repeat_password";
    private static final String USER_REGISTRATION_ERROR = "Can not register user. Login already exists";
    private static final String CLIENT_REGISTRATION_ERROR = "Can not create client account";
    private static final String PASSWORD_ERROR = "Password and repeated password not equal";
    private static final String REDIRECT_VIEW_PROFILE = "pharmacy?command=viewProfile";
    private static final String FORWARD_REGISTER = "/view/jsp/registration.jsp";
    private static final String REDIRECT_REGISTRATION_PAGE = "pharmacy?command=register";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response){
        return FORWARD_REGISTER;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
            factory.startTransaction();
            HttpSession session = request.getSession();
            String login = request.getParameter(User.LOGIN);
            String password = request.getParameter(User.PASSWORD);
            String repeatedPassword = request.getParameter(REPEAT_PASSWORD);
            if(!password.equals(repeatedPassword)){
                LOGGER.error("Error: password and repeated password not equal.");
                session.setAttribute("password_error", PASSWORD_ERROR);
                return REDIRECT_REGISTRATION_PAGE;
            } else {
                session.removeAttribute("password_error");
            }
            String name = request.getParameter(User.NAME);
            String surname = request.getParameter(User.SURNAME);
            String role = request.getParameter(User.USER_ROLE_ID);

            User user = new User(login, password, name, surname, UserRole.valueOf(role.toUpperCase()));
            UserService userService = factory.getUserService();
            if(!userService.create(user)){
                LOGGER.error("Error: can not creat user. User with login " + user.getLogin() + " already exists");
                session.setAttribute("registration_error", USER_REGISTRATION_ERROR);
                return REDIRECT_REGISTRATION_PAGE;
            } else {
                session.removeAttribute("registration_error");
            }
            if(user.getUserRoleId() == User.CLIENT_ID){
                Optional<User> createdUser = userService.findUserByLogin(user.getLogin());
                user.setId(createdUser.get().getId());
                double initialBalance = 0;
                ClientAccount clientAccount = new ClientAccount(user.getId(), initialBalance);
                ClientService clientService = factory.getClientService();
                if(!clientService.create(clientAccount)) {
                    LOGGER.error("Error: can not create client account.");
                    session.setAttribute("client_registration_error", CLIENT_REGISTRATION_ERROR);
                    factory.rollback();
                    return REDIRECT_REGISTRATION_PAGE;
                } else {
                    session.removeAttribute("client_registration_error");
                }
            }
            factory.commit();
            session.setAttribute(User.USER, user);
        }
        return REDIRECT_VIEW_PROFILE;
    }
}
