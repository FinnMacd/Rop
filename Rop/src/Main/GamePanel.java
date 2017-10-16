package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Game.GameManager;

public class GamePanel extends JPanel implements Runnable{
	
	public static volatile int WIDTH = 640, HEIGHT = 640;
	
	public static Thread thread;
	
	private Inputs inputs;
	
	private GameManager gm;
	
	private boolean initialized = false;
	
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		requestFocus();
	}
	
	public void start(){
		thread = new Thread(this,"GameLoop");
		thread.start();
	}
	
	public void init(){
		
		gm = new GameManager();
		
		inputs = new Inputs(this);
		
		Images.loadImages();
		
		addMouseListener(inputs);
		addMouseMotionListener(inputs);
		addKeyListener(inputs);
		addFocusListener(inputs);
		addMouseWheelListener(inputs);
		setFocusable(true);
		
		initialized = true;
	}
	
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/60.0;
		double catchUp = 0;
		int frames = 0;
		int updates = 0;
		
		while(true){
			
			long now = System.nanoTime();
			catchUp += (now-lastTime)/ns;
			lastTime = now;
			
			while(catchUp >= 1){
				update();
				updates++;
				catchUp--;
			}
			
			repaint();
			frames++;
			
			if(System.currentTimeMillis()-timer >= 1000){
				timer += 1000;
				Main.frame.setTitle(Main.title + " | "+updates+"ups, "+ frames + "fps");
				updates = frames = 0;
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void update(){
		gm.update();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(!initialized)return;
		gm.draw((Graphics2D)g);
	}
	
	
}
