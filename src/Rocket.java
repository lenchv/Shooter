import java.awt.Image;

import javax.swing.ImageIcon;

public class Rocket {
	
	private static final int TEXTURES_COUNT = 16;
	private Image[] textures;
	private int imgCounter; 
	private Image rocketImage;
	
	private int startPosX, startPosY;
	private int curPosX, curPosY;
	private int speed = 10;
	private double angle;
	private boolean needsDelete;
	private boolean inTarget;
	private int rightBorder, downBorder;
	
	public Rocket(int startX, int startY, double newAngle, int width, int height) {
		
		imgCounter = 0;
		startPosX = curPosX = startX;
		startPosY = curPosY = startY;
		angle = newAngle;
		needsDelete = false;
		setInTarget(false);
		rightBorder = width;
		downBorder = height;
		int textureNum = (int)Math.round(Math.toDegrees(newAngle) / 22.5);
		if(textureNum == 16) {
			textureNum = 0;
		}
		rocketImage = new ImageIcon("res/Rocket/" + textureNum + ".png").getImage();
	}
	
	public void move() {
		
		if (!inTarget) {
			double a = Math.round(Math.cos(angle) * speed);
			curPosX += (int)a;
			double b = Math.round(Math.sin(angle) * speed);
			curPosY -= (int)b;
		
			if(curPosX <= 0 || curPosX >= rightBorder || curPosY <= 0 || curPosY >= downBorder) {
				needsDelete = true;
			}
		}
		else {
			if(imgCounter < TEXTURES_COUNT - 1) {
				imgCounter++;
				rocketImage = textures[imgCounter];
			}
			else {
				needsDelete = true;
			}
		}
	}

	public int getStartPosX() {
		return startPosX;
	}

	public void setStartPosX(int startPosX) {
		this.startPosX = startPosX;
	}

	public int getStartPosY() {
		return startPosY;
	}

	public void setStartPosY(int startPosY) {
		this.startPosY = startPosY;
	}

	public int getCurPosX() {
		return curPosX;
	}

	public void setCurPosX(int curPosX) {
		this.curPosX = curPosX;
	}

	public int getCurPosY() {
		return curPosY;
	}

	public void setCurPosY(int curPosY) {
		this.curPosY = curPosY;
	}

	public boolean isNeedsDelete() {
		return needsDelete;
	}

	public void setNeedsDelete(boolean needsDelete) {
		this.needsDelete = needsDelete;
	}

	public Image getRocketImage() {
		return rocketImage;
	}

	public void setRocketImage(Image rocketImage) {
		this.rocketImage = rocketImage;
	}

	public boolean isInTarget() {
		return inTarget;
	}

	public void setInTarget(boolean inTarget) {
		this.inTarget = inTarget;
		
		textures = new Image[TEXTURES_COUNT];
		
		for(int i = 0; i < TEXTURES_COUNT; i++) {
			textures[i] = new ImageIcon("res/Rocket/expl" + i + ".png").getImage();
		}
		
		rocketImage = textures[imgCounter];
	}
}
