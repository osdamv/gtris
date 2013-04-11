package data;

import java.util.Timer;
import java.util.TimerTask;

public abstract class DecreasingThread extends Timer {
	private long delay;
	private long delta;

	public DecreasingThread(long delay, long delta) {
		this.delay = delay;
		this.delta = delta;
		schedule(new Task(), delay);
	}

	class Task extends TimerTask {

		@Override
		public void run() {
			if ((delay + delta) > 0)
				delay += delta;
			DecreasingThread.this.run();
			schedule(new Task(), delay);
		}

	}

	public abstract void run();

}
