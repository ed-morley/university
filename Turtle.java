import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.AbstractAction;

import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SMALL_ICON;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;

import javax.swing.AbstractAction;

import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.SMALL_ICON;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Turtle implements ActionListener {
	GraphicsPanel gp;
	CommandPanel cp;

	public void createShowGUI() {

		JFrame frame = new JFrame("Turtle Graphics");

		JMenuBar menuBar; // create a menu bar
		menuBar = new JMenuBar(); // create file menu
		menuBar.setBackground(Color.yellow);

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);// shortcut keys ALT+F
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);// shortcut keys ALT+H

		JMenuItem newitem = new JMenuItem("New"); // adds in the options within
		newitem.setMnemonic(KeyEvent.VK_N); // shortcut keys ALT+F+N // the
											// "file" menu bar
		JMenuItem loaditem = new JMenuItem("Load");
		loaditem.setMnemonic(KeyEvent.VK_L);// shortcut keys ALT+F+L
		JMenuItem saveitem = new JMenuItem("Save");
		saveitem.setMnemonic(KeyEvent.VK_S);// shortcut keys ALT+F+S
		JMenuItem exititem = new JMenuItem("Exit");
		exititem.setMnemonic(KeyEvent.VK_E);// shortcut keys ALT+F+E
		JMenuItem aboutitem = new JMenuItem("About");
		aboutitem.setMnemonic(KeyEvent.VK_A);// shortcut keys ALT+H+A

		newitem.addActionListener(this);
		loaditem.addActionListener(this);
		saveitem.addActionListener(this);
		exititem.addActionListener(this);
		aboutitem.addActionListener(this);

		try {
			Image iconNew = ImageIO.read(getClass().getResource("newicon.png")); // adds
																					// icons
																					// to
																					// the
																					// menu
																					// bar
			Image iconLoad = ImageIO.read(getClass()
					.getResource("loadicon.png"));
			Image iconSave = ImageIO.read(getClass()
					.getResource("saveicon.png"));
			Image iconExit = ImageIO.read(getClass()
					.getResource("Exiticon.png"));
			newitem.setIcon(new ImageIcon(iconNew));
			loaditem.setIcon(new ImageIcon(iconLoad));
			saveitem.setIcon(new ImageIcon(iconSave));
			exititem.setIcon(new ImageIcon(iconExit));
		} catch (IOException e) {
			e.printStackTrace();
		}

		menuBar.add(fileMenu); // add menu bar
		menuBar.add(helpMenu);

		fileMenu.add(newitem); // add menu bar options
		fileMenu.add(loaditem);
		fileMenu.add(saveitem);
		fileMenu.add(exititem);
		helpMenu.add(aboutitem);

		frame.setJMenuBar(menuBar);// makes the file menu show

		gp = new GraphicsPanel(); // graphics panel in Turtle page

		frame.getContentPane().add(gp, BorderLayout.NORTH);

		cp = new CommandPanel(gp);// created the link for command
									// panel
		frame.getContentPane().add(cp, BorderLayout.SOUTH);

		ToolBar tb = new ToolBar(gp, cp);
		frame.getContentPane().add(tb, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits the GUI
																// when clicked
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		Turtle myApp = new Turtle();
		myApp.createShowGUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) { // the implementation of action
													// listener
		JFileChooser chooser = new JFileChooser();

		JMenuItem source = (JMenuItem) (e.getSource());
		if (source.getText().equals("New")) {
			Object[] options = { "Yes I know",
					"No thank goodness for this pop up" };// option box in case
															// you didn't want
															// to clear the
															// graphics panel
			int n = JOptionPane.showOptionDialog(source,
					"Are you sure you want to create a new one without saving",
					"Are you sure you're sure?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]); // makes
																				// sure
																				// you
																				// have
																				// saved
																				// before
																				// you
																				// create
																				// a
																				// new
																				// panel
			if (n == 0) {
				gp.clear();
				gp.repaint();
				cp.x1 = 400;
				cp.x2 = 400;
				cp.y1 = 250;
				cp.y2 = 250;
				System.out.println("n= true" + n);
			} else if (n == 1) {
				JOptionPane.showMessageDialog(source, "New Cancelled");
				System.out.println("n= false" + n);
			} else if (n == JOptionPane.CLOSED_OPTION) {
				JOptionPane.showMessageDialog(source, "Dont break the code...");
			}
		}

		if (source.getText().equals("Load")) {
			// Handle open button action.
			Object[] options = { "Yes I know",
					"No, let me just save it!" };// option box in case
															// you didn't want
															// to clear the
															// graphics panel
			int n = JOptionPane.showOptionDialog(source,
					"Are you sure you want to load without saving",
					"Are you sure you're sure?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			try {
				load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(source, "Load Pressed"); 
		}

		if (source.getText().equals("Save")) {

			try {
				save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(source, "Save Pressed"); // this
																	// saves
																	// the
																	// programme

		}
		if (source.getText().equals("Exit")) {

			Object[] options = { "Yes I know",
					"No thank goodness for this pop up" };// option box in case
															// you didn't want
															// to clear the
															// graphics panel
			int n = JOptionPane.showOptionDialog(source,
					"are you sure you want to Exit without saving?",
					"New option Box", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (n == 0) {
				System.exit(0);// exits the program
				JOptionPane.showMessageDialog(source, "Exiting, Bye!");
			}

		}
		if (source.getText().equals("About")) {
			Runtime rt = Runtime.getRuntime();
			String file = "turtleGraphicsHelp.txt";
			try {
				Process p = rt.exec("notepad " + file);
			} catch (IOException ed) {
				ed.printStackTrace();

			}
		}
	}

	public void save() throws IOException {
		ImageIO.write(gp.image, "PNG", new File("filename.png")); // saves the
																	// file as a
																	// PNG with
																	// the
																	// specified
																	// filename
																	// and
																	// retrieves
																	// the data
																	// on load!
	}

	public void load() throws IOException {
		gp.image = ImageIO.read(new File("filename.png")); // update panel with
															// new paint image
		gp.repaint();
	}
}

