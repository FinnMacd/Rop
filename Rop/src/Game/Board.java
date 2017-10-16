package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Images;

public class Board {
	
	private PieceManager pm;
	
	private static Place[] places;
	
	public static int[][] locations = new int[19][2];
	public static int[] prime = new int[]{1,2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67};
	
	private static int x, y; 
	private static double width = 400, length = (width / 4.0), height = Math.sqrt(Math.pow(width, 2)-Math.pow(width/2, 2));
	public static int midX = (int)(width/2.0),midY = (int)(height / 2.0);
	
	public Board(PieceManager pm, int x, int y){
		
		this.pm = pm;
		places = new Place[19];
		
		Board.x = x;
		Board.y = y;
		
		for(int i = 0; i < 19; i++)
			places[i] = new Place(i);
		
		pm.fitPieces();
		
	}
	
	public void update(){
		
		pm.update();
		
	}
	
	public void draw(Graphics2D g){
		
		g.setColor(Color.black);
		
		pm.drawGoal(g);
		
		for(int i = 0; i < 19; i++){
			places[i].draw(g);
		}
		
		pm.draw(g);
		
	}
	
	public void drawEnd(Graphics2D g, double d){
		
		for(int i = 0; i < 19; i++){
			places[i].draw(g,d);
		}
		
		pm.draw(g, d);
		
	}
	
	public void setPieces(PieceManager pm){
		this.pm = pm;
	}
	
	public static void clearPlace(){
		
		for(Place p:places)
			p.setVacancy(true);
		
	}
	
	public static void setVacancy(int index, boolean empty){
		places[index].setVacancy(empty);
	}
	
	public static boolean isVacanct(int index){
		return places[index].isVacant();
	}
	
	public static void setLocations(){
		for(int i = 0; i < 19; i++){
			locations[i] = place(i);
		}
	}
	
	public static int[] getLoc(int index){
		return locations[index];
	}
	
	public static int[] place(int i){
		
		int y, x, xpos, ypos, offset;
		
		if(i < 3){
			y = 0;
			offset = 0;
		}			
		else if(i < 7){
			y = 1;
			offset = 3;
		}
		else if(i < 12){
			y = 2;
			offset = 7;
		}
		else if(i < 16){
			y = 3;
			offset = 12;
		}
		else{
			y = 4;
			offset = 16;
		}
		
		x = i - offset;
		
		xpos = (int)(Math.abs(y - 2) * 0.5 * length + x*length);
		
		ypos = (int)((height/4.0) * y);
		
		return new int[]{xpos,ypos};
		
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static double getWidth() {
		return width;
	}

	public static double getHeight() {
		return height;
	}
	
	public PieceManager getPiece(){
		return pm;
	}
	
}
