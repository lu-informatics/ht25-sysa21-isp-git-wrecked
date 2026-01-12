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
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;


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
        comboBoxWorkshop.setItems(appModel.getWorkshopRegister().getWorkshops());
    }
    public void setServiceHistoryEntry(ServiceHistoryEntry selectedEntry) {
        this.serviceHistoryEntry = selectedEntry;

        if (selectedEntry != null) {
            datePickerServiceDate.setValue(selectedEntry.getDate());
            textFieldProblemDescription.setText(selectedEntry.getProblemDescription());
            textFieldCost.setText(String.valueOf(selectedEntry.getCost()));
            textFieldPartsReplaced.setText(String.valueOf(selectedEntry.getPartsReplaced()));
            textFieldServiceActivities.setText(String.join(", ", selectedEntry.getServiceActivities()));
            comboBoxWorkshop.setValue(selectedEntry.getWorkshop());
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
    private Label labelResponse;
    @FXML
    private ComboBox <Workshop> comboBoxWorkshop;

    
    @FXML
    public void handleButtonApplyAction() {
        
    LocalDate date = datePickerServiceDate.getValue();
    String problem = textFieldProblemDescription.getText().trim();
    String costText = textFieldCost.getText().trim();
    String partsText = textFieldPartsReplaced.getText().trim();
    Workshop workshop = comboBoxWorkshop.getValue();
    String activitiesText = textFieldServiceActivities.getText();
     
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

    if (workshop == null) {
        showError("A workshop must be selected.");
        return;
    }
    if (activitiesText.isEmpty() || !activitiesText.matches("[a-öA-Ö, ]+")) {
        showError("Service activities must be filled with words");
        return;
    }
    if (comboBoxWorkshop.getValue() == null) {
        showError("A workshop must be selected.");
        return; }
    

    try {
    double cost = Double.parseDouble(costText);
    int parts = Integer.parseInt(partsText);

    ServiceHistoryEntry entry;

    if (serviceHistoryEntry == null) {
        entry = new ServiceHistoryEntry(problem, cost, date, parts);
    } else {
        entry = serviceHistoryEntry;
        entry.setProblemDescription(problem);
        entry.setCost(cost);
        entry.setDate(date);
        entry.setPartsReplaced(parts);
        

    }

        entry.setVehicle(vehicle);
        entry.setWorkshop(workshop);
        entry.addServiceActivity(activitiesText);
        vehicle.refreshAvailability();


}    catch (NumberFormatException e) {
    showError("Cost and parts must be numeric values.");
    return;

}    
    appController.showServiceHistoryEntryViewVehicle(vehicle);
    Stage stage = (Stage) buttonApply.getScene().getWindow();   
    stage.close();

    }




    
    @FXML
    public void handleButtonBackAction(ActionEvent event) {
            appController.showServiceHistoryEntryViewVehicle(vehicle);
            Stage stage=(Stage) buttonBack.getScene().getWindow();
            stage.close();
     }
    private void showError(String message) {
        labelResponse.setText(message);
    }
}


    

