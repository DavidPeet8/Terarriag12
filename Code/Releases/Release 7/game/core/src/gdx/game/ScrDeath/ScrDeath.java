package gdx.game.ScrDeath;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;

import static gdx.game.commonclasses.Constants.RESPAWNHEALTHPERCENT;
import static gdx.game.commonclasses.Constants.WORLDHEIGHT;
import static gdx.game.commonclasses.Constants.WORLDWIDTH;

public class ScrDeath implements Screen, InputProcessor {
    GamTerarria game;
    private OrthographicCamera cam;
    private Viewport viewport;

        public ScrDeath(GamTerarria game){
            this.game = game;
            cam = new OrthographicCamera();
            viewport = new ExtendViewport(WORLDWIDTH, WORLDHEIGHT, cam);
        }

        @Override
        public void show() {
            Gdx.input.setInputProcessor(this);
            System.out.println("ur ded press \"r\" to respawn, \"m\" to go to main menu");
        }

        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            game.getScrPlay().drawDeathMap();
        }

        //<editor-fold desc="Screen Events">
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
            if (keycode == Input.Keys.R) {
                game.getScrPlay().getPlayer().setHealth((int)(game.getScrPlay().getPlayer().getMaxHealth() * RESPAWNHEALTHPERCENT));
                game.getScrPlay().getPlayer().setdPos(game.getScrPlay().getSpawnX(), game.getScrPlay().getSpawnY());
                game.nScreen = 2;
                game.updateState(game.nScreen);

            }
            if(keycode == Input.Keys.M){
                game.nScreen = 0;
                game.updateState(game.nScreen);
            }
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (keycode == Input.Keys.W) {
                game.getScrPlay().getArbKeys()[0] = false;
            }
            if (keycode == Input.Keys.A) {
                game.getScrPlay().getArbKeys()[3] = false;

            }
            if (keycode == Input.Keys.S) {
                game.getScrPlay().getArbKeys()[2] = false;

            }
            if (keycode == Input.Keys.D) {
                game.getScrPlay().getArbKeys()[1] = false;

            }
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
            game.getScrPlay().getArbKeys()[4] = false;
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

