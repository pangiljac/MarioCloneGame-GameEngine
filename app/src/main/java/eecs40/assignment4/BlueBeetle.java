package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

public class BlueBeetle extends Enemy {

    public BlueBeetle(int x, int y, int blockSize) {
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
        else if(counter == 60){
            move = false;
        }

        if(move) {
            counter += 1;
            dx += blockSize / 10;
        }
        else{
            counter -= 1;
            dx -= blockSize / 10;
        }
    }

    @Override
    public void draw(Canvas canvas, PaintBucket paintBucket, int x, int y) {

        this.locationX = x + dx;
        this.locationY = y;

        rect.set(x + dx, y, x + dx + blockSize, y + blockSize  - 20);
        canvas.drawRect(rect,paintBucket.getBlue());
        rect.set(x + dx, y  + blockSize - 20, x  + dx + blockSize, y + blockSize);
        canvas.drawRect(rect, paintBucket.getBlack());
    }


}
