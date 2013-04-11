package ui;

import java.awt.Color;

import javax.swing.JFrame;

import canvas.GtrisCanvas;
import data.GtrisController;

public class Main {
    public static void main(String[] args) {
	JFrame window = new JFrame("Gtris");
	GtrisCanvas canvas = new GtrisCanvas(new GtrisController(window));
	window.add(canvas);
	canvas.setDoubleBuffered(true);
	window.setBackground(new Color(120, 120, 120));
	window.setResizable(false);
	window.pack();
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
