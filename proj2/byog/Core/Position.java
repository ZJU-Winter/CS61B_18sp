package byog.Core;

public class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String info = "(" + this.x + "," + this.y + ")";
        return info;
    }
}
