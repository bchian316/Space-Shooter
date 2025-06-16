import java.awt.Color;
import java.awt.Graphics;

public class Enemy implements Drawable{
	private int x;
	private int y;

	private final int velocityX;
	private int velocityY;
	
	private final int width;
	private final int height;

	private boolean visible;
	
	private final Color green;
	private final int baseX;
	private final int level;
	
	public Enemy(int x, int y, int level){
		
		this.x = x;
		this.baseX = x;
		this.y = y;
		
		this.width = 70;
		this.height = 50;
		this.level = level;

		this.velocityX = -1 * (int) (Math.random() * (this.level) + 1);
		this.velocityY = -1 * (int) (Math.random() * (this.level) + 1);

		this.visible = true;
		
		this.green = new Color(0,255,00);
		
			
	}
	
	@Override
	public void drawMe(Graphics g) {
		if (this.visible == true) {
			g.setColor(green);
			g.fillOval(x, y, width, height);	
		}

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

	public boolean visible() {
		return this.visible;
	}

	public void move() {
		if (this.visible == true) {
			this.x += this.velocityX;
			this.y += this.velocityY;
			// if (this.x <= 0) {
			// 	this.x = 800;
			// }
			if (this.y <= 0) {
				this.velocityY = -this.velocityY;
			}
			if (this.y >= 600) {
				this.velocityY = -this.velocityY;
			}
		}

	}
	public void setVisible(boolean new_value) {
		this.visible = new_value;
	}

	public int height() {
		return this.height;
	}

	public void setX(int newX) {
		this.x = newX;
	}
	
	public boolean checkCollisionWithShip(Ship ship) {
		if (this.visible == false) {
			return false;
		}
		if (this.x + this.width >= ship.x() && this.x <= ship.x() + ship.width() && this.y + this.height >= ship.y()
				&& this.y <= ship.y() + ship.height()) {
			this.x = this.baseX;
			return true;
		}
		return false;
	}
	

	
	


}