package gdx.game.ScrArrayHitDetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import gdx.game.commonclasses.*;
import java.util.ArrayList;

public class ScrArrayHitDetection implements Screen, InputProcessor {

    Game gam;

    SpriteBatch batch;
    SpriteWithSides sprVlad;
    SpriteWithSides sprWall;
    Texture txTemp, txOne, txWall, txHero;
    //boolean bKeys[];
    boolean[] bKeys = new boolean[4]; //0 is w, 1 is d, 2 is s, 3 is a
    SpriteWithSides[][] arWalls = new SpriteWithSides[10][10];

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
    boolean bHitLeftRight = false;
    boolean bHitUp = false;
    boolean bHitDown = false;
    boolean bHitBotBot = false;
    SpriteWithSides lastWallUp;
    SpriteWithSides lastWallDown;

    public ScrArrayHitDetection(Game gam) {  //Referencing the main class.
        this.gam = gam;

        Gdx.input.setInputProcessor((this));
        fTime = 0;
        nXWall = 300;
        nYWall = 100;
        nSpeed = 5;
        nFrame = 0;
        nPos = 0; // the position in the SpriteSheet - 0 to 7
        batch = new SpriteBatch();
        txWall = new Texture("Wall.png");
        txHero = new Texture("alGore.png");
        fW = txHero.getWidth();
        fH = txHero.getHeight();
        //System.out.println(fW + " " + fH);

        sprVlad = new SpriteWithSides(txHero, 300, 700, fW, fH, 0.5, 0, 8, 50, 100);
        sprVlad.init();
        //sprVlad.setPosition(300, 700);
        //sprVlad.setSize((float) sprVlad.dW, (float) sprVlad.dH);

        for (int i = 0; i < 10; i++) {
            arWalls[0][i] = new SpriteWithSides(txWall, i * 50 + 300, 0, 0, 0, 0, 0, 0, 50, 50);
            arWalls[0][i].init();
            // arWalls[0][i].setSize((float) arWalls[0][i].dW, (float) arWalls[0][i].dH);
        }
        for (int i = 0; i < 10; i++) {
            arWalls[1][i] = new SpriteWithSides(txWall, i * 50 + 700, 50, 0, 0, 0, 0, 0, 50, 50);
            arWalls[1][i].init();
            //arWalls[1][i].setSize((float) arWalls[0][i].dW, (float) arWalls[0][i].dH);
        }
        for (int i = 0; i < 10; i++) {
            arWalls[3][i] = new SpriteWithSides(txWall, i * 50 + 700, 100, 0, 0, 0, 0, 0, 50, 50);
            arWalls[3][i].init();
            //arWalls[1][i].setSize((float) arWalls[0][i].dW, (float) arWalls[0][i].dH);
        }
        for (int i = 0; i < 10; i++) {
            arWalls[4][i] = new SpriteWithSides(txWall, i * 50 + 700, 150, 0, 0, 0, 0, 0, 50, 50);
            arWalls[4][i].init();
            //arWalls[1][i].setSize((float) arWalls[0][i].dW, (float) arWalls[0][i].dH);
        }
        for (int i = 0; i < 10; i++) {
            arWalls[2][i] = new SpriteWithSides(txWall, i * 50 + 700, 200, 0, 0, 0, 0, 0, 50, 50);
            arWalls[2][i].init();
            //arWalls[1][i].setSize((float) arWalls[0][i].dW, (float) arWalls[0][i].dH);
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

        /*if (isHit(sprVlad, sprWall)) {
            System.out.println("uhhh what happened");
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
            System.out.println(nHitResult);
        }*/
        if (sprVlad.getY() <= 0) {
            sprVlad.jumpReset(1);
            sprVlad.goBackUpDown();
        }
        //dVel = gravity(sprVlad, sprWall, dVel, dVelLimit, dAccel);
        //sprVlad.gravity(); //may be called in canJump
        //sprVlad.setPosition(Math.round(sprVlad.dX), Math.round(sprVlad.dY));
        //System.out.println("y" + sprVlad.getY());
        //vJump(sprVlad, sprWall);
        //System.out.println(sprVlad.getY());

        batch.begin();

        //sprWall.draw(batch);
        bHitUp = false;
        bHitDown = false;
        bHitLeftRight = false;
        bHitBotBot = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arWalls[j][i] != null) {
                    //arWalls[j][i].setX((float) arWalls[j][i].dX);
                    //arWalls[j][i].setY((float) arWalls[j][i].dY);
                    arWalls[j][i].draw(batch);
                    if (isHit(sprVlad, arWalls[j][i])) {
                        sprVlad.hitTest(arWalls[j][i]);
                    }
                }
            }
        }
                sprVlad.draw(batch);

        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arWalls[j][i] != null) {
                    //arWalls[j][i].setX((float) arWalls[j][i].dX);
                    //arWalls[j][i].setY((float) arWalls[j][i].dY);
                    arWalls[j][i].draw(batch);
                    if (isHit(sprVlad, arWalls[j][i])) {
                        sprVlad.hitTestUpDown(arWalls[j][i]);
                    }
                }
            }
        }*/
        /*if (bHitLeftRight) {
            sprVlad.goBackLeftRight();
            sprVlad.xSpeedReset();
        } 
        if (bHitUp && !bHitBotBot) {
            sprVlad.jumpReset(1);
            sprVlad.goBackUpDown();

            //sprVlad.yVelReset();
            sprVlad.clipToTop(lastWallUp);
        } 
        if (bHitDown ) {
            sprVlad.goBackUpDown();
            sprVlad.yVelReset();
            sprVlad.clipToBot(lastWallDown);
        }*/

        //sprVlad.setX((float) sprVlad.dX);
        //sprVlad.setY((float) sprVlad.dY);
        //sprWall.setX((float) sprWall.dX);
        //sprWall.setY((float) sprWall.dY);
     //   sprVlad.draw(batch);
        //batch.draw(txHero, Math.round((float) sprVlad.dX), Math.round((float) sprVlad.dY), Math.round(sprVlad.dW), Math.round(sprVlad.dH));
        //batch.draw(txWall, Math.round((float) sprWall.dX), Math.round((float) sprWall.dY), Math.round(sprWall.dW), Math.round(sprWall.dH));
        //batch.draw(txWall, Math.round((float) sprVlad.sideTop.dX), Math.round((float) sprVlad.sideTop.dY), Math.round((float) sprVlad.sideTop.dW), Math.round((float) sprVlad.sideTop.dH)); //sides
        //batch.draw(txWall, Math.round((float) sprVlad.sideBot.dX), Math.round((float) sprVlad.sideBot.dY), Math.round((float) sprVlad.sideBot.dW), Math.round((float) sprVlad.sideBot.dH)); //sides
        //batch.draw(txWall, Math.round((float) sprVlad.sideRight.dX), Math.round((float) sprVlad.sideRight.dY), Math.round((float) sprVlad.sideRight.dW), Math.round((float) sprVlad.sideRight.dH)); //sides
        //batch.draw(txWall, Math.round((float) sprVlad.sideLeft.dX), Math.round((float) sprVlad.sideLeft.dY), Math.round((float) sprVlad.sideLeft.dW), Math.round((float) sprVlad.sideLeft.dH)); //sides
