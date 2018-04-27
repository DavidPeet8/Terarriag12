package gdx.game.ScrInvenoryGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.InventoryObj;

import static gdx.game.commonclasses.Textures.texActive;

public class ScrInventory implements Screen, InputProcessor {
    InventoryObj inventoryObj;
    SpriteBatch batch;
    GamTerarria game;

    public ScrInventory(GamTerarria game){
        this.game = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        inventoryObj = game.getScrPlay().getInventoryObj();

        batch.begin();
        for (int r = 0; r < inventoryObj.getHotbar().length; r++) {
            if(inventoryObj.getHotbar()[r] != null) {
                if (inventoryObj.getHotbar()[r].getTex() != null) {
                    batch.draw(inventoryObj.getHotbar()[r].getTex(), r * 40 + 400, 600, 30, 30);
                }
            }
        }


        //easiest to make the inventory a 2D array
        for (int i = 0; i < inventoryObj.getInvenory().length; i++) {
            if (inventoryObj.getInvenory()[i] != null) {
                if (inventoryObj.getInvenory()[i].getTex() != null) {
                    batch.draw(inventoryObj.getInvenory()[i].getTex(), i * 40 + 400, 600, 30, 30);
                }
            }
        }

        batch.end();

        //render inventory like terarria
        //render a trash area like terarria?
        //if possible make it an overlay?
    }

    //<editor-fold desc="Screen Events">
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
        if (keycode == Input.Keys.I) {
            game.nScreen = 2;
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
