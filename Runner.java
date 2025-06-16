import javax.swing.JFrame;
public class Runner 
{
	
	public static void main( String[] args ) {
		JFrame frame = new JFrame("Screen 2");
		
		Screen screen = new Screen();
		
		frame.setSize(800,600);
        frame.setContentPane(screen);
        frame.setLocation(150,25);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.requestFocusInWindow();
	}
}