package scenes;

import bases.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Scene {
    public static BufferedImage background;
    public abstract void init() throws IOException;
    public void deInit() {
        GameObject.clear();
    }
    public abstract void run();
    public abstract void render(Graphics2D graphics2D) throws IOException;
}
