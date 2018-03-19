package gdx.game.ScrMenu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;

public class ScrMenu implements Screen{
    //----------------------------------------------Declare-------------------------------------------------------------

    private OrthographicCamera cam;
    GamTerarria game;
    Viewport viewport;

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrMenu (GamTerarria game, Viewport viewport){
        this.game = game;
        this.viewport = viewport;

        cam = new OrthographicCamera();
        cam.setToOrtho(true);
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

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.apply(true);
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

    }
}
