package pong;
import java.awt.*;
import java.util.*;


public class Ball extends Rectangle {
	 Random random;
	 int xVelocity;
	 int yVelocity;
	 int initialSpeed = 5;
	
	Ball(int x, int y, int BALL_WIDTH, int BALL_HEIGHT){
		super(x,y,BALL_WIDTH, BALL_HEIGHT);
		random = new Random();
		int randomXDirection = random.nextInt(2);//either 0 or 1
		if(randomXDirection == 0) {
			randomXDirection--;
		}
		
		setXDirection(randomXDirection * initialSpeed);
		int randomYDirection = random.nextInt(2);//either 0 or 1
		if(randomYDirection == 0) {
			randomYDirection--;
		}
		
		setYDirection(randomYDirection * initialSpeed);
	 }
	 
	 public void setXDirection(int randomXDirection) {
		 xVelocity = randomXDirection;
	 }
	 
	 public void setYDirection(int randomYDirection) {
		 yVelocity = randomYDirection;
	 }
	 
	 public void move() {
		 x += xVelocity;
		 y += yVelocity;
	 }
	 
	 public void draw(Graphics g) {
		 g.setColor(Color.cyan);
		 g.fillOval(x, y, width, height);//make shape ball like
	 }
	 
}
