public class DotInfo {

	private int x;
	
	private int y;
	
	private int color;
	
	private boolean captured;

    public DotInfo(int x, int y, int color){

    	this.x = x;
    	this.y = y;
    	this.color = color;
    }

    public int getX(){

return x;
    }
    
    public int getY(){
    	return y;

    }
   
    public void setCaptured(boolean captured) {

this.captured = captured;
    }

    public boolean isCaptured(){

return this.captured;
    }

    public int getColor() {

return color;
    }
    
  
 }
