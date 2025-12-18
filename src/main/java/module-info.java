module vikingexpress {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports se.lu.ics;
    exports se.lu.ics.models;

    opens se.lu.ics.controllers to javafx.fxml;
    opens se.lu.ics.models to javafx.base;
}
