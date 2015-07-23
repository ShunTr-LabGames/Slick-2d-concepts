import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
	private boolean run = false;
	private character pj = new character(anchoVentana/2, altoVentana/2, radioPJ, 0.5f);
	
	private LinkedList<Bullet> bullets;
	private static int fireRate = 250;
	private ArrayList <Shape> arrayBloques=new ArrayList <Shape> ();
	
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
		
		pj.render(gc, g, pj);
		
		for (int i=0; i<arrayBloques.size(); i++){
			g.setColor(new Color(0, 255, 0));
			if (pj.bola.intersects(arrayBloques.get(i))) g.setColor(new Color(255, 0, 0));
			g.fillRect(arrayBloques.get(i).getX(), arrayBloques.get(i).getY(), arrayBloques.get(i).getWidth(), arrayBloques.get(i).getHeight());
		}
		
		for (Bullet b : bullets) {
			b.render(gc,g);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		bullets = new LinkedList<Bullet> ();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		Input input = gc.getInput();
		if (!run) startGame();
		
		pj.moveAndCollide(gc, arrayBloques, delta, pj, altoVentana, anchoVentana, radioPJ);
		
		//balas
		/*Iterator<Bullet> i = bullets.iterator();
		while(i.hasNext()){
			Bullet b = i.next();
				if (b.isActive()) {
					b.update(delta);
				} else {
					i.remove();
				}
		}*/
		
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isActive()) {
				bullets.get(i).update(delta);
			} else {
				bullets.remove(i);
			}
		}
		
		if (input.isKeyPressed(Input.KEY_SPACE)){
			bullets.add(new Bullet( new Vector2f(pj.bola.getCenterX(), pj.bola.getCenterY()), new Vector2f(input.getMouseX(), input.getMouseY()) ) );
		}
		
	}
	
	private void startGame(){
		crearBloques();
		run = true;
	}
	
	private void crearBloques(){
		arrayBloques.clear();
		arrayBloques.add( new Rectangle (1, 1, 50, 50)); 
		arrayBloques.add( new Rectangle (300, 250, 100, 10));
		arrayBloques.add( new Rectangle (400, 300, 50, 50));
	}
}
