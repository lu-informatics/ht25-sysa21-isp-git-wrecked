package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;
import se.lu.ics.models.AppModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class InputWorkshopviewController {
    private AppModel appModel;
    private AppController appController;
    private Workshop selectedWorkshop;

    @FXML
    private Button buttonApply;
    @FXML
    private TextField textFieldWorkshopName;
    @FXML
    private TextField textFieldWorkshopAddress;
    @FXML
    private TextField textFieldWorkshopType;

    @FXML
    private void handleButtonApplyAction() {
        String name = textFieldWorkshopName.getText();
        String address = textFieldWorkshopAddress.getText();
        String type = textFieldWorkshopType.getText();

        boolean isInternal = type.equalsIgnoreCase("internal");

        Workshop newWorkshop = new Workshop(name, isInternal, address);
        appModel.getWorkshopRegister().addWorkshop(newWorkshop);

        appController.showWorkshopView();
    }

    @FXML
    private void handleTextFieldWorkshopNameAction() {

    }

    @FXML
    private void handleTextFieldWorkshopAddressAction() {

    }

    @FXML
    private void handleTextFieldWorkshopTypeAction() {

    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setSelectedWorkshop(Workshop selectedWorkshop) {
        this.selectedWorkshop = selectedWorkshop;
    }
}
