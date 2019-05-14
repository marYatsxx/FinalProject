package com.epam.pharmacy.command;

import com.epam.pharmacy.command.impl.*;
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
        commands.put(CommandType.REGISTER, new RegisterCommand());
        commands.put(CommandType.VIEWCATALOG, new ViewCatalogCommand());
        commands.put(CommandType.VIEWHOMEPAGE, new ViewHomePageCommand());
        LOGGER.info("Command container was successfully initialized. " +
                "Total number of commands: " + commands.size());
    }

    public static Command getCommand(String commandName){
        if (commandName == null) {
            LOGGER.trace("Command not found");
            return new NoCommand();
        }
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        if (!commands.containsKey(commandType)) {
            LOGGER.trace("Command " + commandName + " not found");
            return commands.get(CommandType.NOCOMMAND);
        }
        return commands.get(commandType);
    }
}
