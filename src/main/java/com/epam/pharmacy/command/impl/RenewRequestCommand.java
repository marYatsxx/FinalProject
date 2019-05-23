package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.*;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.OrderService;
import com.epam.pharmacy.service.RequestService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RenewRequestCommand implements Command {
    public static final String REDIRECT_VIEW_PROFILE = "/pharmacy?command=viewProfile&prescriptions=true";
    private static final String RESULT = "result";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try(ServiceFactory factory = new ServiceFactory()){
            factory.startTransaction();
            Integer prescriptionId = Integer.parseInt(request.getParameter(Prescription.ID));
            Request renewRequest = new Request(prescriptionId, RequestStatus.UNDER_CONSIDERATION);
            RequestService requestService = factory.getRequestService();
            boolean result = requestService.create(renewRequest);
            request.setAttribute(RESULT, result);
            factory.commit();
        }
        return REDIRECT_VIEW_PROFILE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
