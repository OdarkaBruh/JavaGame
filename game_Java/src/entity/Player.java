package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public BufferedImage up4, up5, up6, down4, down5, down6, left4, left5, left6, right4, right5, right6;
	
	int int_run = 0;
	boolean run;
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public Rectangle plantArea = new Rectangle(0, 0, 48, 48);
	
	private int speedDef;
	public Player(GamePanel gamePanel, KeyHandler keyH) {
		super(gamePanel);
		
		this.keyH = keyH;
		
		screenX = (gamePanel.screenWidth - gamePanel.tileSize) / 2;
		screenY = (gamePanel.screenHeight - gamePanel.tileSize) / 2;
		
		solidArea.x = 3 * gamePanel.scale;
		solidArea.y = 29 * gamePanel.scale;
		solidArea.width = 10 * gamePanel.scale;
		solidArea.height = 3 * gamePanel.scale;
		
		solidAreaX = solidArea.x;
		solidAreaY = solidArea.y;
		
		setDefaultValues(); 
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = 4 * gamePanel.tileSize;
		worldY = 4 * gamePanel.tileSize;
		
		speed = 3;
		speedDef = speed;
		
		direction = "down";
	}
	
	public void getPlayerImage() {
		//CREATE - PLAYER IMAGE
		up1 = setup("/player/player_back1", gamePanel.tileSize, gamePanel.tileSize);
		up2 = setup("/player/player_back2", gamePanel.tileSize, gamePanel.tileSize);
		up3 = setup("/player/player_back3", gamePanel.tileSize, gamePanel.tileSize);

		down1 =  setup("/player/player_front1", gamePanel.tileSize, gamePanel.tileSize);
		down2 =  setup("/player/player_front2", gamePanel.tileSize, gamePanel.tileSize);
		down3 =  setup("/player/player_front3", gamePanel.tileSize, gamePanel.tileSize);

		left1 = setup("/player/player_left1", gamePanel.tileSize, gamePanel.tileSize);
		left2 = setup("/player/player_left2", gamePanel.tileSize, gamePanel.tileSize);
		left3 = setup("/player/player_left3", gamePanel.tileSize, gamePanel.tileSize);

		right1 = setup("/player/player_right1", gamePanel.tileSize, gamePanel.tileSize);
		right2 = setup("/player/player_right2", gamePanel.tileSize, gamePanel.tileSize);
		right3 = setup("/player/player_right3", gamePanel.tileSize, gamePanel.tileSize);

		
	}
	
	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if (int_run == 10) {
				run = true;
			}
			else {
				int_run++;
			}
			if (keyH.upPressed == true) { direction = "up";
			}
			if (keyH.downPressed == true) { direction = "down";
			}
			if (keyH.leftPressed == true) { direction = "left";
			}
			if (keyH.rightPressed == true) { direction = "right";
			}
			
			
			//CHECK TILE COLLISSION
			collision = false;
			gamePanel.collisionCheck.checkTile(this);
			//CHECK TILE COLLISSION
			int objIndex = gamePanel.collisionCheck.checkObj(this, true);
			pickUpObjects(objIndex);
			//COLLISION
			if (collision == false) {
				switch(direction) {
				case("up"): worldY -= speed; break;
				case("down"): worldY += speed; break;
				case("left"): worldX -= speed; break;
				case("right"): worldX += speed; break;
				}
			}
			spriteCount++;
			if (spriteCount > 10) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 3;
				}
				else if (spriteNum == 3) {
					spriteNum = 1;
				}
				
				spriteCount = 0;
			}	
		}
		else {
			if (int_run != 0) {
				int_run--;
			}
			else {
				run = false;
			}
		}
		
		if (keyH.shiftPressed == true) { speed = speedDef*2;
		}
		else { speed = speedDef; }
		
		if (keyH.interactionPressed) {
			gamePanel.collisionCheck.checkDig(this);
			gamePanel.collisionCheck.checkTeleport(this);
		}
	}
	
	public void pickUpObjects(int i) {
		if (i != -1) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
		
		BufferedImage image = null;
		if (run) {
			switch(direction) {
			case "up":
				switch (spriteNum) {
				case 1: image = up1; break;
				case 2: image = up2; break;
				case 3: image = up3; break;
				}
				break;
				
			case "down":
				switch (spriteNum) {
				case 1: image = down1;; break;
				case 2: image = down2; break;
				case 3: image = down3; break;
				}
				break;
				
			case "left":
				switch (spriteNum) {
				case 1: image = left1;; break;
				case 2: image = left2; break;
				case 3: image = left3; break;
				}
				break;
				
			case "right":
				switch (spriteNum) {
				case 1: image = right1;; break;
				case 2: image = right2; break;
				case 3: image = right3; break;
				}
				break;
			}
		}
		else {
			switch(direction) {
			case "up":
				image = up1;
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
		}
		
		g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize*2, null);
	}
}
