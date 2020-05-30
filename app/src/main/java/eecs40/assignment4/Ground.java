package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Ground extends Blocks {

    @Override
    public boolean contact(int x, int y, boolean type) {
        return false;
    }

    public Ground(int x, int y){
        super(x,y);

    }

    @Override
    public void setEnemy(Type type, int blockSize) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas, Rect rect, PaintBucket paintBucket) {
        rect.set(getX(),getY(), getX() + paintBucket.getBlockSize(), getY() + paintBucket.getBlockSize());
        canvas.drawRect(rect, paintBucket.getBrown());
}

    @Override
    public void update() {

    }

    @Override
    public Type collide(boolean left, boolean right, boolean drop, boolean up){
        return Type.UNBREAKABLE;
    }
}
