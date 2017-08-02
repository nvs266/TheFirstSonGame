package bases.renderers;

import bases.Setting;
import bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class ImageRenderer implements Renderer, Setting {

    private BufferedImage image;

    public ImageRenderer(BufferedImage image){
        this.image = image;
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        g2d.drawImage(image,(int) (position.x - image.getWidth() * ANCHOR_X_DEFAULT),(int) (position.y - image.getHeight() * ANCHOR_Y_DEFAULT), null);
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
