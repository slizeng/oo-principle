package cc.oobootcamp.length;

public class Length {
    private int value;

    public Length(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isLongerThan(Length target) {
        return value > target.getValue();
    }

    public boolean isEqualTo(Length target) {
        return value == target.getValue();
    }

    public boolean isShorterThan(Length target) {
        return value < target.getValue();
    }
}
