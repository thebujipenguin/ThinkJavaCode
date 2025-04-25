import java.awt.Color;
import java.awt.Font;//for modifiable font
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameUI implements Actor {
    private final VideoGame game;

    public GameUI(VideoGame game) {
        this.game = game;
    }

    @Override
    public void draw(Graphics g) {//bryan
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 18));

        // HUD
        g.drawString("Score: " + game.getScore(), 10, 30);
        g.drawString("Goal:  " + game.getGoalScore(), 10, 50);
        g.drawString("Time:  " + game.getTimer() / 30 + "s", 10, 70);
        //restart message
        g.setFont(new Font("Consolas", Font.BOLD, 24));
        if (game.getState() == VideoGame.GameState.START) {
            g.drawString("Click to Start", 320, 300);
        } else if (game.getState() == VideoGame.GameState.GAME_OVER) {
            g.drawString("GAME OVER! Click to Restart", 250, 300);
        } else if (game.getState() == VideoGame.GameState.LEVEL_COMPLETE) {
            g.drawString("LEVEL COMPLETE! Click to Continue", 200, 300);
        }
    }

    //meital
    @Override public void step() {}
    @Override public void onCollision(Actor other) {}
    @Override public Rectangle getBounds() {
        return new Rectangle(0, 0, 0, 0); // not used
    }
}
