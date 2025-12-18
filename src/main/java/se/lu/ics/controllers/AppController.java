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

    public void showInputVehicleView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputVehicleView.fxml"));
            Parent root = loader.load();

            InputVehicleViewController inputVehicleController = loader.getController();
            inputVehicleController.setAppController(this);
            inputVehicleController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showMaintenenceViewVehicle() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaintenenceViewVehicle.fxml"));
            Parent root = loader.load();

            MaintenanceScheduleVehicleViewController maintenanceVehicleController = loader.getController();
            maintenanceVehicleController.setAppController(this);
            maintenanceVehicleController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showServiceHistoryEntryViewVehicle(Vehicle selectedVehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/ServiceHistoryEntryViewVehicle.fxml"));
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

    public void showInputWorkshopView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputWorkshopView.fxml"));
            Parent root = loader.load();

            InputWorkshopviewController inputWorkshopController = loader.getController();
            inputWorkshopController.setAppController(this);
            inputWorkshopController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWorkshopEntriesView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServiceHistoryEntryViewWorkshop.fxml"));
            Parent root = loader.load();

            ServiceHistoryEntryView serviceHistoryEntryView = loader.getController();
            serviceHistoryEntryView.setAppController(this);
            serviceHistoryEntryView.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void showWorkshopSchedulesView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaintenanceViewWorkshop.fxml"));
            Parent root = loader.load();

            MaintenanceScheduleViewControllerWorkshop maintenanceWorkshopController = loader.getController();
            maintenanceWorkshopController.setAppController(this);
            maintenanceWorkshopController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void showInputMaintenanceScheduleView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputMaintenanceScheduleView.fxml"));
            Parent root = loader.load();

            InputMaintenanceScheduleViewController inputMaintenanceScheduleController = loader.getController();
            inputMaintenanceScheduleController.setAppController(this);
            inputMaintenanceScheduleController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputServiceHistoryEntryView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputServiceHistoryEntryView.fxml"));
            Parent root = loader.load();

            InputServiceHistoryEntryViewController inputServiceHistoryEntryController = loader.getController();
            inputServiceHistoryEntryController.setAppController(this);
            inputServiceHistoryEntryController.setAppModel(appModel);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showEditInputServiceHistoryEntryView(ServiceHistoryEntry selectedEntry) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputServiceHistoryEntryView.fxml"));
            Parent root = loader.load();

            InputServiceHistoryEntryViewController inputServiceHistoryEntryController = loader.getController();
            inputServiceHistoryEntryController.setAppController(this);
            inputServiceHistoryEntryController.setAppModel(appModel);
            inputServiceHistoryEntryController.setSelectedEntry(selectedEntry);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void showEditInputWorkshopView(Workshop selectedWorkshop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputWorkshopView.fxml"));
            Parent root = loader.load();

            InputWorkshopviewController inputWorkshopController = loader.getController();
            inputWorkshopController.setAppController(this);
            inputWorkshopController.setAppModel(appModel);
            inputWorkshopController.setSelectedWorkshop(selectedWorkshop);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showEditInputScheduleView(MaintenanceSchedule selectedSchedule) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputMaintenanceScheduleView.fxml"));
            Parent root = loader.load();

            InputMaintenanceScheduleViewController inputMaintenanceScheduleController = loader.getController();
            inputMaintenanceScheduleController.setAppController(this);
            inputMaintenanceScheduleController.setAppModel(appModel);
            inputMaintenanceScheduleController.setSelectedSchedule(selectedSchedule);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
