package eecs40.assignment4;

import static eecs40.assignment4.Type.COIN;
import static eecs40.assignment4.Type.UNBREAKABLE;
import eecs40.assignment4.Map;

public class Collision {

    private int locationX,locationY,blockSize;


    enum Direction{
        L,R,U,D,UL,UR,DL,DR,N;
    }

    private class Corner{
        int x;
        int distanceX;
        int distanceY;
        int y;
        boolean horizontal;
        boolean vertical;

        Corner(){
            horizontal = false;
            vertical = false;
        }
    }

    private Map map;
    private Mario player;
    private Display hud;
    int total;


    public Collision(Map map, Mario player, int blockSize, Display hud){

        this.hud = hud;
        this.player = player;
        this.map = map;
        this. blockSize = blockSize;
        locationX = map.getLocationX();
        locationY = map.getLocationY();


    }

    public int getTotal() {
        return total;
    }

    public void collisionTest(Direction direction){

        Corner corner1, corner2, corner3, corner4;
        Corner corner5, corner6;

        corner1 = new Corner();
        corner2 = new Corner();
        corner3 = new Corner();
        corner4 = new Corner();
        corner5 = new Corner();
        corner6 = new Corner();

        if(player.isSuperMario() || player.isFireMario()){

            findBlock(player.getLocationX(), player.getLocationY() - blockSize, corner5);
            findBlock(player.getLocationX() + blockSize, player.getLocationY() - blockSize, corner6);
            System.out.println("SUPER/FIRE MARIO");
            printCorner(corner5);
            printCorner(corner6);
        }



        findBlock(player.getLocationX(), player.getLocationY(), corner1);
        findBlock(player.getLocationX() + blockSize, player.getLocationY(), corner4);

        findBlock(player.getLocationX(), player.getLocationY() + blockSize, corner2);
        findBlock(player.getLocationX() + blockSize, player.getLocationY() + blockSize, corner3);

/*
        printCorner(corner1);
        printCorner(corner2);
        printCorner(corner3);
        printCorner(corner4);
*/
        /*left Check: corner 1 && 2*/
        if(direction == Direction.L || direction == Direction.DL || direction == Direction.UL){
            testSturdyBlock(corner1, corner2, true, false, false, false);
        }
        else if(direction == Direction.R || direction == Direction.DR || direction == Direction.UR){
            testSturdyBlock(corner3, corner4, false, true, false, false);
        }
        if(direction == Direction.D || direction == Direction.DL || direction == Direction.DR){
            testSturdyBlock(corner2, corner3, false, false, true, false);
        }
        else if(direction == Direction.U || direction == Direction.UL || direction == Direction.UR){
            testSturdyBlock(corner1, corner4, false, false, false, true);
        }


    }



     public void collectibleTest(){



        Corner corner1, corner2, corner3, corner4;
        corner1 = new Corner();
        corner2 = new Corner();
        corner3 = new Corner();
        corner4 = new Corner();

        findBlock(player.getLocationX(), player.getLocationY(), corner1);
        findBlock(player.getLocationX(), player.getLocationY() + blockSize, corner2);
        findBlock(player.getLocationX() + blockSize, player.getLocationY() + blockSize, corner3);
        findBlock(player.getLocationX() + blockSize, player.getLocationY(), corner4);


        testCollectibleBlock(corner1);
        testCollectibleBlock(corner2);
        testCollectibleBlock(corner3);
        testCollectibleBlock(corner4);


    }

    public void testCollectibleBlock(Corner corner){
        int holdx, holdy;
        holdx = map.getBlockX(corner.x, corner.y);
        holdy = map.getBlockY(corner.x, corner.y);



        switch (map.getBlockType(corner.x, corner.y, false, false, false, false)) {
            case COIN:
                map.setBlockType(corner.x, corner.y, new Air(holdx,holdy));
                hud.update(200);
                break;
            case MUSHROOM:
                map.setBlockType(corner.x, corner.y, new Air(holdx,holdy));
                hud.update(1000);
                if(!player.isFireMario() && !player.isSuperMario()) {
                    player.setSuperMario(true);
                    player.setTransform(-1);
                }
                break;
            case FLOWER:
                map.setBlockType(corner.x, corner.y, new Air(holdx,holdy));
                hud.update(1000);
                if(!player.isFireMario() && !player.isSuperMario()){
                    player.setSuperMario(true);
                    player.setTransform(-1);
                }

                player.setFireMario(true);
                break;
            case BREAKABLE:
                map.setBlockType(corner.x, corner.y, new Air(holdx,holdy));
            default:
                break;
        }

    }

