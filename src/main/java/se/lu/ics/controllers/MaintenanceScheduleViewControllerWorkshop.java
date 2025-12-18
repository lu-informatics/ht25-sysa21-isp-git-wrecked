package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public class MaintenanceScheduleViewControllerWorkshop {

    private AppModel appModel;
    private AppController appController;
    private Workshop workshop; //varför deklarera?

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    
    @FXML
    private Label labelShowMostExpensiveMaintenance;
    @FXML
    private Label labelResponse;

    @FXML
    private Button buttonBack;

    @FXML
    private MenuItem menuItemAddMaintenance;

    @FXML
    private MenuItem menuItemCancelMaintenance;

    @FXML
    private TableView <MaintenanceSchedule> tableMaintenanceSchedules;
    @FXML
    private TableColumn<MaintenanceSchedule, Number> tableColumnVehicle;
    @FXML
    private TableColumn<MaintenanceSchedule, LocalDate> tableColumnDate;
    @FXML
    private TableColumn<MaintenanceSchedule, String> tableColumnDescription;
    @FXML
    private Label labelWorkshop;

    //koppla tidigare fönster vehicle till detta. 
    public void setWorkshop(Workshop workshop ) {
        this.workshop= workshop;
        labelWorkshop.setText(workshop.getName());
        loadMaintenanceSchedules();
    }

    private void loadMaintenanceSchedules(){
        //metod för visa alla maintenance schedule för viss workshop i tabell

        tableColumnVehicle.setCellValueFactory(new PropertyValueFactory<>("vin"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        ObservableList<MaintenanceSchedule> schedules = FXCollections.observableArrayList(workshop.getMaintenanceScedules());
        //hur göra med observablelist?

        tableMaintenanceSchedules.setItems(schedules);
    }

    @FXML
    public void handleMenuItemAddMaintenanceAction(ActionEvent event) {
        try {
            String name=workshop.getName();

            String vinT =  //fixa input finns inte nu
            String dateT =
            String description =
            
            if (dateT.isEmpty() || vinT.isEmpty() || description.isEmpty()) {
                labelResponse.setText("Please fill in all fields: date, workshop, and maintenance job.");
                return;
            } //flytta till inputen

            LocalDate date = LocalDate.parse(dateT); // gör till typ local date
            int vin = Integer.parseInt(vinT);

            Vehicle vehicle = appModel.getVehicleRegister().findVehicle(vin);

            MaintenanceSchedule schedule = new MaintenanceSchedule(date, description);

            Workshop workshop = appModel.getWorkshopRegister().findWorkshop(name);

            vehicle.addMaintenanceSchedule(schedule);
            schedule.setVehicle(vehicle);
            schedule.setWorkshop(workshop);

            labelResponse.setText("Maintenance schedule booked for vehicle VIN: " + vin);
            labelShowDate.setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // för localdate

            //lägg till i tabellen längst ner
        }
    }

    @FXML
    public void handleMenuItemCancelMaintenanceAction(ActionEvent event) {
        String workshop=workshop.getName();

        //öppna input här även för att radera pga mst välja en bil av flera
       
    }

    @FXML
    private void handleButtonBackAction() {
        appController.showWorkshopView();
    }

}
