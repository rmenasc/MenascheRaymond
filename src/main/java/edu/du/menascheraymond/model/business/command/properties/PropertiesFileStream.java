/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * PropertiesFileStream.java
 */
package edu.du.menascheraymond.model.business.command.properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Implementation of the Command interface that handles a Properties.txt file
 * input and output.
 * Default output: target/propertiesinput.txt
 * Default input: target/propertiesoutput.txt
 * There must be at least one TYPE key and at least one ACTION key.
 * Only supports one command. Therefore, the list of commands will only include
 * one Map command. 
 * @author raymondmenasche
 */
public class PropertiesFileStream implements PropertiesCommand {
    private List<Map<String,String>> commands;
    private Properties data;
    private final String INPUTFILE;
    private final String OUTPUTFILE;
    
    public PropertiesFileStream() {
        commands = new LinkedList<>();
        data = new Properties();
        INPUTFILE = "target/propertiesinput.txt";
        OUTPUTFILE = "target/propertiesoutput.txt";
    }
    
    public PropertiesFileStream(String input, String output) {
        commands = new LinkedList<>();
        data = new Properties();
        INPUTFILE = input;
        OUTPUTFILE = output;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public List<Map<String, String>> getCommands() {
        try (FileReader reader = new FileReader(INPUTFILE))
        {
            data.load(reader);
            //converts properties to Map
            Map<String, String> mappedData = new HashMap<>();
            Set<String> keys = data.stringPropertyNames();
            Iterator<String> i = keys.iterator();
            while (i.hasNext()) {
                String key = i.next();
                mappedData.put(key, data.getProperty(key));
            }
            commands.add(mappedData);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        } catch (Exception e) {
            //handle here
        }
        return commands;
    }

    @Override
    public boolean sendCommands(List<LinkedHashMap<String, String>> cmns) {
        Map<String,String> command = cmns.get(0);
        for (String key: command.keySet()) {
            data.setProperty(key, command.get(key));
        }
        return sendProperties(data);
    }

    @Override
    public boolean sendProperties(Properties d) {
        boolean rv = false;
        try (FileWriter writer = new FileWriter(OUTPUTFILE))
        {
            d.store(writer, "OUTPUT");
            rv = true;
        } catch (IOException e) {
            
        }
        return rv;
    }

    @Override
    public Properties getProperties() {
        getCommands();
        return data;
    }
    
}
