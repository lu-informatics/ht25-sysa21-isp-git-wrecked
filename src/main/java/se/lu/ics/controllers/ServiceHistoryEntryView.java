package se.lu.ics.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.VehicleType;
import se.lu.ics.models.ServiceHistoryEntry;
import se.lu.ics.models.AppModel;

public class ServiceHistoryEntryView {

    private AppModel appModel;
    private AppController appController;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private void handleTextFieldSearchAction() {
        // Search functionality
    }

    @FXML
    private void handleMenuItemAddServiceHistoryAction() {
        appController.showInputServiceHistoryEntryView();
    }

    @FXML
    private void handleMenuItemRemoveServiceHistoryAction() {
        // Remove service entry
    }

    @FXML
    private void handleMenuItemEditServiceHistoryAction() {
        // Edit service entry
    }

}
