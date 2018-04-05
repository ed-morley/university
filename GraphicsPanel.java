

	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.image.BufferedImage;
	import javax.swing.JPanel;

	/**
	 * Represents the graphics display panel within the turtle program. This panel contains an image which is updated to reflect user commands.
	 * 
	 * @author mdixon
	 * 
	 */
	@SuppressWarnings("serial")
	public class GraphicsPanel extends JPanel {

		/**
		 * The default BG colour of the image.
		 */
		private final static Color BACKGROUND_COL = Color.LIGHT_GRAY;
		
		/**
		 * The underlying image used for drawing. This is required so any previous drawing activity is persistent on the panel.
		 */
		public BufferedImage image;

			/**
		 * Draw a line on the image using the given colour.
		 * 
		 * @param color
		 * @param x1
		 * @param y1
		 * @param x2
		 * @param y2
		 */
		public void drawLine(Color color, int x1, int y1, int x2, int y2) {
			
			Graphics g = image.getGraphics();
			
			g.setColor(color);
			
			g.drawLine(x1, y1, x2, y2);
		}
		
		/**
		 * Clears the image contents.
		 */
		public void clear() {
			
			Graphics g = image.getGraphics();
			
			g.setColor(BACKGROUND_COL);
			
			g.fillRect(0, 0, image.getWidth(),  image.getHeight());
		}
		
		@Override
		public void paint(Graphics g) {

			// render the image on the panel.
			g.drawImage(image, 0, 0, null);
		}

		/**
		 * Constructor.
		 */
		GraphicsPanel() {

			setPreferredSize(new Dimension(800, 500));
			setFocusable(true);

			image = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_RGB);
			
			// Set max size of the panel, so that is matches the max size of the image.
			setMaximumSize(new Dimension(image.getWidth(), image.getHeight()));
			
			clear();
		}
	}

