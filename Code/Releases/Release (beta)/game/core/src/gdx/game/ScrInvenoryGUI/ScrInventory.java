package gdx.game.ScrInvenoryGUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.InventoryObj;

public class ScrInventory implements Screen, InputProcessor {
    InventoryObj inventoryObj;
    SpriteBatch batch;
    private GamTerarria game;
    private Vector2 v2FirstIndex = new Vector2(-1, -1);
    private Vector2 v2FinalIndex = new Vector2(-1, -1);
    private Vector2 v2MousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
    private OrthographicCamera gameCam = new OrthographicCamera();

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
        updateCam();
        drawInventoryAndHotBar();
        //render a trash area like terarria?
        //if possible make it an overlay?
    }

    //-------------------------------My Functions--------------------------------

    //<editor-fold desc="My Functions">
    public void clickDown(int button){
        v2MousePos.x = Gdx.input.getX();
        v2MousePos.y = Gdx.input.getY();

        if(v2FirstIndex.x == -1 && v2FirstIndex.y == -1){
            //bounding rect, which does it overlap?
            //get that items index
            //set v2FirstIndex
            for (int i = 0; i < inventoryObj.getInvenory().length; i++) {
                for(int r = 0; r < inventoryObj.getInvenory()[i].length; r++){
                    if(inventoryObj.getInvenory()[i][r] != null) {
                        System.out.println("YA MOMS A HOE");
                        if (inventoryObj.getInvenory()[i][r].getBoundingRectangle().contains(v2MousePos.x, v2MousePos.y)) {
                            v2FirstIndex.set(r, i);
                            System.out.println("y u no wok");
                        }
                    }
                }
            }
        } else if (v2FinalIndex.x == -1 && v2FinalIndex.y == -1) {
            for (int i = 0; i < inventoryObj.getInvenory().length; i++) {
                for(int r = 0; r < inventoryObj.getInvenory()[i].length; r++){
                    if(inventoryObj.getInvenory()[i][r] != null) {
                        if (inventoryObj.getInvenory()[i][r].getBoundingRectangle().contains(v2MousePos.x, v2MousePos.y)) {
                            v2FirstIndex.set(r, i);
                        }
                    }
                }
            }
        }

        if(v2FirstIndex.x >= 0 && v2FirstIndex.y >= 0) {
            if(v2FinalIndex.x >= 0 && v2FinalIndex.y >= 0) {
                inventoryObj.switchInventorySpots(v2FirstIndex, v2FinalIndex);
                v2FirstIndex.set(-1,-1);
                v2FinalIndex.set(-1,-1);
            }
        }
        //what tile does mouse overlap, if any
        //start moving that tile at mouse x and y
        //escape to get rid of it
        //shadow block or actual block

        //if already have a block out,
        //switch blocks --switch the other to active following mouse? or just switch two indexes

        //look at clicking to null too
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
    }//shit is breaking
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
