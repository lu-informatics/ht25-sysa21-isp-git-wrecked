package se.lu.ics.controllers;

import java.security.Provider.Service;

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

    public void showInputVehicleView(Vehicle selectedVehicle) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputVehicleView.fxml"));
            Parent root = loader.load();

            InputVehicleViewController controller = loader.getController();
            controller.setAppController(this);
            controller.setAppModel(appModel);
            controller.setVehicle(selectedVehicle);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMaintenenceViewVehicle(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaintenenceViewVehicle.fxml"));
            Parent root = loader.load();

            MaintenanceScheduleVehicleViewController maintenanceVehicleController = loader.getController();
            maintenanceVehicleController.setAppController(this);
            maintenanceVehicleController.setAppModel(appModel);
            maintenanceVehicleController.setVehicle(selectedVehicle); // skicka vidare fordone t

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showServiceHistoryEntryViewVehicle(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServiceHistoryEntryViewVehicle.fxml"));
            Parent root = loader.load();

            ServiceHistoryEntryVehicleViewController controller = loader.getController();
            controller.setAppController(this);
            controller.setAppModel(appModel);
            controller.setVehicle(selectedVehicle); // 👈 skicka vidare fordonet

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

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

    public void showInputWorkshopView(Workshop selectedWorkshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputWorkshopView.fxml"));
            Parent root = loader.load();

            InputWorkshopviewController inputWorkshopController = loader.getController();
            inputWorkshopController.setAppController(this);
            inputWorkshopController.setAppModel(appModel);
            inputWorkshopController.setWorkshop(selectedWorkshop);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWorkshopEntriesView(Workshop workshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServiceHistoryEntryViewWorkshop.fxml"));
            Parent root = loader.load();

            ServiceHistoryEntryWorkshopViewController serviceHistoryEntryView = loader.getController();
            serviceHistoryEntryView.setAppController(this);
            serviceHistoryEntryView.setAppModel(appModel);
            serviceHistoryEntryView.setWorkshop(workshop);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void showWorkshopSchedulesView(Workshop workshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaintenanceViewWorkshop.fxml"));
            Parent root = loader.load();

            MaintenanceScheduleViewControllerWorkshop maintenanceWorkshopController = loader.getController();
            maintenanceWorkshopController.setAppController(this);
            maintenanceWorkshopController.setAppModel(appModel);
            maintenanceWorkshopController.setWorkshop(workshop);

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

            InputMaintenanceScheduleVehicleViewController inputMaintenanceScheduleController = loader.getController();
            inputMaintenanceScheduleController.setAppController(this);
            inputMaintenanceScheduleController.setAppModel(appModel);
            inputMaintenanceScheduleController.setVehicle(selectedVehicle);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // för att inte gå vidare i koden förrän fönstret stängt
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputMaintenanceWorkshopView(Workshop selectedWorkshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputMaintenanceWorkshopView.fxml"));
            Parent root = loader.load();

            InputMaintenanceWorkshopViewController inputMaintenanceScheduleController = loader.getController();
            inputMaintenanceScheduleController.setAppController(this);
            inputMaintenanceScheduleController.setAppModel(appModel);
            inputMaintenanceScheduleController.setWorkshop(selectedWorkshop);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // för att inte gå vidare i koden förrän fönstret stängt
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputServiceHistoryEntryView(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputServiceHistoryEntryView.fxml"));
            Parent root = loader.load();

            InputServiceHistoryEntryViewController controller = loader.getController();
            controller.setAppController(this);
            controller.setAppModel(appModel);
            controller.setVehicle(selectedVehicle);

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.setScene(new Scene(root));
            stage.showAndWait(); // pop up

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
