/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche. With contributions by DU faculty.
 * File: JSONFileStream.java
 */
package edu.du.menascheraymond.model.services.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Implementation of the JSONService interface.
 * Default input file: target/JSON4361.json
 * Default output file: target/JSON4361.json
 * This implementation converts data from a JSON file into a list of maps.
 * This class follows the same conventions as the other persistent implementations.
 * If the JSON file does not provide an ACTION key a default ADD is added to 
 * the command. 
 * @author raymondmenasche
 */
public class JSONFileStream implements JSONService {
    private final String INPUTFILE;
    private final String OUTPUTFILE;
    
    public JSONFileStream() {
        INPUTFILE = "target/JSON4361.json";
        OUTPUTFILE = "target/JSON4361.json";
    }
    
    public JSONFileStream(String inputFile, String outputFile) {
        INPUTFILE = inputFile;
        OUTPUTFILE = outputFile;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public List<Map<String, String>> getCommands() {
        List<Map<String,String>> rv = new LinkedList<>();
        String jsonString = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUTFILE));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            jsonString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(jsonString);
        
        Set<String> jsonKeys = obj.keySet(); // gets upper keys (ex. Owner, Vehicle, etc...)
        for (String key: jsonKeys) {
            JSONArray ja = obj.getJSONArray(key); // array of objects within each key.
            for (int inx = 0; inx < ja.length(); inx++) {
                Map<String,String> data = new HashMap<>();
                data.put("TYPE", key);
                JSONObject jo = ja.getJSONObject(inx); // data for the object (ex. ownerId, firstName, etc...)
                Set<String> innerKeys = jo.keySet(); // keys for that object.
                for (String innerKey: innerKeys) {
                    Object o = jo.get(innerKey);
                    if (o instanceof Integer) {
                        Integer i = jo.getInt(innerKey);
                        data.put(innerKey, i.toString());
                    }
                    else if (o instanceof String) {
                        data.put(innerKey, jo.getString(innerKey));
                    }
                    else if (o instanceof Boolean) {
                        Boolean b = jo.getBoolean(innerKey);
                        if (b) {
                            data.put(innerKey, "Y");
                        } else {
                            data.put(innerKey, "N");
                        }
                    }
                    else { //Check to see if there is additional data within the object. (Ex. Address)
                        JSONObject additionalData = jo.getJSONObject(innerKey);
                        Set<String> additionalKeys = additionalData.keySet();
                        for (String aKey: additionalKeys) {
                            data.put(aKey, additionalData.getString(aKey));
                        }
                    }
                }
                if (!jo.has("ACTION")) {
                    data.put("ACTION", "ADD"); //defaults to add if not specified on JSON.
                }
                rv.add(data);
            }
        }
        return rv;
    }

    @Override
    public boolean sendCommands(List<LinkedHashMap<String, String>> cmns) {
        boolean rv = false;
        try {
            FileWriter file = new FileWriter(OUTPUTFILE);
            JSONObject carShowInfo = new JSONObject();
            //We create four JSONArrays for four diffirent types of data (ex. Owner, Vehicle, etc...)
            JSONArray firstArray = new JSONArray();
            JSONArray secondArray = new JSONArray();
            JSONArray thirdArray = new JSONArray();
            JSONArray fourthArray = new JSONArray();
            List<String> types = new ArrayList<>(); // maintain a list of types within our commands
            for (LinkedHashMap<String,String> cmd: cmns) {
                if (!types.contains(cmd.get("TYPE"))) {
                    types.add(cmd.get("TYPE")); //adds if it is not present.
                }
                String type = cmd.get("TYPE");
                cmd.remove("TYPE");
                //Check for address keys and store them in their own map:
                String[] addressKeys = {"street1", "street2", "city", "state", "zipCode"};
                LinkedHashMap<String,String> address = new LinkedHashMap<>();
                boolean isPresent = false;
                for (String k: addressKeys) {
                    if (cmd.containsKey(k)) {
                        address.put(k, cmd.get(k));
                        cmd.remove(k);
                        isPresent = true;
                    }
                }
                JSONObject obj = new JSONObject(cmd);
                if (isPresent) { //checks to see if address is present
                    JSONObject addrObj = new JSONObject(address);
                    JSONArray addressArray = new JSONArray();
                    addressArray.put(addrObj);
                    obj.put("Address", addrObj);
                }
                //add command to its corresponding type array
                if (types.size() > 0) {
                    if (types.get(0).equalsIgnoreCase(type)) {
                        firstArray.put(obj);
                    }
                }
                if (types.size() > 1) {
                    if (types.get(1).equalsIgnoreCase(type)) {
                        secondArray.put(obj);
                    }
                } 
                if (types.size() > 2) {
                    if (types.get(2).equalsIgnoreCase(type)) {
                        thirdArray.put(obj);
                    }
                }
                if (types.size() > 3) {
                    if (types.get(3).equalsIgnoreCase(type)) {
                        fourthArray.put(obj);
                    }
                }
                System.out.println(types);
            }
            //if array is not empty add it to the JSON object.
            if (firstArray.length() > 0) {
                carShowInfo.put(types.get(0), firstArray);
                rv = true;
            }
            if (secondArray.length() > 0) {
                carShowInfo.put(types.get(1), secondArray);
            }
            if (thirdArray.length() > 0) {
                carShowInfo.put(types.get(2), thirdArray);
            }
            if (fourthArray.length() > 0) {
                carShowInfo.put(types.get(3), fourthArray);
            }
            
            // integer value to toString will allow for "pretty printing"
            file.write(carShowInfo.toString(4));

            System.out.println(carShowInfo.toString(4));
            file.flush();
            file.close();
        } catch (Exception e1) {
            System.out.println("error " + e1.getMessage());
        }
        return rv;
    }
    
}
