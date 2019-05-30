package com.epam.pharmacy.command.impl.prescription;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Forward to prescription creation page.
 */

public class ViewPrescriptionCommand implements Command {
    private static final String FORWARD_PRESCRIPTION_CREATION_PAGE = "/view/jsp/prescription.jsp";
    private static final String CLIENTS = "clients";
    private static final String MEDICINES = "medicines";
    private static final String RESULT = "result";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        UserService userService = factory.getUserService();
        List<User> clients = userService.findAllClients();
        clients.sort((user, user2) -> user.getName().compareToIgnoreCase(user2.getName()));
        request.setAttribute(CLIENTS, clients);
        MedicineService medicineService = factory.getMedicineService();
        List<Medicine> medicines = medicineService.findMedicineWithPrescription();
        medicines.sort((med, med2) -> med.getName().compareToIgnoreCase(med2.getName()));
        request.setAttribute(MEDICINES, medicines);
        String result = request.getParameter(RESULT);
        request.setAttribute(RESULT, result);

        return FORWARD_PRESCRIPTION_CREATION_PAGE;
    }
}
