/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 */
package edu.du.menascheraymond.controller.maincontroller;

import edu.du.menascheraymond.controller.carshowcontroller.CarShowController;
import edu.du.menascheraymond.controller.ownercontroller.OwnerController;
import edu.du.menascheraymond.controller.vehiclecontroller.VehicleController;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.services.PersistenceService;
import edu.du.menascheraymond.model.services.carshowownerservice.CarShowOwnerService;
import edu.du.menascheraymond.model.services.carshowservice.CarShowService;
import edu.du.menascheraymond.model.services.ownerservice.OwnerService;
import edu.du.menascheraymond.model.services.vehicleservice.VehicleService;
import edu.du.menascheraymond.view.CarShowFrame;
import edu.du.menascheraymond.view.MainMenuFrame;
import edu.du.menascheraymond.view.OwnerFrame;
import edu.du.menascheraymond.view.VehicleFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author raymond
 */
public class MainController implements ActionListener, WindowListener {
    
    private MainMenuFrame mainMenuFrame = null;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public MainController() {
        
    }
    
    public MainController(MainMenuFrame mainMenuFrame) {
        this.mainMenuFrame = mainMenuFrame;
        
        mainMenuFrame.addWindowListener(this);
        mainMenuFrame.getExitItem().addActionListener(this);
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
        //TODO: Code here
        
        System.exit(0);
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
        if (ownerResult.equals("No Result")
                || ownerResult == null
                || ownerResult.equals("")) {
            JOptionPane.showMessageDialog(mainMenuFrame, "Please selecte an owner frist");
        } else {
            if (carShowResult.equals("No Result")
                    || carShowResult == null
                    || carShowResult.equals("")) {
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
        CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
        String selected = mainMenuFrame.getCarShowOwnerList().getSelectedValue();
        for (String id: css.getIds()) {
            if (css.find(id).getCarShowTitle().equals(selected)) {
                mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .remove(mainMenuFrame.getSelectedOwner().getOwnerId(), id);
                loadCarShowOwnerList();
            }
        }
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
        OwnerService os = mainMenuFrame.getManager().getOwnerCollection();
        VehicleService vs = mainMenuFrame.getManager().getVehicleCollection();
        CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
        CarShowOwnerService csos = mainMenuFrame.getManager().getCarShowOwnerCollection();
        for (String id: os.getIds()) {
            Owner o = os.find(id);
            LinkedHashMap<String,String> cmd = new LinkedHashMap<>();
            cmd.put("ACTION", "ADD");
            cmd.put("TYPE", "OWNER");
            cmd.put("ownerId", id);
            cmd.put("firstName", o.getFirstName());
            cmd.put("lastName", o.getLastName());
            cmd.put("phoneNumber", o.getPhoneNumber());
            Integer y = o.getNumYears();
            cmd.put("numYears", y.toString());
            cmd.put("street1", o.getAddress().getStreet1());
            cmd.put("street2", o.getAddress().getStreet2());
            cmd.put("city", o.getAddress().getCity());
            cmd.put("state", o.getAddress().getState());
            cmd.put("zipCode", o.getAddress().getZipCode());
            commands.add(cmd);
        }
        for (String id: vs.getIds()) {
            Vehicle v = vs.find(id);
            LinkedHashMap<String,String> cmd = new LinkedHashMap<>();
            cmd.put("ACTION", "ADD");
            cmd.put("TYPE", "VEHICLE");
            cmd.put("vehicleId", id);
            cmd.put("ownerId", v.getOwnerId());
            cmd.put("manufacturer", v.getManufacturer());
            Integer y = v.getModelYear();
            cmd.put("year", y.toString());
            cmd.put("model", v.getModel());
            cmd.put("subModel", v.getSubModel());
            cmd.put("classification", v.getVehicleClassification().toString());
            if (v.isInsured()) {
                cmd.put("insured", "Y");
            } else {
                cmd.put("insured", "N");
            }
            commands.add(cmd);
        }
        for (String id: css.getIds()) {
            CarShow cs = css.find(id);
            LinkedHashMap<String,String> cmd = new LinkedHashMap<>();
            cmd.put("ACTION", "ADD");
            cmd.put("TYPE", "CARSHOW");
            cmd.put("carShowId", id);
            cmd.put("title", cs.getCarShowTitle());
            cmd.put("date", cs.getCarShowDate().toString());
            if (cs.isSanctioned()) {
                cmd.put("sanctioned", "Y");
            } else {
                cmd.put("sanctioned", "N");
            }
            commands.add(cmd);
        }
        for (String oid: os.getIds()) {
            for (String csid: css.getIds()) {
                CarShowOwner cso = csos.find(oid, csid);
                if (cso != null) {
                    LinkedHashMap<String,String> cmd = new LinkedHashMap<>();
                    cmd.put("ACTION", "ADD");
                    cmd.put("TYPE", "CSO");
                    cmd.put("carShowId", csid);
                    cmd.put("ownerId", oid);
                    commands.add(cmd);
                }
            }
        }
        PersistenceService ps = mainMenuFrame.getManager().getPersistenceService();
        ps.sendCommands(commands);
    }
    
    private void loadCarShowOwnerList() {
        CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
        listModel.removeAllElements();
        for (String id: css.getIds()) {
            if (mainMenuFrame.getManager().getCarShowOwnerCollection()
                    .isPresent(mainMenuFrame.getSelectedOwner().getOwnerId(), id)) {
                listModel.addElement(css.find(id).getCarShowTitle());
                mainMenuFrame.getCarShowOwnerList().setModel(listModel);
                mainMenuFrame.getCarShowOwnerListLabel()
                        .setText(mainMenuFrame.getSelectedOwner().getFirstName() +
                                "'s Car Shows:");
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        
    }

    @Override
    public void windowClosing(WindowEvent we) {
        JOptionPane optionPane = new JOptionPane(
                "Would you like to save before exiting?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        JDialog dialog = new JDialog(mainMenuFrame, "", true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                //setLabel("Thwarted user attept to close window.");
            }
        });
        optionPane.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                String prop = e.getPropertyName();
                if (dialog.isVisible()
                        && (e.getSource() == optionPane)
                        && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                    dialog.setVisible(false);
                }
            }
        });
        dialog.pack();
        dialog.setVisible(true);
        int value = ((Integer)optionPane.getValue()).intValue();
        if (value == JOptionPane.YES_OPTION) {
           save_actionPerformed(null);
        }
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
