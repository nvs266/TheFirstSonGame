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
    GameObject leftIcon;
    GameObject rightIcon;
    GameObject spaceIcon;
    private Map map;
    Player player;
    MediaPlayer introAudio;


    @Override
    public void init() throws IOException {

        AudioUtils.initialize();
        this.introAudio = AudioUtils.playMedia("assets/music/gameplay/intro.mp3");
        this.introAudio.setVolume(0.1d);

        frameCounter = new FrameCounter(100);
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

    private void loadMap() throws IOException {
        map = new Map(30, 50, "assets/map/intromap.txt");
    }

    @Override
    public void render(Graphics2D graphics2D) throws IOException {
        if (!frameCounter.run()) {
            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("serif", Font.BOLD, 40));
            graphics2D.drawString("CHS TEAM", nameTeam.position.x, nameTeam.position.y);
            graphics2D.setFont(new Font("serif", Font.BOLD, 15));
            graphics2D.drawString("Jump/Shoot", nameTeam.position.x - 10, nameTeam.position.y + 100);
            graphics2D.setFont(new Font("serif", Font.BOLD, 15));
            graphics2D.drawString("Left", nameTeam.position.x + 135, nameTeam.position.y + 100);
            graphics2D.setFont(new Font("serif", Font.BOLD, 15));
            graphics2D.drawString("Right", nameTeam.position.x + 210, nameTeam.position.y + 100);
            playerAnimation.render(graphics2D, new Vector2D(Setting.WIDTH_SCREEN / 2, Setting.HEIGHT_SCREEN / 2));
        } else {
            if (player == null) {
                addPlayer();
                player.position.set(Setting.WIDTH_SCREEN / 2, -500);
                loadMap();
            }
            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("serif", Font.BOLD, 40));
            Vector2D posNameTeamInCamera = Camera.instance.posInCamera(nameTeam, nameTeam.position);
            graphics2D.drawString("THE FIRST SON", posNameTeamInCamera.x, posNameTeamInCamera.y);
            graphics2D.setFont(new Font("serif", Font.BOLD, 15));
            graphics2D.drawString("Jump/Shoot", posNameTeamInCamera.x - 10, posNameTeamInCamera.y + 100);
            graphics2D.setFont(new Font("serif", Font.BOLD, 15));
            graphics2D.drawString("Left", posNameTeamInCamera.x + 135, posNameTeamInCamera.y + 100);
            graphics2D.setFont(new Font("serif", Font.BOLD, 15));
            graphics2D.drawString("Right", posNameTeamInCamera.x + 210, posNameTeamInCamera.y + 100);
        }
        if (player != null && player.position.y > 800) {
            SceneManager.instance.requestChangeScene(new Level1Scenes());
            this.introAudio.stop();
        }
    }

    private void addBackground() {
        background = Utils.loadImage("assets/image/New folder/background.png");
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
