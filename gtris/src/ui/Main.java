package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import canvas.GtrisCanvas;
import controller.GtrisController;
import controller.GtrisModel;
import data.Config;
import data.Images;

public class Main {
    public static void main(String[] args) {

	System.setProperty("sun.java2d.opengl", Boolean.TRUE.toString());

	JDialog dialog = null;
	JOptionPane optionPane = new JOptionPane();
	optionPane.setMessage("Difficulty");

	optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(3, 1));
	String[] buttonTxt = { "Easy", "Medium", "Hard" };
	JRadioButton[] radios = new JRadioButton[buttonTxt.length];
	ButtonGroup group= new ButtonGroup();
	
	for (int i = 0; i < buttonTxt.length; i++) {
	    final int id = i;
	    radios[i] = new JRadioButton(buttonTxt[i]);
	    radios[i].addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    Config config = Config.getInstance();
		    switch (id) {
		    case 0:
			config.setCanvasHeight(9);
			config.setCanvasWidth(5);
			config.setInitialFill(5);
			config.setDropSpeed(1);
			break;
		    case 1:
			config.setCanvasHeight(15);
			config.setCanvasWidth(10);
			config.setInitialFill(9);
			config.setDropSpeed(2);
			break;
		    case 2:
			config.setCanvasHeight(20);
			config.setCanvasWidth(20);
			config.setInitialFill(20);
			config.setDrawTime(60000);
			config.setDropSpeed(4);
			break;
		    }
		}
	    });
	    group.add(radios[i]);
	    panel.add(radios[i]);
	}
	optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
	optionPane.add(panel, 1);
	dialog = optionPane.createDialog(null, "Difficulty");
	dialog.setVisible(true);

	JFrame window = new JFrame("Gtris");

	GtrisModel model = new GtrisModel();
	final GtrisCanvas canvas = new GtrisCanvas(model);
	window.setLayout(new BorderLayout());
	JLabel background = new JLabel(new ImageIcon(Images.getImage("background.jpg"))) {
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
