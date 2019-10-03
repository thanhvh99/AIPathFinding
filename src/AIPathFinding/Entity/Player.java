package AIPathFinding.Entity;

import AIPathFinding.GameController;
import AIPathFinding.Sprite;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Player {

    private int[][] board;
    private boolean findPath = true;
    private Point2D.Float position = new Point2D.Float();
    private float speed = 0.05f;
    private char direction = 'S';
    private String path = "";
    private boolean move = false;
    private int state = 1;
    private int stateAdd = 1;
    private int tick = 0;

    public Player() {

    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void setup(int[][] board) {
        this.board = board;
    }

    public void findNewPath() {
        findPath = true;
    }

    public void update() {
        updateAnimation();
        if (move) {
            move();
        }
        if (isRoundPosition()) {
            if (findPath) {
                findPath = false;
                findPath();
            }
            if (!path.isEmpty()) {
                move = true;
                direction = path.charAt(0);
                path = path.substring(1);
            } else {
                move = false;
                findPath = true;
            }
        }
    }

    private boolean isRoundPosition() {
        return position.x == (int) position.x && position.y == (int) position.y;
    }

    private void updateAnimation() {
        if (move) {
            tick++;
            if (tick % (GameController.TICKS_PER_SECOND / 5) == 0) {
                state += stateAdd;
                if (state == 2 || state == 0) {
                    stateAdd *= -1;
                }
            }
        } else {
            state = 1;
            tick = 0;
        }
    }

    private void move() {
        switch (direction) {
            case 'W': position.y -= speed; break;
            case 'D': position.x += speed; break;
            case 'S': position.y += speed; break;
            case 'A': position.x -= speed; break;
        }
        if (Math.abs(Math.round(position.x) - position.x) < speed * 2 / 3) {
            position.x = Math.round(position.x);
        }
        if (Math.abs(Math.round(position.y) - position.y) < speed * 2 / 3) {
            position.y = Math.round(position.y);
        }
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public BufferedImage getImage() {
        switch (direction) {
            case 'W': return Sprite.PLAYER_UP[state];
            case 'D': return Sprite.PLAYER_RIGHT[state];
            case 'S': return Sprite.PLAYER_DOWN[state];
            case 'A': return Sprite.PLAYER_LEFT[state];
        }
        return null;
    }

    private void findPath() {
        path = "";
        int[][] matrix = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, matrix[i], 0, board[0].length);
        }
        pathFinding(matrix, -1, (int) position.x, (int) position.y, "");
    }

    private void pathFinding(int[][] board, int value, int curX, int curY, String direction) {
        if (curX < 0 || curY < 0 || curX >= board[0].length || curY >= board.length) {
            return;
        }
        if (board[curY][curX] == GameController.VALUE_FOOD) {
            if (path.isEmpty() || direction.length() < path.length()) {
                path = direction;
            }
            return;
        }
        if (board[curY][curX] == GameController.VALUE_BLOCK || (board[curY][curX] < 0 && board[curY][curX] >= value)) {
            return;
        }
        board[curY][curX] = value;
        value--;
        pathFinding(board, value, curX + 1, curY, direction + "D");
        pathFinding(board, value, curX - 1, curY, direction + "A");
        pathFinding(board, value, curX, curY + 1, direction + "S");
        pathFinding(board, value, curX, curY - 1,  direction + "W");
    }
}
