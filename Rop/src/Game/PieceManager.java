package Game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Scanner;

import Main.Inputs;

public class PieceManager {
	
	private Piece[] pieces;
	
	private int selected = -1;
	
	private Connection[] connections;
	
	private Connection[] goal;
	
	public static boolean won = false;
	
	public PieceManager(Piece[] pieces, Connection[] connections, Connection[] goal){
		
		this.pieces = pieces;
		this.connections = connections;
		this.goal = goal;
		
	}
	
	public PieceManager(String path){
		
		Scanner scanner = new Scanner(getClass().getResourceAsStream("/Maps/"+path));
		
		pieces = new Piece[scanner.nextInt()];
		
		for(int i = 0; i < pieces.length;i++){
			pieces[i] = new Piece(scanner.nextInt(),i);
		}
		
		connections = new Connection[scanner.nextInt()];
		
		for(int i = 0; i < connections.length;i++){
			connections[i] = new Connection(pieces[scanner.nextInt()],pieces[scanner.nextInt()]);
		}
		
		goal = new Connection[scanner.nextInt()];
		
		for(int i = 0; i < goal.length;i++){
			goal[i] = new Connection(scanner.nextInt(),scanner.nextInt());
		}
		
		won = false;
	}
	
	public void update(){
		
		for(Connection c:connections){
			c.update();
		}
		
		for(Piece p:pieces){
			if(p.moveTrigger){
				int total = 0;
				boolean all = true;
				for(Connection c:connections){
					total+=c.numCon();
				}
				int current = 0;
				for(Connection c:goal){
					for(int i:c.getConnections()){
						int temp = current;
						for(Connection t:connections){
							current += t.check(i);
						}
						if(temp == current)all = false;
					}
				}
				if(current == total && all){
					won = true;
				}
			}
			p.update();
		}
		
		if(selected==-1 &&Inputs.mleft && Inputs.mx > Board.getX()-40 && Inputs.mx < Board.getWidth() + Board.getX() + 40 && Inputs.my > Board.getY() - 40 && Inputs.my < Board.getHeight() + Board.getY() + 40){
			for(Piece p:pieces){
				if(p.getPoly().contains(Inputs.mx, Inputs.my)){
					p.setSelected(true);
					selected = p.getId();
					break;
				}
			}
		}else if(!Inputs.mleft && selected != -1){
			pieces[selected].setSelected(false);
			selected = -1;
		}
		
	}
	
	public void draw(Graphics2D g){
		
		for(Connection c:connections){
			c.draw(g);
		}
		
		for(Piece p:pieces){
			p.draw(g);
		}
		
	}
	
	public void draw(Graphics2D g, double d){
		
		for(Connection c:connections){
			c.draw(g);
		}
		
		for(Piece p:pieces){
			p.draw(g,d);
		}
		
	}
	
	public void drawGoal(Graphics2D g){
		
		for(Connection c:goal)
			c.draw(g);
		
	}
	
	public void fitPieces(){
		
		for(Piece p:pieces){
			Board.setVacancy(p.getPos(), false);
		}
		
	}
	
	public boolean hasWon(){
		return won;
	}
	
}
