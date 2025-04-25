import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.image.BufferStrategy;//temp fix for flashing

/**
 * Draws a collection of actors.
 */
public class Drawing extends Canvas {

    private ArrayList<Actor> list;

    /**
     * Constructs a drawing of given size.
     * 
     * @param width the width in pixels
     * @param height the height in pixels
     */
    public Drawing(int width, int height) {
        setSize(width, height);
        setBackground(Color.BLACK);
        list = new ArrayList<Actor>();
    }

    /**
     * Adds a new actor to the drawing.
     * 
     * @param actor the object to add
     */
    public void add(Actor actor) {
        list.add(actor);
    }

    /**
     * Gets current actors as an array.
     * 
     * @return array of actor objects
     */
    public Actor[] getActors() {
        return list.toArray(new Actor[0]);
    }//changed from Object[] to Actor[]

    /**
     * Draws all the actors on the canvas.
     * 
     * @param g graphics context
     */
    @Override
    public void paint(Graphics g) {//bryan
        for (Actor actor : list) {
            actor.draw(g);
        }
    }


    /**
     * Calls the step method of each actor and updates the drawing.
     * Edited by ChatGPT to fix flashing issue
     */
    public void step() {
        if (getBufferStrategy() == null) {
            createBufferStrategy(2);
            return; // Don't draw this frame yet
        }

        BufferStrategy bs = getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Actor actor : list) {
            actor.step();
            actor.draw(g);
        }

        g.dispose();
        bs.show();
    }

}
