package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LogoutCommand implements Command {

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession().invalidate();
        return LoginCommand.FORWARD_LOGIN_PAGE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
