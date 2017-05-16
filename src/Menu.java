import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private String gameStateWord;
	private int bouncer;
	private boolean startFromTop;
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		this.gameStateWord = null;
		this.bouncer = 0;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.IsgameStateMenu()){
			//playButton
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				gameStateWord = "PlayerMenu";
				
			}
			
			//help button, is Mouse on the help button
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				gameStateWord = "Help";
			}
			
			// quit button, is Mouse on the quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}
		
		if(game.IsgameStatePlayerMenu()){
			//single player button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				gameStateWord = "Game";
				game.removeKeyListener(game.getKeyinput());
				game.getSpawner().levelLoader(game.getSpawner().currentLevelNumber, handler);
				
				game.setKeyinput(new KeyInput(handler));
				game.addKeyListener(game.getKeyinput());
			}
			
			//2 player button
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				gameStateWord = "Game";
				game.removeKeyListener(game.getKeyinput());
				game.getSpawner().levelLoader(game.getSpawner().currentLevelNumber, handler);
				
				game.setKeyinput(new KeyInput(handler));
				game.addKeyListener(game.getKeyinput());
			}
			
			// back button, is Mouse on the button button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				gameStateWord = "Menu";
			}
			
		}
		
		//back button for help
		if(game.IsgameStateHelp()){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				gameStateWord = "Menu";
				return;
			}
		}
	}
	
	/**
	 * helper function, determines whether mouse is in box
	 * @param mx, mouse x
	 * @param my, mouse y
	 * @param x , x position of box
	 * @param y, y position of box
	 * @param width of the box
	 * @param height of the box
	 * @return
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void mouseReleased(MouseEvent e){
		game.setgameState(gameStateWord);
	}
	
	
	
	public void tick(){
		
		if(bouncer < 10 && !startFromTop){
			bouncer++;
		} else {
			startFromTop = true;
			if(bouncer > 0){
				bouncer--;
			} else {
				startFromTop = false;
			}
		}
		
	}
	
	/**
	 * draw the graphics
	 * @param g
	 */
	public void render(Graphics g){
		if (game.IsgameStateMenu()) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Sokoban", 205, 90);

			g.setFont(fnt2);
			g.drawRect(210 - bouncer/2, 150 - bouncer/2, 200 + bouncer, 64 + bouncer);
			g.drawString("Play", 270, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		} else if (game.IsgameStateHelp()) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("i need help too", 240, 290);
			g.drawString("use WASD/arrow keys to move player", 130, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		} else if (game.IsgameStatePlayerMenu()){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Sokoban", 205, 90);

			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Single Player", 217, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("2 Player", 255, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}
	}
}
