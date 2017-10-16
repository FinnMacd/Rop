package Game;

import java.awt.Graphics2D;
import java.awt.Polygon;

import Main.GamePanel;
import Main.Images;
import Main.Inputs;

public class Piece{
	
	private int pos,id,homeX,homeY,relativeX = 0, relativeY = 0;
	private double x,y;
	private int sx,sy;
	
	private boolean selected = false;
	public boolean moveTrigger = false;
	
	public Piece(int pos, int id){
		super();
		
		int[] loc = Board.locations[pos];
		
		this.x = homeX = loc[0];
		this.y = homeY = loc[1];
		this.id = id;
		this.pos = pos;
		
	}
	
	
	public void update(){
		if(moveTrigger)moveTrigger = false;
		double slide = 0.1;
		if(selected){
			x += slide * (Inputs.mx-Board.getX()-x-relativeX);
			y += slide * (Inputs.my-Board.getY()-y-relativeY);
		}else if(Math.abs(x - homeX) > 0.5 || Math.abs(y - homeY) > 0.5 ){
			x += slide * (homeX - x);
			y += slide * (homeY - y);
		}
	}
	
	public void draw(Graphics2D g){
		g.drawImage(Main.Images.hex2, (int)x-40 + Board.getX(), (int)y-35 + Board.getY(), null);
	}
	
	public void draw(Graphics2D g, double d){
		int step = (int)(d * 100.0);
		
		int sx = (int)((Board.midX - x)*Math.pow(0.99, step));
		int sy = -(int)((Board.midY - y)*Math.pow(0.99, step));
		
		this.sx = Board.midX + sx;
		this.sy = Board.midY + sy;
		
		g.drawImage(Images.hex2, Board.midX + sx - 40 + Board.getX(), Board.midY + sy - 35 + Board.getY(), null);
	}

	public int getId() {
		return id;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public Polygon getPoly(){
		return new Polygon(new int[]{(int)x + Board.getX() + -20,(int)x + Board.getX() + 20,(int)x + Board.getX() + 40,(int)x + Board.getX() + 20,(int)x + Board.getX() + -20,(int)x + Board.getX() + -40},
				new int[]{(int)y + Board.getY() + -35,(int)y + Board.getY() + -35,(int)y + Board.getY() + 0,(int)y + Board.getY() + 35,(int)y + Board.getY() + 35,(int)y + Board.getY() + 0},6);
	}

	public boolean isSelected() {
		return selected;
	}
	
	private void getDest(){
		int searchX = Inputs.mx-Board.getX()-relativeX;
		int searchY = Inputs.my-Board.getY()-relativeY;
		
		for(int i = 0; i < 19; i++){
			int[] loc = Board.locations[i];
			
			double distX = loc[0]-searchX;
			double distY = loc[1]-searchY;
			double dist = Math.sqrt(distX * distX + distY * distY);
			
			if(dist<40&&Board.isVacanct(i)){
				Board.setVacancy(pos, true);
				Board.setVacancy(i, false);
				pos = i;
				homeX = loc[0];
				homeY = loc[1];
				break;
			}
			
		}
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected){
			relativeX = Inputs.mx - (int)x - Board.getX();
			relativeY = Inputs.my - (int)y - Board.getY();
		}else{
			getDest();
			moveTrigger = true;
		}
	}

	public int getX() {
		if(!PieceManager.won){
			return (int)x;
		}
		return (int)sx;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		if(!PieceManager.won){
			return (int)y;
		}
		return (int)sy;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
