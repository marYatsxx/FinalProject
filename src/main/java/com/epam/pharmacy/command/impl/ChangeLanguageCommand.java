package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static final String LANGUAGE = "language";
    private static final String REFERER = "referer";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String language = request.getParameter(LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE, language.toLowerCase());
        return request.getHeader(REFERER);
    }
}
