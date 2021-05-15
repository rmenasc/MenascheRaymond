/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.business.factory;

import edu.du.menascheraymond.model.business.command.Command;
import java.lang.reflect.Constructor;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author raymondmenasche
 */
public class CommandFactoryImpl implements CommandFactory {
    private Properties commandConfiguration;
    
    public CommandFactoryImpl() {
        getConfigurationFile();
    }
    
    @Override
    public Command getCommand(String type) {
        Command rv = null;
        String className = commandConfiguration.getProperty(type.toUpperCase());
        if (className != null) {
            try {
                Class<Command> claz = (Class<Command>)Class.forName(className);
                if (claz != null) {
                    Constructor<Command> constructor = claz.getConstructor(new Class[0]);
                    Command command = constructor.newInstance();
                    rv = command;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return rv;
    }
    
    private void getConfigurationFile() {
        try (FileReader reader = new FileReader("target/commandproperties.txt"))
        {
            commandConfiguration = new Properties();
            commandConfiguration.load(reader);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
