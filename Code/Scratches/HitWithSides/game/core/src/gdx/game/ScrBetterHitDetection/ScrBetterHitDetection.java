package gdx.game.ScrBetterHitDetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
 
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import gdx.game.GamTerarria;

public class ScrBetterHitDetection implements Screen, InputProcessor {

    GamTerarria gamTerarria;

    SpriteBatch batch;
    SpriteWithSidesPackageSpecific sprAlGore;
    SpriteWithSidesPackageSpecific sprWall;
    Texture txWall, txHero;
    boolean[] bKeys = new boolean[4]; //0 is w, 1 is d, 2 is s, 3 is a

    int nHitResult = 0;

    public ScrBetterHitDetection(GamTerarria game) { 
        gamTerarria = game;
        
        batch = new SpriteBatch();
        txWall = new Texture("Wall.png");
        txHero = new Texture("alGore.png");
        sprWall = new SpriteWithSidesPackageSpecific(txWall, 300, 150, 0.5, 0, 0, 50 , 50);

        sprAlGore = new SpriteWithSidesPackageSpecific(txHero, 300, 200, 0.5, 0, 8, 50, 100);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (bKeys[0] == true) {
            sprAlGore.jump();
        }
        if (bKeys[1] == true) {
            sprAlGore.changeDir(3);
        } else if (bKeys[3] == true) {
            sprAlGore.changeDir(-3);
        } else {
            sprAlGore.changeDir(0);
        }
        
        sprAlGore.gravity();
        sprAlGore.moveLeftRight();
        sprAlGore.moveSides();
        
        if (isHit(sprAlGore, sprWall)) {
            nHitResult = sprAlGore.hitTest(sprWall);
            if (nHitResult == 0) {
                sprAlGore.nNumJumps = 2;
                sprAlGore.goBackUpDown();
                sprAlGore.dVel = 0;
                sprAlGore.clipToTop(sprWall);
                
            } else if (nHitResult == 1) {
                sprAlGore.goBackUpDown();
                sprAlGore.dVel = 0;
                sprAlGore.clipToBot(sprWall);
            } else if (nHitResult == 2) {
                sprAlGore.goBackLeftRight();
                sprAlGore.dSpeed = 0;
            }
        }
        if (sprAlGore.dY <= 0) {
            sprAlGore.nNumJumps = 2;
            sprAlGore.goBackUpDown();
            sprAlGore.sideTop.goBackUpDown();
            sprAlGore.sideBot.goBackUpDown();
            sprAlGore.sideLeft.goBackUpDown();
            sprAlGore.sideRight.goBackUpDown();

        }
        batch.begin();
        batch.draw(txHero, Math.round((float) sprAlGore.dX), Math.round((float) sprAlGore.dY), Math.round(sprAlGore.dWidth), Math.round(sprAlGore.dHeight));
        batch.draw(txWall, Math.round((float) sprWall.dX), Math.round((float) sprWall.dY), Math.round(sprWall.dWidth), Math.round(sprWall.dHeight));
        batch.draw(txWall, Math.round((float) sprAlGore.sideTop.dX), Math.round((float) sprAlGore.sideTop.dY), Math.round((float) sprAlGore.sideTop.dW),Math.round((float) sprAlGore.sideTop.dH)); //sides
        batch.draw(txWall, Math.round((float) sprAlGore.sideBot.dX), Math.round((float) sprAlGore.sideBot.dY), Math.round((float) sprAlGore.sideBot.dW),Math.round((float) sprAlGore.sideBot.dH)); //sides
        batch.draw(txWall, Math.round((float) sprAlGore.sideRight.dX), Math.round((float) sprAlGore.sideRight.dY), Math.round((float) sprAlGore.sideRight.dW),Math.round((float) sprAlGore.sideRight.dH)); //sides
        batch.draw(txWall, Math.round((float) sprAlGore.sideLeft.dX), Math.round((float) sprAlGore.sideLeft.dY), Math.round((float) sprAlGore.sideLeft.dW),Math.round((float) sprAlGore.sideLeft.dH)); //sides
        
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
        if (keycode == Input.Keys.W) {
            bKeys[0] = true;
        }
        if (keycode == Input.Keys.D) {
            bKeys[1] = true;
        }
        if (keycode == Input.Keys.S) {
            bKeys[2] = true;
        }
        if (keycode == Input.Keys.A) {
            bKeys[3] = true;
        }
        
        
        if(keycode == Input.Keys.UP){
            System.out.println("KILL ME");
            gamTerarria.nScreen++;
            gamTerarria.updateState(gamTerarria.nScreen);
        }
        if(keycode == Input.Keys.DOWN){
            System.out.println("Screw life");
            gamTerarria.nScreen--;
            gamTerarria.updateState(gamTerarria.nScreen);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            bKeys[0] = false;
        }
        if (keycode == Input.Keys.D) {
            bKeys[1] = false;
        }
        if (keycode == Input.Keys.S) {
            bKeys[2] = false;
        }
        if (keycode == Input.Keys.A) {
            bKeys[3] = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        sprAlGore.reset();
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

    public static boolean isHit(SpriteWithSidesPackageSpecific spr1, SpriteWithSidesPackageSpecific spr2) {
        setXY(spr1, Math.round(spr1.getX()), Math.round(spr1.getY()));
        setXY(spr2, Math.round(spr2.getX()), Math.round(spr2.getY()));
        return spr1.retRect().overlaps(spr2.retRect()); 
    }

}
