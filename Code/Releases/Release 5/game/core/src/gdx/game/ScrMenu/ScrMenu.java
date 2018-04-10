package gdx.game.ScrMenu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.Constants;

public class ScrMenu implements Screen{

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
    }

    @Override
    public void render(float delta) {

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

    }
}
