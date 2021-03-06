import java.util.ArrayList;
import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;


public class Character {
	public Shape bola;
	public float vel;
	private boolean alive = true;
	
	public Character (float x,  float y, float radio, float vel) {
		this.bola = new Circle(x, y, radio);
		this.vel = vel;
	}
	
	public float getX() {
		return bola.getX();
	}
	
	public float getY() {
		return bola.getY();
	}
	
	public void render(GameContainer gc, Graphics g, Character pj ) throws SlickException {
		g.setColor(new Color(255, 255, 255));
		g.fillOval(pj.bola.getX() - 10, pj.bola.getY() - 10, 20, 20);
	}
	
	public void moveAndCollide(GameContainer gc, int delta, Character pj, int altoVentana, int anchoVentana, int radioPJ) {
		if (gc.getInput().isKeyDown(gc.getInput().KEY_W) && pj.bola.getY() > 0){
			pj.bola.setY(pj.bola.getY() - pj.vel * delta);
		}
		
		if (gc.getInput().isKeyDown(gc.getInput().KEY_S) && pj.bola.getY() < altoVentana - radioPJ*2) {
			pj.bola.setY(pj.bola.getY() + pj.vel * delta);
		}
		
		if (gc.getInput().isKeyDown(gc.getInput().KEY_A) && pj.bola.getX() > 0) {
			pj.bola.setX(pj.bola.getX() - pj.vel * delta);
		}
		
		if (gc.getInput().isKeyDown(gc.getInput().KEY_D) && pj.bola.getX() < anchoVentana - radioPJ*2) {
			pj.bola.setX(pj.bola.getX() + pj.vel * delta);
		}
	}
	
	public void collideWithEnemy(LinkedList<EnemyWolf> wolfs) {

		for (int i=0; i<wolfs.size(); i++) {
			if (wolfs.get(i).pos.intersects(bola)){
				alive=false;
			}
		}
	}
	
	public boolean alive() {
		return alive;
	}
}
