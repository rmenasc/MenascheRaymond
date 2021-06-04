/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: MainController.java
 */
package edu.du.menascheraymond.controller.maincontroller;

import edu.du.menascheraymond.controller.carshowcontroller.CarShowController;
import edu.du.menascheraymond.controller.ownercontroller.OwnerController;
import edu.du.menascheraymond.controller.vehiclecontroller.VehicleController;
import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.business.factory.CommandFactory;
import edu.du.menascheraymond.model.business.factory.CommandFactoryImpl;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.services.carshowownerservice.CarShowOwnerArrayListImpl;
import edu.du.menascheraymond.model.services.carshowownerservice.CarShowOwnerService;
import edu.du.menascheraymond.model.services.carshowservice.CarShowService;
import edu.du.menascheraymond.model.services.ownerservice.OwnerService;
import edu.du.menascheraymond.view.CarShowFrame;
import edu.du.menascheraymond.view.MainMenuFrame;
import edu.du.menascheraymond.view.OwnerFrame;
import edu.du.menascheraymond.view.VehicleFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * MainController
 * @author raymond
 */
public class MainController implements ActionListener, WindowListener {
    
    private MainMenuFrame mainMenuFrame = null;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private CarShowOwnerService carShowOwnerBackUpCollection = 
            new CarShowOwnerArrayListImpl();
    
    public MainController() {
        
    }
    
