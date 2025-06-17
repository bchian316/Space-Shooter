import javax.swing.JFrame;
public class Runner 
{
    public static final int SCREENWIDTH = 800;
    public static final int SCREENHEIGHT = 600;
	public static void main( String[] args ) {
		JFrame frame = new JFrame("Screen 2");
		
		Screen screen = new Screen();
        frame.setContentPane(screen);
		
		frame.setSize(SCREENWIDTH,SCREENHEIGHT);
        frame.setLocation(150,25);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        
        screen.requestFocusInWindow();
	}
}