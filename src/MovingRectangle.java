/*
 * Ian Davis
 * Project5
 * November 10, 2016
 * This program is a game of freeze tag where the user does the tagging
 */
import java.awt.Color;
import java.util.Random;
import edu.princeton.cs.introcs.StdDraw;
public class MovingRectangle {
	
	// initialize private instance variables
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private int xVelocity;
	private int yVelocity;
	private int canvasSize;
	private Color color;
	private boolean frozen;
	
	Random rnd = new Random();
	
	// constructor
	public MovingRectangle(int x, int y, int w, int h, int xV, int yV, int canSize){
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		xVelocity = xV;
		yVelocity = yV;
		canvasSize = canSize;
		setRandomColor();
		frozen = false;
	}
	
	//draws a filled rectangle 
	public void draw(){
		StdDraw.setPenColor(color);
		StdDraw.filledRectangle(xCoord, yCoord, width/2, height/2);
	}
	
	//moves a rectangle at given velocities if not frozen and contains rectangles within the canvas
	public void move(){
		if(!frozen){
			xCoord += xVelocity;
			yCoord += yVelocity;
		}
		if(xCoord >= canvasSize || xCoord <= 0){
			xVelocity = -1 * xVelocity;//create bounce effect
			setRandomColor();
		}
		if(yCoord >= canvasSize || yCoord <= 0){
			yVelocity = -1 * yVelocity;//create bounce effect
			setRandomColor();
		}
	}
	
	//sets the color of a rectangle
	//for this game it its used to turn rectangels red when frozen
	public void setColor(Color c){
		color = c;
	}
	
	//sets a rectangle randomly to one of seven colors
	//used when rectangles bounce off the outside of the canvas
	//	and when a rectangle is unfrozen
	public void setRandomColor(){
		int i = rnd.nextInt(7);
		if(i == 0){
			color = StdDraw.BLUE;
		}
		else if(i == 1){
			color = StdDraw.CYAN;
		}
		else if(i == 2){
			color =StdDraw.GREEN;
		}
		else if(i == 3){
			color = StdDraw.MAGENTA;
		}
		else if(i == 4){
			color = StdDraw.ORANGE;
		}
		else if(i == 5){
			color = StdDraw.RED;
		}
		else if(i == 6){
			color = StdDraw.YELLOW;
		}
	}
	
	//checks whether a given point is within a given rectangle
	//used in this game to see if a mouse click falls within a rectangle
	public boolean containsPoint(double x, double y){
		if(x > xCoord + width/2 || x < xCoord - width/2 || y > yCoord + height/2 || y < yCoord - height/2){
			return false;
		}
		else{
			return true;
		}
	}
	
	//returns true if a rectangle is frozen, false if moving
	public boolean isFrozen(){
		if(frozen){
			return true;
		}
		else{
			return false;
		}
	}
	
	//sets a given rectangles frozen instance variable
	public void setFrozen(boolean freeze){
		if(freeze){
			frozen = true;
		}
		else if(!freeze){
			frozen = false;
		}
	}
	
	//checks to see if two rectangles intersect eachother
	public boolean isIntersecting(MovingRectangle r){
		double top1 = yCoord + height/2;
		double bottom1 = yCoord - height/2;
		double left1 = xCoord - width/2;
		double right1 = xCoord + width/2;
		
		double top2 = r.yCoord + r.height/2;
		double bottom2 = r.yCoord - r.height/2;
		double left2 = r.xCoord - r.width/2;
		double right2 = r.xCoord + r.width/2;
		
		boolean intersection;
		if (bottom1 > top2){
			intersection = false;
			}
		else if (bottom2 > top1){
			intersection = false;
		}
		else if (right1 < left2){
			intersection = false;
		}
		else if (right2 < left1){
			intersection = false;
		}
		else{
			intersection = true;
		}
		
		return intersection;
	}

}
