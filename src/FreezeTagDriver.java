import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
/*
 * Ian Davis
 * Project5
 * November 10, 2016
 * This program is a game of freeze tag where the user does the tagging.
 */
import java.util.Random;
public class FreezeTagDriver {

	public static final int CANVAS_SIZE = 400;
	public static void main(String[] args) {
		
		StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
		StdDraw.setXscale(0, CANVAS_SIZE);
		StdDraw.setYscale(0, CANVAS_SIZE);
		
		Random rnd = new Random();
		
		//declare and array of 5 moving rectangles
		MovingRectangle[] r = new MovingRectangle[5];
		
		//initialize the values with the MovingRectangle constructor
		for(int i = 0; i < 5; i++){
			r[i] = new MovingRectangle(rnd.nextInt(CANVAS_SIZE+1) ,rnd.nextInt(CANVAS_SIZE+1)
					, rnd.nextInt(16)+10, rnd.nextInt(16)+10,rnd.nextInt(4)+1,rnd.nextInt(4)+1,CANVAS_SIZE);
		}
		
		boolean gameOver = false;
		
		while(!gameOver){
			StdDraw.clear();
			
			for(int i = 0; i < 5; i++){
				r[i].move();
					
				//check if rectangles have been clicked on freeze and turn red
				if(StdDraw.mousePressed() && r[i].containsPoint(StdDraw.mouseX(), StdDraw.mouseY())){
					r[i].setColor(Color.red);
					r[i].setFrozen(true);
				}
				
				//check if all of the rectangles are frozen
				//if they are the game is over
				if(r[0].isFrozen() && r[1].isFrozen() && r[2].isFrozen() && r[3].isFrozen() && r[4].isFrozen()){
					StdDraw.text(CANVAS_SIZE/2, CANVAS_SIZE/2, "You Win");
					gameOver = true;
				}
				
				r[i].draw();
			}
			
			//check for collisions and un-freeze
			for(int i = 0; i < 5; i++){
				for(int j = i+1; j < 5; j++){
					if(r[i].isIntersecting(r[j])){
						if(r[i].isFrozen()){
							r[i].setRandomColor();
						}
						r[j].setFrozen(false);
						r[i].setFrozen(false);
						
					}
				}
			}
			
			StdDraw.show(20);
		}
		
		System.out.println("You win!!!");

	}

}
