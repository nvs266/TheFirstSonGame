package bases.renderers;

import bases.FrameCounter;
import bases.GameObject;
import bases.Setting;
import bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class Animation implements Renderer, Setting {
    private List<BufferedImage> images;
    public int imageIndex;
    private FrameCounter frameCounter;
    private boolean finished;
    private boolean repeat;

    public Animation(int delayFrame, boolean repeat, BufferedImage... imageArr) {
        this.images = Arrays.asList(imageArr);
        frameCounter = new FrameCounter(delayFrame);
        this.repeat = repeat;
    }

    public Animation(BufferedImage... imageArr) {
        this(Setting.DELAY_ANIMATION_DEFAULT, true, imageArr);
    }


    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public BufferedImage getCurrentImage() {
        return images.get(imageIndex);
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        if (frameCounter.run()) {
            if (!finished){
                changeIndex();
            }
            frameCounter.reset();
        }
        BufferedImage image = images.get(imageIndex);
        g2d.drawImage(image,
                (int) (position.x - image.getWidth() * ANCHOR_X_DEFAULT), (int) (position.y - image.getHeight() * ANCHOR_Y_DEFAULT), null);

    }


    @Override
    public float getWidth() {
        return images.get(imageIndex).getWidth();
    }

    @Override
    public float getHeight() {
        return images.get(imageIndex).getHeight();
    }

    private void changeIndex() {
        if (imageIndex >= images.size() - 1) {
            if (repeat) {
                reset();
            } else {
                finished = true;
            }
        } else {
            imageIndex++;
        }
    }

    public void reset() {
        imageIndex = 0;
        finished = false;
    }

    @Override
    public int getIndexImage() {
        return imageIndex;
    }
}