    public void printCorner(Corner corner){
        System.out.println("INFO:");
        System.out.println("CORNER: " + corner.x + "," + corner.y + " distances: " + corner.distanceX + "," + corner.distanceY);
    }


    public void testSturdyBlock(Corner corner1, Corner corner2, boolean left, boolean right, boolean drop, boolean up){

        int hold;
        Corner corner3 = new Corner();



        /*test drop if distances square lines up*/
        if(drop && corner1.distanceX == 0 && corner2.distanceX == 0 ){
            playergroundLocation(player.getLocationX(),player.getLocationY(),corner3);
        }
        else if((left || right) && corner1.distanceY == 0 && corner2.distanceY == 0 ){

            if(left){
                playerleftLocation(player.getLocationX(),player.getLocationY(),corner3);
            }
            else{
                playerrightLocation(player.getLocationX(),player.getLocationY(),corner3);
            }
        }
        else if(up && corner1.distanceY == 0 && corner2.distanceY == 0 ){
            playertopLocation(player.getLocationX(),player.getLocationY(),corner3);
        }


        if(corner1.distanceY == 0 && corner2.distanceY == 0 ) {
            if (corner3.x > 15) {
                corner3.x = 15;
            } else if (corner3.x < 0) {
                corner3.x = 0;
            }
            if (corner3.y > 15) {
                corner3.y = 15;
            } else if (corner3.y < 0) {
                corner3.y = 0;
            }

            if (!(map.getBlockType(corner3.x, corner3.y, left, right, drop, up) == UNBREAKABLE)) {
                return;
            }
        }

        switch (map.getBlockType(corner1.x, corner1.y, left, right, drop, up)) {
            case UNBREAKABLE:

                if(left){
                    hold = corner1.distanceX;
                    if(corner1.distanceX <= corner2.distanceX){

                        player.setLocationX(player.getLocationX() + hold);
                        return;
                    }
                }
                else if(right){
                    hold = corner1.distanceX;
                    if(corner1.distanceX <= corner2.distanceX){

                        player.setLocationX(player.getLocationX() - hold);
                        return;
                    }
                }
                else if(drop){
                    hold = corner1.distanceY;
                    if(corner1.distanceY <= corner2.distanceY){
                        player.setLocationY(player.getLocationY() - hold);
                        player.setTransition(false);

                        return;
                    }
                }
                else{
                    hold = corner1.distanceY;
                    if(corner1.distanceY <= corner2.distanceY){
                        player.setLocationY(player.getLocationY() + hold);
                        player.setFalling(player.getJumping());
                        player.setJumping(0);
                        player.setTransition(true);
                        return;
                    }
                }
                break;
            default:
                break;
        }

        switch ((map.getBlockType(corner2.x, corner2.y, left, right, drop, up))) {
            case UNBREAKABLE:

                if(left){
                    hold = corner2.distanceX;
                    player.setLocationX(player.getLocationX() + hold);
                    return;
                }
                else if(right){
                    hold = corner2.distanceX;
                    player.setLocationX(player.getLocationX() - hold);
                    return;
                }
                else if(drop){
                    hold = corner2.distanceY;

                    player.setLocationY(player.getLocationY() - hold);
                    player.setTransition(false);
                    return;
                }
                else{
                    hold = corner2.distanceY;
                    player.setLocationY(player.getLocationY() + hold);
                    player.setFalling(player.getJumping());
                    player.setJumping(0);
                    player.setTransition(true);

                    return;
                }

            default:
                if((map.getBlockType(corner1.x, corner1.y, left, right, drop, up) == UNBREAKABLE)) {



                    if (left) {
                        hold = corner1.distanceX;
                        player.setLocationX(player.getLocationX() + hold);

                        return;

                    } else if (right) {
                        hold = corner1.distanceX;
                        player.setLocationX(player.getLocationX() - hold);
                        return;
                    } else if (drop) {
                        hold = corner1.distanceY;
                        player.setLocationY(player.getLocationY() - hold);
                        player.setTransition(false);
                        return;

                    } else {
                        hold = corner1.distanceY;
                        player.setLocationY(player.getLocationY() + hold);
                        player.setFalling(player.getJumping());
                        player.setJumping(0);
                        player.setTransition(true);
                        return;

                    }
                }
                break;
        }


        return;
    }

