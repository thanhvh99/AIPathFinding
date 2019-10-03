package AIPathFinding;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public final class Sprite {

    private static Sprite sprite = new Sprite();

    static BufferedImage TOP_LEFT;
    static BufferedImage TOP_RIGHT;
    static BufferedImage BOTTOM_RIGHT;
    static BufferedImage BOTTOM_LEFT;
    static BufferedImage LEFT;
    static BufferedImage RIGHT;
    static BufferedImage MIDDLE;
    static BufferedImage BLOCK;
    static BufferedImage TOP;
    static BufferedImage BOTTOM;

    public static BufferedImage[] PLAYER_UP;
    public static BufferedImage[] PLAYER_LEFT;
    public static BufferedImage[] PLAYER_DOWN;
    public static BufferedImage[] PLAYER_RIGHT;

    static BufferedImage FOOD;

    static {

    }

    private Sprite() {
        try {
            TOP_LEFT = ImageIO.read(Sprite.class.getResource("/Image/top_left.png"));
            TOP_RIGHT = ImageIO.read(Sprite.class.getResource("/Image/top_right.png"));
            BOTTOM_RIGHT = ImageIO.read(Sprite.class.getResource("/Image/bottom_right.png"));
            BOTTOM_LEFT = ImageIO.read(Sprite.class.getResource("/Image/bottom_left.png"));
            LEFT = ImageIO.read(Sprite.class.getResource("/Image/left.png"));
            RIGHT = ImageIO.read(Sprite.class.getResource("/Image/right.png"));
            MIDDLE = ImageIO.read(Sprite.class.getResource("/Image/middle.png"));
            BLOCK = ImageIO.read(Sprite.class.getResource("/Image/block.png"));
            TOP = ImageIO.read(Sprite.class.getResource("/Image/top.png"));
            BOTTOM = ImageIO.read(Sprite.class.getResource("/Image/bottom.png"));

            PLAYER_UP = new BufferedImage[3];
            PLAYER_LEFT = new BufferedImage[3];
            PLAYER_DOWN = new BufferedImage[3];
            PLAYER_RIGHT = new BufferedImage[3];

            PLAYER_UP[0] = ImageIO.read(Sprite.class.getResource("/Image/player_up_1.png"));
            PLAYER_LEFT[0] = ImageIO.read(Sprite.class.getResource("/Image/player_left_1.png"));
            PLAYER_DOWN[0] = ImageIO.read(Sprite.class.getResource("/Image/player_down_1.png"));
            PLAYER_RIGHT[0] = ImageIO.read(Sprite.class.getResource("/Image/player_right_1.png"));

            PLAYER_UP[1] = ImageIO.read(Sprite.class.getResource("/Image/player_up.png"));
            PLAYER_LEFT[1] = ImageIO.read(Sprite.class.getResource("/Image/player_left.png"));
            PLAYER_DOWN[1] = ImageIO.read(Sprite.class.getResource("/Image/player_down.png"));
            PLAYER_RIGHT[1] = ImageIO.read(Sprite.class.getResource("/Image/player_right.png"));

            PLAYER_UP[2] = ImageIO.read(Sprite.class.getResource("/Image/player_up_2.png"));
            PLAYER_LEFT[2] = ImageIO.read(Sprite.class.getResource("/Image/player_left_2.png"));
            PLAYER_DOWN[2] = ImageIO.read(Sprite.class.getResource("/Image/player_down_2.png"));
            PLAYER_RIGHT[2] = ImageIO.read(Sprite.class.getResource("/Image/player_right_2.png"));

            FOOD = ImageIO.read(Sprite.class.getResource("/Image/food.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
