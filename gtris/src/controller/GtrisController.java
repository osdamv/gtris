package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import canvas.Cursor;
import canvas.GtrisCanvas;
import controller.shape.Pair;
import data.Color;
import data.Config;
import data.Square;

/**
 * Canvas and model controller
 * 
 * @author dvelazquez
 * @since 11/04/2013
 */
public class GtrisController {
    private GtrisModel model;
    private GtrisCanvas canvas;
    private Config config = Config.getInstance();
    private NonFixedTimer drawThread;
    private NonFixedTimer droperThread;
    private NonFixedTimer fillerThread;

    /**
     * Constructor, create all the game logic
     * 
     * @param model
     *            to be controlled
     * @param canvas
     *            to be repainted
     */
    public GtrisController(GtrisModel model, final GtrisCanvas canvas) {
	this.model = model;
	this.canvas = canvas;
	initModel();
	initThreads();
	initEvents();
    }

    private void initEvents() {
	canvas.addKeyListener(new KeyListener() {

	    @Override
	    public void keyTyped(KeyEvent e) {

	    }

	    @Override
	    public void keyReleased(KeyEvent e) {

	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
		Cursor c = model.getCursor();
		switch (e.getKeyCode()) {
		// left
		case 37:
		    c.left();
		    break;
		// up
		case 38:
		    c.up();
		    break;
		// right
		case 39:
		    c.right();
		    break;
		// down
		case 40:
		    c.down();
		    break;

		case 32:
		    model.swapSquare();
		    break;
		default:
		    break;
		}

	    }
	});

    }

    private void initThreads() {
	// add a new pair every 2 seconds, decrease the speed of fall every 2
	// minutes 100 ms, with a minimum of 1 second
	fillerThread = new NonFixedTimer(2000, -100, 120000, 1000) {

	    @Override
	    public void run() {
		if (!model.add(new Pair()))
		    gameOver();
		model.performDelete();
	    }

	    
	};
	// drop elements
	droperThread = new NonFixedTimer(400, -25, 6000, 100) {
	    @Override
	    public void run() {
		model.fallSquares();
		model.markDeletable();
	    }
	};
	// repaint thread
	drawThread = new NonFixedTimer(25, 0, 0, 25) {

	    @Override
	    public void run() {
		canvas.repaint();
	    }
	};
    }
    private void gameOver() {
	drawThread.stop();
	droperThread.stop();
	fillerThread.stop();
	System.err.println("game over");	
    }

    private void initModel() {
	for (int y = 0; y < config.getInitialFill(); y++)
	    for (int x = 0; x < config.getCanvasHeight(); x++) {
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
	Cursor cursor = new Cursor();
	model.add(cursor);
	while (model.dropSquare(cursor))
	    ;
	cursor.setInFinallCoordY();
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
