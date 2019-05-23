package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.OrderService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import jdk.nashorn.internal.ir.Optimistic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AddToOrderCommand implements Command {

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try(ServiceFactory factory = new ServiceFactory()){
            factory.startTransaction();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.USER);
            Integer user_id = user.getId();
            LocalDate date = LocalDate.now();
            Integer medicine_id = Integer.parseInt(request.getParameter(Medicine.ID));
            OrderService orderService = factory.getOrderService();
            Order order = new Order(date, user_id, medicine_id, false);
            boolean result = orderService.create(order);
            request.setAttribute("result", result);
            factory.commit();
        }
        return ViewCatalogCommand.REDIRECT_VIEW_CATALOG;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
