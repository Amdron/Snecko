package everything;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Fruit {

	public Rect rect;
	public Snake snake;
	public int width, height;
	public boolean isSpawned = false;
	
	public Fruit(Snake snake, int width, int height) {
		this.snake = snake;
		this.width = width;
		this.height = height;
		this.rect = new Rect(0, 0, width, height);
	}
	
	
	public void spawn () {
		do {
			Random random = new Random();
			int x = (random.nextInt(17)+1)*Constants.cellSize;
			int y = ((random.nextInt(17)+1)*Constants.cellSize)+30;
			for(int i = 0; i < Constants.obstaclesAmount; i++) {	
				if (x >= GameScene.obstacles[i].x && x <= GameScene.obstacles[i].x + GameScene.obstacles[i].width-32 &&
					y >= GameScene.obstacles[i].y && y <= GameScene.obstacles[i].y + GameScene.obstacles[i].height-32) {
					rect.x =32;
					rect.y =62;
					break;
				}
				rect.x = x;
				rect.y = y;
			}
		}	
		 while (snake.rectIntersection(this.rect));
		this.isSpawned = true;
	}
	
	public void update(double dt) {
		if(snake.rectIntersection(this.rect)) { 
			snake.grow();
			this.rect.x = -100;
			this.rect.y = -100;
			this.isSpawned = false;
			}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fill(new Rectangle2D.Double(rect.x, rect.y, width, height));
	}
}
