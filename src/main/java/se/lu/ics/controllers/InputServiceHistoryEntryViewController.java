package se.lu.ics.controllers;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.ServiceHistoryEntry;

public class InputServiceHistoryEntryViewController {

    private AppModel appModel;
    private AppController appController;
    private ServiceHistoryEntry selectedEntry;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    public void setSelectedEntry(ServiceHistoryEntry selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
    



}
