import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;


public class main extends BasicGame {

	private static int altoVentana = 400;
	private static int anchoVentana = 600;
	private int radioPJ = 10;
	private Character pj = new Character(anchoVentana/2, altoVentana/2, radioPJ, 0.3f);
	
	private LinkedList<Bullet> bullets;
	private LinkedList<EnemyWolf> wolfs;
	
	private static int fireRate = 250;
	
	public main(String title) {
		super(title);
	}

	public static void main(String args[]) throws SlickException {
		AppGameContainer app = new AppGameContainer(new main("Slic2DConceptos"));
		app.setDisplayMode(anchoVentana, altoVentana, false);
		app.setVSync(true);
		app.setShowFPS(false);
		app.start();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		if (pj.alive()) {
			pj.render(gc, g, pj);
		}
		
		for (Bullet b : bullets) {
			b.render(gc,g);
		}
		
		for (EnemyWolf w : wolfs) {
			w.render(gc, g);
		}
		
		if (!pj.alive()) {
			Random rand = new Random ();
			g.setColor(new Color(rand.nextInt((255) + 1), rand.nextInt((255) + 1), rand.nextInt((255) + 1)));
			g.drawString("GAME OVER", anchoVentana/2 - 35, altoVentana/2 - 10);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		bullets = new LinkedList<Bullet> ();
		wolfs= new LinkedList<EnemyWolf> ();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		Input input = gc.getInput();
		
		createBicho();
		
		pj.moveAndCollide(gc, delta, pj, altoVentana, anchoVentana, radioPJ);
		pj.collideWithEnemy(wolfs);
		
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isActive()) {
				bullets.get(i).update(delta);
			} else {
				bullets.remove(i);
			}
		}
		
		for (int i=0; i < wolfs.size(); i++) {
			if (wolfs.get(i).isAlive()) {
				wolfs.get(i).checkCollisionWithBullets(bullets);
				wolfs.get(i).update(pj, delta);
			} else {
				wolfs.remove(i);
			}
		}
		
		if (input.isKeyPressed(Input.KEY_SPACE) && pj.alive()){
			bullets.add(new Bullet( new Vector2f(pj.bola.getX(), pj.bola.getY()), new Vector2f(input.getMouseX(), input.getMouseY()) ) );
		}
		
	}

	public void createBicho() {
		if (wolfs.size() <= 0) {
			Random rand = new Random ();
			wolfs.add(new EnemyWolf(new Vector2f(rand.nextInt((anchoVentana) + 1), rand.nextInt((altoVentana) + 1)), 20, 20, 0.1f));
		}
		
	}
	
}
