import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JFrame {
	
	private JLabel jlNumSteps;
	
	private GameModel model;
	
	private List<DotButton> buttonList = new ArrayList<>();

    public GameView(GameModel model, GameController gameController) {

    	this.model = model;

    	setTitle ("Flood It -- the ITI 121 version");
    	
    	JPanel jpMain = new JPanel (new BorderLayout(0, 10)) {
    		@Override
    		public Insets getInsets() { return new Insets (10, 10, 10, 10); }
    	};
    	
    	JPanel jpCenter = new JPanel(new GridLayout (model.getSize(), model.getSize()));
    	JPanel jpSouth = new JPanel(new BorderLayout(0, 5));
    	
    	JPanel jpSouthCenter = new JPanel(new FlowLayout (FlowLayout.CENTER, 5, 0));
    	JPanel jpSouthSouth = new JPanel(new FlowLayout (FlowLayout.CENTER, 15, 0));
    	
    	jpMain.setBackground(Color.WHITE);
    	jpCenter.setOpaque(false);
    	jpSouth.setOpaque(false);
    	jpSouthCenter.setOpaque(false);
    	jpSouthSouth.setOpaque(false);
    	
    	for (int row = 0; row < model.getSize(); row ++)
    	{
    		for (int col = 0; col < model.getSize(); col ++)
    		{
    			DotButton db;
    			jpCenter.add(db = new DotButton(row,  col, model.getColor(row, col), (model.getSize() >= 10 && model.getSize() <= 25) ? 1 : 0));
    			buttonList.add(db);
    		}
    	}

    	for (int color = 0; color < GameModel.NUMBER_OF_COLORS; color ++)
    	{
    		DotButton db;
    		jpSouthCenter.add(db = new DotButton(color, 2));

    		final int curColor = color;
    		db.addActionListener(e -> {
    			gameController.selectColor(curColor);
    		});
    	}
    	
    	jpSouthSouth.add (new JLabel ("Number of steps:"));
    	jpSouthSouth.add (jlNumSteps = new JLabel("0"));

    	JButton jbReset;
    	jpSouthSouth.add (jbReset = new JButton("Reset"));
    	jbReset.setActionCommand("resetButton");
    	jbReset.setBackground(Color.WHITE);
    	jbReset.addActionListener(gameController);
    	JButton jbQuit;
    	jpSouthSouth.add (jbQuit = new JButton("Quit"));
    	jbQuit.setActionCommand("quitButton");
    	jbQuit.setBackground(Color.WHITE);
    	jbQuit.addActionListener(gameController);
    	  	
    	jpSouthCenter.setBorder(BorderFactory.createCompoundBorder(
    			BorderFactory.createLineBorder(Color.GRAY, 3), 
    			BorderFactory.createEmptyBorder(4, 0, 3, 0)));
    	
    	jpSouth.add (jpSouthCenter, BorderLayout.CENTER);
    	jpSouth.add(jpSouthSouth, BorderLayout.SOUTH);
    	jpMain.add(jpCenter, BorderLayout.CENTER);
    	jpMain.add(jpSouth, BorderLayout.SOUTH);
    	
    	add (jpMain);
    	
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	pack();
    	setLocationRelativeTo(null);
    	setVisible (true);

    }

    public void update(){

    	int index = 0;
    	for (int row = 0; row < model.getSize(); row ++)
    	{
    		for (int col = 0; col < model.getSize(); col ++)
    		{
    			if (model.get(row, col).isCaptured())
    			{
    				buttonList.get(index).setColor (model.getCurrentSelectedColor());
    			} else {
    				buttonList.get(index).setColor(model.getColor(row, col));
    			}
    			index ++;
    		}
    	}
    	
    	jlNumSteps.setText(model.getNumberOfSteps() + "");


    }

}
