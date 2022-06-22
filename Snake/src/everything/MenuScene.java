package everything;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class MenuScene extends Scene {

	public KL keyListener;
	public ML mouseListener;
	public BufferedImage title, play, playPressed, exit, exitPressed;
	public Rect playRect, exitRect, titleRect;
	
	public BufferedImage playCurrentImage, exitCurrentImage;
	
	public MenuScene( ML mouseListener) {
		this.mouseListener = mouseListener;
		try {
			BufferedImage spritesheet = ImageIO.read(new File("assets/test.png"));
			title = spritesheet.getSubimage(0, 120, 205, 80);
			play = spritesheet.getSubimage(0, 0, 120, 50);
			playPressed = spritesheet.getSubimage(120, 0, 120, 50);
			exit = spritesheet.getSubimage(0, 60, 90, 50);
			exitPressed = spritesheet.getSubimage(100, 60, 90, 50);
		} catch(Exception e) {e.printStackTrace();}
		
		playCurrentImage = play;
		exitCurrentImage = exit;
		
		titleRect = new Rect (200, 40, 200, 80);
		playRect = new Rect (240, 160, 120, 50);
		exitRect = new Rect (240, 240, 120, 50);
	}
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		if (mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x+playRect.width 
				&& mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y +playRect.height ) { 
			playCurrentImage = playPressed;
			if(mouseListener.isPressed == true) {
				Window.getWindow().changeState(1);
			}
		}
		else { playCurrentImage = play;} 
		
		if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x+exitRect.width 
				&& mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y +exitRect.height ) { 
			exitCurrentImage = exitPressed;
			if(mouseListener.isPressed == true) {
				Window.getWindow().close();
			}
			}
		else { exitCurrentImage = exit;} 
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.screenWidth, Constants.screenHeight);
		g.drawImage(title, (int)titleRect.x, (int)titleRect.y, (int)titleRect.width, (int)titleRect.height, null);
		g.drawImage(playCurrentImage, (int)playRect.x, (int)playRect.y, (int)playRect.width, (int)playRect.height, null);
		g.drawImage(exitCurrentImage, (int)exitRect.x, (int)exitRect.y, (int)exitRect.width, (int)exitRect.height, null);
		
	}

}
