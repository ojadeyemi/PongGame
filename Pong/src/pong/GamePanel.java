package pong;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements  Runnable {
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); //aspect ratio of a ping-pong table is 5:9(5/9 is 0.555..)
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	//Instantiate game objects
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL()); //responds to key tap
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),BALL_DIAMETER, BALL_DIAMETER);
		//ball radnom spaans on y axis in the centre
	}
	
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1 );
		paddle2 = new Paddle((GAME_WIDTH - PADDLE_WIDTH),(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2 );
	}
	
	//paint graphics to screen
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics(); 
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	public void checkCollision() {
		//stop paddles at window edges
		if(paddle1.y <= 0) {
			paddle1.y = 0;
		}
		
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
		}
		
		if(paddle2.y <= 0) {
			paddle2.y = 0;
		}
		
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
		}
		//bounce ball of top and bottom window edges
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		//reward point if ball goes outta bound
		if(ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			
		}
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
		}
		
		// bounce off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = (ball.xVelocity * -1) + 1; // ball gets faster after collision
			ball.setXDirection(ball.xVelocity);
			
		}
		
		if(ball.intersects(paddle2)) {
			ball.xVelocity = (ball.xVelocity * -1) - 1; // ball gets faster after collision
			ball.setXDirection(ball.xVelocity);
		}
	}
	
	public void run() {
		//basic ahhh game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks; //1 billion nanoseconds divided by amount of ticks
		double delta = 0;
		
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	//deals with keyboard input
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			
		}
		
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
	
}
