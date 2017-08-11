package inputs;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputManager {
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean spacePressed;
    public boolean immotal;
    public boolean escapePressed;
    public boolean enterPressed;


    public static final InputManager instance = new InputManager();

    private List<InputListener> inputListeners;

    public void register(InputListener inputListener) {
        this.inputListeners.add(inputListener);
    }

    public void unregister(InputListener inputListener) {
        inputListeners.remove(inputListener);
    }

    private InputManager(){
        inputListeners = new ArrayList<>();
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = true;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
            case KeyEvent.VK_F6:
                immotal = true;
                break;
            default:
                break;
        }

        for (InputListener inputListener : inputListeners) {
            inputListener.onKeyPressed(keyEvent.getKeyCode());
        }
    }

    public void keyRealeased(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = false;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = false;
                break;

            default:
                break;
        }

        Iterator<InputListener> iterator = inputListeners.iterator();
        while (iterator.hasNext()) {
            InputListener inputListener = iterator.next();
            if (inputListener.onKeyReleased(keyEvent.getKeyCode())) {
                iterator.remove();
            }
        }
    }
}
