package eecs40.assignment4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Rect;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MarioThread thread;
    private Rect background;
    private Rect border1, border2, buttonLeft, buttonRight, buttonJump, buttonFireball;
    private boolean left, right, jump, fireball;
    private boolean testing;
    private int point1, point2, point3, point4, point5, point6, point7, point8;

    public static final int maxBlockSize = 16;

    int blockSize;
    Paint white = new Paint();
    Paint yellow = new Paint();
    Paint lightBlue = new Paint();
    Paint red = new Paint();
    Paint green = new Paint();

    private Mario player;
    private Map map;
    private Display hud;



    public GamePanel(Context context){

        super(context);



        getHolder().addCallback(this);

        thread = new MarioThread(getHolder(), this, testing);

        /*determineBlockSize();*/

        background = new Rect();
        border1 = new Rect();
        border2 = new Rect();
        buttonLeft = new Rect();
        buttonRight = new Rect();
        buttonJump = new Rect();
        buttonFireball = new Rect();

        left = false;
        right = false;
        jump = false;
        fireball = false;



        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MarioThread(getHolder(), this, testing);
        thread.setRunning(true);
        thread.start();
    }

    public void update(){
        if(player == null){
            System.out.println("break");
        }
        player.update(left,right,jump,fireball);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(true){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (Exception e){e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawRect(background, lightBlue);


        if(player.getLiveFireballs() != null) {
            for (int k = 0; k < player.getNumberFireballs(); k++) {
                player.drawFireballs(k, canvas);
            }
        }

        map.draw(canvas);
        player.draw(canvas);

        canvas.drawRect(border1, white);
        canvas.drawRect(border2, white);

        if(left){
            canvas.drawRect(buttonLeft, green);
        }
        else{
            canvas.drawRect(buttonLeft, red);
        }

        if(right){
            canvas.drawRect(buttonRight, green);
        }
        else{
            canvas.drawRect(buttonRight, red);
        }

        if(jump){
            canvas.drawRect(buttonJump, green);
        }
        else{
            canvas.drawRect(buttonJump,red);
        }
        if(fireball){
            canvas.drawRect(buttonFireball, green);
        }
        else{
            canvas.drawRect(buttonFireball,red);
        }

        hud.draw(canvas);

    }

    public void firstDraw(Canvas canvas){
        super.draw(canvas);
        background.set(0, 0,canvas.getWidth(), canvas.getHeight());

        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL);
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);
        lightBlue.setColor(Color.rgb(135,206,250));
        lightBlue.setStyle(Paint.Style.FILL);
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);

        canvas.drawRect(background, lightBlue);

        determineBlockSize(canvas);

        map = new Map(blockSize, canvas.getWidth(), canvas.getHeight());
        map.draw(canvas);

        border1.set(0,0, canvas.getWidth()/2 - (8 * blockSize), canvas.getHeight());
        border2.set(canvas.getWidth()/2 + (8 * blockSize), 0, canvas.getWidth(), canvas.getHeight());

        buttonLeft.set(0, canvas.getHeight()/2, (canvas.getWidth()/2 - (8 * blockSize))/2, canvas.getHeight());
        buttonRight.set((canvas.getWidth()/2 - (8 * blockSize))/2, canvas.getHeight()/2, canvas.getWidth()/2 - (8 * blockSize), canvas.getHeight());
        buttonJump.set(canvas.getWidth()/2 + (8 * blockSize),canvas.getHeight()/2,canvas.getWidth()/2 + (8 * blockSize) + (canvas.getWidth()/2 - (8 * blockSize))/2,canvas.getHeight());
        buttonFireball.set(canvas.getWidth()/2 + (8 * blockSize) + (canvas.getWidth()/2 - (8 * blockSize))/2,canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight());
        /*
        buttonFireball.set();

*/

        point1 = 0;
        point2 = canvas.getHeight()/2;
        point3 = canvas.getHeight();
        point4 = (canvas.getWidth()/2 - (8 * blockSize))/2;
        point5 = canvas.getWidth()/2 - (8 * blockSize);
        point6 = canvas.getWidth()/2 + (8 * blockSize);
        point7 = canvas.getWidth()/2 + (8 * blockSize) + (canvas.getWidth()/2 - (8 * blockSize))/2;
        point8 = canvas.getWidth();




        canvas.drawRect(border1, white);
        canvas.drawRect(border2, white);
        canvas.drawRect(buttonLeft, red);
        canvas.drawRect(buttonRight, red);
        canvas.drawRect(buttonJump,red);
        canvas.drawRect(buttonFireball, red);

        hud = new Display(point4, point7);
        player = new Mario(canvas.getWidth(), canvas.getHeight(), blockSize, map, hud);


        hud.draw(canvas);
        player.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int pointerIndex = event.getActionIndex();

        int pointerId = event.getPointerId(pointerIndex);


        float eventX = event.getX(pointerIndex);
        float eventY = event.getY(pointerIndex);

        int action = event.getActionMasked();


        switch (action){
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN: {




                if (0 < eventX && eventX < point4 && point2 < eventY && eventY < point3){
                    left = true;
                }
                else if (point4 < eventX && eventX < point5 && point2 < eventY && eventY < point3) {
                    right = true;
                }
                else if(point6 < eventX && eventX < point7 && point2 < eventY && eventY < point3){
                    jump = true;
                }
                else if(point6 < eventX && eventX < point8 && point2 < eventY && eventY < point3){
                    fireball = true;
                }


                break;
            }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP: {


                if (0 < eventX && eventX < point4 && point2 < eventY && eventY < point3){
                    left = false;
                }
                else if (point4 < eventX && eventX < point5 && point2 < eventY && eventY < point3){
                    right = false;
                }
                else if(point6 < eventX && eventX < point7 && point2 < eventY && eventY < point3){
                    jump = false;
                }
                else if(point6 < eventX && eventX < point8 && point2 < eventY && eventY < point3){
                    fireball = false;
                }

                break;
            }
            default:
                return false;

        }
        invalidate();

        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    public void determineBlockSize(Canvas canvas){


        this.blockSize = (canvas.getHeight())/maxBlockSize;

    }




}
