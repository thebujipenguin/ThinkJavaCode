import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * Example game with a sprite that moves around the screen.
 */
public class VideoGame implements ActionListener {
    private int score = 0;//william made the score system
    private Drawing drawing;
    private Toolkit toolkit;

    /**
     * Set up the drawing and window frame.
     */
    public VideoGame() {

        Sprite sprite = new Sprite("face-smile.png", 25, 150);
        MovingPolygon player = new MovingPolygon(6,40, Color.GREEN, drawing);
        drawing = new Drawing(800, 600);
        drawing.add(sprite);
        drawing.add(player);
        drawing.addKeyListener(sprite);
        drawing.setFocusable(true);

        JFrame frame = new JFrame("Video Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawing);
        frame.pack();
        frame.setVisible(true);

        toolkit = frame.getToolkit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        detectCollisions();
        drawing.step();
        drawing.repaint();
        toolkit.sync();
    }

    public int getScore(){
        return score;
    }

    /**
     * Create and start the timer.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        VideoGame game = new VideoGame();
        Timer timer = new Timer(33, game);
        timer.start();
    }

    private void detectCollisions() {//bryan
        System.out.println("Checking collisions...");
        Actor[] actors = drawing.getActors();
        System.out.println("Actor count: " + actors.length);

        for (int i = 0; i < actors.length; i++) {
            for (int j = i + 1; j < actors.length; j++) {
                Actor a1 = actors[i];
                Actor a2 = actors[j];

                if (isColliding(a1, a2)) {
                    System.out.println("Detected collision between " + a1 + " and " + a2);
                    score++;
                    a1.onCollision(a2);
                    a2.onCollision(a1);
                }
            }
        }
    }

    private boolean isColliding(Actor a1, Actor a2) {
        Rectangle r1 = a1.getBounds();
        Rectangle r2 = a2.getBounds();
        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        return r1.intersects(r2);
        //return a1.getBounds().intersects(a2.getBounds());
    }
}
