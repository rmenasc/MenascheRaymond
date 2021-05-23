/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 */
package edu.du.menascheraymond.controller.maincontroller;

import edu.du.menascheraymond.view.MainMenuFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
        //mainMenuFrame.g
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource().equals(mainMenuFrame.getExitItem())) {
                exitItem_actionPerformed(ae);
            }
        } catch(Exception e) {
            
        }
    }
    
    private void exitItem_actionPerformed(ActionEvent event) throws Exception {
        //TODO: Code here
        
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
