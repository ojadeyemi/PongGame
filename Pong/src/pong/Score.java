package pong;
import java.awt.*;

public class Score extends Rectangle{
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1; //score of player 1;
	int player2; //score of player 2;
	
	Score(int game_width, int game_height){
		Score.GAME_WIDTH = game_width;
		Score.GAME_HEIGHT = game_height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 60)); // font, bold, size
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2) - 85 , 50);
		g.drawString(String.valueOf(player2/10)+ String.valueOf(player2%10), (GAME_WIDTH/2) + 20 , 50);
	}
}
