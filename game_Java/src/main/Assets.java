package main;

import object.OBJ_Teleport;
import tiles_int.TI_Seeds;

public class Assets {
	GamePanel gamePanel;
	int tiles_inter = 0;
	
	public Assets(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void setObject() {
		gamePanel.obj[0][0] = new OBJ_Teleport(gamePanel);
		gamePanel.obj[0][0].worldX = 4 * gamePanel.tileSize;
		gamePanel.obj[0][0].worldY = 11 * gamePanel.tileSize;
		
		gamePanel.obj[1][0] = new OBJ_Teleport(gamePanel);
		gamePanel.obj[1][0].worldX = 2 * gamePanel.tileSize;
		gamePanel.obj[1][0].worldY = 8 * gamePanel.tileSize;
		
		//DOOR2, DOOR3........
	}
	
	public void setTilesInter(int col, int row) {
		
	}
	
	public void createTilesSeed(int col, int row) {
		if (tiles_inter != 50) {
			gamePanel.tilesInter[gamePanel.currentMap][tiles_inter] = new TI_Seeds(gamePanel, col, row);
			tiles_inter++;
		}
		
		//DOOR2, DOOR3........
	}
}
