package die;

import java.util.Random;
import java.awt.*;
import javax.swing.*;

/**
Java die useful if you are playing a game and need a die
 */

public class Die extends JFrame {
	// you just need one random generator
	private static final Random RNG = new Random();

	// all die have the same dimension
	private static final Dimension SIZE = new Dimension(48, 48);

	// generates a value between 0 to 5 you add 1 so it generates values between 1
	// and 6
	private int value = RNG.nextInt(6) + 1;

	
	
	public Die() {
		
		//JFrame will close when you click on the x button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set layout for the frame
		setLayout(new FlowLayout());
		
		//JPanel for adding the roll button
		JPanel rollPanel = new JPanel();

		JButton roll = new JButton("Roll");

		//add event listener to the roll button so that a random number is generate each time you click on roll
		roll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				roll();
			}
		});

		//add roll button to roll JPanel
		rollPanel.add(roll);
		
		
		//add die to JFrame
		add(new DiePanel());
		
		//add JPanel for roll button to the JFrame
		add(rollPanel);

		//set JFrame to take the size of the components inside it
		 pack();
		 
		//make the JFrame visible
		setVisible(true);
	}

	public void roll() {

		// for active die generate random number between 1 and 6
		value = RNG.nextInt(6) + 1;

		// repaints the frame mainly due to changes
		// this calls the paintcomponent method
		repaint();
	}

 

	public void setValue(int value) throws IllegalArgumentException {
		if (value >= 0 && value <= 6)
			this.value = value;
		else
			throw new IllegalArgumentException(value + " is not in the range [1-6]");
	}

	public class DiePanel extends JPanel {

		
		private static final long serialVersionUID = 1L;
		private Color color;

		public DiePanel() {
			// calls second constructor
			this(Color.WHITE);

		}

		/**
		 * Constructs a single die with the specified color.
		 */

		public DiePanel(Color color) {
			this.color = color;
			setPreferredSize(SIZE);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			setBackground(color);
			// for making the die a curvy at the edges
			// 47 is 1 below the size of die to draw inside die
			g.drawRoundRect(0, 0, 47, 47, 5, 5);
			switch (value) {
			case 1:
				// location of upper left hand corner of rectangle that the oval will be inside
				// dots will be 6 pixels in diameter
				g.fillOval(21, 21, 6, 6);
				break;
			case 4:
				g.fillOval(13, 29, 6, 6);
				g.fillOval(29, 13, 6, 6);

			case 2:
				g.fillOval(13, 13, 6, 6);
				g.fillOval(29, 29, 6, 6);
				break;

			case 5: // use the 3 and just add the missing 2 dots
				g.fillOval(9, 33, 6, 6);

				g.fillOval(33, 9, 6, 6);

			case 3:
				g.fillOval(9, 9, 6, 6);
				g.fillOval(21, 21, 6, 6);
				g.fillOval(33, 33, 6, 6);

				break;

			case 6:
				g.fillOval(13, 9, 6, 6);
				g.fillOval(13, 21, 6, 6);
				g.fillOval(13, 33, 6, 6);

				g.fillOval(29, 9, 6, 6);
				g.fillOval(29, 21, 6, 6);
				g.fillOval(29, 33, 6, 6);
				break;

			default:
				System.err.println("Value is out of bounds " + value);
				break;

			}

		}

	}

	public static void main(String[] args) {
		new Die();

	}
}
