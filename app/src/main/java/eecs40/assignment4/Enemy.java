package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

abstract public class Enemy implements MarioObjects {

    int locationX, locationY;
    int dx,dy;
    Rect rect;
    int blockSize, counter;
    boolean move;

    public Enemy(int x, int y, int blockSize){

        this.blockSize = blockSize;
        rect = new Rect();
    }


    abstract boolean contact(int x, int y, boolean type);
    abstract public void draw(Canvas canvas);
    abstract public void update();
    abstract public void draw(Canvas canvas, PaintBucket paintBucket, int x, int y);
}
