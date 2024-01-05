package pong;
import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{
	
	int id;
	int yVelocity;
	int speed = 15; //speed of paddle
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH, PADDLE_HEIGHT);  //since paddle is a rectangle use rectangle constructor
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
	//click W to move 'speed' pixels up;
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
			
			}
	//click W to move 'speed' pixels up;
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
				
			}
			break;
		case 2:
	//click up_arrow to move 'speed' pixels up;
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
				
			}
	//click down_arrow to move 'speed' pixels up;
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
				
			}
			break;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
	
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0); //does not move when key release
				
			}
	
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				
			}
			break;
		case 2:
	
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
	
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}
		
	}
	
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	public void move() {
		y = y + yVelocity;
		
	}
	
	public void draw(Graphics g) {
		if(id==1) {
			g.setColor(Color.green);
		}
		else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);
	}
}