    public MainController(MainMenuFrame mainMenuFrame) {
        this.mainMenuFrame = mainMenuFrame;
        //creates the backup collection
        for (String oid: mainMenuFrame.getManager().getOwnerCollection().getIds()) {
            for (String csid: mainMenuFrame.getManager().getCarShowCollection().getIds()) {
                if (mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .isPresent(oid, csid)) {
                    carShowOwnerBackUpCollection.add(new CarShowOwner(csid, oid));
                }
            }
        }
        carShowOwnerBackUpCollection = mainMenuFrame.getManager().getCarShowOwnerCollection();
        
        mainMenuFrame.addWindowListener(this);
        mainMenuFrame.getExitItem().addActionListener(this);
        mainMenuFrame.getUndoResetMenuItem().addActionListener(this);
        mainMenuFrame.getOwnerMenuItem().addActionListener(this);
        mainMenuFrame.getCarShowMenuItem().addActionListener(this);
        mainMenuFrame.getOwnerMenuItem().addActionListener(this);
        mainMenuFrame.getVehicleMenuItem().addActionListener(this);
        mainMenuFrame.getOwnerSearchButton().addActionListener(this);
        mainMenuFrame.getCarShowSearchButton().addActionListener(this);
        mainMenuFrame.getAddCarShowOwnerButton().addActionListener(this);
        mainMenuFrame.getRemoveCarShowOwnerButton().addActionListener(this);
        mainMenuFrame.getPringItem().addActionListener(this);
        mainMenuFrame.getSaveItem().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource().equals(mainMenuFrame.getExitItem())) {
                exitItem_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getUndoResetMenuItem())) {
                undoReset_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getOwnerSearchButton())) {
                searchOwner_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getCarShowSearchButton())) {
                searchCarShow_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getOwnerMenuItem())) {
                openOwner_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getCarShowMenuItem())) {
                openCarShow_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getVehicleMenuItem())) {
                openVehicle_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getAddCarShowOwnerButton())) {
                addCarShowOwner_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getRemoveCarShowOwnerButton())) {
                removeCarShowOwner_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getPringItem())) {
                printReport_actionPerformed(ae);
            } else if (ae.getSource().equals(mainMenuFrame.getSaveItem())) {
                save_actionPerformed(ae);
            }
        } catch(Exception e) {
            
        }
    }
    
    private void exitItem_actionPerformed(ActionEvent event) throws Exception {
        saveBeforeCloseDialog();
        
        System.exit(0);
    }
    
    private void undoReset_actionPerformed(ActionEvent event) {
        int selection = JOptionPane.showConfirmDialog(mainMenuFrame,
                "Are you sure you want to undo all the changes made to CarShowOwners?",
                "Undo Reset",
                JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION) {
            OwnerService os = mainMenuFrame.getManager().getOwnerCollection();
            CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
            CarShowOwnerService csos = mainMenuFrame.getManager().getCarShowOwnerCollection();
            //Replace all initial object into main collecion.
//            for (String oid: os.getIds()) {
//                for (String csid: css.getIds()) {
//                    CarShowOwner cso = new CarShowOwner(csid, oid);
//                    if (carShowOwnerBackUpCollection.isPresent(oid, csid)) {
//                        csos.add(cso);
//                    } else {
//                        csos.remove(cso);
//                    }
//                }
//            }
            Iterator<String> oi = os.getIds().iterator();
            Iterator<String> csi = css.getIds().iterator();
            while (oi.hasNext()) {
                String oid = csi.next(); 
                while (csi.hasNext()) {
                    String csid = csi.next();
                    CarShowOwner cso = new CarShowOwner(csid, oid);
                    if (carShowOwnerBackUpCollection.isPresent(cso)) {
                        csos.add(cso);
                    } else {
                        csos.remove(cso);
                    }
                }
            }
            loadCarShowOwnerList();
        }
    }
    
    private void searchOwner_actionPerformed(ActionEvent event) {
        OwnerService owners = mainMenuFrame.getManager().getOwnerCollection();
        String searchText = mainMenuFrame.getOwnerSearchField().getText();
        if (owners.isPresent(searchText)) {
            mainMenuFrame.setSelectedOwner(owners.find(mainMenuFrame.getOwnerSearchField().getText()));
            mainMenuFrame.getOwnerSearchResultLabel().setText
                    (mainMenuFrame.getSelectedOwner().getOwnerId() +
                            ": " + mainMenuFrame.getSelectedOwner().getFirstName() +
                            " " + mainMenuFrame.getSelectedOwner().getLastName());
            loadCarShowOwnerList();
        } else {
            mainMenuFrame.getOwnerSearchResultLabel().setText("No Results");
            loadCarShowOwnerList();
        }
    }
    
    private void searchCarShow_actionPerformed(ActionEvent event) {
        String searchId = mainMenuFrame.getCarShowSearchField().getText();
        if (mainMenuFrame.getManager().getCarShowCollection().isPresent(searchId)) {
            mainMenuFrame.setSelectedCarShow(mainMenuFrame.getManager().getCarShowCollection()
                    .find(mainMenuFrame.getCarShowSearchField().getText()));
            mainMenuFrame.getCarShowSearchResultLabel()
                    .setText(mainMenuFrame.getSelectedCarShow()
                    .getCarShowTitle());
        } else {
            mainMenuFrame.getCarShowSearchResultLabel().setText("No Result");
        }
    }
    
    private void openOwner_actionPerformed(ActionEvent event) {
        OwnerFrame ownerFrame = new OwnerFrame();
        ownerFrame.setVisible(true);
        OwnerController ownerController = new OwnerController(ownerFrame, mainMenuFrame);
    }
    
    private void openCarShow_actionPerformed(ActionEvent event) {
        CarShowFrame carShowFrame = new CarShowFrame();
        carShowFrame.setVisible(true);
        CarShowController carShowController = new CarShowController(mainMenuFrame, carShowFrame);
    }
    
    private void openVehicle_actionPerformed(ActionEvent event) {
        VehicleFrame vehicleFrame = new VehicleFrame();
        vehicleFrame.setVisible(true);
        VehicleController vehicleController = new VehicleController(mainMenuFrame, vehicleFrame);
    }
    
    private void addCarShowOwner_actionPerformed(ActionEvent event) {
        String ownerResult = mainMenuFrame.getOwnerSearchResultLabel().getText();
        String carShowResult = mainMenuFrame.getCarShowSearchResultLabel().getText();
        if (ownerResult.equals("No Result") || ownerResult.equals("")) {
            JOptionPane.showMessageDialog(mainMenuFrame, "Please selecte an owner frist");
        } else {
            if (carShowResult.equals("No Result") || carShowResult.equals("")) {
                JOptionPane.showMessageDialog(mainMenuFrame, "Please select a Car Show first");
            } else {
                CarShowOwner cso = new CarShowOwner(mainMenuFrame
                        .getSelectedCarShow().getCarShowId(),
                        mainMenuFrame.getSelectedOwner().getOwnerId());
                mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .add(cso);
                loadCarShowOwnerList();
            }
        }
    }
    
    private void removeCarShowOwner_actionPerformed(ActionEvent event) {
        //parse string ex. Owner Id: O1, CarShow Id: CS1, Title: The great show
        String[] sa = mainMenuFrame.getCarShowOwnerList().getSelectedValue().split(",");
        String[] ownerValues = sa[0].split(":");
        String[] carShowValues = sa[1].split(":");
        String ownerId = ownerValues[1].strip();
        String carShowId = carShowValues[1].strip();

        mainMenuFrame.getManager().getCarShowOwnerCollection()
                .remove(ownerId, carShowId);
        loadCarShowOwnerList();
    }
    
    private void printReport_actionPerformed(ActionEvent event) {
        List<String> fileWriterLines = new ArrayList<>();
        Manager manager = mainMenuFrame.getManager();
        List<String> owners = manager.getOwnerCollection().getIds();
        List<String> vehicles = manager.getVehicleCollection().getIds();
        List<String> carShows = manager.getCarShowCollection().getIds();
        for (String oID: owners) {
            if (manager.getOwnerCollection().isPresent(oID)) {
                Owner o = manager.getOwnerCollection().find(oID);
                String special = "";
                if (o.isSeniorOwner()) {
                    special = "**SO**";
                }
                fileWriterLines.add("OWNER " + o.getFirstName() + " " + o.getLastName()
                        + " " + special + "\n");
                fileWriterLines.add("VEHICLES\n");
                for (String vID: vehicles) {
                    Vehicle v = manager.getVehicleCollection().find(vID);
                    if (v.getOwnerId().equals(oID)) {
                        fileWriterLines.add("\t" + v.getModelYear() + " "
                                + v.getManufacturer() + " " + v.getModel()
                                + " " + v.getSubModel() + "\n");
                    }
                }
                fileWriterLines.add("CAR SHOWS\n");
                for (String cID: carShows) {
                    if (manager.getCarShowOwnerCollection().isPresent(oID, cID)) {
                        CarShow carShow = manager.getCarShowCollection().find(cID);
                        String exclamations = "";
                        if (carShow.isSanctioned()) {
                            exclamations = "!!!!";
                        }
                        fileWriterLines.add("\t" + carShow.getCarShowTitle() + " " 
                                + exclamations + "\n");
                    }
                }
                fileWriterLines.add("\n");
            }
        }
        try (FileWriter fw = new FileWriter("target/OwnerReport.txt"))
        {
            for (String line: fileWriterLines) {
                fw.write(line);
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
    
    private void save_actionPerformed(ActionEvent event) {
        List<LinkedHashMap<String,String>> commands = new LinkedList<>();
        
        String[] types = {"OWNER", "VEHICLE", "CARSHOW", "CSO"};
        for (String type: types) {
            CommandFactory cf = new CommandFactoryImpl();
            Command c = cf.getCommand(type);
            if (c != null) {
                c.setManager(mainMenuFrame.getManager());
                commands.addAll(c.createCommand());
            }
        }
        if (mainMenuFrame.getManager().getPersistenceService().sendCommands(commands)) {
            System.out.println("Save Successful");
        } else {
            System.out.println("Could not save data");
        }
    }
    
    private void loadCarShowOwnerList() {
        CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
        listModel.removeAllElements();
        for (String id: css.getIds()) {
            if (mainMenuFrame.getManager().getCarShowOwnerCollection()
                    .isPresent(mainMenuFrame.getSelectedOwner().getOwnerId(), id)) {
                listModel.addElement("Owner id: " + 
                        mainMenuFrame.getSelectedOwner().getOwnerId() +
                        ", Car Show id: " + id + ", Title: " +
                        css.find(id).getCarShowTitle());
            }
        }
        mainMenuFrame.getCarShowOwnerList().setModel(listModel);
        mainMenuFrame.getCarShowOwnerListLabel()
                .setText(mainMenuFrame.getSelectedOwner().getFirstName() +
                        "'s Car Shows:");
    }
    
    private void saveBeforeCloseDialog() {
        int selection = JOptionPane.showConfirmDialog(mainMenuFrame,
                "Do you want to save before exit?", "Wait",
                JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION) {
            save_actionPerformed(null);
        } 
    }
    
    private void logInPanel() {
        int counter = 0;
        boolean locked = true;
        while (counter < 3 && locked) {
            JPanel panel = new JPanel(new GridLayout(5,1));
            JLabel user = new JLabel("User Name");
            JTextField userName = new JTextField(10);
            JLabel pass = new JLabel("Password");
            JPasswordField password = new JPasswordField(10);
            panel.add(user);
            panel.add(userName);
            panel.add(pass);
            panel.add(password);

            int result = JOptionPane.showConfirmDialog(mainMenuFrame, panel,
                    "Log In", JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if(authenticateUser(userName.getText(), password.getPassword())){
                    unlockUI();
                    locked = false;
                } else {
                    lockUI();
                    JOptionPane.showMessageDialog(mainMenuFrame, "Invalid credentials");
                }
            }
            counter++;
        }
        if (counter == 3 && locked) {
            System.exit(0);
        }
    }
    
    public boolean authenticateUser(String user, char[] password) {
        boolean rv = false;
        try (FileReader reader = new FileReader("target/authproperties.txt"))
        {
            Properties authProps = new Properties();
            authProps.load(reader);
            char[] pass = authProps.getProperty("password").toCharArray();
            if (authProps.getProperty("user").equals(user) 
                    && Arrays.equals(pass, password)) {
                rv = true;
            }
        } catch (IOException ioe) {
            
        }
        return rv;
    }
    
    private void lockUI() {
        mainMenuFrame.getOwnerSearchField().setEnabled(false);
        mainMenuFrame.getOwnerSearchButton().setEnabled(false);
        mainMenuFrame.getCarShowSearchField().setEnabled(false);
        mainMenuFrame.getCarShowSearchButton().setEnabled(false);
        mainMenuFrame.getOwnerMenuItem().setEnabled(false);
        mainMenuFrame.getVehicleMenuItem().setEnabled(false);
        mainMenuFrame.getCarShowMenuItem().setEnabled(false);
    }
    
    private void unlockUI() {
        mainMenuFrame.getOwnerSearchField().setEnabled(true);
        mainMenuFrame.getOwnerSearchButton().setEnabled(true);
        mainMenuFrame.getCarShowSearchField().setEnabled(true);
        mainMenuFrame.getCarShowSearchButton().setEnabled(true);
        mainMenuFrame.getOwnerMenuItem().setEnabled(true);
        mainMenuFrame.getVehicleMenuItem().setEnabled(true);
        mainMenuFrame.getCarShowMenuItem().setEnabled(true);
    }

    @Override
    public void windowOpened(WindowEvent we) { 
        lockUI();
        logInPanel();
    }

    @Override
    public void windowClosing(WindowEvent we) {
        saveBeforeCloseDialog();
        mainMenuFrame.dispose();
    }

    @Override
    public void windowClosed(WindowEvent we) {
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent we) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        
    }

    @Override
    public void windowActivated(WindowEvent we) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        
    }
    
}
