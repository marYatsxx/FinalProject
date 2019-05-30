package com.epam.pharmacy.command.impl.order;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.impl.order.PayForOrdersCommand;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.OrderService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Forward to client profile with open order list
 *
 * @author Marina Yatsushkevich
 */

public class ViewUserOrdersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PayForOrdersCommand.class);
    private static final String ORDERS = "orders";
    private static final String RESULT = "result";
    private static final String MEDICINES = "medicines";
    private static final String FORWARD_VIEW_ORDERS = "/view/jsp/order.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        Integer user_id = user.getId();
        String result = request.getParameter(RESULT);
        OrderService orderService = factory.getOrderService();
        List<Order> orderList = orderService.findOrderByClientId(user_id);
        List<Medicine> medicineList = new ArrayList<>();
        MedicineService medicineService = factory.getMedicineService();
        if(orderList.size()>0){
            for (Order order : orderList) {
                Optional<Medicine> medicine = medicineService.findById(order.getMedicineId());
                medicine.ifPresent(medicineList::add);
            }
        }
        request.setAttribute(ORDERS, orderList);
        request.setAttribute(MEDICINES, medicineList);
        request.setAttribute(RESULT, result);
        return FORWARD_VIEW_ORDERS;
    }
}
