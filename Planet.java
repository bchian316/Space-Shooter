import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Planet implements Drawable, CanAnimate{
	private int x;
	private int y;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static BufferedImage planetImg;

	static {
		try {
			planetImg = ImageIO.read(new File("planet.png"));
		} catch (IOException e) {}
	}

	public Planet() {
		x = (int) (Math.random() * 798);
		y = (int) (Math.random() * 598);
	}

    @Override
	public void drawMe(Graphics g) {
		g.drawImage(planetImg, x, y, WIDTH, HEIGHT, null);
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