package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.ClientService;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ViewProfileCommand implements Command {
    public static final String FORWARD_VIEW_PROFILE = "/view/jsp/profile.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        UserService userService = factory.getUserService();
        Optional<User> createdUser = userService.findUserByLogin(user.getLogin());
        Integer user_id = createdUser.get().getId();
        session.setAttribute(User.NAME, user.getName());
        session.setAttribute(User.SURNAME, user.getSurname());
        session.setAttribute(User.LOGIN, user.getLogin());
        int role_id = user.getUserRoleId();
        session.setAttribute(User.USER_ROLE_ID, role_id);
        if(role_id == User.CLIENT_ID){
            ClientService clientService = factory.getClientService();
            Optional<ClientAccount> clientAccount = clientService.findById(user_id);
            session.setAttribute(ClientAccount.BALANCE, clientAccount.get().getBalance());
        }
        }
        return FORWARD_VIEW_PROFILE;
    }
    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
