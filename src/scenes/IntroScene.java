package scenes;

import Utils.Utils;
import bases.*;
import bases.renderers.Animation;
import inputs.InputListener;
import inputs.InputManager;
import players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class IntroScene extends Scene{
    FrameCounter frameCounter;
    Animation playerAnimation;
    GameObject nameTeam;
    GameObject leftIcon;
    GameObject rightIcon;
    GameObject spaceIcon;
    Player player;


    @Override
    public void init() {
        frameCounter = new FrameCounter(200);
        nameTeam = new GameObject();
        nameTeam.position.set(70, 150);
        GameObject.add(nameTeam);

        leftIcon = new GameObject();
        leftIcon.renderer = new Animation(Utils.loadImage("assets/image/iconmenu/left.png"));
        leftIcon.position.set(220, 200);
        GameObject.add(leftIcon);

        rightIcon = new GameObject();
        rightIcon.renderer = new Animation(Utils.loadImage("assets/image/iconmenu/right.png"));
        rightIcon.position.set(300, 200);
        GameObject.add(rightIcon);

        spaceIcon = new GameObject();
        spaceIcon.renderer = new Animation(Utils.loadImage("assets/image/iconmenu/select.png"));
        spaceIcon.position.set(100, 200);
        GameObject.add(spaceIcon);

        playerAnimation = new Animation(30, true,
                Utils.loadImage("assets/image/player/9.png"),
                Utils.loadImage("assets/image/player/10.png")
        );

        addBackground();
    }

    @Override
    public void run() {

    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (!frameCounter.run()) {
            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("serif", Font.BOLD, 40));
            graphics2D.drawString("CHS TEAM", nameTeam.position.x, nameTeam.position.y);
            playerAnimation.render(graphics2D, new Vector2D(Setting.WIDTH_SCREEN / 2, Setting.HEIGHT_SCREEN / 2));
        } else {
            if (player == null) {
                addPlayer();
                player.position.set(Setting.WIDTH_SCREEN / 2, Setting.HEIGHT_SCREEN / 2);

            }
            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("serif", Font.BOLD, 40));
            nameTeam.position = Camera.instance.posInCamera(nameTeam, nameTeam.position);
            graphics2D.drawString("CHS TEAM", nameTeam.position.x, nameTeam.position.y);
        }
        if (player != null && player.position.y > 800) {
            SceneManager.instance.requestChangeScene(new Level1Scenes());
        }
    }

    private void addBackground() {
        background = Utils.loadImage("assets/image/New folder/background.png");
    }

    private void addPlayer() {
        player = GameObjectPool.recycle(Player.class);
        Camera.instance.setFollowGameObject(player);
    }

}
