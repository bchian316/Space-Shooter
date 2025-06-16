import java.awt.Color;
import java.awt.Graphics;

public class Projectile implements Drawable{
	private int x;
	private int y;
	
	private final int width = 10;
	private final int height = 10;
	
	private final Color red = new Color(255,0,0);

	private boolean visible = true;

	
	public Projectile(int x, int y){
		
		this.x = x;
		this.y = y;
		


	}
	
	public void fire() {
		this.visible = true;
	}

	public void moveRight() {
		if (this.visible) {
			this.x += 10;
		}

		if (this.x > 800) {
			this.reset();
			
		}
	}
	
	public void setY(int newY) {
		if (!this.visible) {
			this.y = newY;
		}
	}

	public void leftScreen() {
		this.visible = false;
	}

	public int x() {
		return this.x;
	}

	public boolean visible() {
		return this.visible;
	}

	public void reset() {
		this.visible = false;
		this.x = 50;
	}
	
	public void setX(int newX) {
		if (!this.visible) {
			this.x= newX;
		}
	}

        @Override
	public void drawMe(Graphics g){
		if (this.visible) {
			g.setColor(red);
			g.fillOval(x,y,width,height);	
		}
		
	}
	
	public void setPosition(int x, int y) {

		if (visible == false) {
			this.x = x;
			this.y = y;
		}
	}

	public void checkCollision(Enemy enemy) {
		if (!enemy.visible() ||!this.visible ) {
			return;
		}
		if (this.x + this.width >= enemy.x() && this.x <= enemy.x() + enemy.width() && this.y + this.height >= enemy.y()
				&& this.y <= enemy.y() + enemy.height()) {
			enemy.setVisible(false);
			this.reset();
		}
	}

}