import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel {
	private final Listener l = new Listener(this);
	private final Star[] stars = new Star[100]; //can be array because stars never change
	private final ArrayList<Enemy> enemies = new ArrayList<>();
	private final Ship ship = new Ship();
	private final ArrayList<Projectile> projectiles = new ArrayList<>();
	private final Planet[] planets = new Planet[15];

	//formatting stuff
	private static final Color BLACK = new Color(0, 0, 0);
	private static final Color WHITE = new Color(255, 255, 255);
	private static final Font FONT = new Font("Arial", Font.PLAIN, 50);

	private int level = 0;
	private final int NUM_LEVELS = 10;

	private final Timer timer;

	public Screen() {
		super();
		this.addKeyListener(this.l);
		this.setFocusable(true);
		this.requestFocusInWindow();

		for (int i = 0; i < stars.length; i++) {
			stars[i] = new Star();
		}
		for (int i = 0; i < planets.length; i++) {
			planets[i] = new Planet();
		}
		goToNextLevel();


		timer = new Timer(30, _ -> this.animate());
		timer.start();
	}

	public void animate() {

		animateShip();

		animateCollection(stars);
		animateCollection(planets);
		animateCollection(projectiles);
		animateCollection(enemies);

		deleteProjectilesOutOfScreen();
		checkIfEnemiesHitOrPassedPlayer();
		checkIfProjectilesHitEnemies();

		if (enemies.isEmpty()) {
			goToNextLevel();
		}

		repaint();

	}

	public void animateShip() {
		ship.animate(this.l.getPressedKeys());
	}
	
	public void animateCollection(ArrayList<? extends CanAnimate> animatables) {
		for (CanAnimate c : animatables) {
			c.animate();
		}
	}

	public void animateCollection(CanAnimate[] animatables) {
		for (CanAnimate c : animatables) {
			if (c != null) {
				c.animate();
			}
		}
	}
	
	public void deleteProjectilesOutOfScreen() {
		for (int i = this.projectiles.size() - 1; i >= 0; i--) {
			if (this.projectiles.get(i).isOutOfScreen()) {
				this.projectiles.remove(i);
			}
		}
	}

	public void checkIfEnemiesHitOrPassedPlayer() {
		//this deducts player lives
		//2 ways of deducting lives: hit by enemy or enemy got past player
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			if (this.enemies.get(i).collidedWithShip(ship) || this.enemies.get(i).passedPlayer()) {
				this.enemies.remove(i);
				this.ship.deductLife();
			}
		}
	}

	public void checkIfProjectilesHitEnemies() {
		for (int i = this.projectiles.size() - 1; i >= 0; i--) {
			//check each projectile with enemies
			for (int j = this.enemies.size() - 1; j >= 0; j--) {
				if (this.enemies.get(j).collidedWithProjectile(this.projectiles.get(i))) {
					this.projectiles.remove(i);
					this.enemies.remove(j);
					break;
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBackground(g);
		drawShip(g);

		drawCollection(stars, g);
		if (level > 5) {
			drawCollection(planets, g);
		}

		drawCollection(projectiles, g);

		drawCollection(enemies, g);

		drawStats(g);
		if (this.ship.isDead()) {
			drawLose(g);
		}
		if (level > NUM_LEVELS) {
			drawWin(g);
		}
	}
	
	public void drawBackground(Graphics g) {
		g.setColor(BLACK);
		g.fillRect(0, 0, Runner.SCREENWIDTH, Runner.SCREENHEIGHT);
	}

	public void drawShip(Graphics g) {
		ship.drawMe(g);
	}
	
	public void drawCollection(ArrayList<? extends Drawable> drawables, Graphics g) {
		for (Drawable d : drawables) {
			d.drawMe(g);
		}
	}

	public void drawCollection(Drawable[] drawables, Graphics g) {
		for (Drawable d : drawables) {
			if (d != null) {
				d.drawMe(g);
			}
		}
	}

	public void drawStats(Graphics g) {
		g.setColor(WHITE);
		g.setFont(FONT);
		g.drawString("Level : " + this.level, 400, 50);
		g.drawString("Lives : " + this.ship.getLives(), 100, 50);
	}

	public void drawLose(Graphics g) {
		g.setColor(BLACK);
		g.fillRect(0, 0, Runner.SCREENWIDTH, Runner.SCREENHEIGHT);
		g.setColor(WHITE);
		g.setFont(FONT);
		g.drawString("U LOSE", 50, 300);
		timer.stop();
	}

	public void drawWin(Graphics g) {
		g.setColor(BLACK);
		g.fillRect(0, 0, Runner.SCREENWIDTH, Runner.SCREENHEIGHT);
		g.setColor(WHITE);
		g.setFont(FONT);
		g.drawString("GAME OVER YOU WON", 50, 300);
		timer.stop();
	}

	private void goToNextLevel() {
		this.level += 1;
		if (this.level <= NUM_LEVELS) {
			for (int i = 0; i < level * 3; i++) {
				int x = Runner.SCREENWIDTH + 70;
				// (max -min +1) + min
				int y = (int) (Math.random() * (Runner.SCREENHEIGHT - 50));
				enemies.add(new Enemy(x, y, level));
			}
		}

	}
	
	public void playerAttack() {
		this.projectiles.add(this.ship.attack());
	}

}