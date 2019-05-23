package com.epam.pharmacy.command.impl;

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

public class UserOrdersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserOrdersCommand.class);
    private static final String ORDERS = "orders";
    private static final String RESULT = "result";
    private static final String MEDICINES = "medicines";
    private static final String FORWARD_VIEW_ORDERS = "/view/jsp/order.jsp";
    private static final String REDIRECT_USER_ORDERS = "pharmacy?command=userOrders&result=";


    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.USER);
            Integer user_id = user.getId();
            String result = request.getParameter(RESULT);
            factory.startTransaction();
            OrderService orderService = factory.getOrderService();
            List<Order> orderList = orderService.findOrderByClientId(user_id);
            List<Medicine> medicineList = new ArrayList<>();
            MedicineService medicineService = factory.getMedicineService();
            for (Order order: orderList) {
                Optional<Medicine> medicine = medicineService.findById(order.getMedicineId());
                if(!medicine.isPresent()){
                    LOGGER.info("Medicine not found. Order will be cancelled.");
                    orderList.remove(order);
                } else {
                    medicineList.add(medicine.get());
                }
            }
            factory.commit();
            request.setAttribute(ORDERS, orderList);
            request.setAttribute(MEDICINES, medicineList);
            request.setAttribute(RESULT, result);
        }
        return FORWARD_VIEW_ORDERS;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String result;
        try(ServiceFactory factory = new ServiceFactory()){
            factory.startTransaction();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.USER);
            Integer user_id = user.getId();
            ClientService clientService = factory.getClientService();
            ClientAccount account = clientService.findById(user_id).get();
            BigDecimal balance = account.getBalance();
            Optional<String[]> chosen = Optional.ofNullable(request.getParameterValues("select"));
            if(chosen.isPresent()){
                OrderService orderService = factory.getOrderService();
                MedicineService medicineService = factory.getMedicineService();
                for (String strId: chosen.get()) {
                    int orderId = Integer.parseInt(strId);
                    Order order = orderService.findById(orderId).get();
                    int medicine_id = order.getMedicineId();
                    Medicine medicine = medicineService.findById(medicine_id).get();
                    BigDecimal price = medicine.getPrice();
                    if(balance.compareTo(price)==1){
                        balance = balance.subtract(price);
                        orderService.updateOrderStatusById(true, order.getId());
                    } else {
                        result = "failure";
                        factory.rollback();
                        return REDIRECT_USER_ORDERS + result;
                    }
                }
                account.setBalance(balance);
                clientService.update(account);
                result = "success";
            } else {
                result = "emptyChoice";
            }
            factory.commit();
        }
        return REDIRECT_USER_ORDERS + result;
    }
}
