package everything;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Snake {

	public Rect[] body = new Rect[324];
	public double bodyWidth, bodyHeight;
	public int size; 
	public int head = 0;
	public int tail = 0;
	public int points = 0;
	public Direction direction = Direction.RIGHT;
	public double updateTime = 0.1;
	public double timeLeftToUpdate = updateTime;
	
	Snake (int size, double startX, double startY, double bodyWidth, double bodyHeight){
	this.size = size;
	this.bodyWidth = bodyWidth;
	this.bodyHeight = bodyHeight;
	for (int i = 0; i < size ; i++) {
		Rect bodyPiece = new Rect (startX + i*bodyWidth, startY, bodyWidth, bodyHeight);
		body[i] = bodyPiece;
		head++;
		}
	head--;
	}
	
	public void grow(){
		double newX = 0.0;
		double newY = 0.0;
		if(direction == Direction.RIGHT) {
			newX = body[tail].x - bodyWidth;
			newY = body[tail].y;
		}
		else if(direction == Direction.LEFT) {
			newX = body[tail].x + bodyWidth;
			newY = body[tail].y;
		}
		else if(direction == Direction.UP) {
			newX = body[tail].x;
			newY = body[tail].y + bodyHeight;
		}
		else if(direction == Direction.DOWN) {
			newX = body[tail].x;
			newY = body[tail].y - bodyHeight;
		}
		Rect newBodyPiece = new Rect(newX, newY, bodyWidth, bodyHeight);
		
		tail = (tail-1) % body.length;
		body[tail] = newBodyPiece;
		points++;
		
	}	
	public boolean selfIntersection() {
		Rect bodyR = body[head];
		return rectIntersection(bodyR);
	}
	
	public boolean rectIntersection(Rect rect) {
		for (int i = tail; i != head; i = (i + 1) % body.length) {
			if(intersection(rect, body[i])) return true;
		}
		return false;
	}
	
	public boolean intersection (Rect r1, Rect r2) {
		return (r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width && r1.y >= r2.y && r1.y +r1.height <= r2.y + r2.height);
	}
	
	public void changeDirection(Direction newDirection) {
		if (direction == Direction.RIGHT && newDirection != Direction.LEFT) {
		direction = newDirection;
		}
		else if (direction == Direction.LEFT && newDirection != Direction.RIGHT) {
			direction = newDirection;
			}
		else if (direction == Direction.UP && newDirection != Direction.DOWN) {
			direction = newDirection;
			}
		else if (direction == Direction.DOWN && newDirection != Direction.UP) {
			direction = newDirection;
			}
	}
	
	public void update(double dt) {
		if (timeLeftToUpdate > 0) {
			timeLeftToUpdate -= dt;
			return;
		}
		timeLeftToUpdate = updateTime;
		if(selfIntersection() ) { Window.getWindow().changeState(0); }
		if(body[head].x < 32 || body[head].x >=608 || body[head].y<62 ||body[head].y>=638) { Window.getWindow().changeState(0); }
		for(int i = 0; i < Constants.obstaclesAmount; i++) { 
			if(body[head].x >= GameScene.obstacles[i].x && body[head].x <= GameScene.obstacles[i].x+GameScene.obstacles[i].width-1 &&
					body[head].y >= GameScene.obstacles[i].y && body[head].y <= GameScene.obstacles[i].y+GameScene.obstacles[i].height-1) { Window.getWindow().changeState(0); }
		}
		
		double newX = 0;
		double newY = 0;
		if(direction == Direction.RIGHT) {
			newX = body[head].x + bodyWidth;
			newY = body[head].y;
		}
		else if(direction == Direction.LEFT) {
			newX = body[head].x - bodyWidth;
			newY = body[head].y;
		}
		else if(direction == Direction.UP) {
			newX = body[head].x;
			newY = body[head].y - bodyHeight;
		}
		else if(direction == Direction.DOWN) {
			newX = body[head].x;
			newY = body[head].y + bodyHeight;
		}
		body[(head +1) % body.length] = body[tail];
		body[tail] = null;
		head = (head + 1) % body.length;
		tail = (tail + 1) % body.length;
		body[head].x = newX;
		body[head].y = newY;
	}
	
	public void draw(Graphics2D g2) {
		for (int i = tail; i != head; i = (i + 1) % body.length) {
			Rect piece = body[i];
			g2.setColor(Color.GREEN);
			g2.fill(new Rectangle2D.Double(piece.x, piece.y, 32, 32));
			
		}
	}
	
}
