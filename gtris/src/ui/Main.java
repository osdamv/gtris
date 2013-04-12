package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import canvas.GtrisCanvas;
import controller.GtrisController;
import controller.GtrisModel;
import data.Images;

public class Main {
    public static void main(String[] args) {
	// System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
	System.setProperty("sun.java2d.opengl", Boolean.TRUE.toString());
	JFrame window = new JFrame("Gtris");
	GtrisModel model = new GtrisModel();
	final GtrisCanvas canvas = new GtrisCanvas(model);
	window.setLayout(new BorderLayout());
	JLabel background = new JLabel(new ImageIcon(Images.getImage("background.jpg"))){
	    private static final long serialVersionUID = -5778396035204347324L;
	    @Override
	    public Dimension getPreferredSize() {	    
	        return canvas.getPreferredSize();
	    }
	};
	window.add(background);
	background.setLayout(new FlowLayout());
	
	new GtrisController(model, canvas);
	background.add(canvas);
	window.setResizable(false);
	window.pack();
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
