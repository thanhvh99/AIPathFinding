package AIPathFinding;

import javax.swing.*;
import java.awt.*;

public class AIPathFinding extends Canvas {

    public static Dimension SCREEN_SIZE;
    public static Dimension CONTENT_SIZE;

    public static final int DEFAULT_ROW = 13;
    public static final int DEFAULT_COL = 21;
    public static int DEFAULT_BLOCK_SIZE;

    private JFrame jFrame = new JFrame();
    private InputHandler inputHandler = new InputHandler();

    private AIPathFinding() {
        SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
        DEFAULT_BLOCK_SIZE = (int) Math.min(SCREEN_SIZE.width * 0.9 / DEFAULT_COL, SCREEN_SIZE.height * 0.9 / DEFAULT_ROW);
        CONTENT_SIZE = new Dimension(DEFAULT_BLOCK_SIZE * DEFAULT_COL, DEFAULT_BLOCK_SIZE * DEFAULT_ROW);
        setupJFrame();
        setupGraphics();
    }

    private void setupGraphics() {
        if (getBufferStrategy() == null) {
            createBufferStrategy(3);
        }
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    private void setupJFrame() {
        jFrame.setLayout(new BorderLayout());
        jFrame.getContentPane().setPreferredSize(CONTENT_SIZE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("AI Path Finding");
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setLocation((SCREEN_SIZE.width - CONTENT_SIZE.width) / 2, 0);
        jFrame.add(this, BorderLayout.CENTER);
        jFrame.pack();
        addMouseListener(inputHandler);
        addKeyListener(inputHandler);
        addMouseMotionListener(inputHandler);
        requestFocus();
    }

    public static void main(String[] args) {
        AIPathFinding game = new AIPathFinding();
        new GameController(game);
    }
}
