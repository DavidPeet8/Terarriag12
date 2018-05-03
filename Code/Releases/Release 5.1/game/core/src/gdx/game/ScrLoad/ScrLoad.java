package gdx.game.ScrLoad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.ScrPlay.ScrPlay;
import gdx.game.commonclasses.*;
import gdx.game.commonclasses.Tile;
import java.util.Random;

import static gdx.game.commonclasses.Constants.*;
import static gdx.game.commonclasses.Textures.*;

public class ScrLoad implements Screen, InputProcessor {

    //<editor-fold desc="Init">
    //----------------------------------------------Declare-------------------------------------------------------------

    GamTerarria game;
    private OrthographicCamera cam;
    Viewport viewport;
    Noise createNoise;
    Random ran = new Random();
    private int[] arnElevation = new int[WORLDWIDTH];
    Long lSeed;

    public boolean bSavedGame = false;

    //----------------------------------------------Create Sprites------------------------------------------------------

    public static Tile[][] artBoxes = new Tile[(Constants.WORLDHEIGHT)][(WORLDWIDTH)];

    Tile tLoad = new Tile(texLoad, Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), null);

    private SpriteBatch batch = new SpriteBatch();
    //</editor-fold>

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrLoad(GamTerarria game, Long lSeed){
        this.lSeed = lSeed;
        this.game = game;
        
        if(lSeed == null){
            lSeed = ran.nextLong();
        }

        game.setScrPlay(new ScrPlay(game));

        tLoad.setPosition(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2);
        
        ran.setSeed(lSeed);
        createNoise = new Noise(2.5,0.05, lSeed);

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        cam.position.set(0, 0, 0);
        cam.update();

        viewport = new ExtendViewport(tLoad.getWidth(), tLoad.getHeight(), cam);

        CreateWorld();
    }

    //----------------------------------------------My Methods----------------------------------------------------------

    private void CreateWorld(){

        //(0,0) bottom left in array
        //(0,0) bottom left in sprite

        if(bSavedGame == true) {
            //could save game to json file
        }else {

            //set all to null, (0,0) is bottom left corner of map
            for (int y = 0; y < (Constants.WORLDHEIGHT); y++) {
                for (int x = 0; x < (WORLDWIDTH); x++) {
                    artBoxes[y][x] = null;
                }
            }

            // surface layer of blocks
            for (int x = 0; x < (WORLDWIDTH); x++) {
                //+ constant shifts ground level up so there is an underground portion to the map
                arnElevation[x] = (int) (createNoise.Noise(x) * 100 / Constants.TILEHEIGHT) + 100;
                artBoxes[arnElevation[x]][x] = new Tile(boxGrass, 16, 16, jvGrass);

                artBoxes[arnElevation[x]][x].setX((x * Constants.TILEWIDTH));
                artBoxes[arnElevation[x]][x].setY((arnElevation[x] * Constants.TILEHEIGHT));
            }
            // blocks under surface layer
            for (int x = 0; x < (WORLDWIDTH); x++) {
                for (int y = 0; y < (Constants.WORLDHEIGHT); y++) {
                    if (!(artBoxes[y][x] instanceof Tile) && y < arnElevation[x]) {

                        //dirt will be default if none get picked
                        //add bollean (0 or 1) multiplier for corrupt biome

                        artBoxes[y][x] = new Tile(boxDirt, 16, 16, jvDirt);

                        BlockChance(y, x);

                        artBoxes[y][x].setX((x * Constants.TILEWIDTH));
                        artBoxes[y][x].setY((y * Constants.TILEHEIGHT));
                    }
                }
            }
        }
    }
    
    public void BlockChance(int y, int x){
        int nMaxNumerator = 100; //essentially increases spawn chances of all blocks at once

        //special one fo stone
        if(createNoise.Noise(x) < nMaxNumerator / (double) y){
            artBoxes[y][x] = new Tile(boxStone, 16, 16, jvStone);
        }

        if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvClout.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxClout, 16, 16, jvClout);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvTitanium.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxTitanium, 16, 16, jvTitanium);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvAdamantite.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxAdamantite, 16, 16,  jvAdamantite);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvOrhicallum.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxOrhicallum, 16, 16, jvOrhicallum);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvMythril.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxMythril, 16, 16, jvMythril);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvPalladium.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxPalladium, 16, 16, jvPalladium);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvCobalt.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxCobalt, 16, 16, jvCobalt);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvSilver.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxSilver, 16, 16, jvSilver);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvPlatinum.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxPlatinum, 16, 16, jvPlatinum);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvGold.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxGold, 16, 16, jvGold);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvIron.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxIron, 16, 16,  jvIron);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvLead.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxLead, 16, 16, jvLead);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvTungsten.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxTungsten, 16, 16, jvTungsten);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvCopper.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxCopper, 16, 16, jvCopper);
        }else if(ran.nextDouble() < (((nMaxNumerator/ (double) y) / 1000) / jvTin.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxTin, 16, 16, jvTin);
        }
    }

    private double SimBlocks(int x, int y){
        int nSimNum = 1;

        if(x > 0 && x < WORLDWIDTH-1 && y > 0 && y < WORLDHEIGHT-1) {
            if (artBoxes[y][x - 1] == artBoxes[y][x]) {
                nSimNum++;
            }
            if (artBoxes[y - 1][x] == artBoxes[y][x]) {
                nSimNum++;
            }
            if (artBoxes[y - 1][x + 1] == artBoxes[y][x]) {
                nSimNum++;
            }
            if (artBoxes[y - 1][x - 1] == artBoxes[y][x]) {
                nSimNum++;
            }
        }
        return Math.pow(5, nSimNum);
    }

    public void updateCam() {
        batch.setProjectionMatrix(cam.combined);
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
        updateCam();

        batch.begin();
        tLoad.draw(batch);
        batch.end();
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
        //----Game----
        game.dispose();
    }

    //<editor-fold desc="Key Events">
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE){
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
//<editor-fold desc="Links">
//----------------------------------------------Links-------------------------------------------------------------------

//https://stackoverflow.com/questions/22802572/write-to-json-using-libgdx

//http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
//https://www.redblobgames.com/maps/terrain-from-noise/
//https://www.redblobgames.com/articles/noise/introduction.html

//https://www.michaelbromley.co.uk/blog/simple-1d-noise-in-javascript/
//</editor-fold>