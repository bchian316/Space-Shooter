import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Planet
{
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage planetImg;

	public Planet() {
		x = (int) (Math.random() * 798);
		y = (int) (Math.random() * 598);
		width = 50;
		height = 50;
		try {
			planetImg = ImageIO.read(new File("planet.png"));
		} catch (IOException e) {}
	}

	public void drawMe(Graphics g) {
		g.drawImage(planetImg, x, y, width, height, null);
	}
	
	public void animate() {
		x--;
		if (x < 0) {
			x = 800;
			y = (int) (Math.random() * 598);
		}
	}
}