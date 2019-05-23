package com.epam.pharmacy.command.impl;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.*;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.*;
import com.epam.pharmacy.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewProfileCommand.class);
    public static final String FORWARD_VIEW_PROFILE = "/view/jsp/profile.jsp";
    private static final String PRESCRIPTIONS = "prescriptions";
    private static final String DOCTOR_PRESCRIPTIONS = "doctorPrescriptions";
    private static final String REQUESTS = "requests";
    private static final String MEDICINES = "medicines";
    private static final String DOCTORS = "doctors";
    private static final String CLIENTS = "clients";
    private static final String DECISION = "decision";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
            factory.startTransaction();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(User.USER);
            UserService userService = factory.getUserService();
            Optional<User> createdUser = userService.findUserByLogin(user.getLogin());
            Integer userId = createdUser.get().getId();
            session.setAttribute(User.NAME, user.getName());
            session.setAttribute(User.SURNAME, user.getSurname());
            session.setAttribute(User.LOGIN, user.getLogin());
            int role_id = user.getUserRoleId();
            session.setAttribute(User.USER_ROLE_ID, role_id);
            if (role_id == User.CLIENT_ID) {
                ClientService clientService = factory.getClientService();
                Optional<ClientAccount> clientAccount = clientService.findById(userId);
                session.setAttribute(ClientAccount.BALANCE, clientAccount.get().getBalance());
            }
            Optional<String> prescriptions = Optional.ofNullable(request.getParameter(PRESCRIPTIONS));
            if (prescriptions.isPresent()) {
                viewClientProfile(request, response, userId);
            }
            Optional<String> requests = Optional.ofNullable(request.getParameter(REQUESTS));
            if (requests.isPresent()) {
                viewDoctorProfile(request, response, userId);
            }
            factory.commit();
        }
        return FORWARD_VIEW_PROFILE;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

    private void viewClientProfile(HttpServletRequest request, HttpServletResponse response, int userId)
                                                                                        throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
            request.removeAttribute(PRESCRIPTIONS);
            PrescriptionService prescriptionService = factory.getPrescriptionService();
            List<Prescription> prescriptionList = prescriptionService.findPrescriptionByClientId(userId);
            if(prescriptionList.size()==0){
                LOGGER.info("No prescriptions found");
                //TODO add attribute
                return;
            }
            MedicineService medicineService = factory.getMedicineService();
            UserService userService = factory.getUserService();
            List<Medicine> medicineList = new ArrayList<>();
            List<User> doctors = new ArrayList<>();
            for (Prescription prescription : prescriptionList) {
                Optional<Medicine> medicine = medicineService.findById(prescription.getMedicineId());
                if (!medicine.isPresent()) {
                    LOGGER.info("Medicine not found. Prescription will be deleted.");
                    prescriptionList.remove(prescription);
                } else {
                    medicineList.add(medicine.get());
                }
                int doctorId = prescription.getDoctorId();
                User doctor = userService.findById(doctorId).get();
                doctors.add(doctor);
            }
            request.setAttribute(PRESCRIPTIONS, prescriptionList);
            request.setAttribute(MEDICINES, medicineList);
            request.setAttribute(DOCTORS, doctors);
            Optional<String> prescriptionId = Optional.ofNullable(request.getParameter(Prescription.ID));
            prescriptionId.ifPresent(id -> request.setAttribute(Prescription.ID, id));
        }
    }

    private void viewDoctorProfile(HttpServletRequest request, HttpServletResponse response, int userId)
                                                                                        throws ServiceException {
        try (ServiceFactory factory = new ServiceFactory()) {
            request.removeAttribute(REQUESTS);
            RequestService requestService = factory.getRequestService();
            List<Request> requests = requestService.findConsideredRequests();
            PrescriptionService prescriptionService = factory.getPrescriptionService();
            List<Request> chosenRequests = new ArrayList<>();
            List<Prescription> chosenPrescriptions = new ArrayList<>();
            for (Request renewRequest: requests) {
                Optional<Prescription> prescription = prescriptionService.findById(renewRequest.getPrescriptionId());
                if(prescription.isPresent()){
                    int doctorId = prescription.get().getDoctorId();
                    if(doctorId==userId){
                        chosenPrescriptions.add(prescription.get());
                        chosenRequests.add(renewRequest);
                    }
                }
            }
            MedicineService medicineService = factory.getMedicineService();
            List<Medicine> medicineList = new ArrayList<>();
            UserService userService = factory.getUserService();
            List<User> clients = new ArrayList<>();
            for (Prescription prescription : chosenPrescriptions) {
                Optional<Medicine> medicine = medicineService.findById(prescription.getMedicineId());
                medicineList.add(medicine.get());
                Optional<User> client = userService.findById(prescription.getClientId());
                clients.add(client.get());
            }
            request.setAttribute(REQUESTS, chosenRequests);
            request.setAttribute(DOCTOR_PRESCRIPTIONS, chosenPrescriptions);
            request.setAttribute(MEDICINES, medicineList);
            request.setAttribute(CLIENTS, clients);

            Optional<String> decision = Optional.ofNullable(request.getParameter(DECISION));
            decision.ifPresent(d->request.setAttribute(DECISION, d));
            Optional<String> requestId = Optional.ofNullable(request.getParameter(Request.ID));
            requestId.ifPresent(id -> request.setAttribute(Request.ID, id));
        }
    }
}
