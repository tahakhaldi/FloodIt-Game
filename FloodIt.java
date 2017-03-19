// Author: Taha Khaldi 
// Student numbers: 8667551 
// Course: ITI 1121-D
// Assignment: 3

/**
 * The class <b>FloodIt</b> launches the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class FloodIt {


	/**
	 * <b>main</b> of the application. Creates the instance of  GameController 
	 * and starts the game. If a game size (<12) is passed as parameter, it is 
	 * used as the board size. Otherwise, a default value is passed
	 * 
	 * @param args
	 *            command line parameters
	 */
	public static void main(String[] args) {

		try
		{
			int size = Integer.parseInt(args[0]);
			if (size <= 10)
			{
				throw new Exception ();
			}
			new GameController(size);
		} 
		catch (Exception e)
		{
			new GameController(12);
		}
		

	}


}