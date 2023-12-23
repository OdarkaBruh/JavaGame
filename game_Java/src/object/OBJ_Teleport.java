package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Teleport extends Entity{
	
	public OBJ_Teleport(GamePanel gamePanel) {
		super(gamePanel);
		up1  = setup("/tiles/debug1", gamePanel.tileSize, gamePanel.tileSize);
		name = "Teleport";
	}
}
