package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class InputServiceHistoryEntryWorkshopViewController {

    private AppModel appModel;
    private AppController appController;
    private Workshop workshop;
    private ServiceHistoryEntry entry;



    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;

        comboBoxVehicle.setItems(appModel.getVehicleRegister().getVehicles());
    }

    public void setEntry(ServiceHistoryEntry entry) {
        this.entry = entry;

        if (entry != null) {
            datePickerServiceDate.setValue(entry.getDate());
            textFieldProblemDescription.setText(entry.getProblemDescription());
            textFieldCost.setText(String.valueOf(entry.getCost()));
            textFieldPartsReplaced.setText(String.valueOf(entry.getPartsReplaced()));
            textFieldServiceActivities.setText(
                    String.join(", ", entry.getServiceActivities())
            );
            comboBoxVehicle.setValue(entry.getVehicle());
        }
    }

    

    @FXML
    private DatePicker datePickerServiceDate;

    @FXML
    private TextField textFieldProblemDescription;

    @FXML
    private TextField textFieldCost;

    @FXML
    private TextField textFieldPartsReplaced;

    @FXML
    private TextField textFieldServiceActivities;

    @FXML
    private ComboBox<Vehicle> comboBoxVehicle;

    @FXML
    private Button buttonApply;

    @FXML
    private Button buttonBack;

    
    @FXML
    private void handleButtonApplyAction() {

 
    LocalDate date = datePickerServiceDate.getValue();
    String problem = textFieldProblemDescription.getText().trim();
    String costText = textFieldCost.getText().trim();
    String partsText = textFieldPartsReplaced.getText().trim();
    String activitiesText = textFieldServiceActivities.getText().trim(); 
    Vehicle vehicle = comboBoxVehicle.getValue();

    if (date == null || problem.isEmpty() || costText.isEmpty() || partsText.isEmpty() || vehicle == null) {
        showError("Fill in date, problem, cost, parts replaced, and vehicle.");
        return;
    }

    double cost;
    int parts;
    try {
        cost = Double.parseDouble(costText);
    } catch (NumberFormatException e) {
        showError("Cost must be a number.");
        return;
    }

    try {
        parts = Integer.parseInt(partsText);
    } catch (NumberFormatException e) {
        showError("Parts replaced must be a whole number.");
        return;
    }
    ServiceHistoryEntry newEntry = new ServiceHistoryEntry();
    newEntry.setDate(date);
    newEntry.setProblemDescription(problem);
    newEntry.setCost(cost);
    newEntry.setPartsReplaced(parts);
    newEntry.setWorkshop(workshop);
    newEntry.setVehicle(vehicle);

    if (!activitiesText.isEmpty()) {
    for (String a : activitiesText.split(",")) {
        String trimmed = a.trim();
        if (!trimmed.isEmpty()) newEntry.addServiceActivity(trimmed);
    }
    }
    if (workshop==null) {
    showError("No workshop selected.");
    return;
    }
    if (entry != null) {
        workshop.addService(newEntry);
        vehicle.addServiceHistoryEntry(newEntry);
    }
  
    Stage stage = (Stage) buttonApply.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void handleButtonBackAction() {
        appController.showWorkshopEntriesView(workshop);
        ((Stage) buttonBack.getScene().getWindow()).close();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


