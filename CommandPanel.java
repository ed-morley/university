import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.util.Scanner;

public class CommandPanel extends JPanel {

	GraphicsPanel gp;

	private JLabel inputLabel;
	private JTextField input;
	int x1 = 400;
	int x2 = 400;
	int y1 = 250;
	int y2 = 250;
	public int angle = 0;
	public Color colour;
	public int value;
	String f = new String();
	String b = new String();
	private Color transparent = new Color(0f, 0f, 0f, 0f);// created a clear colour for penup so it still types but you can't see the colour
	private int forwardInt = 0; //enables the program to catch incorrect commands

	public CommandPanel(GraphicsPanel gp) {

		this.gp = gp;
		inputLabel = new JLabel("Enter Commands here: "); // creates the input label and customizes it! 
		inputLabel.setForeground(Color.YELLOW);
		

		input = new JTextField(30); // creates the text field where the input commands are entered and creates action listener 
		ActionListener listen = new Listener();

		input.addActionListener(listen);
		input.setFont(Font.getFont(Font.SANS_SERIF));// changes the font (requirement 4) 
        

		add(inputLabel);
		add(input);

		setPreferredSize(new Dimension(300, 75));//sets the size of the command panel
		setFocusable(true);
		setBackground(Color.black);

	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				Scanner scan = new Scanner(input.getText());// scans what has been entered by the user

				// PENUP
				if (input.getText().equals("penup")) {
					System.out.println("PenUp");
					colour = transparent; //transparent colour created at the top this page
					forwardInt = 1;
				}
				// PENDOWN
				if (input.getText().equals("pendown")) {
					System.out.println("PenDown"); //able to draw again as the colour is turned white 
					colour = Color.white;
					forwardInt = 1;
				}

				// FORWARDS
				try{
				if (input.getText().contains("forward")) {
					String f = scan.next(); // variable f can be used for saving (not that I do use it haha)
					int value = scan.nextInt();// vaiable "value" is given the value of the number of pixels to move the drawing eg. 50
					
					System.out.println(value);

					if (angle == 0) // angle "0" is heading east
					{
						gp.drawLine(colour, x1 + value, y1, x2, y2);
						gp.repaint();
						x1 = x1 + value;
						x2 = x2 + value;
					}

					if (angle == 1) // angle "1" is heading north
					{
						gp.drawLine(colour, x1, y1 - value, x2, y2);
						gp.repaint();
						y1 = y1 - value;
						y2 = y2 - value;
					}

					if (angle == 2) // angle "2" is heading west
					{
						gp.drawLine(colour, x1 - value, y1, x2, y2);
						gp.repaint();
						x1 = x1 - value;
						x2 = x2 - value;
					}

					if (angle == 3)// angle "3" is heading south
					{
						gp.drawLine(colour, x1, y1 + value, x2, y2);
						gp.repaint();
						y1 = y1 + value;
						y2 = y2 + value;
					}
					forwardInt = 1;
					
				}

				// BACKWARDS
				if (input.getText().contains("backwards")) { //if the command contains backwards do this!
					String f = scan.next();
					int value = scan.nextInt();

					System.out.println(value);
					gp.drawLine(colour, x1 - value, y1, x2, y2);
					gp.repaint();
					x1 = x1 - value;
					x2 = x2 - value;

					if (angle == 0) {//angle "0" is heading east
						gp.drawLine(colour, x1 + value, y1, x2, y2);
						gp.repaint();
						x1 = x1 - value;
						x2 = x2 - value;
					}

					if (angle == 1) {//angle "1" is heading north
						gp.drawLine(colour, x1, y1 - value, x2, y2);
						gp.repaint();
						y1 = y1 + value;
						y2 = y2 + value;
					}

					if (angle == 2) {//angle "2" is heading west
						gp.drawLine(colour, x1 + value, y1, x2, y2);
						gp.repaint();
						x1 = x1 + value;
						x2 = x2 + value;
					}

					if (angle == 3) {//angle "3" is heading south
						gp.drawLine(colour, x1, y1 - value, x2, y2);
						gp.repaint();
						y1 = y1 - value;
						y2 = y2 - value;
					}
					forwardInt = 1;
				}
				}catch (Exception e) {
					System.out.println(e);
				}
				if(input.getText().equals("forward") || input.getText().equals("backwards"))
				{
					JOptionPane.showMessageDialog(input,"You are missing parametres! Make sure you add a number to the command :)");
				}

				// TURNRIGHT
				if (input.getText().equals("turnright")) {
					System.out.println("Right");
					if (angle == 0) { //angle 0 is always east
						angle = 3;
					}

					else if (angle == 1) {
						angle = 0;
					}

					else if (angle == 2) {
						angle = 1;
					} else if (angle == 3) {
						angle = 2;
					}
					forwardInt = 1;
				} // else if is needed so that the turn doesn't just default to
					// this end one!

				// TURNLEFT
				if (input.getText().equals("turnleft")) {
					System.out.println("Left");
					if (angle == 0) {
						angle = 1;
					}

					else if (angle == 1) {
						angle = 2;
					}

					else if (angle == 2) {
						angle = 3;
					} else if (angle == 3) {
						angle = 0;
					}
					forwardInt = 1;

				}

				// COLOR CHANGING
				if (input.getText().equals("black")) {
					System.out.println("Black");
					colour = Color.black;
					forwardInt = 1;
				}
				
				if (input.getText().equals("green")) {
					System.out.println("Green");
					colour = Color.green;
					forwardInt = 1;
				}
				
				if (input.getText().equals("red")) {
					System.out.println("Red");
					colour = Color.red;
					forwardInt = 1;
				}
				
				if (input.getText().equals("yellow")) {
					System.out.println("Secret color");
					colour = Color.yellow;
					forwardInt = 1;
				}
				
				// RESETTING THE PANEL
				if (input.getText().equals("reset")) {
					System.out.println("Reset");
					gp.clear();
					gp.repaint();
					x1 = 400;
					x2 = 400;
					y1 = 250;
					y2 = 250;
					forwardInt = 1;
				}
				if (input.getText().equals("save")) {
					System.out.println("Save");
					ImageIO.write(gp.image, "PNG", new File("filename.png"));
					forwardInt = 1;
				}
				
				if (input.getText().equals("load")) {
					System.out.println("Load");
					gp.image = ImageIO.read(new File("filename.png"));
					gp.repaint();
					forwardInt = 1;
				}
				

				// ABOUT SECTION
				if (input.getText().equals("about")) { //opens a notepad file with a description of commands that can be used on my Turtle
					System.out.println("cheat");
					Runtime rt = Runtime.getRuntime();
					String file = "turtleGraphicsHelp.txt"; 
					forwardInt = 1;																				
					try {
						Process p = rt.exec("notepad " + file);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				
				// CATCHING INCORRECT COMMANDS
			} catch (Exception e) {
				System.out.println(e);
			}
			if (!input.getText().equals( // if input doesn't equal any of the set commands and forwardInt = 0 it must be an invalid command 
					"forward" + "penup" + "pendown" + "turnleft" + "turnright"
							+ "backwards" + "red" + "green" + "black"
							+ "yellow" + "about" + "save" + "load")
					&& forwardInt == 0) {
				System.out.println("That is not a valid command");
				JOptionPane
						.showMessageDialog(input,
								"Not a valid command, Type 'about' to learn the commands available");//typing about brings up the notepad file
			}
			forwardInt = 0;
		}

	}// this is the end of the action listener
}

