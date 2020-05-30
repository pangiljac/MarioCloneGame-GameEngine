package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class Map implements MarioObjects {

    public Blocks[][] map;
    private int blockOffset, blockChange;
    private int offSet = 0;
    private int sceneX, sceneY, blockSize, locationX, locationY;
    private PaintBucket paintBucket;
    private Rect rect;
    private Display hud;
    private int maxMap;
    private boolean stopScroll = false;
    private int extra;

    public void setStopScroll(boolean stopScroll) {
        this.stopScroll = stopScroll;
    }

    public boolean isStopScroll() {
        return stopScroll;
    }

    public Map(int blockSize, int maxX, int maxY) {
        this.blockSize = blockSize;
        this.locationX = maxX / 2 - 8 * blockSize;
        this.locationY = 8;
        rect = new Rect();

        int tempX = locationX - blockSize;
        int tempY = locationY;
        int holdX = tempX;
        int holdY = tempY;

        sceneX = 0;
        sceneY = 8;

        maxMap = 64;
        EnemySpawn holdEnemy;

        paintBucket = new PaintBucket(blockSize);

        map = new Blocks[maxMap][16];
        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < maxMap; x++) {
                tempY = holdY;
                if (x >= maxMap - 17 && x < maxMap) {
                    tempX = holdX;
                } else {
                    tempX = 0;
                }


                if (y == 15) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if ((x == 12 || x == 13 || x == 14 || x == 15) && y == 14) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if ((x == 6 || x == 7 || x == 8) && y == 13) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if (x == 7 & y == 12) {
                    map[x][y] = new Flower(tempX, tempY);
                } else if (x == 13 && y == 13) {
                    map[x][y] = new Mushroom(tempX, tempY);
                } else if ((x == 1 || x == 2 || x == 3) && y == 12) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if ((x == 1 || x == 2 || x == 3) && y == 11) {
                    map[x][y] = new Coin(tempX, tempY);
                } else if((x > 16 && x< 27) && y == 14){
                    map[x][y] = new Coin(tempX, tempY);
                } else if(((x > 16 && x< 20)|| (x > 21 && x < 25) ) && y == 12){
                    map[x][y] = new Breakable(tempX,tempY);
                } else if((x> 30 && x<50) && y==14 ) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if(x == 35 && y == 12){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.BEETLE,blockSize);
                } else if(x>30 && x <45 && y == 13){
                    map[x][y] = new Ground(tempX, tempY);
                } else if (x == 43 & y == 12) {
                    map[x][y] = new Flower(tempX, tempY);
                } else if(x == 55 && y == 10){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.PARATROOPER,blockSize);
                } else if(x == 56 && y == 14){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.PLANT,blockSize);
                } else if(x == 6 && y == 12){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.PLANT,blockSize);
                } else {
                    map[x][y] = new Air(tempX, tempY);
                }

                if (x >= maxMap - 17 && x < maxMap) {
                    holdX += (blockSize);
                    /*System.out.println("x:" + x + "y:" + y + " X:" + map[x][y].getX() + " Y:" + map[x][y].getY());*/
                }


            }
            holdX -= 17 * (blockSize);
            holdY += (blockSize);
        }
    }

    public int getBlockX(int x, int y){

        x += (maxMap -16 - blockOffset - blockChange);
  /*      System.out.println("maxMap:" + maxMap);
        System.out.println("blockOffset:" + blockOffset);*/
        if(x < 0){
            x = 0;
        }
        return map[x][y].getX();
    }

    public int getBlockY(int x, int y){
        /*
        System.out.println("X before:" + x);
        System.out.println("Y before:" + y);
        */
        x += (maxMap - 16 - blockOffset - blockChange);
        if (x < 0) {
            x = 0;
        }
        /*
        System.out.println("X:" + x);
        System.out.println("Y:" + y);
        */
        return map[x][y].getY();
    }

    public Type getBlockType(int x, int y, boolean left, boolean right, boolean up, boolean down){

        x += (maxMap - 16 - blockOffset - blockChange);
        if (x < 0) {
            x = 0;
        }
        return map[x][y].collide(left, right, up, down);
    }

    public void setBlockType(int x, int y, Blocks blocks){
        x += (maxMap - 16 - blockOffset - blockChange);
        if (x < 0) {
            x = 0;
        }
        map[x][y] = blocks;
    }

    public int getBlockChange() {
        return blockChange;
    }

    public int getBlockOffset() {
        return blockOffset;
    }

    public int getOffSet() {
        return offSet;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getSceneX() {
        return sceneX;
    }

    public int getSceneY() {
        return sceneY;
    }

    public void reloadMap(){
        offSet = 0;
        blockOffset = 0;
        int tempX = locationX - blockSize;
        int tempY = locationY;
        int holdX = tempX;
        int holdY = tempY;

        sceneX = 0;
        sceneY = 8;

        maxMap = 64;

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < maxMap; x++) {
                tempY = holdY;
                if (x >= maxMap - 17 && x < maxMap) {
                    tempX = holdX;
                } else {
                    tempX = 0;
                }


                if (y == 15) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if ((x == 12 || x == 13 || x == 14 || x == 15) && y == 14) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if ((x == 6 || x == 7 || x == 8) && y == 13) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if (x == 7 & y == 12) {
                    map[x][y] = new Flower(tempX, tempY);
                } else if (x == 13 && y == 13) {
                    map[x][y] = new Mushroom(tempX, tempY);
                } else if ((x == 1 || x == 2 || x == 3) && y == 12) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if ((x == 1 || x == 2 || x == 3) && y == 11) {
                    map[x][y] = new Coin(tempX, tempY);
                } else if((x > 16 && x< 27) && y == 14){
                    map[x][y] = new Coin(tempX, tempY);
                } else if((x> 30 && x<50) && y==14 ) {
                    map[x][y] = new Ground(tempX, tempY);
                } else if(x == 35 && y == 12){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.BEETLE,blockSize);
                }else if(x>30 && x <45 && y == 13){
                    map[x][y] = new Ground(tempX, tempY);
                } else if (x == 43 & y == 12) {
                    map[x][y] = new Flower(tempX, tempY);
                } else if(x == 55 && y == 10){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.PARATROOPER,blockSize);
                } else if(x == 56 && y == 14){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.PLANT,blockSize);
                } else if(x == 6 && y == 12){
                    map[x][y] = new EnemySpawn(tempX, tempY);
                    map[x][y].setEnemy(Type.PLANT,blockSize);
                } else {
                    map[x][y] = new Air(tempX, tempY);
                }

                if (x >= maxMap - 17 && x < maxMap) {
                    holdX += (blockSize);
                    /*System.out.println("x:" + x + "y:" + y + " X:" + map[x][y].getX() + " Y:" + map[x][y].getY());*/
                }


            }
            holdX -= 17 * (blockSize);
            holdY += (blockSize);
        }


    }

    public void nextMap(){
        extra += 1;
        if(extra == 1){
            offSet = 0;
            blockOffset = 0;
            int tempX = locationX - blockSize;
            int tempY = locationY;
            int holdX = tempX;
            int holdY = tempY;

            sceneX = 0;
            sceneY = 8;

            maxMap = 64;

            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < maxMap; x++) {
                    tempY = holdY;
                    if (x >= maxMap - 17 && x < maxMap) {
                        tempX = holdX;
                    } else {
                        tempX = 0;
                    }


                    if (y == 15) {
                        map[x][y] = new Ground(tempX, tempY);
                    } else if((x == 2 || x == 3 || x ==4) && y== 15){
                        map[x][y] = new Coin(tempX, tempY);
                    }else if ((x > 8 && x < 19) && y == 14) {
                        map[x][y] = new Ground(tempX, tempY);
                    } else if (x == 13 && y == 13) {
                        map[x][y] = new Mushroom(tempX, tempY);
                    } else if((( x < 54 && x > 51) || (x < 49 && x > 46) || (x < 44 && x >41 ) || ( x < 39 && x > 35)) && y == 8){
                        map[x][y] = new Coin(tempX, tempY);
                    } else if(x == 10 && y == 13){
                        map[x][y] = new EnemySpawn(tempX, tempY);
                        map[x][y].setEnemy(Type.BEETLE,blockSize);
                    } else if((( x < 54 && x > 51) || (x < 49 && x > 46) || (x < 44 && x >41 ) || ( x < 39 && x > 35)) && y == 9){
                        map[x][y] = new Ground(tempX, tempY);
                    } else if(( x < 61 && x > 57)  && y == 12){
                        map[x][y] = new Ground(tempX, tempY);
                    }else if (x == 43 & y == 12) {
                        map[x][y] = new Flower(tempX, tempY);
                    } else if((x == 55 || x == 50 || x == 45 || x == 40) && y == 10){
                        map[x][y] = new EnemySpawn(tempX, tempY);
                        map[x][y].setEnemy(Type.PARATROOPER,blockSize);
                    }
                    else if((x == 30 || x == 26 || x == 22) && y == 14){
                        map[x][y] = new EnemySpawn(tempX, tempY);
                        map[x][y].setEnemy(Type.PLANT,blockSize);
                    }
                     else {
                        map[x][y] = new Air(tempX, tempY);
                    }

                    if (x >= maxMap - 17 && x < maxMap) {
                        holdX += (blockSize);
                        /*System.out.println("x:" + x + "y:" + y + " X:" + map[x][y].getX() + " Y:" + map[x][y].getY());*/
                    }


                }
                holdX -= 17 * (blockSize);
                holdY += (blockSize);
            }
        }
        else{

            offSet = 0;
            blockOffset = 0;
            int tempX = locationX - blockSize;
            int tempY = locationY;
            int holdX = tempX;
            int holdY = tempY;

            sceneX = 0;
            sceneY = 8;

            maxMap = 64;

            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < maxMap; x++) {
                    tempY = holdY;
                    if (x >= maxMap - 17 && x < maxMap) {
                        tempX = holdX;
                    } else {
                        tempX = 0;
                    }


                    if (y == 15) {
                        map[x][y] = new Ground(tempX, tempY);
                    }
                    else if((x == 11 || x == 15 || x == 19) && y == 14){
                        map[x][y] = new EnemySpawn(tempX, tempY);
                        map[x][y].setEnemy(Type.PLANT,blockSize);
                    }else if(((x > 20 && x< 25)|| (x > 28 && x < 33) ) && y == 12){
                        map[x][y] = new Breakable(tempX,tempY);
                    }  else if (x == 56 && y == 14) {
                        map[x][y] = new Mushroom(tempX, tempY);
                    } else if ((x == 1 || x == 2 || x == 3 || x == 12 || x==13 || x==14) && y == 12) {
                        map[x][y] = new Ground(tempX, tempY);
                    } else if ((x == 1 || x == 2 || x == 3|| x == 12 || x==13 || x==14) && y == 11) {
                        map[x][y] = new Coin(tempX, tempY);
                    } else if((x > 16 && x< 27) && y == 14){
                        map[x][y] = new Coin(tempX, tempY);
                    }  else if (x == 43 & y == 12) {
                        map[x][y] = new Flower(tempX, tempY);
                    } else if((x == 40 || x== 50 || x == 60) && ( y >9)){
                        map[x][y] = new Ground(tempX, tempY);
                    } else if((x == 47 && y == 14)){
                        map[x][y] = new EnemySpawn(tempX, tempY);
                        map[x][y].setEnemy(Type.BEETLE,blockSize);
                    }else if((x == 53 && y == 14)){
                        map[x][y] = new EnemySpawn(tempX, tempY);
                        map[x][y].setEnemy(Type.BEETLE,blockSize);
                    }else if((x == 26 && y == 14)){
                        map[x][y] = new EnemySpawn(tempX, tempY);
                        map[x][y].setEnemy(Type.BEETLE,blockSize);
                    } else {
                        map[x][y] = new Air(tempX, tempY);
                    }

                    if (x >= maxMap - 17 && x < maxMap) {
                        holdX += (blockSize);
                        /*System.out.println("x:" + x + "y:" + y + " X:" + map[x][y].getX() + " Y:" + map[x][y].getY());*/
                    }


                }
                holdX -= 17 * (blockSize);
                holdY += (blockSize);
            }

        }
    }

    @Override
    public void draw(Canvas canvas) {

        for (int y = 0; y < 16; y++) {
            for (int x = maxMap - 17 - blockOffset- blockChange; x < maxMap - blockOffset; x++) {

                if(x<0){
                    x = 0;
                }

                map[x][y].draw(canvas, rect, paintBucket);

            }
        }
    }

    public void scrollMap(int offSet){



        this.offSet += offSet;
        if (this.offSet >= blockSize){
            this.offSet -= blockSize;

                blockOffset += 1;

            if(maxMap - 17 - blockOffset >= 0) {
                for (int k = 0; k < 16; k++) {
                    map[maxMap - 17 - blockOffset][k].setX(map[maxMap - 17 - blockOffset + 1][k].getX() - blockSize);
                    System.out.println("x:" + (maxMap - 17 - blockOffset) + "y:" + k + " X:" + map[maxMap - 17 - blockOffset][k].getX() + " Y:" + map[maxMap - 17 - blockOffset][k].getY());
                }
            }
        }
        if(this.offSet == 0){
            blockChange = 0;
        }
        else{
            blockChange = 1;
        }


        for (int y = 0; y < 16; y++) {
            for (int x = maxMap - 17 - blockOffset - blockChange; x < maxMap - blockChange ; x++) {

                if(x<0){
                    x = 0;
                }
                map[x][y].setX(map[x][y].getX() + offSet);
            }
        }

        if(map[0][0].getX() > locationX && map[0][0].getX() < locationX +blockSize){
            blockChange = 0;
            this.offSet = 0;

            int tempX = locationX;

            stopScroll = true;

            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < 16; x++) {
                    map[x][y].setX(tempX);
                    tempX += blockSize;
                }
                tempX -= 16 * (blockSize);

            }
        }



    }

    public boolean endofMap(int x, int y){
        if (x < locationX + blockSize){
            return true;
        }
        return false;
    }

    public void moveEnemies(){

        for (int y = 0; y < 16; y++) {
            for (int x = maxMap - 17 - blockOffset - blockChange; x < maxMap - blockChange ; x++) {

                if(x < 0){
                    x = 0;
                }
                if(map[x][y].isContainEnemy()){
                    System.out.println("EnemyUpdate2");
                    map[x][y].update();
                }
            }
        }

    }



    public boolean collideEnemies(int placeX, int placeY, boolean type){

        System.out.println("Collision Test");
        for (int y = 0; y < 16; y++) {
            for (int x = maxMap - 17 - blockOffset - blockChange; x < maxMap - blockChange ; x++) {

                if(x < 0){
                    x = 0;
                }
                if(map[x][y].isContainEnemy()){
                    if(map[x][y].contact(placeX,placeY,type)){
                        if(type){

                            System.out.println("touching enemy");
                            return true;
                        }
                        else {
                            map[x][y] = new Air(map[x][y].getX(), map[x][y].getY());
                            return true;
                        }
                    }

                }
            }
        }


        return false;
    }

  @Override
    public void update() {

    }
}

