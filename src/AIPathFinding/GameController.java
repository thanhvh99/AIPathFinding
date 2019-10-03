package AIPathFinding;

import AIPathFinding.Entity.Food;
import AIPathFinding.Entity.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

    public static final int VALUE_EMPTY = 0;
    public static final int VALUE_BLOCK = 1;
    public static final int VALUE_FOOD = 2;

    public static final int TICKS_PER_SECOND = 60;
    private static final double NS_PER_TICK = 1000000000.0 / TICKS_PER_SECOND;
    private static final int LOOP_DELAY = 2;

    private static final int MODE_ENDLESS = 0;
    private static final int MODE_NORMAL = 1;

    private AIPathFinding game;
    private int gameMode = MODE_NORMAL;
    private int level = 0;
    private int[][] board;
    private int blockSize;
    private Player player;
    private ArrayList<Food> foods;

    public GameController(AIPathFinding game) {
        this.game = game;
        initialize();
        new Thread(GameController.this::run).start();
    }

    private void initialize() {
        foods = new ArrayList<>();
        player = new Player();
        loadLevel();
    }

    private void loadLevel() {
        board = LevelManager.loadLevel(level);
        level++;
        if (board == null) {
            gameMode = MODE_ENDLESS;
            board = new int[AIPathFinding.DEFAULT_ROW][AIPathFinding.DEFAULT_COL];
            blockSize = AIPathFinding.DEFAULT_BLOCK_SIZE;
            foods.add(new Food(0, 0));
            player.setup(board);
            player.setPosition(0, 0);
        } else {
            player.setPosition(0, 0);
            player.setup(board);
            blockSize = (int) Math.min(AIPathFinding.CONTENT_SIZE.width / (float) board[0].length, AIPathFinding.CONTENT_SIZE.height / (float) board.length);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == VALUE_FOOD) {
                        foods.add(new Food(j, i));
                    }
                }
            }
        }
    }

    private void run() {
        double delta = 0;
        long now = System.nanoTime();
        while (true) {
            try {
                Thread.sleep(LOOP_DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }

            delta -= (now - (now = System.nanoTime())) / NS_PER_TICK;

            while (delta > 1) {
                delta -= 1;
                if (!game.getInputHandler().isPauseKeyPressed()) {
                    update();
                }
            }

            inputUpdate();
            render();
        }
    }

    private void inputUpdate() {
        InputHandler handler = game.getInputHandler();
        if (handler.isLeftMousePressed()) {
            int x = handler.getMousePosition().x / blockSize;
            int y = handler.getMousePosition().y / blockSize;
            if (isInRange(x, y)) {
                if (board[y][x] == VALUE_EMPTY) {
                    board[y][x] = VALUE_BLOCK;
                    player.findNewPath();
                }
            }
        }
        if (handler.isRightMousePressed()) {
            int x = handler.getMousePosition().x / blockSize;
            int y = handler.getMousePosition().y / blockSize;
            if (isInRange(x, y)) {
                if (board[y][x] == VALUE_BLOCK) {
                    board[y][x] = VALUE_EMPTY;
                    player.findNewPath();
                }
            }
        }
    }

    private void update() {
        for (int i = foods.size() - 1; i >= 0; i--) {
            Food food = foods.get(i);
            board[food.getY()][food.getX()] = VALUE_EMPTY;
            if (food.getX() == player.getX() && food.getY() == player.getY()) {
                foods.remove(i);
            } else {
                board[food.getY()][food.getX()] = VALUE_FOOD;
            }
        }
        if (foods.isEmpty()) {
            if (gameMode == MODE_ENDLESS) {
                Food food = createFood();
                board[food.getY()][food.getX()] = VALUE_FOOD;
                foods.add(food);
            } else if (gameMode == MODE_NORMAL) {
                loadLevel();
            }
        }
        player.update();
    }

    private Food createFood() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(board[0].length);
            y = random.nextInt(board.length);
        } while (board[y][x] != VALUE_EMPTY);
        return new Food(x, y);
    }

    private boolean isInRange(int x, int y) {
        return x >= 0 && x < board[0].length && y >= 0 && y < board.length;
    }

    private void render() {
        BufferStrategy bufferStrategy = game.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, AIPathFinding.CONTENT_SIZE.width, AIPathFinding.CONTENT_SIZE.height);

        int row = board.length;
        int col = board[0].length;

        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                graphics.drawImage(Sprite.MIDDLE, j * blockSize, i * blockSize, blockSize, blockSize, null);
            }
        }

        for (int i = 0; i < row; i++) {
            graphics.drawImage(Sprite.LEFT, 0, i * blockSize, blockSize, blockSize, null);
            graphics.drawImage(Sprite.RIGHT, (col - 1) * blockSize, i * blockSize, blockSize, blockSize, null);
        }

        for (int i = 0; i < col; i++) {
            graphics.drawImage(Sprite.TOP, i * blockSize, 0, blockSize, blockSize, null);
            graphics.drawImage(Sprite.BOTTOM, i * blockSize, (row -1) * blockSize, blockSize, blockSize, null);
        }

        graphics.drawImage(Sprite.TOP_LEFT, 0, 0, blockSize, blockSize, null);
        graphics.drawImage(Sprite.TOP_RIGHT, (col - 1) * blockSize, 0, blockSize, blockSize, null);
        graphics.drawImage(Sprite.BOTTOM_LEFT, 0, (row - 1) * blockSize, blockSize, blockSize, null);
        graphics.drawImage(Sprite.BOTTOM_RIGHT, (col - 1) * blockSize, (row - 1) * blockSize, blockSize, blockSize, null);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == VALUE_BLOCK) {
                    graphics.drawImage(Sprite.BLOCK, j * blockSize, i * blockSize, blockSize, blockSize, null);
                }
            }
        }

        for (Food food : foods) {
            graphics.drawImage(Sprite.FOOD, food.getX() * blockSize, food.getY() * blockSize, blockSize, blockSize, null);
        }
        graphics.drawImage(player.getImage(), (int) (player.getX() * blockSize), (int) (player.getY() * blockSize), blockSize, blockSize, null);

        bufferStrategy.show();
    }
}
