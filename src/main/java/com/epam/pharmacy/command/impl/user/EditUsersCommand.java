package com.epam.pharmacy.command.impl.user;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.entity.UserRole;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

/**
 * Invoked when admin edits user information. Users can be blocked (unblocked) and deleted
 *
 * @author Marina Yatsushkevich
 */

public class EditUsersCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String DECISION = "decision";
    private static final String BLOCK = "block";
    private static final String UNBLOCK = "unblock";
    private static final String DELETE = "delete";

    private static final String REDIRECT_CLIENTS_PAGE = "pharmacy?command=viewClients";
    private static final String REDIRECT_EMPLOYEES_PAGE = "pharmacy?command=viewEmployees";

    /**
     *
     * @param request
     * @param response
     * @return path to client page if clients were edited and employees page otherwise
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        UserService userService = factory.getUserService();
        User user = userService.findById(userId).get();
        UserRole role = user.getRole();
        String decision = request.getParameter(DECISION);
        switch (decision) {
            case BLOCK:
                userService.changeBlockingStatus(true, userId);
                break;
            case UNBLOCK:
                userService.changeBlockingStatus(false, userId);
                break;
            case DELETE:
                userService.removeById(userId);
                break;
            default:
                throw new NoSuchElementException("Wrong \"decision\" parameter value");
        }

        if(role == UserRole.CLIENT){
            return REDIRECT_CLIENTS_PAGE;
        } else {
            return REDIRECT_EMPLOYEES_PAGE;
        }
    }
}
