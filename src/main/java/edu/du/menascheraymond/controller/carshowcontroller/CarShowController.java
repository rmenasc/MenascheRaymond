/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche.
 * File: 
 */
package edu.du.menascheraymond.controller.carshowcontroller;

import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.services.carshowservice.CarShowService;
import edu.du.menascheraymond.view.CarShowFrame;
import edu.du.menascheraymond.view.MainMenuFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author raymondmenasche
 */
public class CarShowController implements ActionListener, WindowListener {
    
    private MainMenuFrame mainMenuFrame = null;
    private CarShowFrame carShowFrame = null;
    
    public CarShowController() {
        
    }
    
    public CarShowController(MainMenuFrame mainMenuFrame, CarShowFrame carShowFrame) {
        this.mainMenuFrame = mainMenuFrame;
        this.carShowFrame = carShowFrame;
        
        carShowFrame.getExitMenuItem().addActionListener(this);
        carShowFrame.getUndoResetMenuItem().addActionListener(this);
        carShowFrame.getAddCarShowButton().addActionListener(this);
        carShowFrame.getUpdateCarShowButton().addActionListener(this);
        carShowFrame.getRemoveCarShowButton().addActionListener(this);
        carShowFrame.getClearButton().addActionListener(this);
        carShowFrame.getCarShowsList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                carShowsListValueChanged(evt);
            }
        });
        loadCarShowList();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            if (event.getSource().equals(carShowFrame.getExitMenuItem())) {
                exitCarShow_actionPerformed(event);
            } else if (event.getSource().equals(carShowFrame.getUndoResetMenuItem())) {
                undoResetCarShow_actionPerformed(event);
            } else if (event.getSource().equals(carShowFrame.getAddCarShowButton())) {
                addCarShow_actionPerformed(event);
            } else if (event.getSource().equals(carShowFrame.getUpdateCarShowButton())) {
                updateCarShow_actionPerformed(event);
            } else if (event.getSource().equals(carShowFrame.getRemoveCarShowButton())) {
                removeCarShow_actionPerformed(event);
            } else if (event.getSource().equals(carShowFrame.getClearButton())) {
                clearFields_actionPerformed(event);
            }
        } catch (Exception e) {
            
        }
    }

    private void exitCarShow_actionPerformed(ActionEvent event) {
        //TODO: exit code here
        
        carShowFrame.dispose();
    }
    
    private void undoResetCarShow_actionPerformed(ActionEvent event) {
        //TODO: undo code here.
    }
    
    private void addCarShow_actionPerformed(ActionEvent event) {
        String title = carShowFrame.getTitleField().getText();
        int month = Integer.parseInt(carShowFrame.getMonthCombo().getSelectedItem().toString());
        int day = Integer.parseInt(carShowFrame.getDayCombo().getSelectedItem().toString());
        int year = Integer.parseInt(carShowFrame.getYearCombo().getSelectedItem().toString());
        LocalDate date = LocalDate.of(year, month, day);
        boolean sanctioned = carShowFrame.getSanctionedCheckBox().isSelected();
        int nextId = 1;
        String carShowId = "CS" + nextId;
        while (mainMenuFrame.getManager().getCarShowCollection().isPresent(carShowId)) {
            nextId++;
            carShowId = "CS" + nextId;
        }
        if (!carShowFrame.getSelectedCarShowLabel().getText().equals("")
                && !carShowFrame.getSelectedCarShowLabel().getText()
                        .equals("No Car Show selected")
                && carShowFrame.getSelectedCarShowLabel().getText() != null) {
            carShowId = carShowFrame.getSelectedCarShowLabel().getText();
        }
        CarShow carShow = new CarShow.Builder(carShowId, title)
                .withCarShowDate(date).isSanctioned(sanctioned).build();
        if (!mainMenuFrame.getManager().getCarShowCollection()
                .isPresent(carShowId)) {
            mainMenuFrame.getManager().getCarShowCollection().add(carShow);
        }
        loadCarShowList();
    }
    
    private void updateCarShow_actionPerformed(ActionEvent event) {
        CarShow c = mainMenuFrame.getManager().getCarShowCollection()
                .find(mainMenuFrame.getSelectedCarShow().getCarShowId());
        c.setCarShowTitle(carShowFrame.getTitleField().getText());
        int month = Integer.parseInt(carShowFrame.getMonthCombo().getSelectedItem().toString());
        int day = Integer.parseInt(carShowFrame.getDayCombo().getSelectedItem().toString());
        int year = Integer.parseInt(carShowFrame.getYearCombo().getSelectedItem().toString());
        LocalDate date = LocalDate.of(year, month, day);
        c.setCarShowDate(date);
        c.setSanctioned(carShowFrame.getSanctionedCheckBox().isSelected());
    }
    
    private void removeCarShow_actionPerformed(ActionEvent event) {
        if (mainMenuFrame.getManager().getCarShowCollection()
                .remove(mainMenuFrame.getSelectedCarShow())) {
            mainMenuFrame.getManager().getCarShowOwnerCollection()
                    .removeByCarShowId(mainMenuFrame.getSelectedCarShow().getCarShowId());
        }
        loadCarShowList();
    }
    
    private void clearFields_actionPerformed(ActionEvent event) {
        loadCarShowList();
    }
    
    private void loadCarShowList() {
        //resets fields
        mainMenuFrame.setSelectedCarShow(null);
        mainMenuFrame.getCarShowSearchResultLabel().setText("");
        carShowFrame.getAddCarShowButton().setEnabled(true);
        carShowFrame.getUpdateCarShowButton().setEnabled(false);
        carShowFrame.getRemoveCarShowButton().setEnabled(false);
        carShowFrame.getSelectedCarShowLabel().setText("No Car Show selected");
        carShowFrame.getTitleField().setText("");
        carShowFrame.getDayCombo().setSelectedIndex(0);
        carShowFrame.getMonthCombo().setSelectedIndex(0);
        carShowFrame.getYearCombo().setSelectedIndex(0);
        carShowFrame.getSanctionedCheckBox().setSelected(false);
        
        //load carShow list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        CarShowService css = mainMenuFrame.getManager().getCarShowCollection();
        for (String id: css.getIds()) {
            CarShow c = css.find(id);
            listModel.addElement(id + ": " + c.getCarShowTitle());
        }
        carShowFrame.getCarShowsList().setModel(listModel);
    }
    
    private void carShowsListValueChanged(ListSelectionEvent evt) {
        if (carShowFrame.getCarShowsList().getSelectedValue() == null) {
            loadCarShowList();
        } else {
            carShowFrame.getAddCarShowButton().setEnabled(false);
            carShowFrame.getUpdateCarShowButton().setEnabled(true);
            carShowFrame.getRemoveCarShowButton().setEnabled(true);
            String[] sa = carShowFrame.getCarShowsList().getSelectedValue().split(":");
            String id = sa[0];
            carShowFrame.getSelectedCarShowLabel()
                    .setText(carShowFrame.getCarShowsList().getSelectedValue());
            CarShow cs = mainMenuFrame.getManager().getCarShowCollection().find(id);
            carShowFrame.getTitleField().setText(cs.getCarShowTitle());
            LocalDate date = cs.getCarShowDate();
            Integer day = date.getDayOfMonth();
            Integer month = date.getMonthValue();
            Integer year = date.getYear();
            carShowFrame.getDayCombo().setSelectedItem(day.toString());
            carShowFrame.getMonthCombo().setSelectedItem(month.toString());
            carShowFrame.getYearCombo().setSelectedItem(year.toString());
            carShowFrame.getSanctionedCheckBox().setSelected(cs.isSanctioned());
            mainMenuFrame.setSelectedCarShow(cs);
            mainMenuFrame.getCarShowSearchResultLabel()
                    .setText(cs.getCarShowId() + ": " + cs.getCarShowTitle());
        }
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        carShowFrame.dispose();
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
