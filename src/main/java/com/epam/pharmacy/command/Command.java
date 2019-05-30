package com.epam.pharmacy.command;

import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.util.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
