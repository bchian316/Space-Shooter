import java.awt.Color;
import java.awt.Graphics;

public class Projectile implements Drawable, CanAnimate {
	private int x;
	private final int y;
	
	private final int width = 10;
	private final int height = 10;
	
	private final Color red = new Color(255,0,0);


	
	public Projectile(int x, int y){
		
		this.x = x;
		this.y = y;
		


	}
	

    @Override
	public void animate() {
		this.x += 10;
	}
	
	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}
	
	public int height() {
		return height;
	}
	public int width() {
		return width;
	}
	
	public boolean isOutOfScreen() {
		return this.x > 800;
	}
	


        @Override
	public void drawMe(Graphics g){
	
		g.setColor(red);
		g.fillOval(x,y,width,height);	
		
		
	}

	public boolean checkCollision(Enemy enemy) {
		return this.x + this.width >= enemy.x() && this.x <= enemy.x() + enemy.width() && this.y + this.height >= enemy.y()
                        && this.y <= enemy.y() + enemy.height();
	}

}