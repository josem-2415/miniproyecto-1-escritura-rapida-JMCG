package com.example.escriturarapida.model;

/**
 * Interface representing a game timer.
 * Classes implementing this interface manage countdowns and notifications.
 */
public interface ITimer {

    /**
     * Sets a Runnable to be executed when the timer ends.
     *
     * @param onTimeEnd action to execute at the end of the timer
     */
    void setOnTimeEnd(Runnable onTimeEnd);

    /**
     * Sets a Runnable to be executed at each tick of the timer.
     *
     * @param onTick action to execute on each timer tick
     */
    void setOnTick(Runnable onTick);

    /**
     * Starts the timer countdown.
     */
    void startTimer();

    /**
     * Stops the timer.
     */
    void stop();

    /**
     * Resets the timer to its initial value.
     */
    void reset();

    /**
     * Decreases the maximum allowed time by a given number of seconds.
     *
     * @param seconds number of seconds to decrease
     */
    void decreaseTime(double seconds);

    /**
     * Returns the current remaining time.
     *
     * @return current time in seconds
     */
    double getTime();

    /**
     * Returns the maximum allowed time for the timer.
     *
     * @return maximum time in seconds
     */
    double getMaxTime();
}
