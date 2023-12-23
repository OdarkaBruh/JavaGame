package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener{
	GamePanel gamePanel;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean shiftPressed, interactionPressed;
	
	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//TITLE STATE
		if (gamePanel.gameState == gamePanel.stateTitle) {
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gamePanel.ui.chooseNum--;
				if (gamePanel.ui.chooseNum < 0) {
					gamePanel.ui.chooseNum = gamePanel.ui.chooseNum_MAX;
				}
			}
			else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gamePanel.ui.chooseNum++;
				if (gamePanel.ui.chooseNum > gamePanel.ui.chooseNum_MAX) {
					gamePanel.ui.chooseNum = 0;
				}
			}
			else if (code == KeyEvent.VK_ENTER) {
				switch (gamePanel.ui.chooseNum) {
				case 0:
					gamePanel.gameState = gamePanel.statePlay;
					break;
				case 1: 
					gamePanel.gameState = gamePanel.statePlay;
					break;
				case 2:
					System.exit(0);
					break;
				}
			}
		}
		//PLAY STATE
		else if (gamePanel.gameState == gamePanel.statePlay) {
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = true;
					}
			if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_SHIFT) {
				shiftPressed = true;
			}
			
			if (code == KeyEvent.VK_E) {
				interactionPressed = true;
			}
			
			if (code == KeyEvent.VK_P) {
				if (gamePanel.gameState == gamePanel.statePlay) {
					gamePanel.gameState = gamePanel.statePause;
				}
			}
			if (code == KeyEvent.VK_I) {
				if (gamePanel.gameState == gamePanel.statePlay) {
					gamePanel.gameState = gamePanel.stateInv;
				}
			}
		}
		
		else if (gamePanel.gameState == gamePanel.statePause) {
			if (code == KeyEvent.VK_P) {
				gamePanel.gameState = gamePanel.statePlay;
			}
		}
		
		else if (gamePanel.gameState == gamePanel.stateInv) {
			if (code == KeyEvent.VK_I) {
				gamePanel.gameState = gamePanel.statePlay;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
				}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_SHIFT) {
			shiftPressed = false;
		}
		
		if (code == KeyEvent.VK_E) {
			interactionPressed = false;
		}
	}

}
