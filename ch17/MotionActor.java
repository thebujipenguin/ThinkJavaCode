//Written by william with subtle help from chat gpt

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * An Actor that follows the mouse pointer—drawing a small square
 * wherever the mouse is moved or dragged.
 */
public class MotionActor implements Actor, MouseMotionListener {
    private final Drawing drawing;
    private int x, y;

    /**
     * @param drawing the component on which to listen for motion
     */
    public MotionActor(Drawing drawing) {
        this.drawing = drawing;
        // register for mouse‐motion events
        drawing.addMouseMotionListener(this);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x - 5, y - 5, 10, 10);
    }

    @Override
    public void step() {
        // no per‐frame update needed
    }

    // ----- MouseMotionListener methods -----
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}