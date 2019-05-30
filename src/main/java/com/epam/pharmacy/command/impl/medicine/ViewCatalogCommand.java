package com.epam.pharmacy.command.impl.medicine;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.MedicineService;
import com.epam.pharmacy.service.factory.ServiceFactory;
import com.epam.pharmacy.util.ActionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * Forward to catalog page
 * @author Marina Yatsushkevich
 */

public class ViewCatalogCommand implements Command {
    public static final String REDIRECT_VIEW_CATALOG = "/pharmacy?command=viewCatalog&pageCount=1";
    public static final String FORWARD_VIEW_CATALOG = "/view/jsp/catalog.jsp";
    private static final String MEDICINE_LIST = "medicine_list";
    private static final String PAGE_AMOUNT = "pageAmount";
    public static final String PAGE_COUNT = "pageCount";

    /**
     *
     * @param request
     * @param response
     * @return path to catalog
     * @throws ServiceException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        MedicineService medicineService = factory.getMedicineService();
        List<Medicine> medicineList = medicineService.findAll();
        medicineList.sort((med, med2) -> med.getName().compareToIgnoreCase(med2.getName()));
        request.setAttribute(MEDICINE_LIST, medicineList);
        int pageAmount = medicineList.size() / 8 + 1;
        request.setAttribute(PAGE_AMOUNT, pageAmount);
        int pageCount = Integer.parseInt(request.getParameter(PAGE_COUNT));
        request.setAttribute(PAGE_COUNT, pageCount);
        Optional<String> medicine_id = Optional.ofNullable(request.getParameter(Medicine.ID));
        medicine_id.ifPresent(id -> request.setAttribute(Medicine.ID, id));
        return FORWARD_VIEW_CATALOG;
    }
}
