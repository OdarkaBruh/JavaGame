package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Utility;

public class TileManager {
	
	GamePanel gamePanel;
	public Tile[] tile;
	public int mapTileNum[][][];
	
	public TileManager(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		
		tile = new Tile[10];
		mapTileNum = new int[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];
		
		getTileImage();
		drawMap("/maps/map_farm.txt", 0);
		drawMap("/maps/house.txt", 1);
	}
	
	public void getTileImage() {
		//CREATING TILES
		setup(0, "snow1", false);
		setup(1, "dirt1", false);
		setup(2, "dig1", false);
		setup(3, "step1", false);
		setup(4, "wood1", false);
		setup(5, "brick1", true);
		setup(6, "window1", true);
		setup(7, "window2", true);
		
		setup(8, "void", true);
		setup(9, "debug1", true);
	}
	
	public void setup(int i, String path, boolean collision) {
		Utility util = new Utility();
		try {
			tile[i] = new Tile();
			tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+path+".png"));
			tile[i].image = util.scaleImage(tile[i].image, gamePanel.tileSize, gamePanel.tileSize);
			tile[i].collision = collision;
			
		} catch (IOException e) {
		
		}
	}
	public void drawMap(String path, int worldI) {
		try {
			InputStream inStream = getClass().getResourceAsStream(path);
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));
			
			int col = 0;
			int row = 0;
			
			while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
				String line = bufRead.readLine();
				while (col < gamePanel.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum [worldI][col][row] = num;
					col++;
				}
				if (col == gamePanel.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			bufRead.close();
			
		} catch (Exception e) {
			
		}
	}
	
	public void draw (Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		
		while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
			int tileNum = mapTileNum[gamePanel.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gamePanel.tileSize;
			int worldY = worldRow * gamePanel.tileSize;
			
			int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
			int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
			
			if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX && 
					worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX && 
					worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY && 
					worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;
			
			if (worldCol == gamePanel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		
		}
	}
}



































