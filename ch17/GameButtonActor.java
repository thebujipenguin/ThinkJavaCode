import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameButtonActor implements Actor, MouseListener {//william
    private final VideoGame game;

    public GameButtonActor(Drawing drawing, VideoGame game) {
        this.game = game;
        drawing.addMouseListener(this);
    }

    @Override public void draw(Graphics g) {}
    @Override public void step() {}
    @Override public void onCollision(Actor other) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (game.getState()) {//state machine
            case START -> game.startGame();
            case GAME_OVER -> game.resetGame();
            case LEVEL_COMPLETE -> game.nextLevel();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 0, 0); // dummy
    }
}