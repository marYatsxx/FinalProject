package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(NoCommand.class);
    private static final String ERROR_PAGE = "/view/jsp/error.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        return ERROR_PAGE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
