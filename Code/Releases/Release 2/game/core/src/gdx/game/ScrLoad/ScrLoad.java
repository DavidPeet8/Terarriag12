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
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.*;
import gdx.game.commonclasses.Tile;
import java.util.Random;

public class ScrLoad implements Screen, InputProcessor {
    //----------------------------------------------Declare-------------------------------------------------------------

    GamTerarria game;
    OrthographicCamera cam;
    Viewport viewport;
    Noise CreateNoise;
    Random ran = new Random();
    private int[] nElevation = new int[Constants.nWorldWidth];
    Long lSeed;

    JsonReader json = new JsonReader();
    JsonValue Tile = json.parse(Gdx.files.local("JSON/Tile.json"));

    JsonValue leaves = Tile.get("1");
    JsonValue Grass = Tile.get("2");
    JsonValue Dirt = Tile.get("3");
    JsonValue Wood = Tile.get("4");
    JsonValue Stone = Tile.get("5");
    JsonValue Tin = Tile.get("6");
    JsonValue Copper = Tile.get("7");

    JsonValue Iron = Tile.get("8");
    JsonValue Lead = Tile.get("9");
    JsonValue Tungsten = Tile.get("10");

    JsonValue Gold = Tile.get("11");
    JsonValue Platinum = Tile.get("12");
    JsonValue Silver = Tile.get("13");

    JsonValue Cloud = Tile.get("14");
    JsonValue Moss = Tile.get("15");
    JsonValue Crimtane = Tile.get("16");
    JsonValue Demonite = Tile.get("17");

    JsonValue Cobalt = Tile.get("18");
    JsonValue Palladium = Tile.get("19");
    JsonValue Mythril = Tile.get("20");
    JsonValue Orhicallum = Tile.get("21");
    JsonValue Adamantite = Tile.get("22");
    JsonValue Titanium = Tile.get("23");

    JsonValue Water = Tile.get("24");
    JsonValue Lava = Tile.get("25");
    JsonValue Honey = Tile.get("26");
    JsonValue Clout = Tile.get("27");

    public boolean bSavedGame = false;

    //----------------------------------------------Load Textures-------------------------------------------------------

    Texture BoxGrass = new Texture("Boxes/BoxGrass.png");
    Texture BoxDirt = new Texture("Boxes/BoxDirt.png");
    Texture BoxStone = new Texture("Boxes/BoxStone.png");
    Texture BoxCopper = new Texture("Boxes/BoxCopper.png");
    Texture BoxTin = new Texture("Boxes/BoxTin.png");
    Texture BoxIron = new Texture("Boxes/BoxIron.png");
    Texture BoxLead = new Texture("Boxes/BoxLead.png");
    Texture BoxGold = new Texture("Boxes/BoxGold.png");
    Texture BoxCobalt = new Texture("Boxes/BoxCobalt.png");
    Texture BoxDemonite = new Texture("Boxes/BoxDemonite.png");
    Texture BoxCrimtane = new Texture("Boxes/BoxCrimtane.png");
    Texture BoxOrhicallum = new Texture("Boxes/BoxOrhicallum.png");
    Texture BoxClout = new Texture("Boxes/BoxClout.png");
    Texture BoxLeaves = new Texture("Boxes/BoxLeaves.png");
    Texture BoxWood = new Texture("Boxes/BoxWood.png");
    Texture BoxTungsten = new Texture("Boxes/BoxTungsten.png");
    Texture BoxPlatinum = new Texture("Boxes/BoxPlatinum.png");
    Texture BoxSilver = new Texture("Boxes/BoxSilver.png");
    Texture BoxCloud = new Texture("Boxes/BoxCloud.png");
    Texture BoxMoss = new Texture("Boxes/BoxMoss.png");
    Texture BoxPalladium = new Texture("Boxes/BoxPalladium.png");
    Texture BoxMythril = new Texture("Boxes/BoxMythril.png");
    Texture BoxAdamantite = new Texture("Boxes/BoxAdamantite.png");
    Texture BoxTitanium = new Texture("Boxes/BoxTitanium.png");
    Texture BoxWater = new Texture("Boxes/BoxWater.png");
    Texture BoxLava = new Texture("Boxes/BoxLava.png");
    Texture BoxHoney = new Texture("Boxes/BoxHoney.png");
    Texture Load = new Texture("Load.jpg");

    //----------------------------------------------Create Sprites------------------------------------------------------

    public static Tile[][] sprBoxes = new Tile[(Constants.nWorldHeight)][(Constants.nWorldWidth)];

    Tile sprLoad = new Tile(Load, 0,0,false, false);

