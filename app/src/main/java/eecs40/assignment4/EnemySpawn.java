package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

public class EnemySpawn extends Blocks implements MarioObjects {

    private Enemy enemy;

    @Override
    public boolean contact(int x, int y, boolean type) {
        return (enemy.contact(x,y,type));
    }

    public EnemySpawn(int x, int y) {
        super(x, y);
        super.setContainEnemy(true);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void setEnemy(Type type, int blockSize){
        switch (type){
            case BEETLE:
                enemy = new BlueBeetle(getX(), getY(), blockSize);
                break;
            case PLANT:
                enemy = new Plant(getX(), getY(), blockSize);
                break;
            case PARATROOPER:
                enemy = new Paratrooper(getX(),getY(),blockSize);
                break;
            default:
                enemy = null;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas, Rect rect, PaintBucket paintBucket) {
        enemy.draw(canvas, paintBucket, getX(), getY());

    }

    @Override
    public void update() {
        System.out.println("EnemyUpdate2");
        enemy.update();
    }

    @Override
    public Type collide(boolean left, boolean right, boolean drop, boolean up) {
        return Type.AIR;
    }
}
