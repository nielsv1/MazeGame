
import java.util.Random;

public class Path {
	private Random pathGen = new Random();
	private Cell s;
	private Cell e;
	private Maze m;
	
	public Path(Cell s, Cell e, Maze m) {
		this.s = s;
		this.e = e;
		this.m = m;
		
		genSeg(this.s);
	}
	
	private void genSeg(Cell s) {
		int dir = pathGen.nextInt(4);
		int dist = pathGen.nextInt(20) + 1;
		Cell f = new Cell(21, 21);
		switch (dir) {
			case 0:
				if (s.getX() + dist > 41) dist = 41 - s.getX();
				else for (int i = 0; i < dist; i++) {
					m.setCell(s.getX() + i, s.getY(), new Cell(s.getX() + i, s.getY()));
					f = m.getCell(s.getX() + i, s.getY());
				}
				break;
			case 1:
				if (s.getY() + dist > 41) dist = 41 - s.getY();
				else for (int i = 0; i < dist; i++) {
					m.setCell(s.getX(), s.getY() + i, new Cell(s.getX(), s.getY() + i));
					f = m.getCell(s.getX(), s.getY() + i);
				}
				break;
			case 2:
				if (s.getX() - dist < 1) dist = s.getX();
				else for (int i = 0; i < dist; i++) {
					m.setCell(s.getX() - i, s.getY(), new Cell(s.getX() - i, s.getY()));
					f = m.getCell(s.getX() - i, s.getY());
				}
				break;
			case 3:
				if (s.getY() - dist < 1) dist = s.getY();
				else for (int i = 0; i < dist; i++) {
					m.setCell(s.getX(), s.getY() - i, new Cell(s.getX(), s.getY() - i));
					f = m.getCell(s.getX(), s.getY() - i);
				}
				break;
		}
		
		if (e.getX() != f.getX() && e.getY() != f.getY()) genSeg(f);
		else {
			e.setX(f.getX());
			e.setY(f.getY());
		}
	}
}
