package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

abstract public class Blocks implements MarioObjects {

    private int x,y;
    private boolean containEnemy = false;

    abstract public boolean contact(int x, int y, boolean type);


    public Blocks(int x, int y){
        this.x = x;
        this.y = y;

    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public boolean isContainEnemy() {
        return containEnemy;
    }

    public void setContainEnemy(boolean containEnemy) {
        this.containEnemy = containEnemy;
    }

    public void moveX(int x){
        this.x -= x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    abstract public void setEnemy(Type type, int blockSize);

    abstract public void draw(Canvas canvas);

    abstract public void draw(Canvas canvas, Rect rect, PaintBucket paintBucket);

    abstract public void update();

    abstract public Type collide(boolean left, boolean right, boolean drop, boolean up);

}
