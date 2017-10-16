package Game;

import java.awt.Graphics2D;

import Main.Images;

public class Place {

	private int id;
	protected double x;
	protected double y;

	private boolean vacant = true;

	public Place(int id) {
		this.id = id;
		int[] loc = Board.locations[id];
		x = loc[0];
		y = loc[1];
	}
	
	public void draw(Graphics2D g){
		g.drawImage(Images.hex1, (int)x - 40 + Board.getX(), (int)y - 35 + Board.getY(), null);
	}
	
	public void draw(Graphics2D g,double d){
		
		int step = (int)(d * 100.0);
		
		int sx = (int)((Board.midX - x)*Math.pow(0.99, step));
		int sy = (int)((Board.midY - y)*Math.pow(0.99, step));
		
		g.drawImage(Images.hex3, Board.midX + sx - 40 + Board.getX(), Board.midY + sy - 35 + Board.getY(), null);
		
	}
	
	public boolean isVacant() {
		return vacant;
	}

	public void setVacancy(boolean vacant) {
		this.vacant = vacant;
	}
	

}
