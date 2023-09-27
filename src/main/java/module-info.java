module com.arcreane.rotatinglabels {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.arcreane.rotatinglabels to javafx.fxml;
    exports com.arcreane.rotatinglabels;
}