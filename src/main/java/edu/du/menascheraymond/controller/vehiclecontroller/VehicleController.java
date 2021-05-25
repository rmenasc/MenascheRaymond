/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche.
 * File: 
 */
package edu.du.menascheraymond.controller.vehiclecontroller;

import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;
import edu.du.menascheraymond.model.services.vehicleservice.VehicleService;
import edu.du.menascheraymond.view.MainMenuFrame;
import edu.du.menascheraymond.view.VehicleFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author raymondmenasche
 */
public class VehicleController implements ActionListener, WindowListener {
    
    private MainMenuFrame mainMenuFrame = null;
    private VehicleFrame vehicleFrame = null;
    private Owner selectedOwner = null;
    
    public VehicleController() {
        
    }
    
    public VehicleController(MainMenuFrame mainMenuFrame, VehicleFrame vehicleFrame) {
        this.mainMenuFrame = mainMenuFrame;
        this.vehicleFrame = vehicleFrame;
        
        vehicleFrame.getExitMenuItem().addActionListener(this);
        vehicleFrame.getUndoResetMenuItem().addActionListener(this);
        vehicleFrame.getAddVehicleButton().addActionListener(this);
        vehicleFrame.getRemoveVehicleButton().addActionListener(this);
        vehicleFrame.getOwnerSearchButton().addActionListener(this);
        vehicleFrame.getVehiclesList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                vehicleListValueChanged(evt);
            }
        });
        
        loadVehicleList();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            if (event.getSource().equals(vehicleFrame.getExitMenuItem())) {
                exitVehicle_actionPerformed(event);
            } else if (event.getSource().equals(vehicleFrame.getUndoResetMenuItem())) {
                undoResetVehicle_actionPerformed(event);
            } else if (event.getSource().equals(vehicleFrame.getAddVehicleButton())) {
                addVehicle_actionPerformed(event);
            } else if (event.getSource().equals(vehicleFrame.getRemoveVehicleButton())) {
                removeVehicle_actionPerformed(event);
            } else if (event.getSource().equals(vehicleFrame.getOwnerSearchButton())) {
                searchOwner_actionPerformed(event);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    private void exitVehicle_actionPerformed(ActionEvent event) {
        //TODO: exit code here
        
        vehicleFrame.dispose();
    }
    
    private void undoResetVehicle_actionPerformed(ActionEvent event) {
        //TODO: undo code here. 
    }
    
    private void addVehicle_actionPerformed(ActionEvent event) {
        if (selectedOwner != null) {
            String manufacturer = vehicleFrame.getManufacturerField().getText();
            int year = Integer.parseInt(vehicleFrame.getModelYearField().getText());
            String model = vehicleFrame.getModelField().getText();
            String subModel = vehicleFrame.getSubModelField().getText();
            String classification = vehicleFrame.getClassificationCombo()
                    .getSelectedItem().toString();
            boolean insured = vehicleFrame.getInsuredCheckBox().isSelected();
            Random num = new Random();
            String vehicleId = "V" + num.nextInt();
            Vehicle vehicle = new Vehicle.Builder(vehicleId, selectedOwner.getOwnerId())
                    .withManufacturer(manufacturer)
                    .withModelYear(year)
                    .withModel(model)
                    .withSubModel(subModel)
                    .isInsured(insured).build();
            VehicleClassification c;
            switch (classification.toUpperCase()) {
                case "ANTIQUE":
                    c = VehicleClassification.ANTIQUE;
                    break;
                case "CLASSIC":
                    c = VehicleClassification.CLASSIC;
                    break;
                case "MODERN":
                    c = VehicleClassification.MODERN;
                    break;
                default:
                    c = VehicleClassification.ANTIQUE;
                    break;
            }
            vehicle.setVehicleClassification(c);
            if (!mainMenuFrame.getManager().getVehicleCollection()
                    .isPresent(vehicleId)) {
                mainMenuFrame.getManager().getVehicleCollection().add(vehicle);
            }
            loadVehicleList();
        } else {
            //TODO: alert dialog here
        }
    }
    
    private void removeVehicle_actionPerformed(ActionEvent event) {
        mainMenuFrame.getManager().getVehicleCollection()
                .remove(vehicleFrame.getVehiclesList().getSelectedValue());
        loadVehicleList();
    }
    
    private void searchOwner_actionPerformed(ActionEvent event) {
        selectedOwner = mainMenuFrame.getManager().getOwnerCollection()
                .find(vehicleFrame.getSearchOwnerIdField().getText());
        if (selectedOwner == null) {
            vehicleFrame.getOwnerResultLabel().setText("No Result");
            vehicleFrame.getOwnersVehiclesLabel().setText("Owner's Vehicles");
            loadVehicleList();
        } else {
            vehicleFrame.getOwnerResultLabel().setText(selectedOwner.getOwnerId());
            vehicleFrame.getOwnersVehiclesLabel()
                    .setText(selectedOwner.getFirstName() + "'s Vehicles:");
            loadVehicleList();
        }
    }
    
    private void loadVehicleList() {
        //reset fields
        vehicleFrame.getRemoveVehicleButton().setEnabled(false);
        vehicleFrame.getSearchOwnerIdField().setText("");
        vehicleFrame.getManufacturerField().setText("");
        vehicleFrame.getModelYearField().setText("");
        vehicleFrame.getModelField().setText("");
        vehicleFrame.getSubModelField().setText("");
        vehicleFrame.getClassificationCombo().setSelectedIndex(0);
        vehicleFrame.getInsuredCheckBox().setSelected(false);
        if (selectedOwner == null) {
            vehicleFrame.getAddVehicleButton().setEnabled(false);
            vehicleFrame.getOwnerResultLabel().setText("No Selected Owner");
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("EMPTY");
            vehicleFrame.getVehiclesList().setModel(listModel);
        } else {
            vehicleFrame.getAddVehicleButton().setEnabled(true);
            vehicleFrame.getOwnerResultLabel().setText(selectedOwner.getOwnerId());
            VehicleService vs = mainMenuFrame.getManager().getVehicleCollection();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String id: vs.getIds()) {
                Vehicle v = vs.find(id);
                if (v.getOwnerId().equals(selectedOwner.getOwnerId())) {
                    listModel.addElement(id);
                }
            }
            vehicleFrame.getVehiclesList().setModel(listModel);
        }
    }
    
    private void vehicleListValueChanged(ListSelectionEvent evt) {
        if (vehicleFrame.getVehiclesList().getSelectedValue() == null
                || vehicleFrame.getVehiclesList().getSelectedValue().equals("EMPTY")) {
            loadVehicleList();
        } else {
            vehicleFrame.getAddVehicleButton().setEnabled(false);
            vehicleFrame.getRemoveVehicleButton().setEnabled(true);
            Vehicle sv = mainMenuFrame.getManager().getVehicleCollection()
                    .find(vehicleFrame.getVehiclesList().getSelectedValue());
            vehicleFrame.getManufacturerField().setText(sv.getManufacturer());
            Integer year = sv.getModelYear();
            vehicleFrame.getModelYearField().setText(year.toString());
            vehicleFrame.getModelField().setText(sv.getModel());
            vehicleFrame.getSubModelField().setText(sv.getSubModel());
            vehicleFrame.getClassificationCombo()
                    .setSelectedItem(sv.getVehicleClassification().toString());
            vehicleFrame.getInsuredCheckBox().setSelected(sv.isInsured());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //TODO: closing code here
        
        vehicleFrame.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
