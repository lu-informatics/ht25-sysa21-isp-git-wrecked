module vikingexpress {
    exports se.lu.ics;

    requires javafx.fxml;

    requires javafx.controls;

    requires javafx.graphics;

    opens se.lu.ics.controllers to javafx.fxml;

}
