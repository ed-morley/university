import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.util.Scanner;

public class ToolBar extends JPanel implements ActionListener {

	GraphicsPanel gp;
	CommandPanel cp;
	private Color colour;
	private JButton square;
	private JButton black;
	private JButton green;
	private JButton reset;
	private JButton rectangle;
	private JButton red;
	private JButton forward;

	public ToolBar(GraphicsPanel gp, CommandPanel cp) {

		this.gp = gp;
		this.cp = cp;

		forward = new JButton("Forward"); 	//adds the forward button of toolbar and customizes it
		forward.setBackground(Color.BLACK);
		forward.setForeground(Color.yellow);

		square = new JButton("Square");//adds the square button of toolbar and customizes it
		square.setBackground(Color.BLACK);
		square.setForeground(Color.yellow);

		rectangle = new JButton("Rectangle");//adds the rectangle button of toolbar and customizes it
		rectangle.setBackground(Color.BLACK);
		rectangle.setForeground(Color.yellow);

		red = new JButton("Red");//adds the red button of toolbar and customizes it
		red.setBackground(Color.black);
		red.setForeground(Color.yellow);

		black = new JButton("Black");//adds the black button of toolbar and customizes it
		black.setBackground(Color.black);
		black.setForeground(Color.yellow);

		green = new JButton("Green");//adds the green button of toolbar and customizes it
		green.setBackground(Color.black);
		green.setForeground(Color.yellow);

		reset = new JButton("Reset");//adds the reset button of toolbar and customizes it
		reset.setBackground(Color.BLACK);
		reset.setForeground(Color.yellow);

		forward.addActionListener(this);
		square.addActionListener(this);
		rectangle.addActionListener(this);
		red.addActionListener(this);
		black.addActionListener(this);
		green.addActionListener(this);
		reset.addActionListener(this);

		add(forward);
		add(square);// add icons to redo and undo !
		add(rectangle);
		add(red);
		add(black);
		add(green);
		add(reset);

		setPreferredSize(new Dimension(100, 50)); //sets the size of the toolbar
		setFocusable(false);
		setBackground(Color.black);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton) (e.getSource());

		if (source == square) { //Draws a square by linking to CP & GP
			System.out.println("be there or be square");
			gp.drawLine(colour, cp.x1 + 100, cp.y1, cp.x2, cp.y2);
			cp.x1 += 100;
			cp.x2 += 100;
			gp.drawLine(colour, cp.x1, cp.y1 - 100, cp.x2, cp.y2);
			cp.y1 -= 100;
			cp.y2 -= 100;
			gp.drawLine(colour, cp.x1 - 100, cp.y1, cp.x2, cp.y2);
			cp.x1 -= 100;
			cp.x2 -= 100;
			gp.drawLine(colour, cp.x1, cp.y1 + 100, cp.x2, cp.y2);
			cp.y1 += 100;
			cp.y2 += 100;
			gp.repaint();

		}

		if (source == black) { //changes the colour to black, green and red 
			System.out.println("Black");
			cp.colour = Color.black;
		}
		if (source == green) {
			System.out.println("Green like da herb");
			cp.colour = Color.green;
		}
		if (source == red) {
			System.out.println("red like blood");
			cp.colour = Color.red;
		}
		if (source == reset) {
			System.out.println("Resetting");
			gp.clear();
			gp.repaint();
			cp.x1 = 400;
			cp.x2 = 400;
			cp.y1 = 250;
			cp.y2 = 250;
		}	

		if (source == rectangle) {
			System.out.println("Printing a rectangle"); //Draws a rectangle by linking to CP & GP
			gp.drawLine(colour, cp.x1 + 50, cp.y1, cp.x2, cp.y2);
			cp.x1 += 50;
			cp.x2 += 50;
			gp.drawLine(colour, cp.x1, cp.y1 - 100, cp.x2, cp.y2);
			cp.y1 -= 100;
			cp.y2 -= 100;
			gp.drawLine(colour, cp.x1 - 50, cp.y1, cp.x2, cp.y2);
			cp.x1 -= 50;
			cp.x2 -= 50;
			gp.drawLine(colour, cp.x1, cp.y1 + 100, cp.x2, cp.y2);
			cp.y1 += 100;
			cp.y2 += 100;
			gp.repaint();
		}
		if (source == forward) {
			System.out.println("Forward");
			gp.drawLine(colour, cp.x1 + 50, cp.y1, cp.x2, cp.y2); //moves the forward button forward 50 every time clicked
			gp.repaint();
			cp.x1 = cp.x1 + 50;
			cp.x2 = cp.x2 + 50;

		}

	}
}// extends JPanel ends

