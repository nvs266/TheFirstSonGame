package bases;

public class Contraints {
    float top;
    float bottom;
    float left;
    float right;

    public Contraints(float top, float bottom, float left, float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public void make(Vector2D positon) {
        positon.x = Mathx.clamp(positon.x, left, right);
        positon.y = Mathx.clamp(positon.y, top, bottom);
    }
}
