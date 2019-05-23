package com.epam.pharmacy.command.impl;

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

public class CreatePrescriptionCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreatePrescriptionCommand.class);

    private static final String FORWARD_PRESCRIPTION_CREATION_PAGE = "/view/jsp/prescription.jsp";
    private static final String REDIRECT_PRESCRIPTION_CREATION_PAGE = "pharmacy?command=createPrescription&result=";
    private static final String CLIENTS = "clients";
    private static final String MEDICINES = "medicines";
    private static final String RESULT = "result";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try(ServiceFactory factory = new ServiceFactory()){
            UserService userService = factory.getUserService();
            List<User> clients = userService.findAllClients();
            request.setAttribute(CLIENTS, clients);
            MedicineService medicineService = factory.getMedicineService();
            List<Medicine> medicines = medicineService.findMedicineWithPrescription();
            request.setAttribute(MEDICINES, medicines);
            String result = request.getParameter(RESULT);
            request.setAttribute(RESULT, result);
        }
        return FORWARD_PRESCRIPTION_CREATION_PAGE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String result = null;
        try(ServiceFactory factory = new ServiceFactory()){
            factory.startTransaction();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.USER);
            int doctorId = user.getId();
            int clientId = Integer.parseInt(request.getParameter(ClientAccount.CLIENT));
            int medicineId = Integer.parseInt(request.getParameter(Medicine.MEDICINE));
            LocalDate date = LocalDate.now();
            LocalDate validity = date.plusMonths(2);
            Prescription prescription = new Prescription(clientId, validity, medicineId, doctorId);
            PrescriptionService prescriptionService = factory.getPrescriptionService();
            if(prescriptionService.create(prescription)){
                LOGGER.info("Prescription has been created successfully");
                result = "success";
                factory.commit();
            } else {
                LOGGER.info("Prescription creation failed");
                result = "failure";
                factory.rollback();
            }
        }
        return REDIRECT_PRESCRIPTION_CREATION_PAGE + result;
    }
}
