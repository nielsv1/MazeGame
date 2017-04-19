import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
	private int mazeX = 41;
	private int mazeY = 41;
	
	public Main(){
        initUI();
    }
	
    private void initUI(){
        add(new Board(mazeX, mazeY));

        setSize(mazeX * 16, mazeY * 16);
        setResizable(false);
        pack();
        setTitle("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main (String []args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}
