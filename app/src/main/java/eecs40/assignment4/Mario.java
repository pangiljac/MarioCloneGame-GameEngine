package eecs40.assignment4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Mario implements MarioObjects {

    private Rect player;
    private Paint black, red;
    private int minSceneX, minSceneY, maxSceneX, maxSceneY;
    private int locationX, locationY;
    private int blockSize;
    int jumping;
    int falling;
    boolean transition ;
    private Map map;
    private Collision collision;
    private Display hud;
    private int maxX;

    private boolean superMario;
    private boolean fireMario;
    private int transform;
    private int charging;
    private boolean facing;
    private int invulnerability;

    //false = left, true = right

    private ArrayList<Fireball> liveFireballs;

    public ArrayList<Fireball> getLiveFireballs() {
        return liveFireballs;
    }

    public void setTransform(int transform) {
        this.transform = transform;
    }

    public boolean isFireMario() {
        return fireMario;
    }

    public boolean isSuperMario() {
        return superMario;
    }

    public void setSuperMario(boolean superMario) {
        this.superMario = superMario;
    }

    public void setFireMario(boolean fireMario) {
        this.fireMario = fireMario;
    }

    public void setFalling(int falling) {
        this.falling = falling;
    }

    public boolean getTransition(){
        return transition;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getJumping() {
        return jumping;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public void setTransition(boolean transition){
        this.transition = transition;
    }

    public void setJumping(int jumping) {
        this.jumping = jumping;
    }

    public Mario(int maxX, int maxY, int blockSize, Map map, Display hud){

        jumping = 0;
        falling = 25;
        transition = false;

        player = new Rect();
        player.set(maxX/2 - (8 * blockSize),8 + (14* blockSize),maxX/2 - (7 * blockSize), 8 + (15 * blockSize));
        this.blockSize = blockSize;
        this.map = map;
        black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);

        minSceneX = maxX/2 - (8 * blockSize);
        minSceneY = 8;
        maxSceneX = maxX/2 + (8 * blockSize);
        maxSceneY = maxY;
        this.maxX = maxX;
        this.hud = hud;

        locationX = maxX/2 + (7 * blockSize);
        locationY = 8 + (13* blockSize);

        liveFireballs = new ArrayList<>();

        collision = new Collision(map, this, blockSize, hud);
        invulnerability = 200;

    }

    @Override
    public void draw(Canvas canvas){
        int x, y, holdx, holdy;
        if(fireMario){
            black.setColor(Color.RED);
        }
        else{
            black.setColor(Color.BLACK);
        }
        canvas.drawRect(player, black);

        if(superMario && transform > 0){
            player.set(locationX, locationY - blockSize + (transform * 6) , locationX +blockSize, locationY + blockSize);
        }
        else if (superMario){
            player.set(locationX, locationY -blockSize, locationX +blockSize, locationY+blockSize);
        }
        else{
            player.set(locationX, locationY, locationX + blockSize, locationY + blockSize);
        }

        canvas.drawRect(player, black);

    }

    @Override
    public void update() {

    }

    public void restartMap(){

    }

    public void update(boolean left, boolean right, boolean jump, boolean fireball){

            noScrollUpdate(left, right, jump, fireball);

    }

    public void noScrollUpdate(boolean left, boolean right, boolean jump, boolean fireball){

        map.moveEnemies();

        if(invulnerability < 200 ){
            invulnerability += 1;
        }

        if(superMario && transform > 0){
            transform -= 1;
        }

        if(left && (locationX <= minSceneX + 7*blockSize) && !map.isStopScroll()){
            map.scrollMap(13);
        }
        else if(left && locationX > minSceneX && !map.isStopScroll()){
            locationX -= 13;
            if(locationX < minSceneX + 7*blockSize){
                locationX = minSceneX  + 7*blockSize;
            }
        }
        else if(left && locationX > minSceneX && map.isStopScroll()){
            locationX -= 13;
            if(locationX < minSceneX ){
                locationX = minSceneX ;
            }
        }

        if(right && locationX < maxSceneX - blockSize){
            locationX += 13;
            if(locationX > maxSceneX - blockSize){
                locationX = maxSceneX - blockSize;
            }
        }

        if(left){
            facing = false;
        }
        else if(!left && right){
            facing = true;
        }

        // DID YOU CLICK JUMP AND NOT GOING UP, AND NOT GOING DOWN
        if (jumping == 0 && jump && !transition){
            jumping = 25;
        }

        if(!transition && !jump && jumping == 0 && collision.checkToFall()){
            transition = true;
            falling = 0;
        }
        else if(!collision.checkToFall()){
            transition = false;
        }

        // DID YOU STOP CLICKING JUMP
        if(!jump && jumping > 0){
            falling = jumping;
            jumping = 0;
            transition = true;
        }

        // ARE YOU HOLDING JUMP
        if (jumping > 0 && jump && !transition){

            locationY -= (2 + jumping);
            jumping -= 1;
            if (jumping == 0){
                falling = jumping;
                transition = true;
            }
        }

        if(transition){
            locationY += (2 + falling);
            falling += 1;
            if (falling > 25){
                falling = 25;
            }
        }

        if(fireMario && fireball && charging == 0){
            liveFireballs.add(new Fireball(locationX,locationY,facing,blockSize));
            charging = 30;
        }
        else if(charging > 0){
            charging -= 1;
        }

        if(liveFireballs.size() > 0){
            moveFireballs(left && (locationX <= minSceneX + 7*blockSize) && !map.isStopScroll());
        }




        Collision.Direction direction = Collision.Direction.N;

        if(left && transition){
            System.out.println("DL");
            direction = Collision.Direction.DL;
        }
        else if(left && jumping>0){
            System.out.println("UL");
            direction = Collision.Direction.UL;
        }
        else if(right && transition){
            System.out.println("DR");
            direction = Collision.Direction.DR;
        }
        else if(right && jumping>0){
            System.out.println("UR");
            direction = Collision.Direction.UR;
        }
        else if(left){
            System.out.println("L");
            direction = Collision.Direction.L;
        }
        else if(right){
            System.out.println("R");
            direction = Collision.Direction.R;
        }
        else if(jumping>0){
            System.out.println("U");
            direction = Collision.Direction.U;
        }
        else if(transition){
            System.out.println("D");
            direction = Collision.Direction.D;
        }


        collision.collisionTest(direction);

        collision.collectibleTest();


        if(superMario && transform == -1){
            transform = 10;
        }


        if(invulnerability > 200 && map.collideEnemies(locationX,locationY,true)){
            if(!superMario && !fireMario) {
                map.reloadMap();
                locationX = maxX/2 + (7 * blockSize);
                locationY = 8 + (13* blockSize);
                System.out.println("RESET");
                hud.setLives(hud.getLives() -1);
            }
            else if(fireMario){
                fireMario = false;
            }
            else if(superMario){
                superMario = false;
            }
            invulnerability = 0;

        }

        if(invulnerability <= 200){
            invulnerability += 1;
        }
        if(liveFireballs.size() > 0){
            testFireballs();
        }



        player.set(locationX, locationY, locationX + blockSize, locationY+ blockSize);

        if(map.endofMap(locationX, locationY)){
            map.nextMap();
            locationX = maxX/2 + (7 * blockSize);
            locationY = 8 + (13* blockSize);
            map.setStopScroll(false);
        }

    }

    public int getNumberFireballs(){
        return liveFireballs.size();
    }

    public void drawFireballs (int k, Canvas canvas){
        liveFireballs.get(k).draw(canvas);
    }

    public void testFireballs(){
        for (int k = 0; k < liveFireballs.size(); k++) {
            if(map.collideEnemies(liveFireballs.get(k).getX(), liveFireballs.get(k).getY(), false )){
                liveFireballs.remove(k);

            }
        }

    }

    public void moveFireballs(boolean scrolls){
        for (int k = 0; k < liveFireballs.size(); k++){
            liveFireballs.get(k).forward(scrolls);
        }

    }
}
