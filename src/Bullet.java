import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class Bullet {
	
	private Vector2f pos;
	private Vector2f aimVector;
	private int lived = 0;
	private int lado = 10;
	
	private boolean active = true;
	
	private static int maxLifeTime = 2000;
	
	public Bullet (Vector2f pos, Vector2f cursorPos) {
		this.pos = pos;
		this.aimVector = new Vector2f((cursorPos.getX() - pos.getX())/pos.distance(cursorPos), (cursorPos.getY() - pos.getY())/pos.distance(cursorPos));
	}
	
	public float getX() {
		return pos.getX();
	}
	
	public float getY() {
		return pos.getY();
	}
	
	public void setActive(boolean alive) {
		active = alive;
	}
	
	public void update(int  t) {
		if (active){
			pos.set(pos.getX()+ aimVector.getX()*t, pos.getY() + aimVector.getY()*t);
			lived += t;
			
			if (lived > maxLifeTime) active = false;
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (active) {
			g.setColor(Color.red);
			g.fillOval(pos.getX() - 5, pos.getY()-5, lado, lado);
		}
	}
	
	public boolean isActive(){
		return active;
	}
	
	public int getSide() {
		return lado;
	}
	
}
