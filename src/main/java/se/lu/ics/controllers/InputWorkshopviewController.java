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
    private Workshop workshop;

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


   

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setWorkshop(Workshop selectedWorkshop) {
        this.workshop = selectedWorkshop;

        if (workshop != null) {
            textFieldWorkshopName.setText(workshop.getName());
            textFieldWorkshopAddress.setText(workshop.getAddress());
            if (workshop.getIsInternal()) {
                radioButtonInternal.setSelected(true);
            } else {
                radioButtonExternal.setSelected(true);
            }
        }
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
