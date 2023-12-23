package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN
	final int originalTileSize = 16;
	public final int scale = 4;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLD SETTINGS
	public final int maxWorldCol = 20;
	public final int maxWorldRow = 20;
	
	public final int maxMap = 3;
	public int currentMap = 1;
	
	public final int worldW = maxWorldCol * tileSize;
	public final int worldH = maxWorldRow * tileSize;
	
	//GAME STATE
	public int gameState;
	public final int stateTitle = 1;
	public final int statePlay = 2;
	public final int statePause = 0;
	public final int stateInv = 22;
	
	int FPS = 60;
	
	public TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public Collision collisionCheck = new Collision(this);
	public Assets assets = new Assets(this);
	public UI ui = new UI(this);
	public Entity entity = new Entity(this);
	public Player player = new Player(this, keyH);
	public Entity obj[][] = new Entity[maxMap][10];
	public Entity tilesInter[][] = new Entity[maxMap][50];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void setupGame() {
		assets.setObject();
		gameState = stateTitle;
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			
			if (delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
		
	}
	
	public void update() {
		if (gameState == statePlay) {
			player.update();
			
			for (int i = 0; i < tilesInter.length; i++) {
				if (tilesInter[currentMap][i] != null) {
					tilesInter[currentMap][i].update();
				}
			}
		}
		else if (gameState == statePause) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG
//		long drawStart = 0;
//		drawStart = System.nanoTime();
		
		//TITLE SCREEN
		if (gameState == stateTitle) {
			ui.draw(g2);
		}
		else {
			//TILE
			tileM.draw(g2);
			
			for (int i = 0; i < tilesInter[currentMap].length; i++) {
				if (tilesInter[currentMap][i] != null) {
					tilesInter[currentMap][i].draw(g2);
				}
			}
			
			//ADD ENTITIES
			entityList.add(player);
			for (int i = 0; i < obj[currentMap].length; i++) {
				if (obj[currentMap][i]!= null) {
					entityList.add(obj[currentMap][i]);
				}
			}
			
			//SORT
			Collections.sort(entityList, new Comparator<Entity>() {
				
				public int compare(Entity o1, Entity o2) {
				    return Integer.compare(o1.worldY, o2.worldY);
				}
			});
			
			//DRAW ENTITY
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			//REMOVE ENTITY
			entityList.clear();
			
			//UI
			ui.draw(g2);
		}
		
		//DEBUG
//		long drawEnd = System.nanoTime();
//		System.out.println(drawEnd);
		
		g2.dispose();
	}
}
