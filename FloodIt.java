public class FloodIt {

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
