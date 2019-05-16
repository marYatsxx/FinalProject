package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewHomePageCommand implements Command {
    private static final String FORWARD_HOMEPAGE = "/";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return FORWARD_HOMEPAGE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
