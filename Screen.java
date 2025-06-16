import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel {
	private final Listener listener = new Listener();
	private final Star[] starList = new Star[100]; //can be array because stars never change
	private Enemy[] enemyList = new Enemy[3];
	private final Ship ship = new Ship();
	private final Projectile[] projectileList = new Projectile[3];
	private final Color black = new Color(0, 0, 0);
	private final Planet[] planetList = new Planet[15];

	private int level = 1;
	private int lives = 3;

	public Screen() {
		super();
		this.addKeyListener(listener);
		for (int i = 0; i < starList.length; i++) {
			starList[i] = new Star();
		}
		for (int i = 0; i < planetList.length; i++) {
			planetList[i] = new Planet();
		}
		for (int i = 0; i < enemyList.length; i++) {
			int x = 800;
			// (max -min +1) + min
			int y = (int) (Math.random() * (600 - 50 + 1) + 50);
			enemyList[i] = new Enemy(x, y, level);
		}


		new Timer(30, _ -> this.animate()).start();
	}

	public void animate() {
		if (this.level > 2) {
			return;
		}

		ship.animate(listener.getPressedKeys());

		for (Star star : starList) {
			star.animate();
		}
		
		for (Planet planet : planetList) {
			planet.animate();
		}
		for (Projectile projectile : projectileList) {
			if (projectile != null) {
				projectile.moveRight();
				if (projectile.x() >= 800) {
					projectile.leftScreen();
				}
			}
		}

		for (Projectile projectile : projectileList) {
			if (projectile != null) {
				for (Enemy enemy : enemyList) {
					projectile.checkCollision(enemy);
				}
			}
		}
		
		boolean cleared = true;

		for (Enemy enemy : enemyList) {
			enemy.move();
			if (enemy.checkCollisionWithShip(ship)) {
				this.lives -= 1;
			}
			if (enemy.x() <= 0) {
				this.lives -= 1;
				enemy.setX(800);
			}
			if (enemy.visible() == true) {
				cleared = false;
			}
		} 
		if (cleared) {
			goToNextLevel();
		}

		repaint();
	
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(black);
		g.fillRect(0, 0, 800, 600);
		for (Star star : starList) {
			star.drawMe(g);
		}

		if (level > 1) {
			for (Planet planet : planetList) {
				planet.drawMe(g);
			}
		}
		ship.drawMe(g);
		for (Projectile projectile : projectileList) {
			if (projectile != null) {
				projectile.drawMe(g);
			}
		}

		for (Enemy enemy : enemyList) {
			if (enemy != null) {
				enemy.drawMe(g);	
			}
		}
		Color white = new Color(255, 255, 255);
		g.setColor(white);
		Font font = new Font("Arial", Font.PLAIN, 50); 
		g.setFont(font);
		g.drawString("Level : " + this.level, 400, 50);
		g.drawString("Lives : " + this.lives, 100, 50);
		if (lives < 1) {
			this.lives = 3;
			this.level = 0;
			goToNextLevel();
		}
		if (level > 2) {
			g.setColor(black);
			g.fillRect(0, 0, 800, 600);
			g.setColor(white);
			g.setFont(font);
			g.drawString("GAME OVER YOU WON", 50, 300);
		}
	}


	public void goToNextLevel() {
		this.level += 1;
		if (this.level < 3) {
			enemyList = new Enemy[level*3];
				for (int i = 0; i < enemyList.length; i++) {
					int x = 800;
					// (max -min +1) + min
					int y = (int) (Math.random() * (600 - 50 + 1) + 50);
					enemyList[i] = new Enemy(x, y, level*2);
				}
		}
				
	}

}