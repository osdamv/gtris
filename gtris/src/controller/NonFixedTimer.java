package controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Custom timer class to work with Timers(Threads)
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public abstract class NonFixedTimer extends Timer {
    private long delay;
    private long delta;
    private long initTime = System.currentTimeMillis();
    private long timeToApllyDelay;
    private long minimumDelay;

    /**
     * Constructor, set up a timer with his parameters
     * 
     * 
     * 
     * @param delay
     *            time to wait between executions in milliseconds
     * @param delta
     *            to apply to the delay
     * @param timeToApllyDelay
     *            time passed before perform the delay
     * @param minimum
     *            time of delay
     */
    public NonFixedTimer(long delay, long delta, long timeToApllyDelay, long minimumDelay) {
	this.delay = delay;
	this.minimumDelay = minimumDelay;
	this.delta = delta;
	this.timeToApllyDelay = timeToApllyDelay;
	schedule(new Task(), delay);
    }

    /**
     * Custom TimerTask in order to schedule timer with non fixed delay
     * 
     * @author dvelazquez
     * @since 11/04/2013
     */
    class Task extends TimerTask {

	@Override
	public void run() {
	    if ((delay + delta) > minimumDelay && isDrecreaseTime())
		delay += delta;
	    NonFixedTimer.this.run();
	    schedule(new Task(), delay);
	}

    }

    public boolean isDrecreaseTime() {
	long timePassed = System.currentTimeMillis() - initTime;
	if (timePassed >= timeToApllyDelay) {
	    initTime = System.currentTimeMillis();
	    return true;
	}
	return false;
    }

    /**
     * Method to be overridden where is expected the behavior of the Thread
     */
    public abstract void run();

}
