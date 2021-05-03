/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.business.factory;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.services.Service;
import java.lang.reflect.Constructor;

/**
 *
 * @author raymondmenasche
 */
public class CommandFactory {
    public Command getCommand(String commandClass) throws Exception {
        Class<Command> claz = (Class<Command>)Class.forName(commandClass);
        if (claz != null) {
            Constructor<Command> constructor = claz.getConstructor(new Class[0]);
            Command command = constructor.newInstance();
            return command;
        }
        return null;
    }
}
