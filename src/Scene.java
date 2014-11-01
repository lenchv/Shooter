import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Scene extends JPanel implements ActionListener {
	
	private int frameWidth, frameHeight;
	private Timer mainTimer = new Timer(20, this);
	private Player p = new Player();
	private Image backGround = new ImageIcon("res/Background3.png").getImage();
	private CopyOnWriteArrayList<Rocket> rockets = new CopyOnWriteArrayList<Rocket>();
	private CopyOnWriteArrayList<Stone> stones = new CopyOnWriteArrayList<Stone>();
	
	public Scene(int width, int height) {
		
		frameWidth = width;
		frameHeight = height;
		addKeyListener(new MyKeyAdapter());
		addMouseMotionListener(new MyMouseMotionAdapter());
		addMouseListener(new MyMouseAdapter());
		setFocusable(true);
		stones.add(new Stone(800, 360));
		mainTimer.start();
	}
	
	private class MyMouseMotionAdapter extends MouseMotionAdapter {
		
		public void mouseMoved(MouseEvent e) {
			p.setDirX(e.getX());
			p.setDirY(e.getY());
		}
		
		public void mouseDragged(MouseEvent e) {
			p.setDirX(e.getX());
			p.setDirY(e.getY());
		}
	}
	
	private class MyMouseAdapter extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			
			int key = e.getButton();
			if(key == MouseEvent.BUTTON1) {
				Rocket r = new Rocket(p.getPosX() + 64, p.getPosY() + 64, p.getAngle(), frameWidth, frameHeight);
				rockets.add(r);
			}
		}
	}
	
	private class MyKeyAdapter extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_W: 
				p.setMoveUp(true);
				break;
			case KeyEvent.VK_S: 
				p.setMoveDown(true);
				break;
			case KeyEvent.VK_A: 
				p.setMoveLeft(true);
				break;
			case KeyEvent.VK_D: 
				p.setMoveRight(true);
				break;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_W: 
				p.setMoveUp(false);
				break;
			case KeyEvent.VK_S: 
					p.setMoveDown(false);
				break;
			case KeyEvent.VK_A: 
				p.setMoveLeft(false);
				break;
			case KeyEvent.VK_D: 
				p.setMoveRight(false);
				break;
			}
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform old = g2d.getTransform();
		g2d.drawImage(backGround, 0, 0, null);
		g2d.translate(64 + p.getPosX(), 64 + p.getPosY());
		g2d.rotate(-p.getAngle());
		g2d.drawImage(p.getPlayerImage(), -64, -64, null);
		g2d.setTransform(old);
		g2d.setColor(Color.WHITE);
		if (!stones.isEmpty()) {
			for (Stone s : stones) {
				g2d.drawImage(s.getStoneImage(), s.getPosX() - 32, s.getPosY() - 32, null);
			}
		}
		if (!rockets.isEmpty()) {
			for (Rocket r : rockets) {
				if (r.isInTarget()) {
					g2d.drawImage(r.getRocketImage(), r.getCurPosX() - 48, r.getCurPosY() - 48, null);
				}
				else {
					g2d.drawImage(r.getRocketImage(), r.getCurPosX() - 16, r.getCurPosY() - 16, null);
				}
			}
		}
		g2d.drawString("CURSOR X: " + p.getDirX() + " Y: " + p.getDirY(), 1, 10);
		g2d.drawString("PLAYER X: " + p.getPosX() + " Y: " + p.getPosY(), 1, 20);
		g2d.drawString("ANGLE: " + Math.toDegrees(p.getAngle()), 1, 30);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p.move();
		if (!rockets.isEmpty()) {
			for (Rocket r : rockets) {
				if (r.isNeedsDelete()) {
					rockets.remove(r);
				}
				else {
					r.move();
					for (Stone s : stones) {
						if (r.getCurPosX() - 16 > s.getPosX() - 32 && r.getCurPosX() - 16 < s.getPosX() + 16) {
							if (r.getCurPosY() - 16 > s.getPosY() -32 && r.getCurPosY() - 16 < s.getPosY()) {
								r.setInTarget(true);
							}
						}
					}
				}
			}
		}
		if (!stones.isEmpty()) {
			for (Stone s : stones) {
				s.move();
			}
		}
		repaint();
	}
}
