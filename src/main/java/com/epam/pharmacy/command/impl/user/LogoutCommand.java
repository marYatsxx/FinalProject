package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.impl.user.LoginCommand;
import com.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Invoked when user tries to log out
 *
 * @author Marina Yatsushkevich
 */

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession().invalidate();
        return LoginCommand.FORWARD_LOGIN_PAGE;
    }
}
