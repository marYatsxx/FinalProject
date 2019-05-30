package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ViewEmployeesCommand implements Command {
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEES = "employees";
    private static final String DECISION = "decision";

    private static final String FORWARD_EMPLOYEES_PAGE = "/view/jsp/employees.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        UserService userService = factory.getUserService();
        List<User> employees = userService.findAllDoctors();
        employees.addAll(userService.findAllPharmacists());
        request.setAttribute(EMPLOYEES, employees);
        Optional<String> employeeId = Optional.ofNullable(request.getParameter(EMPLOYEE_ID));
        employeeId.ifPresent(doc -> request.setAttribute(EMPLOYEE_ID, doc));
        Optional<String> decision = Optional.ofNullable(request.getParameter(DECISION));
        decision.ifPresent(dec -> request.setAttribute(DECISION, dec));
        return FORWARD_EMPLOYEES_PAGE;
    }
}
