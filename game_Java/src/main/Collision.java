package main;

import entity.Entity;
import entity.Player;
import object.OBJ_Teleport;

public class Collision {
	GamePanel gamePanel;
	
	public Collision(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void checkTile(Entity entity) {
		int entityLeft = entity.worldX + entity.solidArea.x;
		int entityRight = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTop = entity.worldY + entity.solidArea.y;
		int entityDown = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeft/gamePanel.tileSize;
		int entityRightCol = entityRight/gamePanel.tileSize;
		int entityTopRow = entityTop/gamePanel.tileSize;
		int entityDownRow = entityDown/gamePanel.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
			case("up"):
				entityTopRow = (entityTop - entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
				tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
				
				if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
					entity.collision = true;
				}
				break;
			case("down"):
				entityDownRow = (entityDown + entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityDownRow];
				tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityDownRow];
			
				if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
					entity.collision = true;
				}
				break;
			case("left"):
				entityLeftCol = (entityLeft - entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
				tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityDownRow];
			
				if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
					entity.collision = true;
				}
				break;
			case("right"):
				entityRightCol = (entityRight + entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
				tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityDownRow];
			
				if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
					entity.collision = true;
				}
				break;
		}
	}
	
	public int checkObj(Entity entity, boolean player) {
		int index = -1;
		for (int i = 0; i < gamePanel.obj[gamePanel.currentMap].length; i ++) {
			if (gamePanel.obj[gamePanel.currentMap][i] != null) {
				//SOLID AREA - ENTITY
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				//SOLID AREA - OBJ
				gamePanel.obj[gamePanel.currentMap][i].solidArea.x = gamePanel.obj[gamePanel.currentMap][i].worldX + gamePanel.obj[gamePanel.currentMap][i].solidArea.x;
				gamePanel.obj[gamePanel.currentMap][i].solidArea.y = gamePanel.obj[gamePanel.currentMap][i].worldY + gamePanel.obj[gamePanel.currentMap][i].solidArea.y;
				
				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)) {
						if(gamePanel.obj[gamePanel.currentMap][i].collision == true) {
							//entity.collision = true;
						}
						if (player == true) {
							index = i;
						}
						//if player
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)) {
						if(gamePanel.obj[gamePanel.currentMap][i].collision == true) {
							entity.collision = true;
						}
						if (player == true) {
							index = i;
						}
						//if player
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)) {
						if(gamePanel.obj[gamePanel.currentMap][i].collision == true) {
							entity.collision = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)) {
						if(gamePanel.obj[gamePanel.currentMap][i].collision == true) {
							entity.collision = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaX;
				entity.solidArea.y = entity.solidAreaY;
				gamePanel.obj[gamePanel.currentMap][i].solidArea.x = gamePanel.obj[gamePanel.currentMap][i].solidAreaX;
				gamePanel.obj[gamePanel.currentMap][i].solidArea.y = gamePanel.obj[gamePanel.currentMap][i].solidAreaY;
			}
		}
		return index;
	}
	
	public int checkDig(Player player) {
		int index = -1;
		player.solidArea.x = player.worldX + player.solidArea.x;
		player.solidArea.y = player.worldY + player.solidArea.y;
		int x = player.solidArea.x/gamePanel.tileSize;
		int y = player.solidArea.y/gamePanel.tileSize;
		switch(player.direction) {
		case("up"): y--; break;
		case("down"): y++; break;
		case("left"): x--; break;
		case("right"): x++; break;
		}
		if (gamePanel.tileM.mapTileNum[gamePanel.currentMap][x][y] == 1) {
			gamePanel.assets.createTilesSeed(x, y);
		}
		player.solidArea.x = player.solidAreaX;
		player.solidArea.y = player.solidAreaY;
		System.out.println(gamePanel.tilesInter.length);
		return index;
	}
	
	public void checkTeleport(Entity entity) {
		System.out.println("Aa");
		int check = checkObj(entity, true);
		if (check != -1) {
			System.out.println("Ba");
			if (gamePanel.obj[gamePanel.currentMap][check].name == "Teleport") {
				System.out.println("A");
				if (gamePanel.currentMap == 1) {
					gamePanel.currentMap = 0;
					entity.worldX = 10 * gamePanel.tileSize;
					entity.worldY = 11 * gamePanel.tileSize;
				}
				else if (gamePanel.currentMap == 0) {
					gamePanel.currentMap = 1;
					entity.worldX = 4 * gamePanel.tileSize;
					entity.worldY = 4 * gamePanel.tileSize;
				}
			}
		}
	}
}































