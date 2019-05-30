package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ViewClientsCommand implements Command {
    private static final String CLIENT_ID = "clientId";
    private static final String CLIENTS = "clients";
    private static final String DECISION = "decision";

    private static final String FORWARD_CLIENTS_PAGE = "/view/jsp/clients.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        UserService userService = factory.getUserService();
        List<User> clients = userService.findAllClients();
        request.setAttribute(CLIENTS, clients);
        Optional<String> clientId = Optional.ofNullable(request.getParameter(CLIENT_ID));
        clientId.ifPresent(doc -> request.setAttribute(CLIENT_ID, doc));
        Optional<String> decision = Optional.ofNullable(request.getParameter(DECISION));
        decision.ifPresent(dec -> request.setAttribute(DECISION, dec));
        return FORWARD_CLIENTS_PAGE;
    }
}
