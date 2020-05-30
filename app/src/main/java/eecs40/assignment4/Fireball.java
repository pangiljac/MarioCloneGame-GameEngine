package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Fireball implements MarioObjects {

    int x, y;
    Paint red;
    Rect rect;
    private boolean side;
    // left = false, right = true

    public Fireball(int x, int y, boolean side, int blocksize){
        if(side){
            this.x = x + blocksize;

        }
        else{
            this.x = x;

        }

        this.y = y;
        red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
        rect = new Rect();
        rect.set(x - 25, y - 25, x + 25, y + 25);
        this.side = side;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void forward(boolean scrolls){
        if(scrolls){
            if(side){
                x += 40;

            }
            else{
                x -= 14;
            }

        }
        else{
            if(side){
                x += 27;
            }
            else{
                x -= 27;
            }
        }

        if (x < 0){
            x = 0;
        }
        else if (x > 2000){
            x = 2000;
        }
        rect.set(x - 25, y - 25, x + 25, y + 25);


    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, red);
    }

    @Override
    public void update() {

    }
}
