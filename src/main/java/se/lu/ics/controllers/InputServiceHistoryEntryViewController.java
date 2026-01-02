package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;



public class InputServiceHistoryEntryViewController {

    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle;
    //som varaibler för kunna använda samma objekt i flera controllers
    // lägger in vehicle objekt, inte variabel av vehicle

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    @FXML 
    private TextField textFieldProblemDescription;
    @FXML 
    private TextField textFieldCost;
    @FXML 
    private TextField textFieldPartsReplaced;
    @FXML 
    private DatePicker datePickerServiceDate;
    @FXML
    private TextField textFieldServiceActivities;
    @FXML 
    private TextField textFieldWorkshop;
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
    String workshopText = textFieldWorkshop.getText().trim();

    if (date == null || problem.isEmpty() || costText.isEmpty() || partsText.isEmpty() || workshopText.isEmpty()) {
        showError("Fill in date, problem, cost and parts replaced.");
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
    Workshop w = new Workshop(workshopText,false,"");
    newEntry.setWorkshop(w);

    newEntry.setVehicle(vehicle);
    vehicle.addServiceHistoryEntry(newEntry);

    if (!activitiesText.isEmpty()) {
    for (String a : activitiesText.split(",")) {
        String trimmed = a.trim();
        if (!trimmed.isEmpty()) newEntry.addServiceActivity(trimmed);
    }
    }
    if (workshopText.isEmpty()) {
    showError("Enter Workshop Name.");
    return;
    }
  
    Stage stage = (Stage) buttonApply.getScene().getWindow();
    stage.close();
    }

    
    @FXML
    private void handleButtonBackAction(ActionEvent event) {
            appController.showServiceHistoryEntryViewVehicle(vehicle);
            Stage stage=(Stage) buttonBack.getScene().getWindow();
            stage.close();
     }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    
    }
}

