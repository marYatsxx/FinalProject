package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ViewRechargePageCommand implements Command {
    private static final String FORWARD_BALANCE_PAGE = "/view/jsp/balance.jsp";
    private static final String RESULT = "result";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Optional<String> result = Optional.ofNullable(request.getParameter(RESULT));
        result.ifPresent(r -> request.setAttribute(RESULT, r));
        return FORWARD_BALANCE_PAGE;
    }
}
