package com.example.escriturarapida.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

public class TimerController {

    private Timeline timer;
    private double time = 20;

    private Label timeLabel;
    private Arc timeArc;

    public TimerController(Label timeLabel, Arc timeArc){
        this.timeLabel = timeLabel;
        this.timeArc = timeArc;
    }

    public void startTimer(){

        timer = new Timeline(
                new KeyFrame(Duration.seconds(0.05), e -> {

                    time -= 0.05;

                    timeLabel.setText(String.valueOf((int)Math.ceil(time)));

                    timeArc.setLength((360 * time) / 20);

                    if(time <= 0){
                        timer.stop();
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
        time = 20;
        timeArc.setLength(360);
    }
}

