package com.epam.pharmacy.command.impl.medicine;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import com.epam.pharmacy.util.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Invoked when pharmacist wants to edit medicine list
 *
 * @author Marina Yatsushkevich
 */

public class EditMedicineCommand  implements Command {
    private static final String YES = "yes";
    private static final String DELETE = "delete";
    private static final String REDIRECT_VIEW_MEDICINE = "pharmacy?command=viewMedicine&result=";

    /**
     * @param request
     * @param response
     * @return path to medicine page
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        MedicineService service = factory.getMedicineService();

        Optional<String> id = Optional.ofNullable(request.getParameter(Medicine.ID));
        if(id.isPresent()){
            int medicineId = Integer.parseInt(id.get());
            Optional<String> deleteMedicine = Optional.ofNullable(request.getParameter(DELETE));
            if(deleteMedicine.isPresent()){
                boolean result = service.removeById(medicineId);
                return REDIRECT_VIEW_MEDICINE + result;
            }
        }

        String name = request.getParameter(Medicine.NAME);
        String dosage = request.getParameter(Medicine.DOSAGE);
        String strPrice = request.getParameter(Medicine.PRICE);
        BigDecimal price = new BigDecimal(strPrice).setScale(2, BigDecimal.ROUND_DOWN);
        String prescription = request.getParameter(Medicine.NEEDS_PRESCRIPTION);
        boolean needsPrescription = prescription.equalsIgnoreCase(YES);
        Medicine medicine = null;
        if(id.isPresent()){
            int medicineId = Integer.parseInt(id.get());
            medicine = new Medicine(medicineId, name, dosage, price, needsPrescription);
        } else {
            medicine = new Medicine(name, dosage, price, needsPrescription);
        }
        boolean result = service.create(medicine);
        return REDIRECT_VIEW_MEDICINE + result;
    }
}
