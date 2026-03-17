package com.example.escriturarapida.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

public class TimerModel {

    private Timeline timer;

    private double time = 20;
    private double maxTime = 20; // ⬅ tiempo máximo del nivel

    private Runnable onTimeEnd;
    private Label timeLabel;
    private Arc timeArc;

    public void setOnTimeEnd(Runnable onTimeEnd){
        this.onTimeEnd = onTimeEnd;
    }

    public TimerModel(Label timeLabel, Arc timeArc){
        this.timeLabel = timeLabel;
        this.timeArc = timeArc;
    }

    public void startTimer(){

        timer = new Timeline(
                new KeyFrame(Duration.seconds(0.05), e -> {

                    time -= 0.05;

                    timeLabel.setText(String.valueOf((int)Math.ceil(time)));

                    timeArc.setLength((360 * time) / maxTime); // ⬅ usar maxTime

                    if(time <= 0){
                        timer.stop();

                        if(onTimeEnd != null){
                            onTimeEnd.run();
                        }
                    }
                })
        );

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void stop(){
        timer.stop();
    }

    public void reset(){
        time = maxTime; // ⬅ reinicia con el tiempo actual
        timeArc.setLength(360);
    }

    // ⬇ NUEVO MÉTODO
    public void decreaseTime(double seconds){

        maxTime -= seconds;

        if(maxTime < 5){
            maxTime = 5; // límite mínimo
        }

        time = maxTime;
    }
}


