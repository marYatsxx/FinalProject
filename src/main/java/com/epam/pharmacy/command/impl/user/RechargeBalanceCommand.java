package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.ClientService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import com.epam.pharmacy.util.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Invoked when user tries to recharge balance
 *
 * @author Marina Yatsushkevich
 */

public class RechargeBalanceCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RechargeBalanceCommand.class);

    private static final String REDIRECT_BALANCE_PAGE = "pharmacy?command=viewRechargePage&result=";
    private static final String VALIDITY = "validity";
    private static final String INCORRECT_VALIDITY = "error.validity";
    private static final String INCORRECT_AMOUNT = "error.amount";

    /**
     *
     * @param request
     * @param response
     * @return path to recharge balance page with attribute result that is "success" if
     * operation was successfully complete and "failure" otherwise
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        String balance = request.getParameter(ClientAccount.BALANCE);
        String validity = request.getParameter(VALIDITY);
        HttpSession session = request.getSession();
        boolean result = false;
        if(!Validator.validateValidityPeriod(validity)){
            session.setAttribute("balance_error", INCORRECT_VALIDITY);
            return REDIRECT_BALANCE_PAGE + result;
        } else {
            session.removeAttribute("balance_error");
        }
        User user = (User) session.getAttribute(User.USER);
        int userId = user.getId();
        ClientService clientService = factory.getClientService();
        if(clientService.rechargeBalance(userId, balance)){
            session.removeAttribute("balance_error");
            result = true;
        } else {
            session.setAttribute("balance_error", INCORRECT_AMOUNT);
        }
        return REDIRECT_BALANCE_PAGE + result;
    }
}
