//Meital and William

import java.awt.Graphics;
import java.awt.Color;

class MovingPolygon extends RegularPolygon implements Actor{
    //William and Meital wrote down the variables!
    public int xpos;
    public int ypos;
    public double dx;
    public double dy;
    private Drawing drawing;

    public MovingPolygon(int nsides, int radius, Color color, Drawing drawing){//bryan
        super(nsides, radius, color);

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
        //add bouncing off edge
        xpos +=dx;
        ypos +=dy;

        if(xpos<0 || xpos>800){//bryan
            dx *=-1;
        }
        if(ypos<0 || ypos>600){
            dy *=-1;
        }
    }

    @Override
    public void onCollision(Actor other){//bryan
        this.color = Color.RED;//visual feedback
        System.out.println("collided");
    }
}
