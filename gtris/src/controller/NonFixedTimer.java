package controller;

 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Custom timer class to work with Timers(Threads)
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public abstract class NonFixedTimer  {
    private long delay;
    private long delta;
    private long initTime = System.currentTimeMillis();
    private long timeToApllyDelay;
    private long minimumDelay;
    private Timer timer;

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
	timer= new Timer((int) delay, new Task());
	timer.setRepeats(true);
	timer.start();
	
     }
    public void stop(){
	timer.stop();
    }
    /**
     * Custom TimerTask in order to schedule timer with non fixed delay
     * 
     * @author dvelazquez
     * @since 11/04/2013
     */
    class Task implements ActionListener {	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if ((delay + delta) > minimumDelay && isDrecreaseTime()){
		delay += delta;
		timer.setDelay((int) delay);
	    }
	    NonFixedTimer.this.run();
	    

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
