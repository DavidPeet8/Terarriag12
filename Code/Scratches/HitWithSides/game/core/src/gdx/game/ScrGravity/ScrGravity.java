package gdx.game.ScrGravity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamTerarria;

public class ScrGravity implements Screen, InputProcessor {

    GamTerarria gamTerarria;

    SpriteBatch batch;
    SpriteGrav sprAlGore;
    Sprite sprWall;
    Texture txWall, txHero;

    int fWidth, fHeight; // height and width of SpriteSheet image
    int nXWall, nYWall;
    int nSpeed;

    public ScrGravity(GamTerarria game) {  //Referencing the main class.
        gamTerarria = game;
        nXWall = 300;
        nYWall = 100;
        nSpeed = 5;
        batch = new SpriteBatch();
        txWall = new Texture("Wall.png");
        txHero = new Texture("alGore.png");
        sprWall = new Sprite(txWall);
        sprWall.setPosition(nXWall, nYWall);
        fWidth = txHero.getWidth();
        fHeight = txHero.getHeight();

        sprAlGore = new SpriteGrav(txHero, fWidth, fHeight, 300, 500, 0.5, 0, 8);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor((this));
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprAlGore.dLastX = sprAlGore.dX;
            sprAlGore.dX -= nSpeed;

            if (isHit(sprAlGore, sprWall)) {
                sprAlGore.dX += nSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprAlGore.dLastX = sprAlGore.dX;
            sprAlGore.dX += nSpeed;

            if (isHit(sprAlGore, sprWall)) {
                sprAlGore.dX -= nSpeed;
            }
        }
        grav(sprAlGore, sprWall);
        batch.begin();
        batch.draw(txHero, Math.round((float) sprAlGore.dX), Math.round((float) sprAlGore.dY));
        batch.draw(sprWall, sprWall.getX(), sprWall.getY());
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
        if(keycode == Input.Keys.UP){
            System.out.println("FIXING NAMES");
            gamTerarria.nScreen++;
            gamTerarria.updateState(gamTerarria.nScreen);
        }
        if(keycode == Input.Keys.DOWN){
            System.out.println("FIXING NAMES");
            gamTerarria.nScreen--;
            gamTerarria.updateState(gamTerarria.nScreen);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
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
    
    public static void grav(SpriteGrav spr, Sprite sprWall) {
        spr.gravity(); //calling gravity here makes hit detecting work, an issue is if someone presses "w" to jump
        if (isHit(spr, sprWall)) {
            if (spr.dY > sprWall.getY()) {
                spr.dY = sprWall.getHeight() + sprWall.getY();
                spr.dVel = 0;
            } else if (spr.dY <= sprWall.getY()) {
                spr.dY = sprWall.getY() - spr.fHeight;
                spr.dVel = 0;
            }
        }
    }
    
    public static void setXY(Sprite spr, int nX, int nY) {
        spr.setX(nX);
        spr.setY(nY);
    }

    public static boolean isHit(SpriteGrav spr1, Sprite spr2) {
        setXY(spr1, Math.round(spr1.getX()), Math.round(spr1.getY()));
        setXY(spr2, Math.round(spr2.getX()), Math.round(spr2.getY()));
        return spr1.retRect().overlaps(spr2.getBoundingRectangle()); 
    }

}
