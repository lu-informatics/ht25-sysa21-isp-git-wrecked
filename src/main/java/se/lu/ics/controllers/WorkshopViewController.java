package se.lu.ics.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.lu.ics.models.Workshop;
import se.lu.ics.models.WorkshopRegister;
import se.lu.ics.models.AppModel;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;




public class WorkshopViewController {
    private AppModel appModel;
    private AppController appController;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    private TableView<Workshop> workshopTable;

 
      
    @FXML
    private TableColumn <Workshop, String> tableViewWorkshops;

    @FXML
    private TableColumn<Workshop, String> tableColumnName;

    @FXML
    private TableColumn<Workshop, String> TableColumnAddress;

    @FXML
    private TableColumn <Workshop, Boolean> TableColumnType;

    @FXML
    private TableColumn<Workshop, Integer> tableColumnServiceEntries;

    @FXML
    private MenuItem menuItemAddWorkshop;

    @FXML
    private MenuItem menuItemRemoveWorkshop;

    @FXML
    private MenuItem menuItemEditWorkshop;

    @FXML
    private TextField textSearch;

    @FXML
    private Button buttonViewEntries;
    
    @FXML
    private Button buttonViewSchedules;

    @FXML
    private Button backToMainMenuButton;



    @FXML
    private void handleMenuItemAddWorkshopAction(ActionEvent event) {
        appController.showInputWorkshopView();
        
    }
    @FXML 
    private void handleMenuItemRemoveWorkshopAction(ActionEvent event) {

    }
    @FXML
    private void handleMenuItemEditWorkshopAction(ActionEvent event) {
        
    }
    @FXML
    private void handleButtonBackToMainMenuAction(ActionEvent event) {
        appController.showMainView();
    }
    @FXML
    private void handleTextSearchAction (ActionEvent event) {
        String searchText = textSearch.getText().toLowerCase();
        ObservableList<Workshop> filteredWorkshops = FXCollections.observableArrayList();

        for (Workshop workshop : appModel.getWorkshopRegister().getWorkshops()) {
            if (workshop.getName().toLowerCase().contains(searchText) ||
                workshop.getAddress().toLowerCase().contains(searchText) ||
                workshop.getType().toLowerCase().contains(searchText)) {
                filteredWorkshops.add(workshop);
            }
        }
        workshopTable.setItems(filteredWorkshops);
    }

    @FXML
    private void handleButtonViewEntriesAction(ActionEvent event) {
        appController.showWorkshopEntriesView();    

        
    }
        
    
    
    @FXML
    private void initialize() {
        TableColumnAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        
    }

    @FXML 
    private void handleTableViewWorkshopsAction() {
        
        
    }
    @FXML   
    private void handleTableColumnWorkshopNameAction() {

        
        
    }
    @FXML
    private void handleTableColumnWorkshopAddressAction() {
        
        
    }

    @FXML
    private void handleTableColumnWorkshopTypeAction() {

        
        
    }   
    @FXML
    private void handleButtonViewSchedulesAction() {
        appController.showWorkshopSchedulesView();
        
    }






}


