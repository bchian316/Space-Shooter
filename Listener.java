import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Listener implements KeyListener {
    private final Set<Integer> pressedKeys = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        //use this
        System.out.println("keypressed");
        this.pressedKeys.add(e.getKeyCode());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        this.pressedKeys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    public Set<Integer> getPressedKeys() {
        return this.pressedKeys;
    }
    
}