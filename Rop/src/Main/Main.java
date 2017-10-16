package Main;

import javax.swing.JFrame;

public class Main {
	
	public static JFrame frame = new JFrame();
	public static String title = "Rop";
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		
		GamePanel gp = new GamePanel();
		
		frame.setTitle(title);
		frame.add(gp);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		gp.start();
		
	}
	
}
