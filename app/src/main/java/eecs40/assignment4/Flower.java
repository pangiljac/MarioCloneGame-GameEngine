package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Flower extends Blocks {

    @Override
    public boolean contact(int x, int y, boolean type) {
        return false;
    }

    public Flower(int x, int y) {
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
        rect.set(getX() + 10,getY() + 24, getX() + paintBucket.getBlockSize() -10, getY() + paintBucket.getBlockSize() - 24);
        canvas.drawRect(rect, paintBucket.getRed());
        rect.set(getX() + 24,getY() + 10, getX() + paintBucket.getBlockSize() - 24, getY() + paintBucket.getBlockSize() - 10);
        canvas.drawRect(rect, paintBucket.getRed());
        rect.set(getX() + 24,getY() +24, getX() + paintBucket.getBlockSize() -24, getY() + paintBucket.getBlockSize() -24);
        canvas.drawRect(rect, paintBucket.getYellow());
    }

    @Override
    public void update() {

    }

    @Override
    public Type collide(boolean left, boolean right, boolean drop, boolean up) {
        return Type.FLOWER;
    }
}
