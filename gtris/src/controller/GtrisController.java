package controller;

import java.util.Random;

import javax.swing.JFrame;

import canvas.Color;
import canvas.Square;
import controller.shape.Pair;
import data.Config;
import data.GtrisModel;

/**
 * Canvas and model controller
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class GtrisController {
    private GtrisModel model;

    /**
     * Constructor, initialize the model and threads(Timers)
     * 
     * @param model
     *            to be controlled
     * @param window
     *            to be repainted
     */
    public GtrisController(GtrisModel model, final JFrame window) {

	this.model = model;
	for (int y = 0; y < 8; y++)
	    for (int x = 0; x < Config.getInstance().getCanvasHeight(); x++) {
		if (getRandomBoolean())
		    continue;
		Square square = new Square();
		square.setColor(getRandomColor());
		square.setPosX(x);
		model.add(square);
		while (model.dropSquare(square))
		    ;
		square.setInFinallCoordY();
	    }
	// add a new pair every 2 seconds, decrease the speed of fall every 2
	// minutes 100 ms, with a minimum of 1 second
	new NonFixedTimer(2000, -100, 120000, 1000) {

	    @Override
	    public void run() {
		GtrisController.this.model.add(new Pair());

	    }
	};
	// drop elements
	new NonFixedTimer(1, 0, 0, 0) {
	    @Override
	    public void run() {
		GtrisController.this.model.fallSquares();
		GtrisController.this.model.searchCubes();
	    }
	};
	// repaint thread
	new NonFixedTimer(31, 0, 0, 31) {

	    @Override
	    public void run() {
		window.repaint();

	    }
	};

    }

    /**
     * get a range random
     * 
     * @param bottom
     *            limit
     * @param top
     *            limit
     * @return a random number in the given range
     */
    public static final int getRandom(int bottom, int top) {
	Random rnd = new Random(System.nanoTime());
	int value = rnd.nextInt(top);

	return value;
    }

    /**
     * gets a random color
     * 
     * @return a Color
     */
    public static final Color getRandomColor() {
	return Color.valueOf((getRandom(0, 500) / 100));
    }

    /**
     * gets a random boolean
     * 
     * @return a random boolean
     */
    public static final boolean getRandomBoolean() {
	return getRandom(0, 500) > 250;
    }
}
