import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Listener implements KeyListener {
    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Screen s;
    private boolean spacePressed = false; //can't hold down space bttn

    public Listener(Screen s) {
        this.s = s;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //use for shooting
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!spacePressed) {
                s.playerAttack();
            }
            spacePressed = true;
        }
        this.pressedKeys.add(e.getKeyCode());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        this.pressedKeys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    public Set<Integer> getPressedKeys() {
        return this.pressedKeys;
    }
    
}