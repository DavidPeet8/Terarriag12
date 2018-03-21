package gdx.game.ScrDiscreteDetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import gdx.game.commonclasses.*;

public class ScrDiscreteHitDetection implements Screen, InputProcessor {

    Game gam;

    SpriteBatch batch;
    SpriteDiscrete sprAlGore;
    Texture txWall, txHero;
    boolean[] bKeys = new boolean[4]; //0 is w, 1 is d, 2 is s, 3 is a
    SpriteExtended[][] arWalls = new SpriteExtended[10][10];


    int fW, fH;
    int nXWall, nYWall;
    
    public ScrDiscreteHitDetection(Game gam) {  //Referencing the main class.
        this.gam = gam;

        Gdx.input.setInputProcessor((this));
        nXWall = 300;
        nYWall = 100;
        batch = new SpriteBatch();
        txWall = new Texture("Wall.png");
        txHero = new Texture("alGore.png");
        fW = txHero.getWidth();
        fH = txHero.getHeight();

        sprAlGore = new SpriteDiscrete(txHero, 300, 700, 0.5, 0, 8, 50, 100);
        sprAlGore.init();

        for (int i = 0; i < 10; i++) {
            arWalls[0][i] = new SpriteExtended(txWall, i * 50 + 300, 0, 50, 50);
            arWalls[0][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[1][i] = new SpriteExtended(txWall, i * 50 + 700, 50, 50, 50);
            arWalls[1][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[3][i] = new SpriteExtended(txWall, i * 50 + 700, 100,50, 50);
            arWalls[3][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[4][i] = new SpriteExtended(txWall, i * 50 + 700, 150, 50, 50);
            arWalls[4][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[2][i] = new SpriteExtended(txWall, i * 50 + 700, 200, 50, 50);
            arWalls[2][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[5][i] = new SpriteExtended(txWall, i * 50 + 700, 600, 50, 50);
            arWalls[5][i].init();
        }
        

    }

    @Override
    public void show() {

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

        sprAlGore.move(arWalls);

        /*if (sprAlGore.getY() <= 0) {
            sprAlGore.jumpReset(1);
            sprAlGore.goBackUpDown();
        }*/

        batch.begin();

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (arWalls[j][i] != null) {
                    arWalls[j][i].draw(batch);
                }
            }
        }
        sprAlGore.draw(batch);

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
            sprAlGore.jump();
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

}