//        
        //batch.draw(txHero, Math.round((float) sprWall.sideTop.dX), Math.round((float) sprWall.sideTop.dY), Math.round((float) sprWall.sideTop.dW),Math.round((float) sprWall.sideTop.dH)); //sides
        //batch.draw(txHero, Math.round((float) sprWall.sideBot.dX), Math.round((float) sprWall.sideBot.dY), Math.round((float) sprWall.sideBot.dW),Math.round((float) sprWall.sideBot.dH)); //sides
        //batch.draw(txHero, Math.round((float) sprWall.sideRight.dX), Math.round((float) sprWall.sideRight.dY), Math.round((float) sprWall.sideRight.dW),Math.round((float) sprWall.sideRight.dH)); //sides
        //batch.draw(txHero, Math.round((float) sprWall.sideLeft.dX), Math.round((float) sprWall.sideLeft.dY), Math.round((float) sprWall.sideLeft.dW),Math.round((float) sprWall.sideLeft.dH)); //sides

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
            //bKeys[0] = true;
            sprVlad.jump();
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

    public static boolean isHit(SpriteWithSides spr1, SpriteWithSides spr2) {
        setXY(spr1, Math.round(spr1.getX()), Math.round(spr1.getY()));
        setXY(spr2, Math.round(spr2.getX()), Math.round(spr2.getY()));
        //System.out.println(spr1.retRect());
        //System.out.println(spr2.getBoundingRectangle());
        return spr1.retRect().overlaps(spr2.retRect()); //System.out.println("is hit");
    }

}
