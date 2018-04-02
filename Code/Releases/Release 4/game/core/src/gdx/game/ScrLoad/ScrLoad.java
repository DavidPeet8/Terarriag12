package gdx.game.ScrLoad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.*;
import gdx.game.commonclasses.Tile;
import java.util.Random;

public class ScrLoad implements Screen, InputProcessor {
    //----------------------------------------------Declare-------------------------------------------------------------

    GamTerarria game;
    private OrthographicCamera cam;
    Viewport viewport;
    Noise createNoise;
    Random ran = new Random();
    private int[] arnElevation = new int[Constants.WORLDWIDTH];
    Long lSeed;

    JsonReader jrJson = new JsonReader();
    JsonValue jvTile = jrJson.parse(Gdx.files.local("JSON/Tile.json"));

    JsonValue jvLeaves = jvTile.get("1");
    JsonValue jvGrass = jvTile.get("2");
    JsonValue jvDirt = jvTile.get("3");
    JsonValue jvWood = jvTile.get("4");
    JsonValue jvStone = jvTile.get("5");
    JsonValue jvTin = jvTile.get("6");
    JsonValue jvCopper = jvTile.get("7");

    JsonValue jvIron = jvTile.get("8");
    JsonValue jvLead = jvTile.get("9");
    JsonValue jvTungsten = jvTile.get("10");

    JsonValue jvGold = jvTile.get("11");
    JsonValue jvPlatinum = jvTile.get("12");
    JsonValue jvSilver = jvTile.get("13");

    JsonValue jvCloud = jvTile.get("14");
    JsonValue jvMoss = jvTile.get("15");
    JsonValue jvCrimtane = jvTile.get("16");
    JsonValue jvDemonite = jvTile.get("17");

    JsonValue jvCobalt = jvTile.get("18");
    JsonValue jvPalladium = jvTile.get("19");
    JsonValue jvMythril = jvTile.get("20");
    JsonValue jvOrhicallum = jvTile.get("21");
    JsonValue jvAdamantite = jvTile.get("22");
    JsonValue jvTitanium = jvTile.get("23");

    JsonValue jvWater = jvTile.get("24");
    JsonValue jvLava = jvTile.get("25");
    JsonValue jvHoney = jvTile.get("26");
    JsonValue jvClout = jvTile.get("27");

    public boolean bSavedGame = false;

    //----------------------------------------------Load Textures-------------------------------------------------------

    Texture boxGrass = new Texture("Boxes/BoxGrass.png");
    Texture boxDirt = new Texture("Boxes/BoxDirt.png");
    Texture boxStone = new Texture("Boxes/BoxStone.png");
    Texture boxCopper = new Texture("Boxes/BoxCopper.png");
    Texture boxTin = new Texture("Boxes/BoxTin.png");
    Texture boxIron = new Texture("Boxes/BoxIron.png");
    Texture boxLead = new Texture("Boxes/BoxLead.png");
    Texture boxGold = new Texture("Boxes/BoxGold.png");
    Texture boxCobalt = new Texture("Boxes/BoxCobalt.png");
    Texture boxDemonite = new Texture("Boxes/BoxDemonite.png");
    Texture boxCrimtane = new Texture("Boxes/BoxCrimtane.png");
    Texture boxOrhicallum = new Texture("Boxes/BoxOrhicallum.png");
    Texture boxClout = new Texture("Boxes/BoxClout.png");
    Texture boxLeaves = new Texture("Boxes/BoxLeaves.png");
    Texture boxWood = new Texture("Boxes/BoxWood.png");
    Texture boxTungsten = new Texture("Boxes/BoxTungsten.png");
    Texture boxPlatinum = new Texture("Boxes/BoxPlatinum.png");
    Texture boxSilver = new Texture("Boxes/BoxSilver.png");
    Texture boxCloud = new Texture("Boxes/BoxCloud.png");
    Texture boxMoss = new Texture("Boxes/BoxMoss.png");
    Texture boxPalladium = new Texture("Boxes/BoxPalladium.png");
    Texture boxMythril = new Texture("Boxes/BoxMythril.png");
    Texture boxAdamantite = new Texture("Boxes/BoxAdamantite.png");
    Texture boxTitanium = new Texture("Boxes/BoxTitanium.png");
    Texture boxWater = new Texture("Boxes/BoxWater.png");
    Texture boxLava = new Texture("Boxes/BoxLava.png");
    Texture boxHoney = new Texture("Boxes/BoxHoney.png");

    Texture texLoad = new Texture("Load.jpg");

    //----------------------------------------------Create Sprites------------------------------------------------------

    public static Tile[][] artBoxes = new Tile[(Constants.WORLDHEIGHT)][(Constants.WORLDHEIGHT)];

    Tile tLoad = new Tile(texLoad, Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false, false, null);

