//Meital and William

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

class MovingPolygon extends RegularPolygon implements Actor{
    //William and Meital wrote down the variables!
    public int xpos;
    public int ypos;
    public double dx;
    public double dy;
    private Drawing drawing;
    private boolean hasCollided = false;
    private Color originalColor;
    private int collisionTimer = 0;
    private int respawnDelay = 0;//TIMERS! TIMERS! TIMERS! GET YOUR PIPING HOT TIMERS!


    public MovingPolygon(int nsides, int radius, Color color, Drawing drawing){//bryan
        super(nsides, radius, color);
        this.originalColor = color;

        this.xpos = 400;
        this.ypos = 300;
        this.dx = 2;
        this.dy = 2;
        this.drawing = drawing;
    }



    public void draw(Graphics g) {
        g.translate(xpos, ypos);
        super.draw(g);
        g.translate(-xpos, -ypos);
    }

    public void step(){//bryan
        xpos += dx;
        ypos += dy;

        if (xpos < 0 || xpos > 800) {
            dx *= -1;
        }
        if (ypos < 0 || ypos > 600) {
            dy *= -1;
        }

        if (collisionTimer > 0) {
            collisionTimer--;
            if (collisionTimer == 0) {
                this.color = originalColor;
                hasCollided = false;
            }
        }
        //TIMMMMMEEEEEERRRRRSSSSS
        if (respawnDelay > 0) {
            respawnDelay--;
            if (respawnDelay == 0) {
                // 💫 Respawn somewhere random with new speed
                this.xpos = (int)(Math.random() * 700 + 50);
                this.ypos = (int)(Math.random() * 500 + 50);
                this.dx = Math.random() * 4 - 2;
                this.dy = Math.random() * 4 - 2;
                this.color = originalColor;
                hasCollided = false;
            }
        }
    }

    @Override
    public void onCollision(Actor other) {//bryan
        if (!hasCollided && respawnDelay == 0) {
            hasCollided = true;
            collisionTimer = 10;
            this.color = Color.RED;

            this.xpos = -1000;
            this.ypos = -1000;
            this.dx = 0;
            this.dy = 0;

            respawnDelay = 30;// just over 1 second i think
        }
    }


    @Override
    public Rectangle getBounds() {
        Rectangle base = super.getBounds();
        return new Rectangle(xpos + base.x, ypos + base.y, base.width, base.height);
    }
}
