import  java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;//temp fix for flashing

/**
 * Example game with a sprite that moves around the screen.
 */
public class VideoGame implements ActionListener {
    private int score = 0;//william made the score system
    private Drawing drawing;
    private Toolkit toolkit;
    private GameState state = GameState.START;
    private int timer = 600; // ~20 seconds (600 ticks at 33ms/frame)
    private int level = 1;
    private int goalScore = 10;

    /**
     * Set up the drawing and window frame.
     */
    public VideoGame() {

        Sprite sprite = new Sprite("player.png", 25, 150);//player

        drawing = new Drawing(800, 600);
        drawing.add(sprite);
        //drawing.add(player);
        drawing.addKeyListener(sprite);
        drawing.setFocusable(true);
        //enemies (asteroids):
        for (int i = 0; i < 5; i++) {//meital
            int sides = (int) (Math.random() * 3 + 5); // 5-7 sides
            Color c = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());//random color
            MovingPolygon asteroid = new MovingPolygon(sides, 40, c, drawing);
            asteroid.xpos = (int) (Math.random() * 700 + 50);//random start pos
            asteroid.ypos = (int) (Math.random() * 500 + 50);
            asteroid.dx = Math.random() * 4 - 2;
            asteroid.dy = Math.random() * 4 - 2;
            drawing.add(asteroid);
        }
        drawing.add(new GameUI(this));
        drawing.add(new GameButtonActor(drawing, this));
        JFrame frame = new JFrame("Video Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawing);
        frame.pack();
        frame.setVisible(true);

        toolkit = frame.getToolkit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {//bryan
        if (state == GameState.PLAYING) {
            timer--;

            if (timer <= 0 && score < goalScore) {
                state = GameState.GAME_OVER;
            } else if (score >= goalScore) {
                state = GameState.LEVEL_COMPLETE;
            }

            detectCollisions();
        }

        drawing.step();
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
        Actor[] actors = drawing.getActors();

        for (int i = 0; i < actors.length; i++) {
            for (int j = i + 1; j < actors.length; j++) {
                Actor a1 = actors[i];
                Actor a2 = actors[j];

                if (a1 != a2 && isColliding(a1, a2) && !bothAsteroids(a1, a2)) {
                    score++;
                    a1.onCollision(a2);
                    a2.onCollision(a1);
                }
            }
        }
    }

    private boolean isColliding(Actor a1, Actor a2) {//bryan
        Rectangle r1 = a1.getBounds();
        Rectangle r2 = a2.getBounds();
        return r1.intersects(r2);
    }

    private boolean bothAsteroids(Actor a1, Actor a2) {//meital
        return a1 instanceof MovingPolygon && a2 instanceof MovingPolygon;
    }

    enum GameState {//bryan
        START, PLAYING, GAME_OVER, LEVEL_COMPLETE
    }

    /**
     * Getters
     * @return GameState = what state the game is in
     * william
     */
    public GameState getState() { return state; }
    public int getTimer() { return timer; }

    public void startGame() {//bryan
        state = GameState.PLAYING;
        score = 0;
        timer = 600;
    }

    public void resetGame() {//bryan
        state = GameState.START;
        score = 0;
        level = 1;
    }

    public void nextLevel() {//bryan
        level++;
        state = GameState.PLAYING;
        score = 0;
        timer = 500;
        goalScore += 5; //each level requires 5 more points

        int sides = (int) (Math.random() * 3 + 5); // 5-7 sides
        Color c = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());//random color
        MovingPolygon asteroid = new MovingPolygon(sides, 40, c, drawing);
        asteroid.xpos = (int) (Math.random() * 700 + 50);//random start pos
        asteroid.ypos = (int) (Math.random() * 500 + 50);
        asteroid.dx = Math.random() * 4 - 2;
        asteroid.dy = Math.random() * 4 - 2;
        drawing.add(asteroid);
    }

    public int getGoalScore() {
        return goalScore;
    }
}
