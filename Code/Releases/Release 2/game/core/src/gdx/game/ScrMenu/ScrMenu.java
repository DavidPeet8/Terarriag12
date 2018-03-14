package gdx.game.ScrMenu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;

public class ScrMenu implements Screen{
    //----------------------------------------------Declare-------------------------------------------------------------

    OrthographicCamera cam;
    GamTerarria game;
    Viewport viewport;

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrMenu (GamTerarria game, OrthographicCamera cam, Viewport viewport){
        this.cam = cam;
        this.game = game;
        this.viewport = viewport;
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
