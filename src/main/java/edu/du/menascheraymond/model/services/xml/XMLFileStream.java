/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche. With contributions by DU faculty.
 * File: XMLFileStream.java
 */
package edu.du.menascheraymond.model.services.xml;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implementation of the XMLService interface. 
 * Default input file: target/XML4361.xml
 * Default output file: target/XML4361.xml
 * This implementation turns data from an XML file into a List of maps following
 * the same convention as the other persistence implementations. 
 * Each Map object will have an Action key and a Type key. If the XML file
 * does not provide the Action value a default value of ADD would be supplied.
 * @author raymondmenasche
 */
public class XMLFileStream implements XMLService {
    private final String INPUTFILE;
    private final String OUTPUTFILE;
    
    public XMLFileStream() {
        INPUTFILE = "target/XML4361.xml";
        OUTPUTFILE = "target/XML4361.xml";
    }
    
    public XMLFileStream(String inputFile, String outputFile) {
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
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(INPUTFILE);
            if (file.exists()) {
                Document doc = db.parse(file);
                Element rootElement = doc.getDocumentElement();
                NodeList childNodes = rootElement.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) { // gets upper object ex. Owner, Vehicle, etc...
                    Node upperNode = childNodes.item(i);
                    if (upperNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element upperElement = (Element)upperNode;
                        Map<String,String> map = new HashMap<>();
                        map.put("TYPE", upperElement.getTagName());
                        NodeList innerChildNodes = upperElement.getChildNodes();
                        for (int idx = 0; idx < innerChildNodes.getLength(); idx++) { //gets inner object data ex. ownerId, firstName, etc...
                            Node innerNode = innerChildNodes.item(idx);
                            if (innerNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element innerElement = (Element)innerNode;
                                NodeList additionalData = innerElement.getChildNodes();
                                if (additionalData.getLength() > 1) { //case of Address or other additional data
                                    for (int x = 0; x < additionalData.getLength(); x++) {
                                        Node an = additionalData.item(x);
                                        if (an.getNodeType() == Node.ELEMENT_NODE) {
                                            Element ae = (Element)an;
                                            map.put(ae.getTagName(), ae.getTextContent());
                                        }
                                    }
                                } else {
                                    map.put(innerElement.getTagName(), innerElement.getTextContent()); //actual data (ex. ownerId, firstName, etc...)
                                }
                            }
                        }
                        if (!map.containsKey("ACTION") && !map.isEmpty()) {
                            map.put("ACTION", "ADD"); //default action
                        }
                        rv.add(map);
                    }
                }

            }
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("exception " + e1.getMessage());
        }
        return rv;
    }

    @Override
    public boolean sendCommands(List<LinkedHashMap<String, String>> cmns) {
        boolean rv = false;
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("CarShowData");
            document.appendChild(rootElement);
            
            for (LinkedHashMap<String,String> command: cmns) {
                Element childElement = document.createElement(command.get("TYPE"));
                command.remove("TYPE");
                rootElement.appendChild(childElement);
                Element addressElement = document.createElement("Address"); //in case of address
                boolean isPresent = false;
                for (String key: command.keySet()) {
                    //Check for address elementst so they can be put into their own elelment
                    String[] addressKeys = {"street1", "street2", "city", "state", "zipCode"};
                    Element innerElement = document.createElement(key);
                    innerElement.appendChild(document.createTextNode(command.get(key)));
                    for (String k: addressKeys) {
                        if (k.equalsIgnoreCase(key)) {
                            isPresent = true;
                            break;
                        } 
                    }
                    
                    if (isPresent) { //checks if address elements are present
                        addressElement.appendChild(innerElement);
                    } else {
                        childElement.appendChild(innerElement);
                    }
                }
                if (isPresent) {
                    childElement.appendChild(addressElement);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Indentation
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(OUTPUTFILE));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            rv = true;

        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException
            | DOMException e) {
            System.out.println(e.getMessage());

        }
        return rv;
    }
    
}
