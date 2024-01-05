package pong;
import java.awt.*;
import javax.swing.*;

//game frame houses our panel
//has minimize button, close button etc
public class GameFrame extends JFrame{
	
	GamePanel panel;
	
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("OJ's Pong Game"); //title
		this.setResizable(false); //cannot resize panel;
		this.setBackground(Color.black); //
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x button closes application
		this.pack(); //makes game frame fit our panel
		this.setVisible(true);
		this.setLocationRelativeTo(null);//appears in middle of screen when run instead of top left
		
	}
}
