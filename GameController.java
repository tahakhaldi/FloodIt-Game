// Author: Taha Khaldi 
// Student numbers: 8667551 
// Course: ITI 1121-D
// Assignment: 3

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

	private GameModel model;
	private GameView view;

	private boolean first = true;

	private final int [] row_neighbors = { 0, -1, 0, 1 };
	private final int [] col_neighbors = { -1, 0, 1, 0 };
	private final Object[] options = { "Quit", "Play Again" };


	/**
	 * Constructor used for initializing the controller. It creates the game's view 
	 * and the game's model instances
	 * 
	 * @param size
	 *            the size of the board on which the game will be played
	 */
	public GameController(int size) {

		model = new GameModel(size);
		SwingUtilities.invokeLater(() -> {
			view = new GameView(model, this);
			reset();
		});
		

	}

	/**
	 * resets the game
	 */
	public void reset(){

		first = true;
		model.capture(0, 0);
		selectColor(model.getColor(0, 0));

	}

	/**
	 * Callback used when the user clicks a button (reset or quit)
	 *
	 * @param e
	 *            the ActionEvent
	 */

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command)
		{
		case "quitButton":
			quit();
			break;
		case "resetButton":		
			model.reset();
			reset();
			view.update();
			break;

		}

	}

	/**
	 * <b>selectColor</b> is the method called when the user selects a new color.
	 * If that color is not the currently selected one, then it applies the logic
	 * of the game to capture possible locations. It then checks if the game
	 * is finished, and if so, congratulates the player, showing the number of
	 * moves, and gives two options: start a new game, or exit
	 * @param color
	 *            the newly selected color
	 */
	public void selectColor(int color){

		model.setCurrentSelectedColor(color);
		if (first) {
			first = false;
		} else {
			model.step();
		}
		stackBasedFlooding(color);
		view.update();
		if (model.isFinished())
		{

			int option = JOptionPane.showOptionDialog(view, "Congratulation, you won in " + model.getNumberOfSteps() + " steps!\n"
					+ "Would you like to play again?", "Won", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[1]);
			if (option == 0)
			{
				quit();
			} else {
				model.reset();
				reset();
				view.update();
			}
		}
	}

	private void quit()
	{
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
	}

	private void stackBasedFlooding (int newColor)
	{
		Stack<DotInfo> stack = this.new MyStack();

		for (int row = 0; row < model.getSize(); row ++)
		{
			for (int col = 0; col < model.getSize(); col ++)
			{
				DotInfo di = model.get(row, col);

				if (di.isCaptured())
				{
					stack.push (di);
				}
			}
		}

		while (!stack.isEmpty())
		{
			DotInfo di = stack.pop();

			for (int i = 0; i < 4; i ++) // 4 because only 4 neighbors can have 1 dot.
			{
				int nRow = di.getX() + row_neighbors[i];
				int nCol = di.getY() + col_neighbors[i];
				if (nRow >= 0 && nCol >= 0 && nRow < model.getSize() && nCol < model.getSize())
				{
					DotInfo diNeighbor = model.get(nRow, nCol);
					if (!diNeighbor.isCaptured() && diNeighbor.getColor() == newColor)
					{
						model.capture(nRow, nCol);
						stack.push(diNeighbor);
					} // End If
				}

			} // End For
		} // End While
	}


	class MyStack implements Stack<DotInfo> {

		private DotInfo [] array = new DotInfo [0];

		@Override
		public boolean isEmpty() {

			return array.length == 0;
		}

		@Override
		public DotInfo peek() {
			if (isEmpty())
			{
				return null;
			}
			return array[0];
		}

		@Override
		public DotInfo pop() {
			DotInfo res = peek();
			if (res != null)
			{
				DotInfo [] newArray = new DotInfo [array.length - 1];
				if (newArray.length != 0)
				{
					System.arraycopy(array, 1, newArray, 0, newArray.length);
				}
				array = newArray;
			}

			return res;
		}

		@Override
		public void push(DotInfo element) {
			DotInfo [] newArray = new DotInfo [array.length + 1];
			if (!isEmpty())
			{
				System.arraycopy(array, 0, newArray, 1, array.length);
			}

			newArray[0] = element;
			array = newArray;
		}
		@Override
		public String toString()
		{
			return Arrays.toString(array);
		}

	}

}