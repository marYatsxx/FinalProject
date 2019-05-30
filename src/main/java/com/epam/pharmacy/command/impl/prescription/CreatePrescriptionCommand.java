package com.epam.pharmacy.command.impl.prescription;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.PrescriptionService;
import com.epam.pharmacy.service.UserService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 * Invoked when doctor creates new prescription. Invoked when doctor creates new prescription
 *
 * @author Marina Yatsushkevich
 */

public class CreatePrescriptionCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreatePrescriptionCommand.class);
    private static final String REDIRECT_PRESCRIPTION_CREATION_PAGE = "pharmacy?command=viewPrescription&result=";

    /**
     *
     * @param request
     * @param response
     * @return path to prescription creation page with attribute result that is "success" if
     * creation was successfully complete and "failure" otherwise
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String result = null;
        ServiceFactory factory = new ServiceFactory();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        int doctorId = user.getId();
        int clientId = Integer.parseInt(request.getParameter(ClientAccount.CLIENT));
        int medicineId = Integer.parseInt(request.getParameter(Medicine.MEDICINE));
        LocalDate date = LocalDate.now();
        LocalDate validity = date.plusMonths(2);
        Prescription prescription = new Prescription(clientId, validity, medicineId, doctorId);
        PrescriptionService prescriptionService = factory.getPrescriptionService();
        if (prescriptionService.create(prescription)) {
            LOGGER.info("Prescription has been created successfully");
            result = "success";
        } else {
            LOGGER.info("Prescription creation failed");
            result = "failure";
        }
        return REDIRECT_PRESCRIPTION_CREATION_PAGE + result;
    }
}
