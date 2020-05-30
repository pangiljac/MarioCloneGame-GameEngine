 package eecs40.assignment4;

import android.graphics.Canvas;

public class Plant extends Enemy {

    boolean move2;
    int counter2;


    public Plant(int x, int y, int blockSize) {
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
            x-=10;
            y-=10;
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
        counter += 1;

        if(counter < 10) {

            dy -= blockSize / 10;
        }
        else if(10 < counter && counter < 100){

        }
        else if(100 < counter && counter < 110){

            dy += blockSize / 10;
        }
        else if(110 < counter && counter < 200){

        }
        else if(counter > 200){ counter = 0;}

    }

    @Override
    public void draw(Canvas canvas, PaintBucket paintBucket, int x, int y) {
        this.locationX = x;
        this.locationY = y + dy + blockSize;
        rect.set(x, y + dy + blockSize, x + 20, y + blockSize + dy + blockSize);
        canvas.drawRect(rect,paintBucket.getGreen());
        rect.set(x + blockSize - 20, y  + dy  + blockSize, x  + blockSize , y + blockSize + dy + blockSize);
        canvas.drawRect(rect, paintBucket.getGreen());
        rect.set(x, y  + dy  + blockSize + 40, x  + blockSize , y + blockSize + dy + blockSize);
        canvas.drawRect(rect, paintBucket.getGreen());

    }

}
