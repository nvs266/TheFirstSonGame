package physics;

import bases.GameObject;
import bases.Setting;
import tklibs.Mathx;

public class BoxCollider extends GameObject implements Setting{
    public float width;
    public float height;
    public static HIT_LOCATION hitLocation;

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

    public boolean collideWith(float top, float bottom, float left, float right) {
        if (this.top() > bottom) hitLocation = HIT_LOCATION.HIT_UPPER;
        else if (this.right() < left) hitLocation = HIT_LOCATION.HIT_RIGHT;
        else if (this.left() > right) hitLocation = HIT_LOCATION.HIT_LEFT;
        else if (this.bottom() < top) hitLocation = HIT_LOCATION.HIT_LOWER;
        boolean xOverlap = Mathx.inRange(left, this.left(), this.right())
                || Mathx.inRange(this.left(), left, right);

        boolean yOverlap = Mathx.inRange(top, this.top(), this.bottom())
                || Mathx.inRange(this.top(), top, bottom);

        return xOverlap && yOverlap;
    }

    public boolean collideWith(BoxCollider other) {
        return collideWith(other.top(), other.bottom(), other.left(), other.right());
    }

    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", height=" + height +
                ", screenPosition=" + screenPosition +
                '}';
    }
}