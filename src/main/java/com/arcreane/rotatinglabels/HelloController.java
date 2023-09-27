package com.arcreane.rotatinglabels;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class HelloController {
    public Label Hundreds;
    public Label Tenth;
    public Label Units;
    public HBox BoxLabelContainer;
    @FXML
    private Label welcomeText;

    Integer m_iHundreds;
    Integer m_iTenth;
    Integer m_iUnits;
    Random rand;

    int m_iTotal;

    public HelloController() {
        rand = new Random();
        m_iHundreds = 0;
        m_iTenth = 0;
        m_iUnits = 0;
        m_iTotal = 0;
    }

    public void startSpin(ActionEvent actionEvent) {
        m_iTotal = rand.nextInt(10,25);
        for(var child : BoxLabelContainer.getChildren()) {
            RotateTransition rt1 = new RotateTransition(Duration.millis(rand.nextInt(1000,5000)), child);
            rt1.setByAngle(-360);
            rt1.setCycleCount(rand.nextInt(7,15));
            rt1.setInterpolator(Interpolator.LINEAR);
            rt1.setAutoReverse(false);
            rt1.play();

            child.rotateProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    if ((Double) t1 < -90.0
                            && (Double) t1 > -270.0
                            && ((Label)child).getTextFill() != Color.BLACK) {
                        //Hundreds.setText("  ");
                        ((Label)child).setTextFill(Color.BLACK);
                        ((Label)child).setBackground(Background.fill(Color.BLACK));
                        ((Label)child).setUserData(((Integer)((Label)child).getUserData()+ 1) % 10);
                    } else if ((Double) observableValue.getValue() < -270.0 && !Hundreds.equals(" ")) {
                        ((Label)child).setTextFill(Color.WHEAT);
                        ((Label)child).setBackground(Background.fill(Color.DARKBLUE));
                        ((Label)child).setText(((Integer)((Label)child).getUserData()).toString());
                    }
                    else if((Double) t1 < -360.0)
                    {
                        ((Label)child).setRotate(0);
                    }
                }
            });
//            rt1.setOnFinished(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    ((Label)child).setRotate(0);
//                }
//            });
//        rt2.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                //System.out.println(Hundreds.getTransforms().get(1));
//                Hundreds.setText(String.valueOf(h));
//                rt1.play();
//            }
//        });
        }
    }

    public void initialise() {
        Hundreds.setUserData(m_iHundreds);
        Tenth.setUserData(m_iTenth);
        Units.setUserData(m_iUnits);
    }
}