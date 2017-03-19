import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameController implements ActionListener {

	private GameModel model;
	private GameView view;

	private boolean first = true;

	private final int [] row_neighbors = { 0, -1, 0, 1 };
	private final int [] col_neighbors = { -1, 0, 1, 0 };
	private final Object[] options = { "Quit", "Play Again" };

	public GameController(int size) {

		model = new GameModel(size);
		SwingUtilities.invokeLater(() -> {
			view = new GameView(model, this);
			reset();
		});
		

	}
	
	public void reset(){

		first = true;
		model.capture(0, 0);
		selectColor(model.getColor(0, 0));

	}
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
					} 
				}

			} /
		} 
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
