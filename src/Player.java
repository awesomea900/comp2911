


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();

	public Player(int x, int y, ID id) {
		super(x, y, id);

	}

	public void tick() {
		//x += velX;
		//y += velY;
		
		//x = Game.clamp(x, Game.WIDTH - 44, 0);
		//y = Game.clamp(y, Game.HEIGHT - 72, 0);
	}

	/**
	 * draws the player
	 */
	public void render(Graphics g) {
		if (id == ID.Player) {
			g.setColor(Color.white);
		} else if (id == ID.Player2) {
			g.setColor(Color.blue);
		}
		
		Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("player.png");
        g.drawImage(i, x, y, this);
	}
	


}
