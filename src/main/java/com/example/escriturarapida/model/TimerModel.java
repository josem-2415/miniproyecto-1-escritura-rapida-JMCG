package com.example.escriturarapida.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Model responsible for game timer logic.
 */
public class TimerModel implements ITimer {

    /** JavaFX Timeline used as a timer. */
    private Timeline timer;

    /** Current remaining time in seconds. */
    private double time = 20;

    /** Maximum time allowed for the current round. */
    private double maxTime = 20;

    /** Runnable executed when time ends. */
    private Runnable onTimeEnd;

    /** Runnable executed on each tick of the timer. */
    private Runnable onTick;


    /**
     * Constructor.
     *
     * @param initialTime initial time value
     */
    public TimerModel(double initialTime){
        this.time = initialTime;
        this.maxTime = initialTime;
    }

    /**
     * Sets action when time ends.
     */
    @Override
    public void setOnTimeEnd(Runnable onTimeEnd){
        this.onTimeEnd = onTimeEnd;
    }

    /**
     * Sets action on each tick.
     */
    @Override
    public void setOnTick(Runnable onTick){
        this.onTick = onTick;
    }

    /**
     * Starts the timer.
     */
    @Override
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

    /**
     * Stops the timer.
     */
    @Override
    public void stop(){
        if(timer != null){
            timer.stop();
        }
    }

    /**
     * Resets the timer.
     */
    @Override
    public void reset(){
        time = maxTime;
    }

    /**
     * Decreases maximum time.
     *
     * @param seconds seconds to decrease
     */
    @Override
    public void decreaseTime(double seconds){

        maxTime -= seconds;

        if(maxTime < 5){
            maxTime = 5;
        }

        time = maxTime;
    }

    // getters para la vista
    /**
     * Returns current time.
     */
    @Override
    public double getTime(){
        return time;
    }

    /**
     * Returns maximum time.
     */
    @Override
    public double getMaxTime(){
        return maxTime;
    }
}

