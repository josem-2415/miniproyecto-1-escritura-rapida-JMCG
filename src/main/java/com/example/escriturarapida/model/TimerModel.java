package com.example.escriturarapida.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimerModel implements ITimer {

    private Timeline timer;

    private double time = 20;
    private double maxTime = 20;

    private Runnable onTimeEnd;
    private Runnable onTick;

    public TimerModel(double initialTime){
        this.time = initialTime;
        this.maxTime = initialTime;
    }

    public void setOnTimeEnd(Runnable onTimeEnd){
        this.onTimeEnd = onTimeEnd;
    }

    public void setOnTick(Runnable onTick){
        this.onTick = onTick;
    }

    public void startTimer(){

        timer = new Timeline(
                new KeyFrame(Duration.seconds(0.05), e -> {

                    time -= 0.05;

                    // notificar a la vista
                    if(onTick != null){
                        onTick.run();
                    }

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
        if(timer != null){
            timer.stop();
        }
    }

    public void reset(){
        time = maxTime;
    }

    public void decreaseTime(double seconds){

        maxTime -= seconds;

        if(maxTime < 5){
            maxTime = 5;
        }

        time = maxTime;
    }

    // getters para la vista
    public double getTime(){
        return time;
    }

    public double getMaxTime(){
        return maxTime;
    }
}

