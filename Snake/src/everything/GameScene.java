package everything;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JLabel;

public class GameScene extends Scene {

	public Rect background, foreground;
	public Snake snake;
	public KL keyListener;
	public Fruit fruit;
	public JLabel label = new JLabel();
	public static Rect[] obstacles = new Rect[Constants.obstaclesAmount];
	
	public GameScene(KL keyListener) {
		background = new Rect(0, 0, Constants.screenWidth, Constants.screenHeight);
		foreground = new Rect(Constants.cellSize, Constants.cellSize+30, 18*Constants.cellSize, 18*Constants.cellSize);
		snake = new Snake(5, Constants.cellSize, Constants.cellSize+30, Constants.cellSize, Constants.cellSize);
		this.keyListener = keyListener;
		obstacles=obstacleArray();
		fruit = new Fruit(snake, Constants.cellSize, Constants.cellSize);
		fruit.spawn();
	}
	
	public Rect createObstacle() {
		Rect obstacle = new Rect(0, 0, 0, 0);
		Random random = new Random();
		obstacle.x = ((double)random.nextInt(17) + 1)*Constants.cellSize;
		obstacle.y = (((double)random.nextInt(17) + 1)*Constants.cellSize)+30;
		obstacle.width = ((double)random.nextInt(4) + 1)*Constants.cellSize;
		obstacle.height = ((double)random.nextInt(4) + 1)*Constants.cellSize;
		return obstacle;
	}
	
	public Rect[] obstacleArray() {
		int i = 0;
		while (i<Constants.obstaclesAmount) {
			Rect obstacle = createObstacle();
			if(obstacle.y <Constants.cellSize*2+30 && obstacle.x < snake.size*Constants.cellSize + Constants.cellSize*2) { }
			else {
				obstacles[i]=obstacle;
				i++;
				}
		}
		return obstacles;		
	}
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
		if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
			snake.changeDirection(Direction.UP);
		}
		else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
			snake.changeDirection(Direction.DOWN);
		}
		else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
			snake.changeDirection(Direction.LEFT);
		} 
		else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
			snake.changeDirection(Direction.RIGHT);
		}
		else if (keyListener.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			Window.getWindow().changeState(0);
			return;
		}
		if (!fruit.isSpawned) fruit.spawn();
		
		fruit.update(dt);
		snake.update(dt);
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));
		
		g2.setColor(Color.WHITE);
		g2.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));
		
		g2.setColor(Color.BLACK);
		for(int i = 0; i<Constants.obstaclesAmount; i++) {
			g2.fill(new Rectangle2D.Double(obstacles[i].x, obstacles[i].y, obstacles[i].width, obstacles[i].height));
		}
		g.setColor(Color.WHITE);
		int n = snake.points;
		String s = Integer.toString(n);
		g.drawString(s, 600, 40);
		snake.draw(g2);
		fruit.draw(g2);
	}

}
