package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public class Connection {
	
	private Piece first,second;
	
	private static Stroke stroke1 = new BasicStroke(16);
	private static Stroke stroke2 = new BasicStroke(3);
	
	private int[][] lines1 = new int[][]{{0,1,2},{16,17,18},{2,6,11},{7,12,16},{11,15,18},{0,3,7},
			{0,8,16},{1,9,17},{2,10,18},{0,5,11},{3,9,15},{7,13,18},{7,4,2},{12,9,6},{16,14,11}};
	private int[][] lines2 = new int[][]{{3,4,5,6},{12,13,14,15},{1,5,10,15},{3,8,13,17},{1,4,8,12},{6,10,14,17}};
	private int[][] lines3 = new int[][]{{7,8,9,10,11},{0,4,9,14,18},{2,5,9,13,16}};
	
	private boolean filled;
	
	private int x1,x2,x3,x4,y1,y2,y3,y4;
	
	private ArrayList<Integer> connections;
	
	public Connection(Piece a, Piece b){
		
		first = a;
		second = b;
		this.filled = true;
		connections = new ArrayList<Integer>();
		fillIn(a.getPos(),b.getPos());
		
	}
	
	public Connection(int an, int bn){
		
		connections = new ArrayList<Integer>();
		
		int[] a = Board.getLoc(an);
		int[] b = Board.getLoc(bn);
		
		filled = false;
		
		double diffX = -(a[0] - b[0]);
		double diffY = (a[1] - b[1]);
		
		double dist = Math.sqrt(Math.pow(diffX, 2)+ Math.pow(diffY, 2));
		
		double scale = 7.0/dist;
		
		x1 = (int)(a[0] + diffY*scale);
		y1 = (int)(a[1] + diffX*scale);
		
		x2 = (int)(a[0] - diffY*scale);
		y2 = (int)(a[1] - diffX*scale);
		
		x3 = (int)(b[0] + diffY*scale);
		y3 = (int)(b[1] + diffX*scale);
		
		x4 = (int)(b[0] - diffY*scale);
		y4 = (int)(b[1] - diffX*scale); 
		
		fillIn(an,bn);
		
	}
	
	public void update(){
		if(first.moveTrigger || second.moveTrigger)fillIn(first.getPos(),second.getPos());
	}
	
	public void draw(Graphics2D g){
		
		g.setColor(Color.black);
		if(filled){
			g.setStroke(stroke1);
			g.drawLine(first.getX() + Board.getX(), first.getY() + Board.getY(), second.getX() + Board.getX(), second.getY() + Board.getY());
		}else{
			g.setStroke(stroke2);
			g.drawLine(x1+ Board.getX(), y1+ Board.getY(), x3+ Board.getX(), y3+ Board.getY());
			g.drawLine(x2+ Board.getX(), y2+ Board.getY(), x4+ Board.getX(), y4+ Board.getY());
		}
		
	}
	
	private void fillIn(int a, int b){
		
		connections.clear();
		
		for(int i = 0; i < 15; i++){
			if((a == lines1[i][0] && b == lines1[i][2]) || (a == lines1[i][2] && b == lines1[i][0])){
				connections.add(Board.prime[lines1[i][0]] * Board.prime[lines1[i][1]]);
				connections.add(Board.prime[lines1[i][1]] * Board.prime[lines1[i][2]]);
				return;
			}
		}
		
		for(int i = 0; i < 6; i++){
			if((a == lines2[i][0] && b == lines2[i][2]) || (a == lines2[i][2] && b == lines2[i][0])){
				connections.add(Board.prime[lines2[i][0]] * Board.prime[lines2[i][1]]);
				connections.add(Board.prime[lines2[i][1]] * Board.prime[lines2[i][2]]);
				return;
			}
			if((a == lines2[i][1] && b == lines2[i][3]) || (a == lines2[i][3] && b == lines2[i][1])){
				connections.add(Board.prime[lines2[i][1]] * Board.prime[lines2[i][2]]);
				connections.add(Board.prime[lines2[i][2]] * Board.prime[lines2[i][3]]);
				return;
			}
			if((a == lines2[i][0] && b == lines2[i][3]) || (a == lines2[i][3] && b == lines2[i][0])){
				connections.add(Board.prime[lines2[i][0]] * Board.prime[lines2[i][1]]);
				connections.add(Board.prime[lines2[i][1]] * Board.prime[lines2[i][2]]);
				connections.add(Board.prime[lines2[i][2]] * Board.prime[lines2[i][3]]);
				return;
			}
		}
		
		for(int i = 0; i < 3; i++){
			if((a == lines3[i][0] && b == lines3[i][2]) || (a == lines3[i][2] && b == lines3[i][0])){
				connections.add(Board.prime[lines3[i][0]] * Board.prime[lines3[i][1]]);
				connections.add(Board.prime[lines3[i][1]] * Board.prime[lines3[i][2]]);
				return;
			}
			if((a == lines3[i][1] && b == lines3[i][3]) || (a == lines3[i][3] && b == lines3[i][1])){
				connections.add(Board.prime[lines3[i][1]] * Board.prime[lines3[i][2]]);
				connections.add(Board.prime[lines3[i][2]] * Board.prime[lines3[i][3]]);
				return;
			}
			if((a == lines3[i][2] && b == lines3[i][4]) || (a == lines3[i][4] && b == lines3[i][2])){
				connections.add(Board.prime[lines3[i][2]] * Board.prime[lines3[i][3]]);
				connections.add(Board.prime[lines3[i][3]] * Board.prime[lines3[i][4]]);
				return;
			}
			if((a == lines3[i][0] && b == lines3[i][3]) || (a == lines3[i][3] && b == lines3[i][0])){
				connections.add(Board.prime[lines3[i][0]] * Board.prime[lines3[i][1]]);
				connections.add(Board.prime[lines3[i][1]] * Board.prime[lines3[i][2]]);
				connections.add(Board.prime[lines3[i][2]] * Board.prime[lines3[i][3]]);
				return;
			}
			if((a == lines3[i][1] && b == lines3[i][4]) || (a == lines3[i][4] && b == lines3[i][1])){
				connections.add(Board.prime[lines3[i][1]] * Board.prime[lines3[i][2]]);
				connections.add(Board.prime[lines3[i][2]] * Board.prime[lines3[i][3]]);
				connections.add(Board.prime[lines3[i][3]] * Board.prime[lines3[i][4]]);
				return;
			}
			if((a == lines3[i][0] && b == lines3[i][4]) || (a == lines3[i][4] && b == lines3[i][0])){
				connections.add(Board.prime[lines3[i][0]] * Board.prime[lines3[i][1]]);
				connections.add(Board.prime[lines3[i][1]] * Board.prime[lines3[i][2]]);
				connections.add(Board.prime[lines3[i][2]] * Board.prime[lines3[i][3]]);
				connections.add(Board.prime[lines3[i][3]] * Board.prime[lines3[i][4]]);
				return;
			}
		}
		
		connections.add(Board.prime[a] * Board.prime[b]);
		
	}

	public ArrayList<Integer> getConnections() {
		return connections;
	}
	
	public int numCon(){
		return connections.size();
	}
	
	public int check(int i){
		for(int p:connections){
			if (p == i)return 1;
		}
		return 0;
	}
	
}
