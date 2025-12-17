package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;
import se.lu.ics.models.MaintenanceSchedule;

public class InputMaintenanceScheduleViewController {

    private AppModel appModel;
    private AppController appController;
    private MaintenanceSchedule selectedSchedule;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setSelectedSchedule(MaintenanceSchedule selectedSchedule) {
        this.selectedSchedule = selectedSchedule;

    }

}
