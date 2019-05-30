package com.epam.pharmacy.command.impl.prescription;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.impl.medicine.ViewCatalogCommand;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.PrescriptionService;
import com.epam.pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Invoked when client tries to buy medicine which needs prescription
 *
 * @author Marina Yatsushkevich
 */

public class CheckPrescriptionCommand implements Command {
    private static final String HAS_PRESCRIPTION = "hasPrescription";

    /**
     * if prescription found redirect to catalog page with attribute hasPrescription=true, else hasPrescription=false
     * @param request
     * @param response
     * @return path to prescription creation page
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        Integer user_id = user.getId();
        Integer medicineId = Integer.parseInt(request.getParameter(Medicine.ID));
        PrescriptionService prescriptionService = factory.getPrescriptionService();
        Optional<Prescription> prescription = prescriptionService.findPrescriptionByClientAndMedicine(user_id, medicineId);
        boolean hasPrescription = prescription.isPresent();
        request.setAttribute(Medicine.ID, medicineId);
        request.setAttribute(HAS_PRESCRIPTION, hasPrescription);
        return ViewCatalogCommand.REDIRECT_VIEW_CATALOG;
    }
}
