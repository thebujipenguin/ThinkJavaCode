//written by william with help by AI

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * An Actor that listens for mouse clicks and draws a circle
 * at the clicked location.
 */
public class ClickActor implements Actor, MouseListener {
    private final Drawing drawing;
    private int x, y;
    private boolean hasClicked = false;

    /**
     * @param drawing the component on which to listen for clicks
     */
    public ClickActor(Drawing drawing) {
        this.drawing = drawing;
        // register for mouse‐click events
        drawing.addMouseListener(this);
    }

    @Override
    public void draw(Graphics g) {
        if (hasClicked) {
            g.setColor(Color.RED);
            g.fillOval(x - 10, y - 10, 20, 20);
            hasClicked = false;
        }
    }

    @Override
    public void step() {
        // nothing to animate per frame
    }

    // ----- MouseListener methods -----
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        hasClicked = true;
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}