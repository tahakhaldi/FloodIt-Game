import java.util.Arrays;
import java.util.Random;

public class GameModel {

	public static final int COLOR_0           = 0;
	public static final int COLOR_1           = 1;
	public static final int COLOR_2           = 2;
	public static final int COLOR_3           = 3;
	public static final int COLOR_4           = 4;
	public static final int COLOR_5           = 5;
	public static final int NUMBER_OF_COLORS  = 6;


	private int size;

	private int curStepsNumber;

	private int currentSelectedColor;

	private DotInfo model[][];

	private final Random random = new Random ();

	public GameModel(int size) {

		this.size = size;
		model = new DotInfo[size][size];
		reset();
	}

	public void reset(){

		for (int row = 0; row < size; row ++)
		{
			for (int col = 0; col < size; col ++)
			{
				model[row][col] = new DotInfo (row, col, random.nextInt(NUMBER_OF_COLORS));
				model[row][col].setCaptured(false);
			}
		}
		curStepsNumber = 0;

	}

	public int getSize(){

		return size;
	}
	
	public int getColor(int i, int j){

		return model[i][j].getColor();
	} 
	public boolean isCaptured(int i, int j){

		return model[i][j].isCaptured();
	}
 
	public void capture(int i, int j){

		model[i][j].setCaptured(true);
	}  
	public int getNumberOfSteps(){

		return curStepsNumber;
	}
	
	public void setCurrentSelectedColor(int val) {

		this.currentSelectedColor = val;
	}
 
	public int getCurrentSelectedColor() {

		return this.currentSelectedColor;
	}

	public DotInfo get(int i, int j) {

		return model[i][j];
	}
	public void step(){

		curStepsNumber ++;
	}

	public boolean isFinished(){

		for (int row = 0; row < size; row ++)
		{
			for (int col = 0; col < size; col ++)
			{
				if (!model[row][col].isCaptured())
				{
					return false;
				}
			}
		}
		return true;
	}
	public String toString(){

		return Arrays.deepToString(model);
	}
}
