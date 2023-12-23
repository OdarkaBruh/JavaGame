package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UI {
	GamePanel gamePanel;
	Font fontStandart;
	Font fontBold;
	Graphics2D g2;
	//BufferedImage image;
	public int chooseNum = 0;
	public final int chooseNum_MAX = 2;
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		fontStandart = new Font("Arial", Font.PLAIN, 40);
		fontStandart = new Font("Arial", Font.BOLD, 60);
		//OBJ_key ket = new OBJ_Key();
		//keyimage = key.image;
		
	}
	
	public void draw(Graphics2D g2) {
//		
//		//g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, 0)
//		g2.drawString("Hello! ", 50, 50);
		
		this.g2 = g2;
		g2.setFont(fontStandart);
		g2.setColor(Color.white);
		
		if (gamePanel.gameState == gamePanel.stateTitle) {
			drawTitleScreen();
		}
		else if (gamePanel.gameState == gamePanel.stateInv) {
			drawInventory();
		}
		
		else if (gamePanel.gameState == gamePanel.statePause) {
			drawPauseScreen();
		}
	}
	public void drawInventory() {
		int frameX = 1 * gamePanel.tileSize;
		int frameY = 1 * gamePanel.tileSize;
		int frameWidth = 14 * gamePanel.tileSize;
		int frameHeight = 3 * gamePanel.tileSize;
		createSubWindow(frameX, frameY, frameWidth, frameHeight);
	}
	
	public void createSubWindow(int x, int y, int wigth, int height) {
		Color c = new Color (0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, wigth, height, 35, 35);
		
		c = new Color (255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x+5, y+5, wigth-10, height-10, 25, 25);
	}
	
	public void drawTitleScreen() {
		//BACKGROUNG
		g2.setColor(new Color(70, 120, 80));
		g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		
		//TITLE
		g2.setFont(fontBold);
		String text = "Take me home...";
		
		int x = getXcenter(text);
		int y = gamePanel.screenHeight/3;
		
		//SHADOW
		g2.setColor(Color.BLUE);
		g2.drawString(text, x+5, y+5);
		
		//TITLE DRAW
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		
		//IMAGE
		// x = ...
		// y = ...
		// g2.drawImage(image, x, y, size1, size2, null);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		text = "NEW GAME";
		x = getXcenter(text);
		y += gamePanel.tileSize*3;
		g2.drawString(text, x, y);
		if (chooseNum == 0) {
			g2.drawString(">", x-gamePanel.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXcenter(text);
		y += gamePanel.tileSize;
		g2.drawString(text, x, y);
		if (chooseNum == 1) {
			g2.drawString(">", x-gamePanel.tileSize, y);
		}
		
		text = "Quit";
		x = getXcenter(text);
		y += gamePanel.tileSize;
		g2.drawString(text, x, y);
		if (chooseNum == 2) {
			g2.drawString(">", x-gamePanel.tileSize, y);
		}
	}

	public void drawPauseScreen() {
		String text = "PAUSED";
		
		int y = gamePanel.screenHeight/2;
		int x = getXcenter(text);
		
		g2.drawString(text, x, y);
	}

	public int getXcenter(String text) {
		int l = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return (gamePanel.screenWidth - l)/2;
	}
}
