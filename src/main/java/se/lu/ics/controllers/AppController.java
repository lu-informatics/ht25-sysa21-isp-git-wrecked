package se.lu.ics.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.lu.ics.models.AppModel;

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
}
