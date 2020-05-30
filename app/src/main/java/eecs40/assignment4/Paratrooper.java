package eecs40.assignment4;

import android.graphics.Canvas;

public class Paratrooper extends Enemy {

    public Paratrooper(int x, int y, int blockSize) {
        super(x, y, blockSize);
    }

    @Override
    boolean contact(int x, int y, boolean type) {

        if(type) {
            if (this.locationX < x && x < this.locationX + blockSize && this.locationY < y && y < this.locationY + blockSize) {
                return true;
            }
            else if(this.locationX < x + blockSize && x + blockSize < this.locationX + blockSize && this.locationY < y && y < this.locationY + blockSize){
                return true;
            }
            else if(this.locationX < x && x  < this.locationX + blockSize && this.locationY < y + blockSize && y + blockSize < this.locationY + blockSize){
                return true;
            }
            else if(this.locationX < x + blockSize && x + blockSize < this.locationX + blockSize && this.locationY < y + blockSize && y + blockSize < this.locationY + blockSize){
                return true;
            }
            else if (this.locationX < x +1 && x+1 < this.locationX + blockSize && this.locationY < y+1 && y +1 < this.locationY + blockSize) {
                return true;
            }
            else if(this.locationX < x + blockSize -1 && x + blockSize -1 < this.locationX + blockSize && this.locationY < y + 1 && y +1 < this.locationY + blockSize){
                return true;
            }
            else if(this.locationX < x + 1 && x + 1  < this.locationX + blockSize && this.locationY < y + blockSize - 1 && y + blockSize - 1 < this.locationY + blockSize){
                return true;
            }
            else if(this.locationX < x + blockSize - 1 && x + blockSize - 1 < this.locationX + blockSize && this.locationY < y + blockSize - 1 && y + blockSize - 1 < this.locationY + blockSize){
                return true;
            }

        }
        else{
            x-=25;
            y-=25;
            if (this.locationX < x && x < this.locationX + blockSize && this.locationY < y && y < this.locationY + blockSize) {
                return true;
            }
            else if(this.locationX < x +50 && x + 50 < this.locationX + blockSize && this.locationY < y && y < this.locationY + blockSize){
                return true;
            }
            else if(this.locationX < x && x  < this.locationX + blockSize && this.locationY < y +50 && y + 50 < this.locationY){
                return true;
            }
            else if(this.locationX < x + 50 && x + 50 < this.locationX + blockSize && this.locationY < y + 50 && y + 50 < this.locationY + blockSize){
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {
        if(counter == 0 && !move){
            move = true;
        }
        else if(counter == 50){
            move = false;
        }

        if(move) {
            counter += 1;
            dy -= blockSize / 10;
        }
        else{
            counter -= 1;
            dy += blockSize / 10;
        }
    }

    @Override
    public void draw(Canvas canvas, PaintBucket paintBucket, int x, int y) {

        this.locationX = x;
        this.locationY = y + dy;

        System.out.println("CHECK THIS: "+x + ","+(y + dy));
        rect.set(x, y + dy, x + blockSize - 20, y + blockSize + dy);
        canvas.drawRect(rect,paintBucket.getGreen());
        rect.set(x + blockSize - 20, y  + dy, x  + blockSize, y + blockSize + dy);
        canvas.drawRect(rect, paintBucket.getYellow());
    }
}
