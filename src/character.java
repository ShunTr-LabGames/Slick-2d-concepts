import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;


public class character {
	public Shape bola;
	public float vel;
	
	public character (float x,  float y, float radio, float vel) {
		this.bola = new Circle(x, y, radio);
		this.vel = vel;
	}
	
	public void render(GameContainer gc, Graphics g, character pj ) throws SlickException {
		g.setColor(new Color(255, 255, 255));
		g.fillOval(pj.bola.getX(), pj.bola.getY(), 20, 20);
	}
	
	public void moveAndCollide(GameContainer gc, ArrayList arrayBloques, int delta, character pj, int altoVentana, int anchoVentana, int radioPJ) {
		if (gc.getInput().isKeyDown(gc.getInput().KEY_UP) && pj.bola.getY() > 0 && checkCollision(pj.bola.getX(), pj.bola.getY() - pj.vel * delta, radioPJ, arrayBloques)){
			pj.bola.setY(pj.bola.getY() - pj.vel * delta);
		}
		
		if (gc.getInput().isKeyDown(gc.getInput().KEY_DOWN) && pj.bola.getY() < altoVentana - radioPJ*2 && checkCollision(pj.bola.getX(), pj.bola.getY() + pj.vel * delta, radioPJ, arrayBloques)) {
			pj.bola.setY(pj.bola.getY() + pj.vel * delta);
		}
		
		if (gc.getInput().isKeyDown(gc.getInput().KEY_LEFT) && pj.bola.getX() > 0 && checkCollision(pj.bola.getX() - pj.vel * delta, pj.bola.getY(), radioPJ, arrayBloques)) {
			pj.bola.setX(pj.bola.getX() - pj.vel * delta);
		}
		
		if (gc.getInput().isKeyDown(gc.getInput().KEY_RIGHT) && pj.bola.getX() < anchoVentana - radioPJ*2 && checkCollision(pj.bola.getX() + pj.vel * delta, pj.bola.getY(), radioPJ, arrayBloques)) {
			pj.bola.setX(pj.bola.getX() + pj.vel * delta);
		}
	}
	
	private boolean checkCollision(float x, float y, int radioPJ, ArrayList<Shape> arrayBloques) {
		Shape checkBall = new Circle(x, y, radioPJ);
		
		for (int i=0; i<arrayBloques.size(); i++){
			if (checkBall.intersects(arrayBloques.get(i))) return false; 
		}
		
		return true;
	}
}
