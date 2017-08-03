package bases;

public interface Setting {
    int HEIGHT_SCREEN = 600;
    int WIDTH_SCREEN = 384;
    int DELAY = 17 ; // 17 frame ~= 60 fps
    int DELAY_ANIMATION_DEFAULT = 5;
    float ANCHOR_X_DEFAULT = 0.5f;
    float ANCHOR_Y_DEFAULT = 0.5f;
    float SPEED_PLAYER = 1.5f;
    int WIDTH_GRID = 32;
    int HEIGHT_GRID = 32;
    enum HIT_LOCATION{HIT_UPPER, HIT_LOWER, HIT_LEFT, HIT_RIGHT}
}
