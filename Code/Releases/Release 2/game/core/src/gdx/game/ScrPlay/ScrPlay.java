package gdx.game.ScrPlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.ScrLoad.ScrLoad;
import gdx.game.commonclasses.*;

public class ScrPlay implements Screen, InputProcessor {
    //----------------------------------------------Declare-------------------------------------------------------------

    GamTerarria game;
    OrthographicCamera cam;
    Viewport viewport;
    FileHandle Tiles = Gdx.files.local("JSON/Tile.json");

    //----------------------------------------------Load Textures-------------------------------------------------------

    Texture TexBack = new Texture("Back.jpg");
    Texture TexPlay = new Texture("player.jpg");

    //----------------------------------------------Create Sprites------------------------------------------------------

    SpriteWithSidesCommon sprPlayer = new SpriteWithSidesCommon(TexPlay, 16, 32,0,0,100, 0, 0);
    SpriteWithSidesCommon sprBackground = new SpriteWithSidesCommon(TexBack, 0,0,0,0,0, 0, 0);

    //----------------------------------------------Create Other classes------------------------------------------------

    SpriteBatch batch = new SpriteBatch();
    boolean[] arbKeys = new boolean[4]; //0 is w, 1 is d, 2 is s, 3 is a

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrPlay (GamTerarria game, OrthographicCamera cam, Viewport viewport){
        this.game = game;
        this.cam = cam;
        this.viewport = viewport;

        sprPlayer.setX(0 * Constants.nTileWidth);
        sprPlayer.setY(100 * Constants.nTileHeight);
        //scaling to block width and height?
        sprBackground.setX(cam.position.x - Gdx.graphics.getWidth()/2);
        sprBackground.setY(cam.position.y - Gdx.graphics.getHeight()/2);
    }

    //----------------------------------------------My Functions------------------------------------------------

    public void drawMap(){

        batch.begin();
        sprBackground.draw(batch);

        for (int y = 0; y < Constants.nWorldHeight; y++){
            for (int x = 0; x < Constants.nWorldWidth; x++){
                if(ScrLoad.sprBoxes[y][x] instanceof Tile){
                    ScrLoad.sprBoxes[y][x].draw(batch);
                }
            }
        }
        sprPlayer.draw(batch);
        batch.end();
    }

    public boolean canMine(int  button, Vector3 MousePos){
        if (button == Input.Buttons.LEFT) {

            if (MousePos.x < sprPlayer.getX() + Constants.nEffectiveRadius * Constants.nTileWidth
                    && MousePos.x > sprPlayer.getX() - Constants.nEffectiveRadius * Constants.nTileWidth) {
                if (MousePos.y < sprPlayer.getY() + Constants.nEffectiveRadius * Constants.nTileHeight
                        && MousePos.y > sprPlayer.getY() - Constants.nEffectiveRadius * Constants.nTileHeight) {
                    return true;
                }
            }
        }
        return false;
    }

    public void keyDownInput(int keycode){
        if(keycode == Input.Keys.UP){
            game.nScreen++;
            game.updateState(game.nScreen);
        }
        if(keycode == Input.Keys.DOWN){
            game.nScreen--;
            game.updateState(game.nScreen);
        }

        if(keycode == Input.Keys.W){
            arbKeys[0] = true;
        }
        if(keycode == Input.Keys.A){
            arbKeys[3] = true;

        }
        if(keycode == Input.Keys.S){
            arbKeys[2] = true;

        }
        if(keycode == Input.Keys.D){
            arbKeys[1] = true;

        }
    }

    public void keyUpInput(int keycode){
        if(keycode == Input.Keys.W){
            arbKeys[0] = false;
        }
        if(keycode == Input.Keys.A){
            arbKeys[3] = false;

        }
        if(keycode == Input.Keys.S){
            arbKeys[2] = false;

        }
        if(keycode == Input.Keys.D){
            arbKeys[1] = false;

        }
    }

    public void keyAction(){
        if(arbKeys[0] == true){
            cam.translate(0,1);
        }
        if(arbKeys[1] == true){
            cam.translate(1,0);
        }
        if(arbKeys[2] == true){
            cam.translate(0,-1);
        }
        if(arbKeys[3] == true){
            cam.translate(-1,0);
        }
        cam.update();
    }
    
    public void updateCam(){
        cam.position.set(sprPlayer.getX() * Constants.nTileWidth,
                sprPlayer.getY() * Constants.nTileHeight, 0);
        batch.setProjectionMatrix(cam.combined);
        cam.update();
        System.out.println("FUKERDEEZ");
    }

    //----------------------------------------------Abstract Methods------------------------------------------------

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        updateCam(); //is causing flickering
        keyAction();
        
        drawMap();

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
        game.dispose();
        batch.dispose();
        TexPlay.dispose();
    }
    
    @Override
    public boolean keyDown(int keycode) {
        keyDownInput(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyUpInput(keycode);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {  
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 MousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        cam.unproject(MousePos);

        if (canMine(button, MousePos) == true) {

            int nMouseXTILE = (int)(MousePos.x /  Constants.nTileWidth);
            int nMouseYTILE = (int)(MousePos.y /  Constants.nTileHeight);

            //try catch to get rid of need for bounds checking edges of array
            try {
                ScrLoad.sprBoxes[nMouseYTILE][nMouseXTILE] = null;

                System.out.println(ScrLoad.sprBoxes[nMouseYTILE][nMouseXTILE]);
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println(e);
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 MousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        cam.unproject(MousePos);

        if (canMine(pointer, MousePos) == true) {

            int nMouseXTILE = (int)(MousePos.x /  Constants.nTileWidth);
            int nMouseYTILE = (int)(MousePos.y /  Constants.nTileHeight);

            //try catch to get rid of need for bounds checking edges of array
            try {
                ScrLoad.sprBoxes[nMouseYTILE][nMouseXTILE] = null;

                System.out.println(ScrLoad.sprBoxes[nMouseYTILE][nMouseXTILE]);
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println(e);
            }
        }

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

// https://stackoverflow.com/questions/41896919/java-lang-nullpointerexception-when-spritebatch-end-occurs
//weird, need to call super constructor in sprite2 class before anything in Sprite 2 is useful