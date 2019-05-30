package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewEditProfileCommand implements Command {
    private static final String FORWARD_EDIT_PROFILE = "/view/jsp/editProfile.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return FORWARD_EDIT_PROFILE;
    }
}
