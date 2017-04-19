import java.awt.Color;
import java.awt.Graphics;

public class Exit extends Cell {
	public Exit(int x, int y) {
		super(x, y);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
        g.fillRect((super.getX() * 16), (super.getY() * 16), 16, 16);
	}
}
