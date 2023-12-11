module es.ieslosmontecillos.appusotimelineanimation {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.appusotimelineanimation to javafx.fxml;
    exports es.ieslosmontecillos.appusotimelineanimation;
}