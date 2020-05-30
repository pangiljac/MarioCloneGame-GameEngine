package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Display implements MarioObjects {

    private int location1, location2;

    private int score = 0;
    private int lives = 3;
    private Paint textPaint;

    public Display(int location1, int location2){
        this.location1 = location1;
        this.location2 = location2;
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(100);
    }


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawText("Score: ", location2 - 150, 125, textPaint );
        canvas.drawText(" " + score + " ", location2 - 150, 200, textPaint );
        canvas.drawText("Lives: ", location1 - 150, 125, textPaint);
        canvas.drawText(" " + lives + " ", location1 - 150, 200, textPaint );
    }

    @Override
    public void update() {

    }
    public void update(int score) {
        this.score += score;

    }
}
