package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Coin extends Blocks {

    @Override
    public boolean contact(int x, int y, boolean type) {
        return false;
    }

    public Coin(int x, int y) {
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

        rect.set(getX() + 15,getY() +6, getX() + paintBucket.getBlockSize() -15, getY() + paintBucket.getBlockSize() -6);
        canvas.drawRect(rect, paintBucket.getGold());

    }

    @Override
    public void update(){

    }

    @Override
    public Type collide(boolean left, boolean right, boolean drop, boolean up) {
        return Type.COIN;
    }


}
