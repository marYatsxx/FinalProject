package com.epam.pharmacy.command.impl.request;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.entity.Request;
import com.epam.pharmacy.entity.RequestStatus;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.PrescriptionService;
import com.epam.pharmacy.service.RequestService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class ProcessRequestCommand implements Command {
    private static final String REQUEST_ID = "request_id";
    private static final String DECISION = "decision";
    private static final String REDIRECT_VIEW_PROFILE = "pharmacy?command=viewProfile&requests=true";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        String decision = request.getParameter(DECISION);
        int requestId = Integer.parseInt(request.getParameter(REQUEST_ID));
        RequestService requestService = factory.getRequestService();
        requestService.processRequest(requestId, decision);
        return REDIRECT_VIEW_PROFILE;
    }
}
