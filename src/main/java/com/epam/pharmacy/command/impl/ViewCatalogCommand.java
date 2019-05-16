package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewCatalogCommand implements Command {
    private static final String FORWARD_VIEW_CATALOG = "/view/jsp/catalog.jsp";
    private static final String MEDICINE_LIST = "medicine_list";
    private static final String PAGE_AMOUNT = "pageAmount";
    private static final String PAGE_COUNT = "pageCount";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        try (ServiceFactory factory = new ServiceFactory()) {
            MedicineService medicineService = factory.getMedicineService();
            List<Medicine> medicineList = medicineService.findAll();
            request.setAttribute(MEDICINE_LIST, medicineList);
            int pageAmount = medicineList.size()/8 + 1;
            request.setAttribute(PAGE_AMOUNT, pageAmount);
            int pageCount = Integer.parseInt(request.getParameter(PAGE_COUNT));
            request.setAttribute(PAGE_COUNT, pageCount);
        }
        return FORWARD_VIEW_CATALOG;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }
}
