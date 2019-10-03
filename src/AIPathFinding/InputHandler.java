package AIPathFinding;

import java.awt.*;
import java.awt.event.*;

public class InputHandler implements MouseMotionListener, MouseListener, KeyListener {

    private boolean leftMousePressed = false;
    private boolean rightMousePressed = false;
    private boolean pauseKeyPressed = false;
    private Point mousePosition = new Point();

    InputHandler() {

    }

    boolean isPauseKeyPressed() {
        return pauseKeyPressed;
    }

    boolean isLeftMousePressed() {
        return leftMousePressed;
    }

    boolean isRightMousePressed() {
        return rightMousePressed;
    }

    Point getMousePosition() {
        return mousePosition;
    }

    // Mouse listener function
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMousePressed = true;
        }
        if (e.getButton() != MouseEvent.BUTTON1) {
            rightMousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMousePressed = false;
        }
        if (e.getButton() != MouseEvent.BUTTON1) {
            rightMousePressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Mouse motion listener function
    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }

    // Key listener function
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'p') {
            pauseKeyPressed = !pauseKeyPressed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
