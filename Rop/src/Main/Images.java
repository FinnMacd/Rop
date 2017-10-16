package Main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Images {
	
	public static BufferedImage hex1, hex2, hex3;
	
	public static void loadImages(){
		try{
			hex1 = ImageIO.read(Images.class.getResourceAsStream("/Images/Hex.png"));
			hex2 = ImageIO.read(Images.class.getResourceAsStream("/Images/Hex2.png"));
			hex3 = ImageIO.read(Images.class.getResourceAsStream("/Images/Hex3.png"));
		}catch(Exception e){
			System.err.println("could not load images.");
			JOptionPane.showMessageDialog(null, "could not load images");
		}
	}
	
}
