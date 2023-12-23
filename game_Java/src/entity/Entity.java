package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Utility;

public class Entity {
	GamePanel gamePanel;
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public String direction = "up";
	
	public int spriteCount = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaX;
	public int solidAreaY;
	public boolean collision = false;
	
	public BufferedImage image;
	public String name;
	
	public Entity(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		 
		int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
		int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
		
		if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX && 
				worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX && 
				worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY && 
				worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
			
			switch(direction) {
			case "up":
				switch (spriteNum) {
					case 1:
						image = up1;
						break;
					case 2:
						image = up1;
						break;
				}
				break;
				
			case "down":
				image = down1;
				break;
				
			case "left":
				image = left1;
				break;
				
			case "right":
				image = right1;
				break;
		}
			
			g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
		}
	}
	
	public BufferedImage setup(String path, int width, int height) {
		Utility util = new Utility();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path+".png"));
			image = util.scaleImage(image, width, height);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		return image;
	}
	
	public void update() {
		
	}
}
