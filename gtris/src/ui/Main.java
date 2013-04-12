package ui;

import javax.swing.JFrame;

import canvas.GtrisCanvas;
import controller.GtrisController;
import controller.GtrisModel;

public class Main {
    public static void main(String[] args) {
	//System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
	System.setProperty("sun.java2d.opengl", Boolean.TRUE.toString());
	JFrame window = new JFrame("Gtris");
	GtrisModel model = new GtrisModel();
	GtrisCanvas canvas = new GtrisCanvas(model);
	
 	new GtrisController(model, canvas);
	window.add(canvas);
	window.setResizable(false);
	window.pack();
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
