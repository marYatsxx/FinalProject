package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProfileCommand implements Command {
    private static final String FORWARD_EDIT_PROFILE = "/view/jsp/editProfile.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        return FORWARD_EDIT_PROFILE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
