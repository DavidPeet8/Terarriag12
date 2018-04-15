package gdx.game.ScrMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.Constants;

public class ScrMenu implements Screen, InputProcessor{

    //<editor-fold desc="Init">
    //----------------------------------------------Declare-------------------------------------------------------------

    private OrthographicCamera cam;
    GamTerarria game;
    Viewport viewport;
    //</editor-fold>

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrMenu (GamTerarria game){
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(true);
        cam.position.set(0, 0, 0);
        cam.update();

        viewport = new ExtendViewport(Constants.WORLDWIDTH, Constants.WORLDHEIGHT, cam);
    }

    //------------------------------------------------My Methods--------------------------------------------------------

    public void updateCam() {
        //batch.setProjectionMatrix(cam.combined);
        cam.position.set(0, 0, 0);
        cam.update();
    }

    //----------------------------------------------Abstract Methods----------------------------------------------------

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    //<editor-fold desc="Screen Events - pause, resume">
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
    //</editor-fold>

    @Override
    public void dispose() {
        game.dispose();
    }


    //<editor-fold desc="Key Events">
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            game.nScreen++;
            game.updateState(game.nScreen);
        }
        if (keycode == Input.Keys.DOWN) {
            game.nScreen--;
            game.updateState(game.nScreen);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Touch/Click Events">
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Mouse Events">
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
    //</editor-fold>
}
