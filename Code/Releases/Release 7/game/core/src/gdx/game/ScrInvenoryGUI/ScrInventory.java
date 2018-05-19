package gdx.game.ScrInvenoryGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.InventoryObj;

import static gdx.game.commonclasses.Textures.texBackInv;

public class ScrInventory implements Screen, InputProcessor {
    InventoryObj inventoryObj;
    SpriteBatch batch;
    private GamTerarria game;
    private Vector2 v2FirstIndexInv = new Vector2(-1, -1);
    private Vector2 v2FinalIndexInv = new Vector2(-1, -1);
    private int nFirstIndexHot = -1;
    private int nFinalIndexHot = -1;
    private Vector3 v3MousePos = new Vector3(0, 0, 0);
    private OrthographicCamera gameCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        inventoryObj = game.getScrPlay().getInventoryObj();
        updateCam();
        drawBack();
        drawInventoryAndHotBar();
        //render a trash area like terarria?
        //if possible make it an overlay?
    }

    //-------------------------------My Functions--------------------------------

    //<editor-fold desc="My Functions">
    public void clickDown(int button){
        v3MousePos.x = Gdx.input.getX();
        v3MousePos.y = Gdx.input.getY();

        gameCam.unproject(v3MousePos);

        //first
        if(v2FirstIndexInv.x == -1 && v2FirstIndexInv.y == -1 && nFirstIndexHot == -1){
            breakPoint1:
            for (int i = 0; i < inventoryObj.getInvenory().length; i++) {
                for(int r = 0; r < inventoryObj.getInvenory()[i].length; r++){
                    if(inventoryObj.getInvenory()[i][r] != null) {
                        if (inventoryObj.getInvenory()[i][r].getBoundingRectangle().contains(v3MousePos.x, v3MousePos.y)) {
                            v2FirstIndexInv.set(r, i);
                            break breakPoint1;
                        }
                    }
                }
            }
            for (int i = 0; i < inventoryObj.getHotbar().length; i++) {
                if(inventoryObj.getHotbar()[i] != null) {
                    if (inventoryObj.getHotbar()[i].getBoundingRectangle().contains(v3MousePos.x, v3MousePos.y)) {
                        nFirstIndexHot = i;
                        break;
                    }
                }
            }
        }

        //final
        else if (nFinalIndexHot == -1 && v2FinalIndexInv.x == -1 && v2FinalIndexInv.y == -1) {
            for (int i = 0; i < inventoryObj.getHotbar().length; i++) {
                if(inventoryObj.getHotbar()[i] != null) {
                    if (inventoryObj.getHotbar()[i].getBoundingRectangle().contains(v3MousePos.x, v3MousePos.y)) {
                        nFinalIndexHot = i;
                        break;
                    }
                }
            }
            breakPoint4:
            for (int i = 0; i < inventoryObj.getInvenory().length; i++) {
                for(int r = 0; r < inventoryObj.getInvenory()[i].length; r++){
                    if(inventoryObj.getInvenory()[i][r] != null) {
                        if (inventoryObj.getInvenory()[i][r].getBoundingRectangle().contains(v3MousePos.x, v3MousePos.y)) {
                            v2FinalIndexInv.set(r, i);
                            break breakPoint4;
                        }
                    }
                }
            }
        }

        if((v2FirstIndexInv.x >= 0 && v2FirstIndexInv.y >= 0) || nFirstIndexHot >= 0) {
            if((v2FinalIndexInv.x >= 0 && v2FinalIndexInv.y >= 0) || nFinalIndexHot >= 0) {
                inventoryObj.switchInventorySpots(v2FirstIndexInv, v2FinalIndexInv, nFirstIndexHot, nFinalIndexHot);
                v2FirstIndexInv.set(-1,-1);
                v2FinalIndexInv.set(-1,-1);
                nFinalIndexHot = -1;
                nFirstIndexHot = -1;
            }
        }
        //what tile does mouse overlap, if any
        //start moving that tile at mouse x and y
        //escape to get rid of it
        //shadow block or actual block

        //if already have a block out,
        //switch blocks --switch the other to active following mouse? or just switch two indexes
    }

    public void drawInventoryAndHotBar(){
        for (int r = 0; r < inventoryObj.getHotbar().length; r++) { //the hotbar is causing the crash
            if(inventoryObj.getHotbar()[r] != null) {
                if (inventoryObj.getHotbar()[r].getTex() != null) {
                    inventoryObj.getHotbar()[r].setPosition(r * 40 + 400, 600);
                    inventoryObj.getHotbar()[r].setSize(30,30);
                    inventoryObj.getHotbar()[r].draw(batch);
                }
            }
        }

        //easiest to make the inventory a 2D array
        for (int i = 0; i < inventoryObj.getInvenory().length; i++) {
            for(int q = 0; q < inventoryObj.getInvenory()[i].length; q++) {
                if (inventoryObj.getInvenory()[i][q] != null) {
                    if (inventoryObj.getInvenory()[i][q].getTex() != null) {
                        inventoryObj.getInvenory()[i][q].setPosition(q * 40 + 400, i * 40 + 400);
                        inventoryObj.getInvenory()[i][q].setSize(30,30);
                        inventoryObj.getInvenory()[i][q].draw(batch);
                    }
                }
            }
        }
    }

    public void updateCam() {
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        gameCam.update();
    }

    public void drawBack(){
        batch.begin();
        batch.draw(texBackInv, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }
    //</editor-fold>

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
        if (keycode == Input.Keys.I) {
            game.nScreen = 2;
            game.updateState(game.nScreen);
        }
        if (keycode == Input.Keys.ESCAPE) {
            v2FirstIndexInv.set(-1,-1);
            v2FinalIndexInv.set(-1,-1);
            nFirstIndexHot = -1;
            nFinalIndexHot = -1;
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
        clickDown(button);
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
