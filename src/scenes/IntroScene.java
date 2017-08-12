package scenes;

import Utils.Utils;
import bases.*;
import bases.renderers.Animation;
import inputs.InputListener;
import inputs.InputManager;
import javafx.scene.media.MediaPlayer;
import players.Player;
import tklibs.AudioUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class IntroScene extends Scene{
    FrameCounter frameCounter;
    Animation playerAnimation;
    GameObject nameTeam;
    private Map map;
    Player player;
    public static MediaPlayer introAudio;
    boolean check;

    @Override
    public void init() throws IOException {
        check = false;

        AudioUtils.initialize();
        this.introAudio = AudioUtils.playMedia("assets/music/gameplay/intro.mp3");
        this.introAudio.setVolume(0.1d);

        frameCounter = new FrameCounter(50);
        nameTeam = new GameObject();
        nameTeam.position.set(70, 150);
        GameObject.add(nameTeam);

        playerAnimation = new Animation(30, true,
                Utils.loadImage("assets/image/player/9.png"),
                Utils.loadImage("assets/image/player/10.png")
        );

        addBackground();
    }

    @Override
    public void run() {

    }

    private void loadMap() throws IOException {
        map = new Map(30, 50, "assets/map/intromap.txt");
    }

    @Override
    public void render(Graphics2D graphics2D) throws IOException {
        if (!frameCounter.run()) {
            playerAnimation.render(graphics2D, new Vector2D(Setting.WIDTH_SCREEN / 2, Setting.HEIGHT_SCREEN / 2));
        } else {
            if (player == null) {
                addPlayer();
                player.position.set(Setting.WIDTH_SCREEN / 2, -500);
                loadMap();
            }
            if (Player.instance != null && Player.velocity.y != 0) {
                check = true;
            }
            if (check) {
                graphics2D.setColor(Color.WHITE);
                graphics2D.setFont(new Font("serif", Font.BOLD, 40));
                Vector2D posNameTeamInCamera = Camera.instance.posInCamera(nameTeam, nameTeam.position);
                graphics2D.drawString("THE FIRST SON", posNameTeamInCamera.x, posNameTeamInCamera.y);
            }
        }
        if (player != null && player.position.y > 800) {
            SceneManager.instance.requestChangeScene(new Level1Scenes());
            this.introAudio.stop();
        }
    }

    private void addBackground() {
        background = Utils.loadImage("assets/image/New folder/newbackground.png");
    }

    private void addPlayer() {
        player = GameObjectPool.recycle(Player.class);
        Camera.instance.setFollowGameObject(player);
    }

    @Override
    public void deInit() {
        super.deInit();
        introAudio.stop();
    }
}
