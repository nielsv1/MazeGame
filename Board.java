import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	private final int B_WIDTH;
	private final int B_HEIGHT;
	private Timer timer;
	private Player player;
	private Maze maze;
	private int mazeX;
	private int mazeY;
	public int points;
	private double timeLeft = 40* 60;
	private boolean sTime = false;

	public Board(int mazeX, int mazeY) {
		B_WIDTH = mazeX * 16;
		B_HEIGHT = mazeY * 16;
		this.mazeX = mazeX;
		this.mazeY = mazeY;

		initBoard();
		initObjects();
		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				char key = e.getKeyChar();
				if (key == 'w' && maze.getCell(player.getX() / 16, (player.getY() - 16) / 16) != null) player.setY(player.getY() - 16);
				else if (key == 'a' && maze.getCell((player.getX() - 16) / 16, player.getY() / 16) != null) player.setX(player.getX() - 16);
				else if (key == 's' && maze.getCell(player.getX() / 16, (player.getY() + 16) / 16) != null) player.setY(player.getY() + 16);
				else if (key == 'd' && maze.getCell((player.getX() + 16) / 16, player.getY() / 16) != null) player.setX(player.getX() + 16);
				else if (key == ' '){
					reset();
				}
				else if (e.getKeyCode() == 17 && sTime) sTime = false;
				else if (e.getKeyCode() == 17) sTime = true;
			}

			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {}
		});
	}
	
	private void initBoard() {
		setFocusable(true);
		setBackground(Color.black);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), 100, 25);
	}

	private void initObjects() {
		player = new Player(B_WIDTH / 2, B_HEIGHT / 2);
		maze = new Maze(mazeX, mazeY);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(timeLeft <= 0){
			g.setFont(new Font("Arial", 128, 32));
			g.setColor(Color.white);
			drawCenteredText("Time's up!", B_WIDTH / 2, B_HEIGHT / 2, g);
			drawCenteredText("Final score: " + points, B_WIDTH / 2, (int) Math.floor(B_HEIGHT / 1.5), g);
		} else {
			maze.draw(g);
			player.draw(g);
			checkExit();
			drawGUI(g);
			if (sTime) timeLeft -= 0.5; 
			else timeLeft--;
		}
	}

	private class ScheduleTask extends TimerTask {
		@Override
		public void run() {
			repaint();
		}
	}

	private boolean checkExit(){
		Exit exit = maze.getExit();
		if (player.getX() == (exit.getX() * 16) + 8 && player.getY() == (exit.getY() * 16) + 8) {
			points++;
			reset();
			return true;
		} else return false;
	}
	
	private void drawGUI(Graphics g){
		g.setColor(Color.red);
		g.setFont(new Font("Arial", 128, 32));
		g.drawString("Points: " + points, 16, 32);
		g.drawString("Time Remaining: " + (int) Math.floor(timeLeft / 40), 16, 64);
	}
	
	private void reset() {
		player.setX(B_WIDTH / 2);
		player.setY(B_HEIGHT / 2);
		try {
			maze = new Maze(mazeX, mazeY);
		} catch (Error e) {
			System.out.println("Error in maze generation");
			reset();
		}
	}
	
	private void drawCenteredText(String s, int x, int y, Graphics g) {
    	g.drawString(s, x - s.length() * 10 , y - 5);
    }
}
