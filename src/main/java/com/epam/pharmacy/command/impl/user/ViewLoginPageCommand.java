package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.pharmacy.command.impl.user.LoginCommand.FORWARD_LOGIN_PAGE;

public class ViewLoginPageCommand implements Command {
    private static final String FORWARD_LOGIN_PAGE = "/view/jsp/login.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return FORWARD_LOGIN_PAGE;
    }
}