    public boolean checkToFall(){
        Corner corner1, corner2, corner3;

        corner1 = new Corner();
        corner2 = new Corner();
        corner3 = new Corner();

        findBlock(player.getLocationX(), player.getLocationY() + blockSize + 1, corner1);
        findBlock(player.getLocationX() + blockSize, player.getLocationY() + blockSize + 1, corner2);


        playergroundLocation(player.getLocationX(),player.getLocationY(),corner3);
        if(corner3.x >= 15){
                corner3.x = 15;
        }
        if(corner3.y >=15){
            corner3.y = 14;
        }

        if(!(map.getBlockType(corner1.x, corner1.y, false, false, true, false) == UNBREAKABLE) && !(map.getBlockType(corner2.x, corner2.y, false, false, true, false) == UNBREAKABLE)){

            return true;
        }
        else if(corner1.distanceX == 0 && corner2.distanceX == 0 && !(map.getBlockType(corner3.x, corner3.y, false, false, true, false) == UNBREAKABLE)){

            return true;
        }
        else{

            return false;
        }
    }

    public void playergroundLocation (int x, int y, Corner corner){
        x = x + blockSize/2;
        y = (y + blockSize);
        findBlock(x, y, corner);
    }

    public void playerleftLocation (int x, int y, Corner corner){
        x = (x );
        y = (y +  blockSize/2);
        findBlock(x, y, corner);
    }

    public void playerrightLocation(int x, int y, Corner corner){
        x = (x + blockSize);
        y = (y + blockSize/2);
        findBlock(x, y, corner);
    }

    public void playertopLocation(int x, int y, Corner corner){
        x = (x + blockSize/2);
        y = (y );
        findBlock(x, y, corner);
    }



    public void findBlock(int x, int y, Corner corner) {

        int k;
        int hold1, hold2, maxHold;
        int line;
        int maxBlockX;

        x -= locationX;
        y -= locationY;

        hold1 = 0;
        hold2 = map.getOffSet();
        if(hold2 == 0){
            hold2 = blockSize;
        }
        maxHold = 16 * blockSize;
        line = x;
        maxBlockX = 16 + map.getBlockChange();

        if (line < 0){
            line = 0;
        }



//FIND CORNER X
        for (k = 0; k < maxBlockX; k++) {
            //line is not parallel to the sides
            if (hold1 < line && line < hold2) {

                //in one block
                corner.x = k;
                if(line - hold1 < hold2 - line){
                    corner.distanceX = line - hold1;
                }
                else{
                    corner.distanceX = hold2 - line;
                }
                break;
            }
            else if (line == hold1 || line == hold2) {


                corner.x = k;
                corner.vertical = true;
                corner.distanceX = 0;
                break;

            }
            else {
                hold1 = hold2;
                hold2 += blockSize;
                if(hold2 > maxHold){
                    hold2 = maxHold;
                }
            }
        }

        hold1 = 0;
        hold2 = blockSize;
        line = y;

//FIND CORNER Y
        for (k = 0; k < 16; k++) {
            //line is not parallel to the sides
            if (hold1 < line && line < hold2) {
                //in one block
                corner.y = k;
                if(line - hold1 < hold2 - line){
                    corner.distanceY = line - hold1;
                }
                else{
                    corner.distanceY = hold2 - line;
                }
                break;
            }
            else if (line == hold1 || line == hold2) {
                corner.y = k;
                corner.horizontal = true;
                corner.distanceY = 0;
                break;

            }
            else {
                hold1 = hold2;
                hold2 += blockSize;
            }
        }
    }



    public boolean logicXOR(boolean a, boolean b){
        if(a == b){
            return false;
        }
        else{
            return true;
        }
    }
}






