package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.AppModel;



public class InputWorkshopviewController {
    private AppModel appModel;
    private AppController appController;

    @FXML
    private Button buttonApply;
    @FXML
    private TextField textFieldWorkshopName;
    @FXML
    private TextField textFieldWorkshopAddress;
    
    @FXML
    private RadioButton radioButtonInternal;
    @FXML
    private RadioButton radioButtonExternal;
    @FXML
    private Label labelErrorMessage;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonBack;


    @FXML
    private void handleButtonApplyAction() {
        String name = textFieldWorkshopName.getText();
        String address = textFieldWorkshopAddress.getText();

        if (name.trim().isEmpty() || address.trim().isEmpty() || name == null || address == null) {
            labelErrorMessage.setText("Please fill in all fields.");
            return;     
        }
        for (Workshop workshop : appModel.getWorkshopRegister().getWorkshops()) {
            if (workshop.getName().equalsIgnoreCase(name)) {
            labelErrorMessage.setText("A workshop with this name already exists.");
            return;
        }
    }
        boolean isInternal;
        
        if (radioButtonInternal.isSelected()) {
            isInternal = true;
        } else if (radioButtonExternal.isSelected()) {
            isInternal = false;
        } else {
            labelErrorMessage.setText("Please select a workshop type.");
            return;
        }


        Workshop newWorkshop = new Workshop(name, isInternal, address);
        appModel.getWorkshopRegister().addWorkshop(newWorkshop);

        appController.showWorkshopView();
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    public void handleButtonResetAction() {
        textFieldWorkshopName.clear();
        textFieldWorkshopAddress.clear();
        radioButtonInternal.setSelected(false);
        radioButtonExternal.setSelected(false);
        labelErrorMessage.setText("All fields have been cleared.");
    }

    @FXML
    public void handleButtonBackAction() {
        appController.showWorkshopView();
    }


}
