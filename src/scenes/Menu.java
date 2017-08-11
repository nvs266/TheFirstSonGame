package scenes;

import inputs.InputListener;
import inputs.InputManager;

import java.awt.event.KeyEvent;

public class Menu implements InputListener{
    public int total;
    public int currentIndex;

    public Menu(int total) {
        currentIndex = 1;
        this.total = total;
    }

    @Override
    public boolean onKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            currentIndex++;
            if (currentIndex > total) currentIndex = total;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            currentIndex--;
            if (currentIndex < 1) currentIndex = 1;
        }
        return false;
    }

    @Override
    public boolean onKeyReleased(int keyCode) {
        return false;
    }
}
