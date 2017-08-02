package tklibs;

public class Mathx {
    public static float clamp(float value, float min, float max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static boolean inRange(float value, float min, float max) {
        return value >= min && value <= max;
    }
}
