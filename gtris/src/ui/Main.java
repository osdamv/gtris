package ui;

import java.awt.Color;

import javax.swing.JFrame;

import canvas.GtrisCanvas;
import data.GtrisController;
import data.GtrisModel;

public class Main {
    public static void main(String[] args) {
	JFrame window = new JFrame("Gtris");
	GtrisModel model = new GtrisModel();
	GtrisCanvas canvas = new GtrisCanvas(model);
	new GtrisController(model, window);
	window.add(canvas);
	canvas.setDoubleBuffered(true);
	window.setBackground(new Color(120, 120, 120));
	window.setResizable(false);
	window.pack();
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
