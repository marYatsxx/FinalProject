package com.epam.pharmacy.controller;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.CommandManager;
import com.epam.pharmacy.util.ActionType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response, ActionType.GET);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response, ActionType.POST);
    }

    private String process(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
                                                                                throws ServletException, IOException {
        String command = request.getParameter("command");
        String page;
        Command action = CommandManager.getCommand(command);
        try {
            page = action.execute(request, response, actionType);
            if(actionType == ActionType.GET){
                dispatch(request, response, page);
            } else {
                response.sendRedirect(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            page = "/view/jsp/error.jsp";
        }
        return page;
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}