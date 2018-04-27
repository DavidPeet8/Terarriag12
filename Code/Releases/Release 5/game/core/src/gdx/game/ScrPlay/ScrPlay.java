package gdx.game.ScrPlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.ScrInvenoryGUI.ScrInventory;
import gdx.game.ScrLoad.ScrLoad;
import gdx.game.commonclasses.*;

import static gdx.game.commonclasses.Constants.*;
import static gdx.game.commonclasses.Textures.*;

public class ScrPlay implements Screen, InputProcessor {

    //<editor-fold desc="Init">
    //----------------------------------------------Declare-------------------------------------------------------------

    private GamTerarria game;
    private OrthographicCamera gameCam;
    private Viewport gameViewport;
    private int nInitScreenWidth, nInitScreenHeight, nOffsetX, nOffsetY, nXSub, nYSub;
    private InventoryObj objInventory;
    private HUD hud;
    private int nDelta;

    //----------------------------------------------Create Sprites------------------------------------------------------
    private int nPlayerSpawnX = 30 * TILEWIDTH;
    private int nPlayerSpawnY = 1880;
    private int nDrawMapXInit, nDrawMapYInit, nDrawMapXFinal, nDrawMapYFinal;
    SpriteDiscrete sprPlayer = new SpriteDiscrete(texPlay, nPlayerSpawnX, nPlayerSpawnY, 50, 48, 64,
            100, 100, 100, 100);

    //----------------------------------------------Create Other classes------------------------------------------------
    private SpriteBatch batch = new SpriteBatch();
    private SpriteBatch fixedBatch = new SpriteBatch();
    boolean[] arbKeys = new boolean[5]; //0 is w, 1 is d, 2 is s, 3 is a, 4 is Left Mouse
    public static Tile[][] artSubsetBoxes = new Tile[8][8];
    //</editor-fold>

    //----------------------------------------------Constructor---------------------------------------------------------
    public ScrPlay(GamTerarria game) {
        this.game = game;

        nInitScreenWidth = Gdx.graphics.getWidth();
        nInitScreenHeight = Gdx.graphics.getHeight();

        //<editor-fold desc="Player">
        sprPlayer.init();
        nOffsetX = (int)sprPlayer.getWidth()/2/TILEWIDTH ;
        nOffsetY = (int)sprPlayer.getHeight()/2/TILEHEIGHT;
        //</editor-fold>

        //<editor-fold desc="Camera and Viewport">
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false);
        gameCam.position.set(sprPlayer.getX(), sprPlayer.getY(), 0);
        gameCam.update();

        gameViewport = new ExtendViewport(WORLDWIDTH, WORLDHEIGHT, gameCam);
        //</editor-fold>

        //----------initilize HUD and Inventoy------------
        objInventory = new InventoryObj();
        hud = new HUD();

