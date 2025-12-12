package se.lu.ics.controllers;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import se.lu.ics.App;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;
import se.lu.ics.models.VehicleRegister;
import java.time.LocalDate;

public class MaintenanceScheduleVehicleViewController {
    private AppModel appModel;
    private AppController appController;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private TextField textFieldChooseDate;
    @FXML
    private TextField textFieldVin;
    @FXML
    private TextField textFieldWorkshop;
    @FXML
    private MenuItem menuItemFind;
    @FXML
    private MenuItem menuItemBook;
    @FXML
    private MenuItem menuItemCancel;
    @FXML
    private Label labelShowDate;
    @FXML
    private Label labelShowWorkshop;
    @FXML
    private Label labelShowMaintenanceJob;
    @FXML 
    private Label labelShowMostExpensiveWorkshop;
    @FXML
    private Label labelShowMostExpensiveMaintenance;
    @FXML
    private Label labelResponse;

    @FXML
    public void initialize() {
        updateMostExpensiveWorkshop();
    }  
    //metod för att uppdatera

    @FXML
    public void handleButtonFindMaintenanceSceduleAction(ActionEvent event){
        String vinT=textFieldVin.getText();

        if(vinT==null || vinT.isEmpty()){
            labelResponse.setText("Please enter a valid VIN.");
            return;
        }
    
        try {
            int vin=Integer.parseInt(vinT);


            Vehicle vehicle=appModel.getVehicleRegister().findVehicle(vin);
            MaintenanceSchedule schedule=vehicle.getMaintenanceSchedule();


            if(schedule!=null){
                LocalDate date=schedule.getDate();
                if(date==null){
                    labelShowDate.setText("No date scheduled");
                } else {
                    labelShowDate.setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // för localdate
                    labelShowMaintenanceJob.setText(schedule.getDescription());
                    labelShowWorkshop.setText(schedule.getWorkshop().getName());
                    labelResponse.setText("Maintenance schedule found for vehicle VIN: " + vin);
                }
            } else {
                labelResponse.setText("No maintenance schedule found for vehicle VIN: " + vin);
                clearMaintenanceLabels();
            }
        } catch (NumberFormatException e){
            labelResponse.setText("Please enter a numeric VIN.");
        } catch (IllegalArgumentException e){
            labelResponse.setText(e.getMessage());
        }
    }

    @FXML
    public void handleButtonBookMaintenanceScheduleAction(ActionEvent event){
        String vinT=textFieldVin.getText();
        String workshop=textFieldWorkshop.getText();

        //hur? behövs ju massa fält då Först skapa maintenance objekt 
        // och sedan addera till bil??

    
    updateMostExpensiveWorkshop(); // någonstans i slutet
}


    @FXML
    public void handleButtonCancelMaintenanceScheduleAction(ActionEvent event){
        String vinT=textFieldVin.getText();

        if(vinT==null || vinT.isEmpty()){
            labelResponse.setText("Please enter a valid VIN.");
            return;
        }
        try{
            int vin=Integer.parseInt(vinT);
            Vehicle vehicle=appModel.getVehicleRegister().findVehicle(vin);
            MaintenanceSchedule schedule=vehicle.getMaintenanceSchedule();

            if(schedule!=null){
                LocalDate date = schedule.getDate();
                if(date==null){
                    labelShowDate.setText("No date.");
                    labelResponse.setText("No maintenance schedule to cancel for vehicle VIN: " + vin);
                } else {
                    labelShowDate.setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // för localdate
                }
                labelShowMaintenanceJob.setText(schedule.getDescription());
                labelShowWorkshop.setText(schedule.getWorkshop().getName());

                vehicle.removeMaintenanceSchedule();
                labelResponse.setText("Maintenance schedule cancelled for vehicle VIN: " + vin);
                updateMostExpensiveWorkshop();
            } 
            clearMaintenanceLabels();
            } catch (NumberFormatException e){
                labelResponse.setText("Please enter a numeric VIN.");
            } catch (IllegalArgumentException e){
                labelResponse.setText(e.getMessage());
            }
    }

    //metod för att visa most expensive uppdaterad.
    
    private void updateMostExpensiveWorkshop(){
        WorkshopRegister workshopRegister=appModel.getWorkshopRegister();
        Workshop mostExpensiveWorkshop=workshopRegister.getMostExpensiveWorkshop();

        if(mostExpensiveWorkshop!=null){
            labelShowMostExpensiveWorkshop.setText(mostExpensiveWorkshop.getName());

        } else {
            labelShowMostExpensiveWorkshop.setText("No data availible.");
        }
    }

    private void clearMaintenanceLabels(){
        labelShowDate.setText("");
        labelShowMaintenanceJob.setText("");
        labelShowWorkshop.setText("");
    }
}
