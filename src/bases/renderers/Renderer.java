package bases.renderers;

import bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public interface Renderer {
    void render(Graphics2D g2d, Vector2D position);
    float getWidth();
    float getHeight();
    void reset();
    int getIndexImage();
    boolean isFinished();
    BufferedImage getCurrentImage();
}
