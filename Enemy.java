import java.awt.Color;
import java.awt.Graphics;

public class Enemy implements Drawable, CanAnimate {
	private int x;
	private int y;

	private static final int MINIMUM_SPEED = 1;
	private final int velocityX;
	private int velocityY;
	
	private final int width;
	private final int height;

	
	private final Color green;
	
	public Enemy(int x, int y, int level){
		
		this.x = x;
		this.y = y;
		
		this.width = 70;
		this.height = 50;

		//minimum speed of 1
		this.velocityX = -1 * (int) (Math.random() * (level) + MINIMUM_SPEED);
		this.velocityY = -1 * (int) (Math.random() * (level) + MINIMUM_SPEED);
		
		this.green = new Color(0,255,00);
		
			
	}
	
	@Override
	public void drawMe(Graphics g) {
		g.setColor(green);
		g.fillOval(x, y, width, height);	
		

	}
	
	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public int width() {
		return this.width;
	}

    @Override
	public void animate() {
		
		this.x += this.velocityX;
		this.y += this.velocityY;
		// if (this.x <= 0) {
		// 	this.x = 800;
		// }
		if (this.y <= 0) {
			this.velocityY = -this.velocityY;
		}
		if (this.y >= Runner.SCREENHEIGHT - this.height) {
			this.velocityY = -this.velocityY;
		}
		

	}

	public int height() {
		return this.height;
	}

	public void setX(int newX) {
		this.x = newX;
	}
	
	public boolean collidedWithShip(Ship ship) {
		return this.x + this.width >= ship.x() && this.x <= ship.x() + ship.width() && this.y + this.height >= ship.y()
                        && this.y <= ship.y() + ship.height();
	}
	
	public boolean collidedWithProjectile(Projectile p) {
		return this.x + this.width >= p.x() && this.x <= p.x() + p.width() && this.y + this.height >= p.y()
                        && this.y <= p.y() + p.height();
	}
	
	public boolean passedPlayer() {
		return this.x <= -this.width;
	}


}