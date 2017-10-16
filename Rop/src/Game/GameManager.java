package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Main.Images;

public class GameManager {
	
	private Board b;
	
	private int level = 1;
	
	public boolean switching = false,phase2 = false;
	
	private double increment = 0.0;
	
	public GameManager(){
		
		Board.setLocations();
		b = new Board(new PieceManager(String.valueOf(level)), 120, 140);
	}
	
	public void update(){
		
		if(!switching){
			b.update();
		}
		
		if(b.getPiece().hasWon() && !switching){
			switching = true;
			phase2 = false;
			level++;
			increment = 0.0;
			Board.clearPlace();
			System.out.println("won");
		}
	}
	
	public void draw(Graphics2D g){
		if(!switching){
			b.draw(g);
		}else if(!phase2){
			b.drawEnd(g, increment);
			increment += 0.02;
			if(increment >= 5){
				b.setPieces(new PieceManager(String.valueOf(level)));
				phase2 = true;
				increment = 1;
			}
		}else{
			g.drawImage(Images.hex2, Board.midX + Board.getX() - (int)(40.0 * increment), Board.midY + Board.getY() - (int)(35.0 * increment), (int)(80.0*increment), (int)(70.0*increment), null);
			increment += increment * 0.1;
			if(increment > 17){
				switching = false;
				increment = 1.0;
			}
		}
		if(!switching && phase2){
			g.setColor(Color.black);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)increment));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			increment -= 0.003;
			if(increment <0){
				phase2 = false;
			}
		}
	}
	
}
