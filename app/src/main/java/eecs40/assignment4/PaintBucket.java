package eecs40.assignment4;

import android.graphics.Color;
import android.graphics.Paint;

public class PaintBucket {

    private int blockSize;
    private Paint black;
    private Paint brown;
    private Paint gold;
    private Paint white;
    private Paint red;
    private Paint yellow;
    private Paint blue;
    private Paint green;
    private Paint lightBrown;

    public PaintBucket(int blockSize){
        this.blockSize = blockSize;
        black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);
        brown = new Paint();
        brown.setColor(Color.rgb(102,51,0));
        brown.setStyle(Paint.Style.FILL);
        gold = new Paint();
        gold.setColor(Color.rgb(212, 175,55));
        gold.setStyle(Paint.Style.FILL);
        white = new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);
        red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
        yellow = new Paint();
        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL);
        blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);
        green = new Paint();
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);
        lightBrown = new Paint();
        lightBrown.setColor(Color.rgb(179,139,109));
        lightBrown.setStyle(Paint.Style.FILL);

    }

    public int getBlockSize() {
        return blockSize;
    }

    public Paint getBlack() {
        return black;
    }

    public Paint getBrown() {
        return brown;
    }

    public Paint getGold() {
        return gold;
    }

    public Paint getWhite() {
        return white;
    }

    public Paint getRed() {
        return red;
    }

    public Paint getYellow() {
        return yellow;
    }

    public Paint getBlue() {
        return blue;
    }

    public Paint getGreen() {
        return green;
    }

    public Paint getLightBrown() {
        return lightBrown;
    }
}
