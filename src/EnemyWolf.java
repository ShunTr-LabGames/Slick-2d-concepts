import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class EnemyWolf {
	
	public Shape pos;
	private int ancho;
	private int alto;
	private boolean alive = true;
	private float velMovement;
	
	public EnemyWolf(Vector2f pos, int ancho, int alto, float vel) {
		this.pos = new Rectangle(pos.getX(), pos.getY(), ancho, alto);
		this.ancho = ancho;
		this.alto = alto;
		this.velMovement = vel;
	}
	
	public float getX(){
		return pos.getX();
	}
	
	public float getX2() {
		return pos.getX() + ancho;
	}
	
	public float getY(){
		return pos.getY();
	}
	
	public float getY2(){
		return pos.getY() + alto;
	}
	
	public void set(Vector2f posEnemy) {
		pos.setLocation(posEnemy);
	}
	
	public void set(float x, float y) {
		pos.setLocation(x, y);
	}
	
	public void render(GameContainer gc, Graphics g) {
		if (alive) {
			g.setColor(new Color(255,255,255));
			g.fillRect(pos.getX(), pos.getY(), 10, 10);
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void update(Character player, int delta) {

		Vector2f dir = new Vector2f( player.getX() - pos.getX(), player.getY() - pos.getY());
		
		dir.normalise();
		float vel = delta * velMovement;
		pos.setLocation(pos.getX() + (dir.getX() * vel), pos.getY() + (dir.getY() * vel));
		
	}
	
	public void checkCollisionWithBullets(LinkedList<Bullet> bullets) {
		for (Bullet b : bullets) {
			Shape bullet = new Circle(b.getX(), b.getY(), b.getSide());
			
			if (bullet.intersects(pos)){
				alive=false;
			}
		}
	}
	
}
