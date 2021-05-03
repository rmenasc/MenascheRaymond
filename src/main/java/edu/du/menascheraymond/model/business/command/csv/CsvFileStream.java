/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * CvsFileStream.java
 */
package edu.du.menascheraymond.model.business.command.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Command interface to handle CSV File input and output.
 * Default: Reads from target/CarShowData.txt
 * Default: Writes to targe/CarShowDataOutput.txt
 * The commands are presented in the form of a List of maps where each map
 * includes a key and value. Each map contains at least a key including the
 * type of object (ex. Owner, CarShow, etc) and one key with an action
 * (ex. ADD, REM, etc). The rest of the key and values would change depending
 * on the object being passed. Each Map within the list will be converted to 
 * a comma separated line. Therefore, each map is a command within a list of 
 * Commands.
 * The CSV line should always start with a type followed by the action.
 * (ex. TYPE=OWNER,ACTION=ADD,key=VALUE,key=VALUE)
 * @author raymondmenasche
 */
public class CsvFileStream implements CsvCommand {
    private List<Map<String, String>> commands;
    private final String OUTPUTFILE;
    private final String INPUTFILE;
    
    public CsvFileStream() {
        commands = new LinkedList<>();
        OUTPUTFILE = "target/CarShowDataOutput.txt";
        INPUTFILE = "target/CarShowData.txt";
    }
    
    public CsvFileStream(String inputFile, String outputFile) {
        this.commands = new LinkedList<>();
        this.OUTPUTFILE = outputFile;
        this.INPUTFILE = inputFile;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public List<Map<String,String>> getCommands() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTFILE)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                Map<String,String> command = new LinkedHashMap<>();
                String[] data = line.split(",");
                if (data.length >= 3) {
                    for (String value: data) {
                        String[] keyValue = value.split("=");
                        if (keyValue.length == 2) {
                            command.put(keyValue[0].strip(), keyValue[1]);
                        } else {
                            throw new IllegalArgumentException("Illegal key value pair");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Data size mismatch");
                }
                commands.add(command);
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
        return commands;
    }

    @Override
    public boolean sendCommands(List<LinkedHashMap<String,String>> cmds) {
        boolean rv = false;
        String line = "";
        for (LinkedHashMap<String,String> command: cmds) {
            int counter = 0;
            for (String key: command.keySet()) {
                if (counter == 0) {
                    line += key + "=" + command.get(key);
                } else {
                    line += "," + key + "=" + command.get(key);
                }
                counter++;
            }
            line += "\n";
        }
        try (FileWriter fw = new FileWriter(OUTPUTFILE))
        {
            fw.write(line);
            rv = true;
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
        return rv;
    }
    
}
