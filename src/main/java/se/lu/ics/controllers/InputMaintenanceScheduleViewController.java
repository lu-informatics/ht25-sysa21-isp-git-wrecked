package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class InputMaintenanceScheduleViewController {

    private AppModel appModel;
    private AppController appController;
    private Vehicle vehicle;
    //som varaibler för kunna använda samma objekt i flera controllers
    // lägger in vehicle objekt, inte variabel av vehicle
    //skapar instans av vehicle direkt när controller skapas

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
    private TextField textFieldWorkshop;
    @FXML
    private TextField textFieldDate;
    @FXML
    private TextField textFieldDescription;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelResponse;
    
    @FXML
    private void handleButtonApplyAction(ActionEvent event) {
        String workshop = textFieldWorkshop.getText().trim();
        String dateT = textFieldDate.getText();
        String description = textFieldDescription.getText().trim();

        if(workshop.isEmpty() || dateT.isEmpty() || description.isEmpty()) {
            labelResponse.setText("Please fill in all fields: workshop, date, and description.");
            return;
        }

        LocalDate date; //deklarera för att använda hela metoden
        Workshop foundWorkshop;

        try {
            date = LocalDate.parse(dateT); // gör till typ local date
        }catch(Exception e){
                    labelResponse.setText("Invalid date format. Please use YYYY-MM-DD."); 
                    return;
        }
        try{
            foundWorkshop=appModel.getWorkshopRegister().findWorkshop(workshop);
        }catch(IllegalArgumentException e){
            labelResponse.setText(e.getMessage());
            return;
        }
        
        MaintenanceSchedule newSchedule = new MaintenanceSchedule(date, description);

        vehicle.addMaintenanceSchedule(newSchedule);
        newSchedule.setVehicle(vehicle);
        newSchedule.setWorkshop(foundWorkshop);
        
        appController.showMaintenenceViewVehicle(vehicle);
        Stage stage=(Stage) buttonApply.getScene().getWindow();
        stage.close();
        //stänga fönstret efter apply button
    }
    
    @FXML
        private void handleButtonBackAction(ActionEvent event) {
            appController.showMaintenenceViewVehicle(vehicle);
            Stage stage=(Stage) buttonBack.getScene().getWindow();
            stage.close();
        }
}
