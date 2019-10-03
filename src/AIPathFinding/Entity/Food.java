package AIPathFinding.Entity;

import java.awt.*;

public class Food {

    private Point position = new Point();

    public Food(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }
}
