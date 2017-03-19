// Author: Taha Khaldi 
// Student numbers: 8667551 
// Course: ITI 1121-D
// Assignment: 3

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

// ADD YOUR IMPORTS HERE

/**
 * In the application <b>FloodIt</b>, a <b>DotButton</b> is a specialized color of
 * <b>JButton</b> that represents a dot in the game. It can have one of six colors
 * 
 * The icon images are stored in a subdirectory ``data''. We have 3 sizes, ``normal'',
 * ``medium'' and ``small'', respectively in directory ``N'', ``M'' and ``S''.
 *
 * The images are 
 * ball-0.png => grey icon
 * ball-1.png => orange icon
 * ball-2.png => blue icon
 * ball-3.png => green icon
 * ball-4.png => purple icon
 * ball-5.png => red icon
 *
 *  <a href=
 * "http://developer.apple.com/library/safari/#samplecode/Puzzler/Introduction/Intro.html%23//apple_ref/doc/uid/DTS10004409"
 * >Based on Puzzler by Apple</a>.
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {
	
	private final static ImageIcon S_ICONS[] = new ImageIcon [GameModel.NUMBER_OF_COLORS];
	private final static ImageIcon M_ICONS[] = new ImageIcon [GameModel.NUMBER_OF_COLORS];
	private final static ImageIcon N_ICONS[] = new ImageIcon [GameModel.NUMBER_OF_COLORS];
	
	// Initialize all image icons only once when the program loaded to avoid creating new ImageIcon objects
	// each time when the DotButton changes its color.
	static {
		for (int i = 0; i < S_ICONS.length; i ++)
		{
			S_ICONS[i] = new ImageIcon (DotButton.class.getResource("data/S/ball-" + i + ".png"));
		}
		for (int i = 0; i < M_ICONS.length; i ++)
		{
			M_ICONS[i] = new ImageIcon (DotButton.class.getResource("data/M/ball-" + i + ".png"));
		}
		for (int i = 0; i < N_ICONS.length; i ++)
		{
			N_ICONS[i] = new ImageIcon (DotButton.class.getResource("data/N/ball-" + i + ".png"));
		}
	}

	private int row;
	private int column;
	private int color;
	private int iconSize;

	/**
	 * Constructor used for initializing a cell of a specified color.
	 * 
	 * @param row
	 *            the row of this Cell
	 * @param column
	 *            the column of this Cell
	 * @param color
	 *            specifies the color of this cell
	 * @param iconSize
	 *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
	 */

	public DotButton(int row, int column, int color, int iconSize) {

		this (color, iconSize);
		this.row = row;
		this.column = column;
	}

	/**
	 * Other constructor used for initializing a cell of a specified color.
	 * no row or column info available. Uses "-1, -1" for row and column instead
	 * 
	 * @param color
	 *            specifies the color of this cell
	 * @param iconSize
	 *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
	 */   
	public DotButton(int color, int iconSize) {

		this.iconSize = iconSize;
		setColor(color);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setPreferredSize(new Dimension (getIcon().getIconWidth(), getIcon().getIconHeight()));

		this.row = this.column = -1;
	}



	/**
	 * Changes the cell color of this cell. The image is updated accordingly.
	 * 
	 * @param color
	 *            the color to set
	 */

	public void setColor(int color) {

		// ADD YOUR CODE HERE
		this.color = color;

		if (iconSize == 0)
		{
			setIcon (S_ICONS[color]);
		} else if (iconSize == 1)
		{
			setIcon (M_ICONS[color]);
		} else if (iconSize == 2)
		{
			setIcon (N_ICONS[color]);
		}
	}

	/**
	 * Getter for color
	 *
	 * @return color
	 */
	public int getColor(){

		return color;
	}

	/**
	 * Getter method for the attribute row.
	 * 
	 * @return the value of the attribute row
	 */

	public int getRow() {

		return row;
	}

	/**
	 * Getter method for the attribute column.
	 * 
	 * @return the value of the attribute column
	 */

	public int getColumn() {

		return column;
	}

}