        game.setScrInventory(new ScrInventory(game));
    }

    //----------------------------------------------My Functions------------------------------------------------
    public void drawMap() {
        fixedBatch.begin();
        fixedBatch.draw(texBack, 0, 0, nInitScreenWidth, nInitScreenHeight);
        fixedBatch.end();

        drawMapBoundarys();

        batch.begin();
        for (int y = nDrawMapYInit; y < nDrawMapYFinal; y++) {
            for (int x = nDrawMapXInit; x < nDrawMapXFinal; x++) {
                if (ScrLoad.artBoxes[y][x] instanceof Tile) {
                    ScrLoad.artBoxes[y][x].draw(batch);
                }
            }
        }
        sprPlayer.draw(batch);
        batch.end();
    }

    public void drawMapBoundarys(){
        nDrawMapXInit = (int) (sprPlayer.getX() / TILEWIDTH) - DRAWXRADIUS + nOffsetX;
        nDrawMapYInit = (int) (sprPlayer.getY() / TILEHEIGHT) - DRAWYRADIUS + nOffsetY;
        nDrawMapXFinal = (int) (sprPlayer.getX() / TILEWIDTH) + DRAWXRADIUS + nOffsetX;
        nDrawMapYFinal = (int) (sprPlayer.getY() / TILEHEIGHT) + DRAWYRADIUS + nOffsetY;

        if(nDrawMapXInit < 0) {
            nDelta = -nDrawMapXInit; //need this as a positive
            nDrawMapXFinal += nDelta;
            nDrawMapXInit = 0;
        }
        else if(nDrawMapXFinal > WORLDWIDTH) {
            nDelta = WORLDWIDTH - nDrawMapXFinal ;
            nDrawMapXInit += nDelta;
            nDrawMapXFinal = WORLDWIDTH;
        }
        if(nDrawMapYInit < 0) {
            nDelta = -nDrawMapYInit; //need this as a positive
            nDrawMapYFinal += nDelta;
            nDrawMapYInit = 0;
        }
        else if(nDrawMapYFinal > WORLDHEIGHT) {
            nDelta = WORLDHEIGHT - nDrawMapYFinal;
            nDrawMapYInit += nDelta;
            nDrawMapYFinal = WORLDHEIGHT;
        }
    }

    public void drawDeathMap() {
        fixedBatch.begin();
        fixedBatch.draw(texBack, 0, 0, nInitScreenWidth, nInitScreenHeight);
        fixedBatch.end();

        batch.begin();
        for (int y = 0; y < Constants.WORLDHEIGHT; y++) {
            for (int x = 0; x < WORLDWIDTH; x++) {
                if (ScrLoad.artBoxes[y][x] instanceof Tile) {
                    ScrLoad.artBoxes[y][x].draw(batch);
                }
            }
        }
        batch.end();
    }

    public boolean canMine(int button, Vector3 MousePos) {
        if (button == Input.Buttons.LEFT) {

            if (MousePos.x < sprPlayer.getX() + Constants.EFFECTIVERADIUS * Constants.TILEWIDTH
                    && MousePos.x > sprPlayer.getX() - Constants.EFFECTIVERADIUS * Constants.TILEWIDTH) {
                if (MousePos.y < sprPlayer.getY() + Constants.EFFECTIVERADIUS * Constants.TILEHEIGHT
                        && MousePos.y > sprPlayer.getY() - Constants.EFFECTIVERADIUS * Constants.TILEHEIGHT) {
                    return true;
                }
            }
        }
        return false;
    }

    public void keyAction() {

        if (arbKeys[0] == true) {// W
            sprPlayer.jump();
        }
        if (arbKeys[1] == true) {//D
            sprPlayer.changeDir(PLRMOVEMENTSPEED);//appear to be onscreen move values
        } else if (arbKeys[3] == true) {//A
            sprPlayer.changeDir(-PLRMOVEMENTSPEED); //apear to be onscreen move values
        }else {
            sprPlayer.changeDir(0);
        }

        if(arbKeys[4] == true) {
            clickDown(Input.Buttons.LEFT);
        }
    }

    public void updateCam() {
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.position.set(sprPlayer.getX() + sprPlayer.getWidth()/2, sprPlayer.getY() + sprPlayer.getHeight()/2, 0);
        gameCam.position.x = MathUtils.clamp(gameCam.position.x, gameCam.viewportWidth/2, WORLDWIDTH * TILEWIDTH - gameCam.viewportWidth/2);
        gameCam.position.y = MathUtils.clamp(gameCam.position.y, gameCam.viewportHeight/2, WORLDHEIGHT * TILEHEIGHT - gameCam.viewportHeight/2);
        gameCam.update();
    }

    public void clickDown(int button){
        Vector3 v3MousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        gameCam.unproject(v3MousePos);

        int nMouseXtile = (int) (v3MousePos.x / Constants.TILEWIDTH);
        int nMouseYtile = (int) (v3MousePos.y / Constants.TILEHEIGHT);
        //called once per click

        if(objInventory.getActive() != null) {
            try {
                if (ScrLoad.artBoxes[nMouseYtile][nMouseXtile] != null) {
                    if (objInventory.getActive().getToolType().equals(ScrLoad.artBoxes[nMouseYtile][nMouseXtile].getRequiredToolType())) { //check if tool type matches
                        if (objInventory.getActive().getToolLevel() >= ScrLoad.artBoxes[nMouseYtile][nMouseXtile].getRequiredToolLevel()) { //check if level matches

                            if (canMine(button, v3MousePos) == true) {
                                ScrLoad.artBoxes[nMouseYtile][nMouseXtile].setDurability(ScrLoad.artBoxes[nMouseYtile][nMouseXtile].getDurability() - objInventory.getActive().getMineRate());

                                if (ScrLoad.artBoxes[nMouseYtile][nMouseXtile].getDurability() <= 0) {
                                    objInventory.addTo(Item.createItem(ScrLoad.artBoxes[nMouseYtile][nMouseXtile]));
                                    ScrLoad.artBoxes[nMouseYtile][nMouseXtile] = null;
                                }
                            }


                        }
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }

//-------------------Killing--------------
            if(objInventory.getActive().getToolType().equals("Sword")){
                //do the killaz thing
                sprPlayer.decrementHealth(-objInventory.getActive().getDamage());
            }


//-------------Placing--------------- MUST BE LAST HAS POSSIBILITY TO MAKE NULL
            if (objInventory.getActive().getToolType().equals("Not")) {
                //error thing
                if(
                        (nMouseXtile + 1 < sprPlayer.getX() / Constants.TILEWIDTH || nMouseXtile > sprPlayer.getX() / Constants.TILEWIDTH + sprPlayer.getWidth()/Constants.TILEWIDTH )
                                ||
                        (nMouseYtile+1 < sprPlayer.getY() /Constants.TILEHEIGHT || nMouseYtile > sprPlayer.getY() / Constants.TILEHEIGHT + sprPlayer.getHeight()/ Constants.TILEHEIGHT)
                        )
                { //not inside yourself
                    //+1 on y prevents you from placing inside yourself when faling
                    try {
                        if (ScrLoad.artBoxes[nMouseYtile][nMouseXtile] == null) {
                            ScrLoad.artBoxes[nMouseYtile][nMouseXtile] = Tile.createTile(objInventory.getActive()); //not quite doing things properly, geting here not setting
                            ScrLoad.artBoxes[nMouseYtile][nMouseXtile].setX((nMouseXtile * Constants.TILEWIDTH));
                            ScrLoad.artBoxes[nMouseYtile][nMouseXtile].setY((nMouseYtile * Constants.TILEHEIGHT));
                            objInventory.getActive().decrementStack(-1);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    if (objInventory.getActive().getStack() <= 0) {
                        objInventory.setActive(null);
                    }
                }

            }
        }

    }//for killing not polling? or yes polling

    public void boundsCheckPlayer(){
        if(sprPlayer.getX() + sprPlayer.getWidth()> WORLDWIDTH * TILEWIDTH){
            sprPlayer.setdX(WORLDWIDTH * TILEWIDTH - sprPlayer.getWidth());
        }
        if(sprPlayer.getX() < 0){
            sprPlayer.setdX(0);
        }
        if(sprPlayer.getY() + sprPlayer.getHeight() > WORLDHEIGHT * TILEHEIGHT){
            sprPlayer.setdY(WORLDHEIGHT * TILEHEIGHT - sprPlayer.getHeight());
        }
        if(sprPlayer.getY() < 0){
            sprPlayer.setdY(0);
        }
    }

    public void checkEvents(){
        if(sprPlayer.getHealth() <= 0){
            System.out.println(sprPlayer.getHealth());
            game.nScreen = 4;
            game.updateState(game.nScreen);
        }
    }

    //<editor-fold desc="getters and setters">
    public int getSpawnX(){return nPlayerSpawnX;}
    public int getSpawnY(){
        return nPlayerSpawnY;
    }
    public void setPlayer(SpriteDiscrete updatedPlayer){
        sprPlayer = updatedPlayer;
    }
    public SpriteDiscrete getPlayer(){
        return sprPlayer;
    }
    public boolean[] getArbKeys(){
        return arbKeys;
    }
    public InventoryObj getInventoryObj(){return objInventory;}
    //</editor-fold>

    //----------------------------------------------Abstract Methods------------------------------------------------
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        checkEvents();

        //create subset
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                try{
                    nXSub = (int) sprPlayer.getX() / Constants.TILEWIDTH - 4 + nOffsetX + x;
                    nYSub = (int) sprPlayer.getY() / Constants.TILEHEIGHT - 4 + nOffsetY + y;
                    artSubsetBoxes[y][x] = ScrLoad.artBoxes[nYSub][nXSub];
                }catch (Exception e){}
            }
        }

        //0,0 is bottom left, set up like Q1 of cartesian system

        keyAction();
        sprPlayer.move(artSubsetBoxes);
        boundsCheckPlayer(); //set dx, dy not x, y so i am on same wavelength as matt's hit detection
        updateCam();
        drawMap();
        hud.update(sprPlayer, objInventory);
    }

    //<editor-fold desc="Screen Events - pause, resume">
    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
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
        batch.dispose();
        texPlay.dispose();
        texBack.dispose();
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
            game.nScreen = 3;
            game.updateState(game.nScreen);
        }

        if (keycode == Input.Keys.W) {
            arbKeys[0] = true;
        }
        if (keycode == Input.Keys.A) {
            arbKeys[3] = true;

        }
        if (keycode == Input.Keys.S) {
            arbKeys[2] = true;

        }
        if (keycode == Input.Keys.D) {
            arbKeys[1] = true;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            arbKeys[0] = false;
        }
        if (keycode == Input.Keys.A) {
            arbKeys[3] = false;

        }
        if (keycode == Input.Keys.S) {
            arbKeys[2] = false;

        }
        if (keycode == Input.Keys.D) {
            arbKeys[1] = false;
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
        arbKeys[4] = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        arbKeys[4] = false;
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
        //negative if up positive if down, always 1
        objInventory.switchActive(amount);
        return true;
    }
    //</editor-fold>
}
//<editor-fold desc="Links">
//----------------------------------------------Links-------------------------------------------------------------------

// https://stackoverflow.com/questions/41896919/java-lang-nullpointerexception-when-spritebatch-end-occurs
//weird, need to call super constructor in sprite2 class before anything in Sprite 2 is useful
//</editor-fold>
