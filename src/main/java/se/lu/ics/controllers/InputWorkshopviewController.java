package se.lu.ics.controllers;

import se.lu.ics.models.AppModel;

public class InputWorkshopviewController {
    private AppModel appModel;
    private AppController appController;

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

}

