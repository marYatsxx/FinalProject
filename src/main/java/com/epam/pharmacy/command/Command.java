package com.epam.pharmacy.command;

import com.epam.pharmacy.util.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    default String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType){
        if (ActionType.GET == actionType) {
            return doGet(request, response);
        } else {
            return  doPost(request, response);
        }
    }

    String doGet(HttpServletRequest request, HttpServletResponse response);
    String doPost(HttpServletRequest request, HttpServletResponse response);
}