    SpriteBatch batch = new SpriteBatch();

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrLoad(GamTerarria game, Long lSeed, OrthographicCamera cam, Viewport viewport){
        this.lSeed = lSeed;
        this.cam = cam;
        this.viewport = viewport;
        this.game = game;
        
        if(lSeed == null){
            lSeed = ran.nextLong();
        }
        
        ran.setSeed(lSeed);
        //larger scale smaller wavelength smaller scale  larger wavelength
        CreateNoise = new Noise(2.5,0.05, lSeed);

        sprLoad.setX(Gdx.graphics.getWidth()/2 - sprLoad.tex.getWidth()/2);
        sprLoad.setY(Gdx.graphics.getHeight()/2 - sprLoad.tex.getHeight()/2);
        
        CreateWorld();
    }

    //----------------------------------------------My Methods----------------------------------------------------------

    private void CreateWorld(){

        if(bSavedGame == true) {
            //could save game to json file
        }else {

            //set all to null, (0,0) is bottom left corner of map
            for (int y = 0; y < (Constants.nWorldHeight); y++) {
                for (int x = 0; x < (Constants.nWorldWidth); x++) {
                    sprBoxes[y][x] = null;
                }
            }

            // surface layer of blocks
            for (int x = 0; x < (Constants.nWorldWidth); x++) {
                //+ constant shifts ground level up so there is an underground portion to the map
                nElevation[x] = (int) (CreateNoise.Noise(x) * 100 / Constants.nTileHeight) + 100;
                sprBoxes[nElevation[x]][x] = new Tile(BoxGrass, 16, 16, false, true);

                sprBoxes[nElevation[x]][x].setX((x * Constants.nTileWidth));
                sprBoxes[nElevation[x]][x].setY((nElevation[x] * Constants.nTileHeight));
            }
            // blocks under surface layer
            for (int x = 0; x < (Constants.nWorldWidth); x++) {
                for (int y = 0; y < (Constants.nWorldHeight); y++) {
                    if (!(sprBoxes[y][x] instanceof Tile) && y < nElevation[x]) {

                        //dirt will be default if none get picked
                        //add bollean (0 or 1) multiplier for corrupt biome
                        
                        if(ran.nextInt(nElevation[x])> 100){
                            sprBoxes[y][x] = new Tile(BoxStone, 16, 16, false, true);
                        }else{
                            sprBoxes[y][x] = new Tile(BoxDirt, 16, 16, false, true);
                        }

                        BlockChance(y, x);

                        sprBoxes[y][x].setX((x * Constants.nTileWidth));
                        sprBoxes[y][x].setY((y * Constants.nTileHeight));
                    }
                }
            }
        }
    }
    
    public void BlockChance(int y, int x){
        if(ran.nextDouble() < (((double)y / 100) / Clout.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxClout, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Titanium.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxTitanium, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Adamantite.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxAdamantite, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Orhicallum.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxOrhicallum, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Mythril.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxMythril, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Palladium.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxPalladium, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Cobalt.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxCobalt, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Silver.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxSilver, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Platinum.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxPlatinum, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Gold.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxGold, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Iron.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxIron, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Lead.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxLead, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Tungsten.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxTungsten, 16, 16, false, true);
        }else if(ran.nextDouble() < (((double)y / 100) / Copper.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxCopper, 16, 16, false, true);
        }else if(ran.nextDouble() < ((((double)y / 100) / 1000) / Tin.getDouble("rarity")) * SimBlocks(x, y)){
            sprBoxes[y][x] = new Tile(BoxTin, 16, 16, false, true);
        }
    }

    private double SimBlocks(int x, int y){
        int nSimNum = 1;

        if(x > 0 && x < Constants.nWorldWidth-1 && y > 0 && y < Constants.nWorldHeight-1) {
            if (sprBoxes[y][x - 1] == sprBoxes[y][x]) {
                nSimNum++;
            }
            if (sprBoxes[y - 1][x] == sprBoxes[y][x]) {
                nSimNum++;
            }
            if (sprBoxes[y - 1][x + 1] == sprBoxes[y][x]) {
                nSimNum++;
            }
            if (sprBoxes[y - 1][x - 1] == sprBoxes[y][x]) {
                nSimNum++;
            }
        }
        return Math.pow(nSimNum, 5);
    }

    //----------------------------------------------Abstract Methods----------------------------------------------------

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        
        cam.position.set(0, 0, 0);
        batch.setProjectionMatrix(cam.combined);
        cam.update();
        
        System.out.println("DA SHOW TING");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        System.out.println("FUK DIS SHIT");
        batch.begin();
        sprLoad.draw(batch);
        batch.end();
        
        
        //for the end game, will automatically go to play screen once done loading
//        game.nScreen= 2;
//        game.updateState(game.nScreen);
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
        BoxGrass.dispose();
        BoxStone.dispose();
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