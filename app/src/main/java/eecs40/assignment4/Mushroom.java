package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Mushroom extends Blocks {

    @Override
    public boolean contact(int x, int y, boolean type) {
        return false;
    }

    public Mushroom(int x, int y) {
        super(x, y);
    }

    @Override
    public void setEnemy(Type type, int blockSize) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas, Rect rect, PaintBucket paintBucket) {

        rect.set(getX() + 20,getY() +6, getX() + paintBucket.getBlockSize() -20, getY() + paintBucket.getBlockSize());
        canvas.drawRect(rect, paintBucket.getWhite());
        rect.set(getX() + 10,getY() +6, getX() + paintBucket.getBlockSize() -10, getY() + paintBucket.getBlockSize() -30);
        canvas.drawRect(rect, paintBucket.getRed());

    }

    @Override
    public void update() {

    }

    @Override
    public Type collide(boolean left, boolean right, boolean drop, boolean up) {
        return Type.MUSHROOM;
    }
}
