package tiles_int;

import main.GamePanel;

public class TI_Seeds extends TilesInter {
	GamePanel gamePanel;
	int begunTime;
	int grow_chance;
	
	public TI_Seeds(GamePanel gamePanel, int col, int row) {
		super(gamePanel, col, row);
		
		collision = true;
		this.worldX = col*gamePanel.tileSize;
		this.worldY = row*gamePanel.tileSize;
		up1 = setup("/tiles/seed1", gamePanel.tileSize, gamePanel.tileSize);
	}
	
}
