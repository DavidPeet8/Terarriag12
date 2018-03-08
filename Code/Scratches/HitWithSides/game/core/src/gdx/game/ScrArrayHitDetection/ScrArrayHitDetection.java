package gdx.game.ScrArrayHitDetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Game;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.*;

public class ScrArrayHitDetection implements Screen, InputProcessor {

    GamTerarria gamTerarria;

    SpriteBatch batch;
    SpriteWithSidesCommon sprAlGore;
    Texture txWall, txHero;
    boolean[] bKeys = new boolean[4]; //0 is w, 1 is d, 2 is s, 3 is a
    SpriteWithSidesCommon[][] arWalls = new SpriteWithSidesCommon[10][10];

    public ScrArrayHitDetection(GamTerarria gam) {
        gamTerarria = gam;
        batch = new SpriteBatch();
        txWall = new Texture("Wall.png");
        txHero = new Texture("alGore.png");

        sprAlGore = new SpriteWithSidesCommon(txHero, 300, 700, 0.5, 0, 8, 50, 100);
        sprAlGore.init();

        for (int i = 0; i < 10; i++) {
            arWalls[0][i] = new SpriteWithSidesCommon(txWall, i * 50 + 300, 0, 0, 0, 0, 50, 50);
            arWalls[0][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[1][i] = new SpriteWithSidesCommon(txWall, i * 50 + 700, 50,  0, 0, 0, 50, 50);
            arWalls[1][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[3][i] = new SpriteWithSidesCommon(txWall, i * 50 + 700, 100, 0, 0, 0, 50, 50);
            arWalls[3][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[4][i] = new SpriteWithSidesCommon(txWall, i * 50 + 700, 150, 0, 0, 0, 50, 50);
            arWalls[4][i].init();
        }
        for (int i = 0; i < 10; i++) {
            arWalls[2][i] = new SpriteWithSidesCommon(txWall, i * 50 + 700, 200, 0, 0, 0, 50, 50);
            arWalls[2][i].init();
        }

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor((this));
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
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

        if (sprAlGore.getY() <= 0) {
            sprAlGore.jumpReset(1);
            sprAlGore.goBackUpDown();
        }

        batch.begin();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arWalls[j][i] != null) {
                    arWalls[j][i].draw(batch);
                    if (isHit(sprAlGore, arWalls[j][i])) {
                        sprAlGore.hitTest(arWalls[j][i]);
                    }
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

    public static boolean isHit(SpriteWithSidesCommon spr1, SpriteWithSidesCommon spr2) {
        setXY(spr1, Math.round(spr1.getX()), Math.round(spr1.getY()));
        setXY(spr2, Math.round(spr2.getX()), Math.round(spr2.getY()));
        return spr1.retRect().overlaps(spr2.retRect());
    }

}
