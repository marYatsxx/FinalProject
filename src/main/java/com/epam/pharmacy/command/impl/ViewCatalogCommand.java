package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCatalogCommand implements Command {
    private static final String FORWARD_VIEW_CATALOG = "/view/jsp/catalog.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        return FORWARD_VIEW_CATALOG;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
