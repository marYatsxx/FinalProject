package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.ClientService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RechargeBalanceCommand implements Command {
    private static final String FORWARD_BALANCE_PAGE = "/view/jsp/balance.jsp";
    private static final String REDIRECT_BALANCE_PAGE = "pharmacy?command=rechargeBalance&result=true";
    private static final String RESULT = "result";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Optional<String> result = Optional.ofNullable(request.getParameter(RESULT));
        result.ifPresent(r -> request.setAttribute(RESULT, r));
        return FORWARD_BALANCE_PAGE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
            double balance = Double.parseDouble(request.getParameter(ClientAccount.BALANCE));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.USER);
            ClientService clientService = factory.getClientService();
            ClientAccount clientAccount = clientService.findById(user.getId()).get();
            clientAccount.setBalance(balance);
            clientService.update(clientAccount);
        }
        return REDIRECT_BALANCE_PAGE;
    }
}