    private SpriteBatch batch = new SpriteBatch();

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrLoad(GamTerarria game, Long lSeed){
        this.lSeed = lSeed;
        this.game = game;
        
        if(lSeed == null){
            lSeed = ran.nextLong();
        }

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
                for (int x = 0; x < (Constants.WORLDWIDTH); x++) {
                    artBoxes[y][x] = null;
                }
            }

            // surface layer of blocks
            for (int x = 0; x < (Constants.WORLDWIDTH); x++) {
                //+ constant shifts ground level up so there is an underground portion to the map
                arnElevation[x] = (int) (createNoise.Noise(x) * 100 / Constants.TILEHEIGHT) + 100;
                artBoxes[arnElevation[x]][x] = new Tile(boxGrass, 16, 16, false, true, jvGrass);

                artBoxes[arnElevation[x]][x].setX((x * Constants.TILEWIDTH));
                artBoxes[arnElevation[x]][x].setY((arnElevation[x] * Constants.TILEHEIGHT));
            }
            // blocks under surface layer
            for (int x = 0; x < (Constants.WORLDWIDTH); x++) {
                for (int y = 0; y < (Constants.WORLDHEIGHT); y++) {
                    if (!(artBoxes[y][x] instanceof Tile) && y < arnElevation[x]) {

                        //dirt will be default if none get picked
                        //add bollean (0 or 1) multiplier for corrupt biome

                        artBoxes[y][x] = new Tile(boxDirt, 16, 16, false, true, jvDirt);

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
            artBoxes[y][x] = new Tile(boxStone, 16, 16, false, true, jvStone);
        }

        if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvClout.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxClout, 16, 16, false, true, jvClout);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvTitanium.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxTitanium, 16, 16, false, true, jvTitanium);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvAdamantite.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxAdamantite, 16, 16, false, true, jvAdamantite);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvOrhicallum.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxOrhicallum, 16, 16, false, true, jvOrhicallum);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvMythril.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxMythril, 16, 16, false, true, jvMythril);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvPalladium.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxPalladium, 16, 16, false, true, jvPalladium);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvCobalt.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxCobalt, 16, 16, false, true, jvCobalt);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvSilver.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxSilver, 16, 16, false, true, jvSilver);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvPlatinum.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxPlatinum, 16, 16, false, true, jvPlatinum);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvGold.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxGold, 16, 16, false, true, jvGold);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvIron.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxIron, 16, 16, false, true, jvIron);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvLead.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxLead, 16, 16, false, true, jvLead);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvTungsten.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxTungsten, 16, 16, false, true, jvTungsten);
        }else if(ran.nextDouble() < ((nMaxNumerator/ (double) y) / jvCopper.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxCopper, 16, 16, false, true, jvCopper);
        }else if(ran.nextDouble() < (((nMaxNumerator/ (double) y) / 1000) / jvTin.getDouble("rarity")) * SimBlocks(x, y)){
            artBoxes[y][x] = new Tile(boxTin, 16, 16, false, true, jvTin);
        }
    }

    private double SimBlocks(int x, int y){
        int nSimNum = 1;

        if(x > 0 && x < Constants.WORLDWIDTH-1 && y > 0 && y < Constants.WORLDHEIGHT-1) {
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

    private void Load(){
        //try to load images and json in here this is trash
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

    @Override
    public void dispose() {
        //----Textures----
        boxGrass.dispose();
        boxStone.dispose();
        boxGrass.dispose();
        boxDirt.dispose();
        boxStone.dispose();
        boxCopper.dispose();
        boxTin.dispose();
        boxIron.dispose();
        boxLead.dispose();
        boxGold.dispose();
        boxCobalt.dispose();
        boxDemonite.dispose();
        boxCrimtane.dispose();
        boxOrhicallum.dispose();
        boxClout.dispose();
        boxLeaves.dispose();
        boxWood.dispose();
        boxTungsten.dispose();
        boxPlatinum.dispose();
        boxSilver.dispose();
        boxCloud.dispose();
        boxMoss.dispose();
        boxPalladium.dispose();
        boxMythril.dispose();
        boxAdamantite.dispose();
        boxTitanium.dispose();
        boxWater.dispose();
        boxLava.dispose();
        boxHoney.dispose();
        texLoad.dispose();

        //----Game----
        game.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.UP){
            game.nScreen++;
            game.updateState(game.nScreen);
        }
        if(keycode == Input.Keys.DOWN){
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

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
    
}
//----------------------------------------------Links-------------------------------------------------------------------

//https://stackoverflow.com/questions/22802572/write-to-json-using-libgdx

//http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
//https://www.redblobgames.com/maps/terrain-from-noise/
//https://www.redblobgames.com/articles/noise/introduction.html

//https://www.michaelbromley.co.uk/blog/simple-1d-noise-in-javascript/