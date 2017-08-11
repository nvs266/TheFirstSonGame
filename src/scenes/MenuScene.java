package scenes;


import inputs.InputListener;
import inputs.InputManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class MenuScene implements InputListener{
    private List<String> titles;
    public int currentIndex;

    public MenuScene(String... titleArr) {
        titles = Arrays.asList(titleArr);
        currentIndex = 0;
        InputManager.instance.register(this);
    }

    public void render(Graphics2D graphics2D) {
        for (int i = 0; i < titles.size(); i++) {
            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("serif", Font.BOLD, 30));
            if (i == currentIndex) {
                graphics2D.drawString(titles.get(i), 50, 50 * i + 200);
            } else {
                graphics2D.drawString(titles.get(i), 70, 50 * i + 200);

            }
        }
    }

    @Override
    public boolean onKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            currentIndex++;
            if (currentIndex >= titles.size()) currentIndex = 0;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            currentIndex--;
            if (currentIndex < 0) currentIndex = titles.size() - 1;
        }
        return false;
    }

    @Override
    public boolean onKeyReleased(int keyCode) {
        return false;
    }
}
