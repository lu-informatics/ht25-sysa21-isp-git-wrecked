package se.lu.ics;

public class App {
    public static void main(String[] args) {
    
        @Override
        public void start(Stage primaryStage) {
        AppModel appModel = new AppModel();
        AppController appController = new AppController(primaryStage, appModel);
        appController.showMainView();
        }
    
    }
}
