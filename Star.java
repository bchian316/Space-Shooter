import java.awt.Color;
import java.awt.Graphics;


public class Star implements Drawable, CanAnimate {
	private int x;
	private int y;
	private final Color white = new Color(255, 255, 255);

	public Star() {
		x = (int) (Math.random() * 798);
		y = (int) (Math.random() * 598);
	}

    @Override
	public void drawMe(Graphics g) {
		g.setColor(white);
		g.fillRect(x, y, 3, 3);
	}
	
    @Override
	public void animate() {
		x--;
		if (x < 0) {
			x = 800;
			y = (int) (Math.random() * 598);
		}
	}
}