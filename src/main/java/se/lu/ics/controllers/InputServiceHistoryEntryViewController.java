package se.lu.ics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.ServiceHistoryEntry;

public class InputServiceHistoryEntryViewController {

    private AppModel appModel;
    private AppController appController;
    private ServiceHistoryEntry selectedEntry;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setSelectedEntry(ServiceHistoryEntry selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
@FXML 
private TextField textFieldProblem;
@FXML 
private TextField textFieldCost;
@FXML 
private TextField textFieldParts;
@FXML 
private DatePicker datePicker;
@FXML 
private TextField textFieldWorkshop;

@FXML
private void handleButtonSaveAction() {
    ServiceHistoryEntry newEntry = new ServiceHistoryEntry();
    textFieldProblem.getText();
    Double.parseDouble( textFieldCost.getText());
    Integer.parseInt( textFieldParts.getText());
    datePicker.getValue();
    textFieldWorkshop.getText();    
    Integer.parseInt(textFieldParts.getText());

    appModel.addServiceHistoryEntry(selectedVehicle,selectedWorksshop, entry);
    appController.showPreviousView();
}
}
