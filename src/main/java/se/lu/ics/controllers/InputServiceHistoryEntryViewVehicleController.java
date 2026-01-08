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
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;



public class InputServiceHistoryEntryViewVehicleController {

    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle;
    private ServiceHistoryEntry serviceHistoryEntry;
    private Workshop workshop;
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
    public void setServiceHistoryEntry(ServiceHistoryEntry selectedEntry) {
        this.serviceHistoryEntry = selectedEntry;

        if (selectedEntry != null) {
            datePickerServiceDate.setValue(selectedEntry.getDate());
            textFieldProblemDescription.setText(selectedEntry.getProblemDescription());
            textFieldCost.setText(String.valueOf(selectedEntry.getCost()));
            textFieldPartsReplaced.setText(String.valueOf(selectedEntry.getPartsReplaced()));
            textFieldServiceActivities.setText(String.join(", ", selectedEntry.getServiceActivities()));
            textFieldWorkshop.setText(selectedEntry.getWorkshop().getName());
        }
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
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
    public void handleButtonApplyAction() {

    LocalDate date = datePickerServiceDate.getValue();
    String problem = textFieldProblemDescription.getText().trim();
    String costText = textFieldCost.getText().trim();
    String partsText = textFieldPartsReplaced.getText().trim();
    String workshopName = textFieldWorkshop.getText().trim();
     
    if (date == null) {
        showError("Service date must be selected.");
        return;
    }

    if (problem.isEmpty()) {
        showError("Problem description cannot be empty.");
        return;
    }

    if (costText.isEmpty()) {
        showError("Cost cannot be empty.");
        return;
    }

    if (partsText.isEmpty()) {
        showError("Parts replaced value cannot be empty.");
        return;
    }

    if (workshopName.isEmpty()) {
        showError("A workshop must be selected.");
        return;
    }

    double cost;
    int parts;

    try {
        cost = Double.parseDouble(costText);
        if (cost < 0) {
            showError("Cost cannot be negative.");
            return;
        }
    } catch (NumberFormatException e) {
        showError("Cost must be a valid number.");
        return;
    }

    try {
        parts = Integer.parseInt(partsText);
        if (parts < 0) {
            showError("Parts replaced cannot be negative.");
            return;
        }
    } catch (NumberFormatException e) {
        showError("Parts replaced must be a whole number.");
        return;
    }

    
    try {
        Workshop selectedWorkshop =
                appModel.getWorkshopRegister().findWorkshop(workshopName);

        if (selectedWorkshop == null) {
            showError("Workshop not found.");
            return;
        }

        ServiceHistoryEntry entry;

        if (serviceHistoryEntry == null) {
            entry = new ServiceHistoryEntry(problem, cost, date, parts);

            vehicle.addServiceHistoryEntry(entry);
            selectedWorkshop.addService(entry);

            entry.setWorkshop(selectedWorkshop);

        }
        else {
            entry = serviceHistoryEntry;

            entry.setDate(date);
            entry.setProblemDescription(problem);
            entry.setCost(cost);
            entry.setPartsReplaced(parts);

            Vehicle oldVehicle = entry.getVehicle();
            if (oldVehicle != vehicle) {
                oldVehicle.removeServiceHistoryEntry(entry);
                vehicle.addServiceHistoryEntry(entry);
            }

            Workshop oldWorkshop = entry.getWorkshop();
            if (oldWorkshop != selectedWorkshop) {
                oldWorkshop.removeService(entry);
                selectedWorkshop.addService(entry);
            }
        }

        showError("Service history entry saved successfully!");
        Stage stage = (Stage) buttonApply.getScene().getWindow();
        stage.close();
        
    } catch (IllegalArgumentException e) {
        showError(e.getMessage());
    }
}



    
    @FXML
    public void handleButtonBackAction(ActionEvent event) {
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

