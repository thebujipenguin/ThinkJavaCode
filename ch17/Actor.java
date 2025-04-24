import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Graphical simulation element.
 */
public interface Actor {//must be implemented by another class, does not contain implementation of methods

    /**
     * Draws the simulation element in the context.
     * 
     * @param g graphics context
     */
    void draw(Graphics g);

    /**
     * Updates the state of the simulation element.
     */
    void step();

    /**
     * Collisions
     */
    void onCollision(Actor other);

    /**
     * For Sprite class
     */
    Rectangle getBounds();
}
