package eecs40.assignment4;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MarioThread extends Thread {
    private SurfaceHolder surfaceHolder;
    public static Canvas canvas;


    private GamePanel gamePanel;

    private boolean testing;
    private boolean running;
    private double average_FPS;
    public static final int MAX_FPS = 30;




    public MarioThread(SurfaceHolder surfaceHolder, GamePanel gamePanel, boolean testing) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

        try{
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder){
                this.gamePanel.firstDraw(canvas);

            }
        } catch (Exception e) {

            if(testing) {
                System.out.println("exception after update/draw");
            }

            e.printStackTrace();
        } finally{
            if (!(canvas == null)){
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }catch(Exception e) {e.printStackTrace();}

            }
        }

    }


    public void setRunning(boolean running){
        this.running = running;
    }

    @Override
    public void run(){
        int frames= 0;
        long totalTime = 0;
        long maxTime = 1000/MAX_FPS;
        long startTime;
        long timeMillis;
        long waitTime;




        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);

                }
            } catch (Exception e) {

                if(testing) {
                    System.out.println("exception after update/draw");
                }

                e.printStackTrace();
            } finally{
                if (!(canvas == null)){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e) {e.printStackTrace();}

                }
            }

            timeMillis = (System.nanoTime() - startTime)/ 1000000;
            waitTime = maxTime - timeMillis;
            try{
                if(waitTime > 0){

                    if(testing) {
                        System.out.println("waiting");
                    }
                    this.sleep(waitTime);

                }
            } catch (Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frames++;
            if(frames != MAX_FPS){

                if(testing) {
                    System.out.println("Frames = " + frames);
                }


                continue;
            }
            else{
                average_FPS = 1000/((totalTime/frames)/1000000);
                frames = 0;
                totalTime = 0;
            }
        }
    }
}
