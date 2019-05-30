package com.epam.pharmacy.command.impl.order;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.ClientService;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.OrderService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Invoked when user pays for orders
 *
 * @author Marina Yatsushkevich
 */

public class PayForOrdersCommand implements Command {
    private static final String REDIRECT_USER_ORDERS = "pharmacy?command=viewUserOrders&result=";
    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";
    private static final String EMPTY_CHOICE = "emptyChoice";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String result;
        ServiceFactory factory = new ServiceFactory();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        Integer user_id = user.getId();
        Optional<String[]> chosen = Optional.ofNullable(request.getParameterValues("select"));
        ClientService clientService = factory.getClientService();
        ClientAccount account = clientService.findById(user_id).get();
        BigDecimal balance = account.getBalance();
        if (chosen.isPresent()) {
            OrderService orderService = factory.getOrderService();
            String payResult = orderService.payForOrders(chosen.get(), balance);
            if(payResult.equals(FAILURE)){
                result = FAILURE;
                return REDIRECT_USER_ORDERS + result;
            }
            balance = new BigDecimal(payResult);
            account.setBalance(balance);
            clientService.update(account);
            result = SUCCESS;
        } else {
            result = EMPTY_CHOICE;
        }
        return REDIRECT_USER_ORDERS + result;
    }
}
