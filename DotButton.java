import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DotButton extends JButton {
	
	private final static ImageIcon S_ICONS[] = new ImageIcon [GameModel.NUMBER_OF_COLORS];
	private final static ImageIcon M_ICONS[] = new ImageIcon [GameModel.NUMBER_OF_COLORS];
	private final static ImageIcon N_ICONS[] = new ImageIcon [GameModel.NUMBER_OF_COLORS];
	
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

	public DotButton(int row, int column, int color, int iconSize) {

		this (color, iconSize);
		this.row = row;
		this.column = column;
	}

	public DotButton(int color, int iconSize) {

		this.iconSize = iconSize;
		setColor(color);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setPreferredSize(new Dimension (getIcon().getIconWidth(), getIcon().getIconHeight()));

		this.row = this.column = -1;
	}

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

	public int getColor(){

		return color;
	}

	public int getRow() {

		return row;
	}

	public int getColumn() {

		return column;
	}

}
