package bases;

import tklibs.Mathx;

public class BoxCollider extends GameObject implements Setting{
    private float width;
    private float height;
    private HIT_LOCATION hitLocation; // tra ve vi chi cham cua vat this so voi vat other

    public BoxCollider(float width, float height) {
        super();
        this.width = width;
        this.height = height;
    }

    public BoxCollider() {
        this(0, 0);
    }

    public float left() {
        return this.screenPosition.x - width / 2;
    }

    public float right() {
        return this.screenPosition.x + width / 2;
    }

    public float top() {
        return this.screenPosition.y - height / 2;
    }

    public float bottom() {
        return this.screenPosition.y + height / 2;
    }

    public boolean collideWith(BoxCollider other) {
        // xet vi tri tuong doi
            if (this.screenPosition.y  < other.screenPosition.y) hitLocation = HIT_LOCATION.HIT_UPPER;
            else if (this.screenPosition.y > other.screenPosition.y) hitLocation = HIT_LOCATION.HIT_LOWER;
            else if (this.screenPosition.x < other.screenPosition.x) hitLocation = HIT_LOCATION.HIT_LEFT;
            else if (this.screenPosition.x > other.screenPosition.x) hitLocation = HIT_LOCATION.HIT_RIGHT;
        // xet overlap
        boolean xOverlap = Mathx.inRange(other.left(), this.left(), this.right())
                || Mathx.inRange(this.left(), other.left(), other.right());
        boolean yOverlap = Mathx.inRange(other.top(), this.top(), this.bottom())
                || Mathx.inRange(this.top(), other.top(), other.bottom());
        return xOverlap && yOverlap;
    }
}
