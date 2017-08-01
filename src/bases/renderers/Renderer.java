package bases.renderers;

import bases.Vector2D;

import java.awt.*;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public interface Renderer {
    void render(Graphics2D g2d, Vector2D position);
}