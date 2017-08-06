package bases;

public interface Setting {
    //Screen
    int HEIGHT_SCREEN = 600;
    int WIDTH_SCREEN = 384;
    //Grid
    int WIDTH_GRID = 32;
    int HEIGHT_GRID = 32;
    //Delay
    int DELAY = 17 ; // 17 frame ~= 60 fps
    int DELAY_ANIMATION_DEFAULT = 5;
    //anchor
    float ANCHOR_X_DEFAULT = 0.5f;
    float ANCHOR_Y_DEFAULT = 0.5f;
    //Player
    float SPEED_PLAYER = 1f;
    float GRAVITY_PLAYER = 0.02f;
    float SPEED_JUMP_PLAYER = -2.2f;
    float SPEED_JUMPP_HIT_ENEMY = -1f;
    enum HIT_LOCATION{HIT_UPPER, HIT_LOWER, HIT_LEFT, HIT_RIGHT}
    float SIZE_ENEMY_ACTIVE = 400;
    int COOLDOWN = 30;
    int START_LIFE = 4;
    int START_TOTAL_BULLETS = 8;
}
