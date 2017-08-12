package players;

import bases.GameObject;
import bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trail extends GameObject {
    private float alpha;
    private BufferedImage image;
    private float life;

    public Trail() {
    }

    public void setTrail(Vector2D position, float life, BufferedImage image) {
        this.position.set(position);
        this.life = life;
        this.image = image;
        this.alpha = 1;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (alpha > life) {
            alpha -= (life - 0.000000000000000000000000000000000000000000001f);
        }
        else this.setActive(false);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setComposite(makeTransparent(alpha));


        g2d.drawImage(image,  (int)(position.x - image.getWidth()/2 - camera.getPosition().x + Player.instance.velocity.x), (int)(position.y - image.getHeight()/2 - camera.getPosition().y + Player.instance.velocity.y), null);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }
}
