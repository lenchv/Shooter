import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	
	private static final int TEXTURES_COUNT = 16;
	private Image[] textures;
	private Image playerImage; 
	private int imgCounter;
	
	private int posX, posY;
	private int dirX, dirY;
	private int moveSpeed;
	private double angle;
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	
	public Player() {
		
		posX = posY = 200;
		angle = 0;
		moveSpeed = 7;
		
		textures = new Image[TEXTURES_COUNT];
		
		for(int i = 0; i < TEXTURES_COUNT; i++) {
			textures[i] = new ImageIcon("res/Player/player00" + i + ".png").getImage();
		}
		
		imgCounter = 0;
		playerImage = textures[imgCounter];
	}
	
	public void move() {
		
		int pX = posX + 64;
		int pY = posY + 64;
		
		if(imgCounter < TEXTURES_COUNT - 1) {
			imgCounter++;
		}
		else {
			imgCounter = 0;
		}
		playerImage = textures[imgCounter];
		
		if(moveUp) {
			posY -= moveSpeed;
		}
		else {
			if(moveDown) {
				posY += moveSpeed;
			}
		}
		
		if(moveLeft) {
			posX -= moveSpeed;
		}
		else {
			if(moveRight) {
				posX += moveSpeed;
			}
		}
		
		if(dirX > pX && dirY <= pY) { //  0 - 89
			angle = Math.acos(distance(dirX, pY, pX, pY) / distance(dirX, dirY, pX, pY));
		}
		
		if(dirX <= pX && dirY < pY) { // 90 - 179
			angle = Math.PI/2 + Math.acos(distance(pX, pY, pX, dirY) / distance(dirX, dirY, pX, pY));
		}
		
		if(dirX < pX && dirY >= pY) { //180 - 269
			angle = Math.PI + Math.acos(distance(dirX, pY, pX, pY) / distance(dirX, dirY, pX, pY));
		}
			
		if(dirX >= pX && dirY > pY) { //270 - 359
			angle = Math.PI + Math.PI/2 + Math.acos(distance(pX, pY, pX, dirY) / distance(dirX, dirY, pX, pY));
		}
	}
	
	private double distance(double x1, double y1, double x2, double y2) {
		return(Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
	}
	
	public void setMoveUp(boolean bool) {
		moveUp = bool;
	}
	
	public void setMoveDown(boolean bool) {
		moveDown = bool;
	}
	
	public void setMoveLeft(boolean bool) {
		moveLeft = bool;
	}
	
	public void setMoveRight(boolean bool) {
		moveRight = bool;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Image getPlayerImage() {
		return playerImage;
	}
	
	public void setDirX(int newX) {
		dirX = newX;
	}
	
	public int getDirX() {
		return dirX;
	}
	
	public void setDirY(int newY) {
		dirY = newY;
	}
	
	public int getDirY() {
		return dirY;
	}
}
