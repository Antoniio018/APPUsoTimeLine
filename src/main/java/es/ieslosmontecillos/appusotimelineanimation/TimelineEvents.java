package es.ieslosmontecillos.appusotimelineanimation;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


public class TimelineEvents extends Application {

    private SequentialTransition sequentialTransition;


    @Override public void start(Stage stage) {
        stage.setTitle("Transitions and Timeline Animation");
        final Scene scene = new Scene(new Group(), 600, 450);
        scene.setFill(Color.WHITE);

        IntegerProperty segundos = new SimpleIntegerProperty(15);
        final Label time = new Label(segundos.getValue().toString());

        final Rectangle rectBasicTimeline = new Rectangle(100, 50, 100, 50);
        rectBasicTimeline.setFill(Color.RED);

        ((Group)scene.getRoot()).getChildren().addAll(rectBasicTimeline,time);
        stage.setScene(scene);
        stage.show();

        final Timeline temporizador = new Timeline();
        temporizador.setAutoReverse(false);
        final KeyValue kvTemp = new KeyValue(segundos,0);
        final KeyFrame kfTemp = new KeyFrame(Duration.seconds(segundos.doubleValue()), kvTemp);
        temporizador.getKeyFrames().add(kfTemp);
        temporizador.play();

        segundos.addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
                System.out.println(newValue.toString());
                time.setText(newValue.toString());
            }

        });

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 300);
        final KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        // create a sequential transition to do four transitions one after another
        sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(timeline);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.play();


    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
