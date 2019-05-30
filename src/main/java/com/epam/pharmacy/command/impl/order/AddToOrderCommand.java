package com.epam.pharmacy.command.impl.order;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.impl.medicine.ViewCatalogCommand;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.OrderService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import com.epam.pharmacy.util.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * Invoked when user creates new order
 *
 * @author Marina Yatsushkevich
 */

public class AddToOrderCommand implements Command {

    /**
     * @param request
     * @param response
     * @return path to catalog
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        Integer user_id = user.getId();
        LocalDate date = LocalDate.now();
        Integer medicine_id = Integer.parseInt(request.getParameter(Medicine.ID));
        OrderService orderService = factory.getOrderService();
        Order order = new Order(date, user_id, medicine_id, false);
        boolean result = orderService.create(order);
        request.setAttribute("result", result);
        return ViewCatalogCommand.REDIRECT_VIEW_CATALOG;
    }
}
