import java.awt.Image;

import javax.swing.ImageIcon;


public class Stone {
	
	private static final int TEXTURES_COUNT = 16;
	private Image[] textures;
	private Image stoneImage;
	private int imgCounter;
	private int paintCounter;
	
	private int posX, posY;
	
	public Stone(int x, int y) {
		
		setPosX(x);
		setPosY(y);
		
		textures = new Image[TEXTURES_COUNT];
		
		for(int i = 0; i < TEXTURES_COUNT; i++) {
			textures[i] = new ImageIcon("res/Stone/rock_type_D00" + i + ".png").getImage();
		}
		
		paintCounter = 0; 
		imgCounter = 0;
		setStoneImage(textures[imgCounter]);
	}
	
	public void move() {
		
		paintCounter++;
		
		if(paintCounter == 2) {
			paintCounter = 0;
			
			if(imgCounter < TEXTURES_COUNT - 1) {
				imgCounter++;
			}
			else {
				imgCounter = 0;
			}
			stoneImage = textures[imgCounter];
		}
	}

	public Image getStoneImage() {
		return stoneImage;
	}

	public void setStoneImage(Image stoneImage) {
		this.stoneImage = stoneImage;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
