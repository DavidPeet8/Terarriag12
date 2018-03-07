package gdx.game.ScrPlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.ScrGravity.Sprite2;
import gdx.game.ScrLoad.ScrLoad;
import gdx.game.ScrLoad.Tile;
import gdx.game.GamTerarria;
import gdx.game.commonclasses.Constants;

public class ScrPlay implements Screen, InputProcessor {
    //----------------------------------------------Declare-------------------------------------------------------------

    GamTerarria game;

    FileHandle Tiles = Gdx.files.local("JSON/Tile.json");

    //----------------------------------------------Load Textures-------------------------------------------------------

    //Intellij and Netbeans load assets differently
    Texture TexBack = new Texture("Back.jpg");
    Texture TexPlay = new Texture("player.jpg");

    //----------------------------------------------Create Sprites------------------------------------------------------

    Sprite2 player = new Sprite2(TexPlay, 16, 32, 0,0,0,0,100);
    Sprite2 Background = new Sprite2(TexBack, 0, 0, 0,0,0,0,0);

    //----------------------------------------------Create Other classes------------------------------------------------

    OrthographicCamera cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    SpriteBatch batch = new SpriteBatch();

    //----------------------------------------------Constructor---------------------------------------------------------

    public ScrPlay (GamTerarria game){
        this.game = game;

        cam.setToOrtho(false);
        player.setX(0 * Constants.nTileWidth);
        player.setY(100 * Constants.nTileHeight);
        cam.position.set(player.getX(), player.getY(), 0);
        cam.update();

        Background.setX(cam.position.x - Gdx.graphics.getWidth()/2);
        Background.setY(cam.position.y - Gdx.graphics.getHeight()/2);
        batch.setProjectionMatrix(cam.combined);
    }

    //----------------------------------------------My Functions------------------------------------------------

    public void drawMap(){

        batch.begin();
        Background.draw(batch);

        //can do looping much more efficiantly now
        for (int y = 0; y < Constants.nWorldHeight; y++){
            for (int x = 0; x < Constants.nWorldWidth; x++){
                if(ScrLoad.sprBoxes[y][x] instanceof Tile){
                    ScrLoad.sprBoxes[y][x].draw(batch);
                }
            }
        }
        player.draw(batch);
        batch.end();
    }


    //----------------------------------------------Abstract Methods------------------------------------------------

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        System.out.println(game.nScreen);
        
        cam.position.x = player.getX();
        cam.position.y = player.getY();

        Background.setX(cam.position.x - Gdx.graphics.getWidth()/2);
        Background.setY(cam.position.y - Gdx.graphics.getHeight()/2);
        cam.update();
        
        drawMap();

    }

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

    @Override
    public void dispose() {
        game.dispose();
        batch.dispose();
        TexPlay.dispose();
    }
    
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.UP){
            System.out.println("KILL ME");
            game.nScreen++;
            game.updateState(game.nScreen);
        }
        if(keycode == Input.Keys.DOWN){
            System.out.println("Screw life");
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

// https://stackoverflow.com/questions/41896919/java-lang-nullpointerexception-when-spritebatch-end-occurs
//weird, need to call super constructor in sprite2 class before anything in Sprite 2 is useful