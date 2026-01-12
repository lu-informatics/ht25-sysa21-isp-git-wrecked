package se.lu.ics.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Workshop;

public class AppController {
    private final Stage primaryStage;
    private final AppModel appModel;

    public AppController(Stage primaryStage, AppModel appModel) {
        this.primaryStage = primaryStage;
        this.appModel = appModel;
    }

    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            Parent root = loader.load();

            MainViewController mainController = loader.getController();
            mainController.setAppController(this);
            mainController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showVehicleView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VehicleView.fxml"));
            Parent root = loader.load();

            VehicleViewController vehicleController = loader.getController();
            vehicleController.setAppController(this);
            vehicleController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputVehicleView() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputVehicleView.fxml"));
            Parent root = loader.load();

            InputVehicleViewController inputVehicleController = loader.getController();
            inputVehicleController.setAppController(this);
            inputVehicleController.setAppModel(appModel);
            
            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void showEditInputVehicleView(Vehicle vehicle) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputVehicleView.fxml"));
            Parent root = loader.load();

            InputVehicleViewController inputVehicleController = loader.getController();
            inputVehicleController.setAppController(this);
            inputVehicleController.setAppModel(appModel);
            inputVehicleController.setVehicle(vehicle);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace(); 

        }
    }
    public void showWorkshopView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WorkshopView.fxml"));
            Parent root = loader.load();

            WorkshopViewController workshopController = loader.getController();
            workshopController.setAppController(this);
            workshopController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     public void showInputWorkshopView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputWorkshopView.fxml"));
            Parent root = loader.load();

            InputWorkshopviewController inputWorkshopController = loader.getController();
            inputWorkshopController.setAppController(this);
            inputWorkshopController.setAppModel(appModel);
            
            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showEditInputWorkshopView(Workshop workshop) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputWorkshopView.fxml"));
            Parent root = loader.load();

            InputWorkshopviewController inputWorkshopController = loader.getController();
            inputWorkshopController.setAppController(this);
            inputWorkshopController.setAppModel(appModel);
            inputWorkshopController.setWorkshop(workshop);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMaintenanceScheduleVehicleView(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaintenanceScheduleVehicleView.fxml"));
            Parent root = loader.load();

            MaintenanceScheduleVehicleViewController maintenanceVehicleController = loader.getController();
            maintenanceVehicleController.setAppController(this);
            maintenanceVehicleController.setAppModel(appModel);
            maintenanceVehicleController.setVehicle(selectedVehicle);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void showInputMaintenanceScheduleVehicleView(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/InputMaintenanceScheduleVehicleView.fxml"));
            Parent root = loader.load();

            InputMaintenanceScheduleVehicleViewController inputMaintenanceScheduleVehicleController = loader.getController();
            inputMaintenanceScheduleVehicleController.setAppController(this);
            inputMaintenanceScheduleVehicleController.setAppModel(appModel);
            inputMaintenanceScheduleVehicleController.setVehicle(selectedVehicle);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputEditMaintenanceScheduleVehicleView(Vehicle vehicle, MaintenanceSchedule schedule) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputMaintenanceScheduleVehicleView.fxml"));
            Parent root = loader.load();

            InputMaintenanceScheduleVehicleViewController inputMaintenanceScheduleVehicleController = loader.getController();
            inputMaintenanceScheduleVehicleController.setAppController(this);
            inputMaintenanceScheduleVehicleController.setAppModel(appModel);
            inputMaintenanceScheduleVehicleController.setVehicle(vehicle);
            inputMaintenanceScheduleVehicleController.setMaintenanceSchedule(schedule);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void showMaintenanceScheduleWorkshopView(Workshop workshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaintenanceScheduleWorkshopView.fxml"));
            Parent root = loader.load();

            MaintenanceScheduleWorkshopViewController maintenanceScheduleWorkshopController = loader.getController();
            maintenanceScheduleWorkshopController.setAppController(this);
            maintenanceScheduleWorkshopController.setAppModel(appModel);
            maintenanceScheduleWorkshopController.setWorkshop(workshop);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
     public void showInputMaintenanceScheduleWorkshopView(Workshop selectedWorkshop, MaintenanceSchedule selectedSchedule) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputMaintenanceScheduleWorkshopView.fxml"));
            Parent root = loader.load();

            InputMaintenanceScheduleWorkshopViewController inputMaintenanceScheduleWorkshopController = loader.getController();
            inputMaintenanceScheduleWorkshopController.setAppController(this);
            inputMaintenanceScheduleWorkshopController.setAppModel(appModel);
            inputMaintenanceScheduleWorkshopController.setWorkshop(selectedWorkshop);
            inputMaintenanceScheduleWorkshopController.setMaintenanceSchedule(selectedSchedule);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showServiceHistoryEntryVehicleView(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServiceHistoryEntryVehicleView.fxml"));
            Parent root = loader.load();

            ServiceHistoryEntryVehicleViewController serviceHistoryEntryVehicleController = loader.getController();
            serviceHistoryEntryVehicleController.setAppController(this);
            serviceHistoryEntryVehicleController.setAppModel(appModel);
            serviceHistoryEntryVehicleController.setVehicle(selectedVehicle); // 👈 skicka vidare fordonet

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputServiceHistoryEntryVehicleView(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputServiceHistoryEntryVehicleView.fxml"));
            Parent root = loader.load();

            InputServiceHistoryEntryVehicleViewController inputServiceHistoryEntryVehicleController = loader.getController();
            inputServiceHistoryEntryVehicleController.setAppController(this);
            inputServiceHistoryEntryVehicleController.setAppModel(appModel);
            inputServiceHistoryEntryVehicleController.setVehicle(selectedVehicle);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait(); // pop up

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputEditServiceHistoryEntryVehicleView(Vehicle vehicle, ServiceHistoryEntry entry) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputServiceHistoryEntryVehicleView.fxml"));
            Parent root = loader.load();

            InputServiceHistoryEntryVehicleViewController inputServiceHistoryEntryVehicleController = loader.getController();
            inputServiceHistoryEntryVehicleController.setAppController(this);
            inputServiceHistoryEntryVehicleController.setAppModel(appModel);
            inputServiceHistoryEntryVehicleController.setVehicle(vehicle);
            inputServiceHistoryEntryVehicleController.setServiceHistoryEntry(entry);
        

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showServiceHistoryEntryWorkshopView(Workshop workshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServiceHistoryEntryWorkshopView.fxml"));
            Parent root = loader.load();

            ServiceHistoryEntryWorkshopViewController serviceHistoryEntryWorkshop = loader.getController();
            serviceHistoryEntryWorkshop.setAppController(this);
            serviceHistoryEntryWorkshop.setAppModel(appModel);
            serviceHistoryEntryWorkshop.setWorkshop(workshop);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void showInputServiceHistoryEntryWorkshopView(Workshop selectedWorkshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputServiceHistoryEntryWorkshopView.fxml"));
            Parent root = loader.load();

            InputServiceHistoryEntryWorkshopViewController inputServiceHistoryEntryWorkshopController = loader.getController();
            inputServiceHistoryEntryWorkshopController.setAppController(this);
            inputServiceHistoryEntryWorkshopController.setAppModel(appModel);
            inputServiceHistoryEntryWorkshopController.setWorkshop(selectedWorkshop);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait(); 
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputEditServiceHistoryEntryWorkshopView(Workshop selectedWorkshop, ServiceHistoryEntry selectedEntry) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputServiceHistoryEntryWorkshopView.fxml"));
            Parent root = loader.load();

            InputServiceHistoryEntryWorkshopViewController inputServiceHistoryEntryWorkshopController = loader.getController();
            inputServiceHistoryEntryWorkshopController.setAppController(this);
            inputServiceHistoryEntryWorkshopController.setAppModel(appModel);
            inputServiceHistoryEntryWorkshopController.setEntry(selectedEntry);
            inputServiceHistoryEntryWorkshopController.setWorkshop(selectedWorkshop);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait(); 
    

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
