import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;


public class Ship implements Drawable{
	private static final int SPEED = 10;
	private int x = 50;
	private int y = (int) (Math.random() * 520);
	private int width = 100;
	private int height = 80;
	private BufferedImage shipImg;

	public Ship() {
		try {
			shipImg = ImageIO.read(new File("alien.png"));
		} catch (IOException e) {}
	}

    @Override
	public void drawMe(Graphics g) {
		g.drawImage(shipImg, x, y, width, height, null);
	}

	public void animate(Set<Integer> pressedKeys) {
		if (pressedKeys.contains(KeyEvent.VK_UP)) {
			this.moveUp();
		}
		if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
			this.moveDown();
		}
	}
	public void moveUp() {
		if (y > 0) {
			y-=SPEED;
		}
	}
	
	public void moveDown() {
		if (y < 550) {
			y+=SPEED;
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

	public int height() {
		return this.height;
	}

	
}