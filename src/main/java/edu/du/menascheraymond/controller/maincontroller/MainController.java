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
import edu.du.menascheraymond.model.services.carshowservice.CarShowService;
import edu.du.menascheraymond.model.services.ownerservice.OwnerService;
import edu.du.menascheraymond.view.CarShowFrame;
import edu.du.menascheraymond.view.MainMenuFrame;
import edu.du.menascheraymond.view.OwnerFrame;
import edu.du.menascheraymond.view.VehicleFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author raymond
 */
public class MainController implements ActionListener, WindowListener {
    
    private MainMenuFrame mainMenuFrame = null;
    private Owner selectedOwner;
    private CarShow selectedCarShow;
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
            selectedOwner = owners.find(mainMenuFrame.getOwnerSearchField().getText());
            mainMenuFrame.getOwnerSearchResultLabel().setText
                    (selectedOwner.getFirstName() + " " + selectedOwner.getLastName());
            CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
            listModel.removeAllElements();
            for (String id: css.getIds()) {
                if (mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .isPresent(selectedOwner.getOwnerId(), id)) {
                    listModel.addElement(css.find(id).getCarShowTitle());
                    mainMenuFrame.getCarShowOwnerList().setModel(listModel);
                    mainMenuFrame.getCarShowOwnerListLabel()
                            .setText(selectedOwner.getFirstName() + "'s Car Shows:");
                }
            }
        } else {
            mainMenuFrame.getOwnerSearchResultLabel().setText("No Results");
        }
    }
    
    private void searchCarShow_actionPerformed(ActionEvent event) {
        String searchId = mainMenuFrame.getCarShowSearchField().getText();
        if (mainMenuFrame.getManager().getCarShowCollection().isPresent(searchId)) {
            selectedCarShow = mainMenuFrame.getManager().getCarShowCollection()
                    .find(mainMenuFrame.getCarShowSearchField().getText());
            mainMenuFrame.getCarShowSearchResultLabel().setText(selectedCarShow
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
            //TODO: add alert stating no owner selected.
        } else {
            if (carShowResult.equals("No Result")
                    || carShowResult == null
                    || carShowResult.equals("")) {
                //TODO: add alert stating no car show selected
            } else {
                CarShowOwner cso = new CarShowOwner(selectedCarShow.getCarShowId(),
                        selectedOwner.getOwnerId());
                mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .add(cso);
                searchOwner_actionPerformed(event);
            }
        }
    }
    
    private void removeCarShowOwner_actionPerformed(ActionEvent event) {
        CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
        String selected = mainMenuFrame.getCarShowOwnerList().getSelectedValue();
        for (String id: css.getIds()) {
            if (css.find(id).getCarShowTitle().equals(selected)) {
                mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .remove(selectedOwner.getOwnerId(), id);
                searchOwner_actionPerformed(event);
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

    @Override
    public void windowOpened(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent we) {
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
