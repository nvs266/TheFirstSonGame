package bases.renderers;

import bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/31/2017.
 */
public class ImageRenderer implements Renderer {

    private BufferedImage image;
    private Vector2D anchor;

    public ImageRenderer(BufferedImage image){
        this.image = image;
        this.anchor = new Vector2D(0.5f,0.5f);
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        g2d.drawImage(image,(int) (position.x - image.getWidth() * anchor.x),(int) (position.y - image.getHeight() * anchor.y), null);
    }
}
