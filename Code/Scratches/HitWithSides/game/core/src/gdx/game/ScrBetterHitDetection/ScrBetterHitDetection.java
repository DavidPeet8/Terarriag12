package gdx.game.ScrBetterHitDetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
 
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.*;
import java.util.ArrayList;

public class ScrBetterHitDetection implements Screen, InputProcessor {

    GamTerarria game;

    SpriteBatch batch;
    Sprite2 sprVlad;
    Sprite2 sprWall;
    Texture txSheet, txTemp, txOne, txWall, txHero;
    Animation araniVlad[];
    //boolean bKeys[];
    boolean[] bKeys = new boolean[4]; //0 is w, 1 is d, 2 is s, 3 is a

    TextureRegion trTemp; // a single temporary texture region

    int fW, fH, fSx, fSy; // height and width of SpriteSheet image - and the starting upper coordinates on the Sprite Sheet
    int nFrame, nPos;
    int nX, nY, nXWall, nYWall;
    int nSpeed;
    int nUpDown, nLeftRight;
    float fTime;
    boolean canJump;
    double dVel;
    double dVelLimit = 10;
    double dAccel = 1;
    int nHitResult = 0;

    public ScrBetterHitDetection(GamTerarria game) {  //Referencing the main class.
        this.game = game;
        
        fTime = 0;
        nXWall = 300;
        nYWall = 100;
        nSpeed = 5;
        nFrame = 0;
        nPos = 0; // the position in the SpriteSheet - 0 to 7
        araniVlad = new Animation[8];
        batch = new SpriteBatch();
        txSheet = new Texture("Vlad.png");
        txWall = new Texture("Wall.png");
        txHero = new Texture("alGore.png");
        sprWall = new Sprite2(txWall, 300, 150, fW, fH, 0.5, 0, 0, 50 , 50);
        //sprWall.setPosition(300, 100);
        fW = txHero.getWidth();
        fH = txHero.getHeight();
        //System.out.println(fW + " " + fH);

        sprVlad = new Sprite2(txHero, 300, 200, fW, fH, 0.5, 0, 8, 50, 100);

        //sprVlad.setPosition(300, 800);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        System.out.println(game.nScreen);
        
        //trTemp = araniVlad[nPos].getKeyFrame(nFrame, true);
        //sprVlad.gravity();
        if (bKeys[0] == true) {
            sprVlad.jump();
        }
        if (bKeys[1] == true) {
            sprVlad.changeDir(3);
        } else if (bKeys[3] == true) {
            sprVlad.changeDir(-3);
        } else {
            sprVlad.changeDir(0);
        }
        //System.out.println(bKeys[1]);
        
        sprVlad.gravity();
        sprVlad.moveLeftRight();
        sprVlad.moveSides();
        
        if (isHit(sprVlad, sprWall)) {
            /*sprVlad.goBack();
            sprVlad.dVel =  0;*/
            nHitResult = sprVlad.hitTest(sprWall);
            if (nHitResult == 0) {
                sprVlad.nNumJumps = 2;
                sprVlad.goBackUpDown();
                sprVlad.dVel = 0;
                sprVlad.clipToTop(sprWall);
                
            } else if (nHitResult == 1) {
                sprVlad.goBackUpDown();
                sprVlad.dVel = 0;
                sprVlad.clipToBot(sprWall);
            } else if (nHitResult == 2) {
                sprVlad.goBackLeftRight();
                sprVlad.dSpeed = 0;
            }
        }
        if (sprVlad.dY <= 0) {
            sprVlad.nNumJumps = 2;
            sprVlad.goBackUpDown();
            sprVlad.sideTop.goBackUpDown();
            sprVlad.sideBot.goBackUpDown();
            sprVlad.sideLeft.goBackUpDown();
            sprVlad.sideRight.goBackUpDown();

        }
        //dVel = gravity(sprVlad, sprWall, dVel, dVelLimit, dAccel);
        //sprVlad.gravity(); //may be called in canJump
        //sprVlad.setPosition(Math.round(sprVlad.dX), Math.round(sprVlad.dY));
        //System.out.println("y" + sprVlad.getY());
        //vJump(sprVlad, sprWall);
        //System.out.println(sprVlad.getY());
        batch.begin();
        batch.draw(txHero, Math.round((float) sprVlad.dX), Math.round((float) sprVlad.dY), Math.round(sprVlad.dW), Math.round(sprVlad.dH));
        batch.draw(txWall, Math.round((float) sprWall.dX), Math.round((float) sprWall.dY), Math.round(sprWall.dW), Math.round(sprWall.dH));
        batch.draw(txWall, Math.round((float) sprVlad.sideTop.dX), Math.round((float) sprVlad.sideTop.dY), Math.round((float) sprVlad.sideTop.dW),Math.round((float) sprVlad.sideTop.dH)); //sides
        batch.draw(txWall, Math.round((float) sprVlad.sideBot.dX), Math.round((float) sprVlad.sideBot.dY), Math.round((float) sprVlad.sideBot.dW),Math.round((float) sprVlad.sideBot.dH)); //sides
        batch.draw(txWall, Math.round((float) sprVlad.sideRight.dX), Math.round((float) sprVlad.sideRight.dY), Math.round((float) sprVlad.sideRight.dW),Math.round((float) sprVlad.sideRight.dH)); //sides
        batch.draw(txWall, Math.round((float) sprVlad.sideLeft.dX), Math.round((float) sprVlad.sideLeft.dY), Math.round((float) sprVlad.sideLeft.dW),Math.round((float) sprVlad.sideLeft.dH)); //sides
        
        batch.draw(txHero, Math.round((float) sprWall.sideTop.dX), Math.round((float) sprWall.sideTop.dY), Math.round((float) sprWall.sideTop.dW),Math.round((float) sprWall.sideTop.dH)); //sides
        batch.draw(txHero, Math.round((float) sprWall.sideBot.dX), Math.round((float) sprWall.sideBot.dY), Math.round((float) sprWall.sideBot.dW),Math.round((float) sprWall.sideBot.dH)); //sides
        batch.draw(txHero, Math.round((float) sprWall.sideRight.dX), Math.round((float) sprWall.sideRight.dY), Math.round((float) sprWall.sideRight.dW),Math.round((float) sprWall.sideRight.dH)); //sides
        batch.draw(txHero, Math.round((float) sprWall.sideLeft.dX), Math.round((float) sprWall.sideLeft.dY), Math.round((float) sprWall.sideLeft.dW),Math.round((float) sprWall.sideLeft.dH)); //sides
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        txHero.dispose();
        txWall.dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        //System.out.println("keydown");
        if (keycode == Input.Keys.W) {
            bKeys[0] = true;
                //sprVlad.jump();
        }
        if (keycode == Input.Keys.D) {
            bKeys[1] = true;
                //sprVlad.jump();
        }
        if (keycode == Input.Keys.S) {
            bKeys[2] = true;
                //sprVlad.jump();
        }
        if (keycode == Input.Keys.A) {
            bKeys[3] = true;
                //sprVlad.jump();
        }
        
        /*
         if (keycode == Keys.A) {
         nLeftRight = 1;
         } else if (keycode == Keys.D) {
         nLeftRight = 2;
         }*/
        
        
        if(keycode == Input.Keys.UP){
            System.out.println("KILL ME");
            game.nScreen++;
            game.updateState(game.nScreen);
        }
        if(keycode == Input.Keys.DOWN){
            System.out.println("Screw life");
            game.nScreen--;
            game.updateState(game.nScreen);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        nFrame = 0;
        //System.out.println("oh");
        if (keycode == Input.Keys.W) {
            bKeys[0] = false;
                //sprVlad.jump();
        }
        if (keycode == Input.Keys.D) {
            bKeys[1] = false;
                //sprVlad.jump();
        }
        if (keycode == Input.Keys.S) {
            bKeys[2] = false;
                //sprVlad.jump();
        }
        if (keycode == Input.Keys.A) {
            bKeys[3] = false;
                //sprVlad.jump();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        sprVlad.reset();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
    public static void setXY(Sprite spr, int nX, int nY) {
        spr.setX(nX);
        spr.setY(nY);
    }

    public static boolean isHit(Sprite2 spr1, Sprite2 spr2) {
        setXY(spr1, Math.round(spr1.getX()), Math.round(spr1.getY()));
        setXY(spr2, Math.round(spr2.getX()), Math.round(spr2.getY()));
        return spr1.retRect().overlaps(spr2.retRect()); 
    }

}
