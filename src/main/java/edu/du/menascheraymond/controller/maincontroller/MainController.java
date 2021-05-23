/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 */
package edu.du.menascheraymond.controller.maincontroller;

import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.services.carshowservice.CarShowService;
import edu.du.menascheraymond.model.services.ownerservice.OwnerService;
import edu.du.menascheraymond.view.MainMenuFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.DefaultListModel;

/**
 *
 * @author raymond
 */
public class MainController implements ActionListener, WindowListener {
    
    private MainMenuFrame mainMenuFrame = null;
    
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
            Owner owner = owners.find(mainMenuFrame.getOwnerSearchField().getText());
            mainMenuFrame.getOwnerSearchResultLabel().setText
                    (owner.getFirstName() + " " + owner.getLastName());
            CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
            DefaultListModel<String> modelList = new DefaultListModel<>();
            for (String id: css.getIds()) {
                if (mainMenuFrame.getManager().getCarShowOwnerCollection()
                        .isPresent(owner.getOwnerId(), id)) {
                    modelList.addElement(css.find(id).getCarShowTitle());
                    mainMenuFrame.getCarShowOwnerList().setModel(modelList);
                }
            }
        } else {
            mainMenuFrame.getOwnerSearchResultLabel().setText("No Results");
        }
    }
    
    private void searchCarShow_actionPerformed(ActionEvent event) {
        String searchId = mainMenuFrame.getCarShowSearchField().getText();
        if (mainMenuFrame.getManager().getCarShowCollection().isPresent(searchId)) {
            CarShow cs = mainMenuFrame.getManager().getCarShowCollection()
                    .find(mainMenuFrame.getCarShowSearchField().getText());
            mainMenuFrame.getCarShowSearchResultLabel().setText(cs.getCarShowTitle());
        } else {
            mainMenuFrame.getCarShowSearchResultLabel().setText("No Result");
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
