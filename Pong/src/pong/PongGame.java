package pong;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class PongGame {

	public static void main(String[] args) {
		
		GameFrame frame = new GameFrame();
		playMusic("C:\\Users\\OJ Adeyemi\\Desktop\\SCHOOL\\YEAR 2\\ADVANCED OOP\\ECLIPSE_FILES\\Pong\\src\\pong\\Soul 4 Real Candy Rain.wav");

	}
	
	 public static void playMusic(String s) {
	        
		 try {
	            File file = new File(s);
	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
	            Clip clip = AudioSystem.getClip();
	            clip.open(inputStream);
	            // Get the volume control for the clip
	            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

	            // Set the volume level (in decibels)
	            float volume = -20.0f; // Adjust this value to set the desired volume
	            gainControl.setValue(volume);
	            clip.start();;
	            clip.loop(Clip.LOOP_CONTINUOUSLY);
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	            e.printStackTrace();
	            // Handle the exception as needed
	        }
	 }
}
