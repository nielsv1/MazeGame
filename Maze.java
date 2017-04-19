import java.awt.Graphics;
import java.util.Random;

public class Maze {
	private Cell[][] cells;
	private Random rand = new Random();
	private int sizeX;
	private int sizeY;
	private Exit exit;
	
	public Maze(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		cells = new Cell[sizeX][sizeY];
		gen();
	}
	
	private void gen() {
		int eX = rand.nextInt(sizeX);
		int eY = (rand.nextInt(2) * (sizeY - 1));
		exit = new Exit(eX, eY);
		//sets exit location
		setCell(eX, eY, exit);
		//sets start location
		setCell((int) Math.ceil(sizeX / 2), (int) Math.ceil(sizeY / 2), new Cell((int) Math.ceil(sizeX / 2), (int) Math.ceil(sizeY / 2)));
		//generates paths until exit is reached
		new Path(getCell((int) Math.ceil(sizeX / 2), (int) Math.ceil(sizeY / 2)), getCell(eX, eY), this);
	}
	
	public void draw(Graphics g) {
		for (Cell[] cellArray : cells) {
			for (Cell cell : cellArray) {
				if (cell != null) cell.draw(g);
			}
		}
		exit.draw(g);
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public void setCell(int x, int y, Cell c) {
		cells[x][y] = c;
	}
	
	public Exit getExit(){
		return exit;
	}
}