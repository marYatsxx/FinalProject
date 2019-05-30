package com.epam.pharmacy.command.impl.medicine;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import com.epam.pharmacy.util.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Forward to medicine page
 * author Marina Yatsushkevich
 */

public class ViewMedicineCommand implements Command {
    private static final String MEDICINE_LIST = "medicine_list";
    private static final String MEDICINE_TO_CHANGE = "changedMedicine";
    private static final String FORWARD_SHOW_MEDICINE = "/view/jsp/medicine.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        MedicineService medicineService = factory.getMedicineService();
        List<Medicine> medicineList = medicineService.findAll();
        request.setAttribute(MEDICINE_LIST, medicineList);
        Optional<String> medicineId = Optional.ofNullable(request.getParameter(Medicine.ID));
        if (medicineId.isPresent()) {
            int id = Integer.parseInt(medicineId.get());
            System.out.println("MEDICINE ID " + id);
            Medicine medicine = medicineService.findById(id).get();
            request.setAttribute(MEDICINE_TO_CHANGE, medicine);
        }
        return FORWARD_SHOW_MEDICINE;
    }
}
