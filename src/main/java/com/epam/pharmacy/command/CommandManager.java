package com.epam.pharmacy.command;

import com.epam.pharmacy.command.impl.*;
import com.epam.pharmacy.command.impl.medicine.EditMedicineCommand;
import com.epam.pharmacy.command.impl.medicine.ViewCatalogCommand;
import com.epam.pharmacy.command.impl.medicine.ViewMedicineCommand;
import com.epam.pharmacy.command.impl.order.AddToOrderCommand;
import com.epam.pharmacy.command.impl.order.PayForOrdersCommand;
import com.epam.pharmacy.command.impl.order.ViewUserOrdersCommand;
import com.epam.pharmacy.command.impl.prescription.CheckPrescriptionCommand;
import com.epam.pharmacy.command.impl.prescription.CreatePrescriptionCommand;
import com.epam.pharmacy.command.impl.prescription.ViewPrescriptionCommand;
import com.epam.pharmacy.command.impl.request.ProcessRequestCommand;
import com.epam.pharmacy.command.impl.request.RenewRequestCommand;
import com.epam.pharmacy.command.impl.user.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final Logger LOGGER = LogManager.getLogger(CommandManager.class);

    private static Map<CommandType, Command> commands = new HashMap<>();

    static {
        commands.put(CommandType.NOCOMMAND, new NoCommand());
        commands.put(CommandType.LOGIN, new LoginCommand());
        commands.put(CommandType.LOGOUT, new LogoutCommand());
        commands.put(CommandType.VIEWPROFILE, new ViewProfileCommand());
        commands.put(CommandType.EDITPROFILE, new EditProfileCommand());
        commands.put(CommandType.VIEWLOGINPAGE, new ViewLoginPageCommand());
        commands.put(CommandType.VIEWEDITPROFILE, new ViewEditProfileCommand());
        commands.put(CommandType.VIEWREGISTER, new ViewRegisterPageCommand());
        commands.put(CommandType.REGISTER, new RegisterCommand());
        commands.put(CommandType.VIEWCATALOG, new ViewCatalogCommand());
        commands.put(CommandType.VIEWHOMEPAGE, new ViewHomePageCommand());
        commands.put(CommandType.ADDTOORDER, new AddToOrderCommand());
        commands.put(CommandType.CHECKPRESCRIPTION, new CheckPrescriptionCommand());
        commands.put(CommandType.VIEWPRESCRIPTION, new ViewPrescriptionCommand());
        commands.put(CommandType.VIEWRECHARGEPAGE, new ViewRechargePageCommand());
        commands.put(CommandType.RECHARGEBALANCE, new RechargeBalanceCommand());
        commands.put(CommandType.VIEWUSERORDERS, new ViewUserOrdersCommand());
        commands.put(CommandType.PAYFORORDERS, new PayForOrdersCommand());
        commands.put(CommandType.RENEWREQUEST, new RenewRequestCommand());
        commands.put(CommandType.CREATEPRESCRIPTION, new CreatePrescriptionCommand());
        commands.put(CommandType.PROCESSREQUEST, new ProcessRequestCommand());
        commands.put(CommandType.CHANGELANGUAGE, new ChangeLanguageCommand());
        commands.put(CommandType.VIEWMEDICINE, new ViewMedicineCommand());
        commands.put(CommandType.EDITUSERS, new EditUsersCommand());
        commands.put(CommandType.VIEWEMPLOYEES, new ViewEmployeesCommand());
        commands.put(CommandType.VIEWCLIENTS, new ViewClientsCommand());
        commands.put(CommandType.EDITMEDICINE, new EditMedicineCommand());
        LOGGER.info("Command container was successfully initialized. " +
                "Total number of commands: " + commands.size());
    }

    public static Command getCommand(String commandName){
        if (commandName == null) {
            LOGGER.trace("Command not found");
            return new NoCommand();
        }
        CommandType commandType = CommandType.NOCOMMAND;
        for(CommandType type: CommandType.values()){
            if(commandName.equalsIgnoreCase(type.name())){
                commandType = CommandType.valueOf(commandName.toUpperCase());
                if (!commands.containsKey(commandType)) {
                    LOGGER.trace("Command " + commandName + " not found");
                    return commands.get(CommandType.NOCOMMAND);
                }
            }
        }

        return commands.get(commandType);
    }
}
