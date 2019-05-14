package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.InvalidMarkException;

public class LogoutCommand implements Command {

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "/view/jsp/login.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
