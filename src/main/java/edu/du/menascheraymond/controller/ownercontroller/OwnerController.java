/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche.
 * File: OwnerController.java
 */
package edu.du.menascheraymond.controller.ownercontroller;

import edu.du.menascheraymond.model.domain.Address;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.regularexpressions.RegEx;
import edu.du.menascheraymond.model.services.carshowservice.CarShowService;
import edu.du.menascheraymond.model.services.ownerservice.OwnerService;
import edu.du.menascheraymond.view.MainMenuFrame;
import edu.du.menascheraymond.view.OwnerFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Owner controller.
 * @author raymondmenasche
 */
public class OwnerController implements ActionListener, WindowListener {
    private OwnerFrame ownerFrame = null;
    private MainMenuFrame mainMenuFrame = null;
    
    public OwnerController() {
        
    }
    
    public OwnerController(OwnerFrame ownerFrame, MainMenuFrame mainMenuFrame) {
        this.ownerFrame = ownerFrame;
        this.mainMenuFrame = mainMenuFrame;
        
        ownerFrame.getExitMenuItem().addActionListener(this);
        ownerFrame.getUndoResetMenuItem().addActionListener(this);
        ownerFrame.getAddOwnerButton().addActionListener(this);
        ownerFrame.getUpdateOwnerButton().addActionListener(this);
        ownerFrame.getRemoveOwnerButton().addActionListener(this);
        ownerFrame.getSearchOwnerButton().addActionListener(this);
        ownerFrame.getClearButton().addActionListener(this);
        ownerFrame.getOwnersList().addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent evt) {
                        ownersListValueChanged(evt);
                    }
                });
        
        loadOwnerList();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            if (event.getSource().equals(ownerFrame.getExitMenuItem())) {
                exitItem_actionPerformed(event);
            } else if (event.getSource().equals(ownerFrame.getUndoResetMenuItem())) {
                undoReset_actionPerformed(event);
            } else if (event.getSource().equals(ownerFrame.getAddOwnerButton())) {
                addOwner_actionPerformed(event);
            } else if (event.getSource().equals(ownerFrame.getUpdateOwnerButton())) {
                updateOwner_actionPerformed(event);
            } else if (event.getSource().equals(ownerFrame.getRemoveOwnerButton())) {
                removeOwner_actionPerformed(event);
            } else if (event.getSource().equals(ownerFrame.getSearchOwnerButton())) {
                searchOwner_actionPerformed(event);
            } else if (event.getSource().equals(ownerFrame.getClearButton())) {
                clearFields_actionPerformed(event);
            }
        } catch (Exception ex) {
            
        }
    }
    
    private void exitItem_actionPerformed(ActionEvent event) {
        //TODO: more here
        
        ownerFrame.dispose();
    }
    
    private void undoReset_actionPerformed(ActionEvent event) {
        //TODO: undo code
    }
    
    private void addOwner_actionPerformed(ActionEvent event) {
        if (checkInputs()) {
            String firstName = ownerFrame.getFirstNameField().getText();
            String lastName = ownerFrame.getLastNameField().getText();
            String phoneNumber = ownerFrame.getPhoneField().getText();
            int numYears = 0;
            try {
                numYears = Integer.parseInt(ownerFrame.getNumYearsField().getText());
            } catch (IllegalArgumentException iae) {
                System.out.println("Error: "  + iae);
            }
            String street1 = ownerFrame.getStreet1Field().getText();
            String street2 = ownerFrame.getStreet2Field().getText();
            String city = ownerFrame.getCityField().getText();
            String state = ownerFrame.getStateField().getText();
            String zipCode = ownerFrame.getZipCodeField().getText();
            Address address = new Address.Builder()
                    .withStreet1(street1)
                    .withStreet2(street2)
                    .withCity(city)
                    .withState(state)
                    .withZip(zipCode).build();
            int nextId = 1;
            String ownerId = "O" + nextId;
            while (mainMenuFrame.getManager().getOwnerCollection().isPresent(ownerId)) {
                nextId++;
                ownerId = "O" + nextId;
            }
            if (!ownerFrame.getSelectedOwnerLabel().getText().equals("")
                    && !ownerFrame.getSelectedOwnerLabel().getText().equals("No owner selected")
                    && ownerFrame.getSelectedOwnerLabel().getText() != null) {
                ownerId = ownerFrame.getSelectedOwnerLabel().getText();
            }
            Owner owner = new Owner.Builder(ownerId, firstName, lastName)
                    .withPhoneNumber(phoneNumber)
                    .withNumYears(numYears)
                    .withAddress(address).build();
            if (!mainMenuFrame.getManager().getOwnerCollection().isPresent(ownerId)) {
                mainMenuFrame.getManager().getOwnerCollection().add(owner);
            }
            loadOwnerList();
        }
    }
    
    private void updateOwner_actionPerformed(ActionEvent event) {
        if (checkInputs()) {
            Owner o = mainMenuFrame.getManager().getOwnerCollection()
                    .find(mainMenuFrame.getSelectedOwner().getOwnerId());
            o.setFirstName(ownerFrame.getFirstNameField().getText());
            o.setLastName(ownerFrame.getLastNameField().getText());
            o.setPhoneNumber(ownerFrame.getPhoneField().getText());
            o.setNumYears(Integer.parseInt(ownerFrame.getNumYearsField().getText()));
            o.getAddress().setStreet1(ownerFrame.getStreet1Field().getText());
            o.getAddress().setStreet2(ownerFrame.getStreet2Field().getText());
            o.getAddress().setCity(ownerFrame.getCityField().getText());
            o.getAddress().setState(ownerFrame.getStateField().getText());
            o.getAddress().setZipCode(ownerFrame.getZipCodeField().getText());
        }
    }
    
    private void removeOwner_actionPerformed(ActionEvent event) {
        if (mainMenuFrame.getManager().getOwnerCollection()
                .remove(mainMenuFrame.getSelectedOwner())) {
            mainMenuFrame.getManager().getVehicleCollection()
                    .removeByOwnerId(mainMenuFrame.getSelectedOwner().getOwnerId());
            mainMenuFrame.getManager().getCarShowOwnerCollection()
                    .removeByOwnerId(mainMenuFrame.getSelectedOwner().getOwnerId());
        }
        loadOwnerList();
    }
    
    private void searchOwner_actionPerformed(ActionEvent event) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (mainMenuFrame.getManager().getOwnerCollection()
                .isPresent(ownerFrame.getSearchOwnerField().getText())) {
            listModel.addElement(ownerFrame.getSearchOwnerField().getText());
        } else {
            listModel.addElement("No results");
        }
        ownerFrame.getOwnersList().setModel(listModel);
    }
    
    private void clearFields_actionPerformed(ActionEvent event) {
        loadOwnerList();
    }
    
    private void loadOwnerList() {
        //reset all fields
        mainMenuFrame.setSelectedOwner(null);
        mainMenuFrame.getOwnerSearchResultLabel().setText("");
        DefaultListModel<String> modelList = new DefaultListModel<>();
        mainMenuFrame.getCarShowOwnerList().setModel(modelList);
        ownerFrame.getAddOwnerButton().setEnabled(true);
        ownerFrame.getRemoveOwnerButton().setEnabled(false);
        ownerFrame.getUpdateOwnerButton().setEnabled(false);
        ownerFrame.getFirstNameField().setText("");
        ownerFrame.getLastNameField().setText("");
        ownerFrame.getPhoneField().setText("");
        ownerFrame.getNumYearsField().setText("");
        ownerFrame.getStreet1Field().setText("");
        ownerFrame.getStreet2Field().setText("");
        ownerFrame.getCityField().setText("");
        ownerFrame.getStateField().setText("");
        ownerFrame.getZipCodeField().setText("");
        ownerFrame.getSelectedOwnerLabel().setText("No owner selected");
        //reset list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        OwnerService owners = mainMenuFrame.getManager().getOwnerCollection();
        for (String id: owners.getIds()) {
            Owner o = mainMenuFrame.getManager().getOwnerCollection().find(id);
            if (o != null) {
                listModel.addElement(id + ": " + o.getLastName() + ", " + o.getFirstName());
            }
        }
        
        ownerFrame.getOwnersList().setModel(listModel);
        
    }
    
    private void ownersListValueChanged(ListSelectionEvent evt) {
        if (ownerFrame.getOwnersList().getSelectedValue() == null
                || ownerFrame.getOwnersList().getSelectedValue().equals("No results")) {
            loadOwnerList();
        } else {
            String[] spl = ownerFrame.getOwnersList().getSelectedValue().split(":");
            String id = spl[0];
            ownerFrame.getAddOwnerButton().setEnabled(false);
            ownerFrame.getRemoveOwnerButton().setEnabled(true);
            ownerFrame.getUpdateOwnerButton().setEnabled(true);
            ownerFrame.getSelectedOwnerLabel()
                    .setText(ownerFrame.getOwnersList().getSelectedValue());
            Owner o = mainMenuFrame.getManager().getOwnerCollection().find(id);
            ownerFrame.getFirstNameField().setText(o.getFirstName());
            ownerFrame.getLastNameField().setText(o.getLastName());
            ownerFrame.getPhoneField().setText(o.getPhoneNumber());
            Integer i = o.getNumYears();
            ownerFrame.getNumYearsField().setText(i.toString());
            Address a = o.getAddress();
            ownerFrame.getStreet1Field().setText(a.getStreet1());
            ownerFrame.getStreet2Field().setText(a.getStreet2());
            ownerFrame.getCityField().setText(a.getCity());
            ownerFrame.getStateField().setText(a.getState());
            ownerFrame.getZipCodeField().setText(a.getZipCode());
            mainMenuFrame.setSelectedOwner(o);
            mainMenuFrame.getOwnerSearchResultLabel()
                    .setText(ownerFrame.getOwnersList().getSelectedValue());
            CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String csid: css.getIds()) {
                if (mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .isPresent(mainMenuFrame.getSelectedOwner().getOwnerId(), csid)) {
                    listModel.addElement(css.find(csid).getCarShowTitle());
                    mainMenuFrame.getCarShowOwnerList().setModel(listModel);
                    mainMenuFrame.getCarShowOwnerListLabel()
                            .setText(mainMenuFrame.getSelectedOwner().getFirstName() +
                                    "'s Car Shows:");
                }
            }
        }
    }
    
    private boolean checkInputs() {
        boolean rv = true;
        RegEx regex = new RegEx();
        if (ownerFrame.getFirstNameField().getText().equals("")
                || ownerFrame.getFirstNameField().getText() == null) {
            rv = false;
            JOptionPane.showMessageDialog(ownerFrame, "Please enter the owner's First Name");
        } else if (ownerFrame.getLastNameField().getText().equals("")
                || ownerFrame.getLastNameField().getText() == null) {
            rv = false;
            JOptionPane.showMessageDialog(ownerFrame, "Pleas enter owner Last Name");
        } else if (!regex.isPhoneNumber(ownerFrame.getPhoneField().getText())) {
            rv = false;
            JOptionPane.showMessageDialog(ownerFrame, "Please enter phone in xxx-xxx-xxxx format");
        } else if (!regex.isNumber(ownerFrame.getNumYearsField().getText())) {
            rv = false;
            JOptionPane.showMessageDialog(ownerFrame, "Please enter a number of years");
        } else if (regex.isZipCode(ownerFrame.getZipCodeField().getText())
                && !ownerFrame.getZipCodeField().getText().equals("")) {
            rv = false;
            JOptionPane.showMessageDialog(ownerFrame, "Please Enter valid zip code: Ex 21122");
        }
        return rv;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ownerFrame.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
}
