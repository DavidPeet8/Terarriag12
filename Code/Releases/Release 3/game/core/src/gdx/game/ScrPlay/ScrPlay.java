package gdx.game.ScrPlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.GamTerarria;
import gdx.game.ScrLoad.ScrLoad;
import gdx.game.commonclasses.*;

import java.util.Arrays;

public class ScrPlay implements Screen, InputProcessor {
    //----------------------------------------------Declare-------------------------------------------------------------

    GamTerarria game;
    private OrthographicCamera cam;
    Viewport viewport;
    JsonReader json = new JsonReader();
    JsonValue tile = json.parse(Gdx.files.local("JSON/Tile.json"));
    int nInitScreenWidth, nInitScreenHeight;
    int nCurrentBlockDurability = 0;
    Item[] hotbar = new Item[10];
    Item[] inventory = new Item[30];

    //----------------------------------------------Load Textures-------------------------------------------------------
    Texture texBack = new Texture("Back.jpg");
    Texture texPlay = new Texture("player.jpg");

    JsonValue leaves = tile.get("1");
    JsonValue Grass = tile.get("2");
    JsonValue Dirt = tile.get("3");
    JsonValue Wood = tile.get("4");
    JsonValue Stone = tile.get("5");
    JsonValue Tin = tile.get("6");
    JsonValue Copper = tile.get("7");

    JsonValue Iron = tile.get("8");
    JsonValue Lead = tile.get("9");
    JsonValue Tungsten = tile.get("10");

    JsonValue Gold = tile.get("11");
    JsonValue Platinum = tile.get("12");
    JsonValue Silver = tile.get("13");

    JsonValue Cloud = tile.get("14");
    JsonValue Moss = tile.get("15");
    JsonValue Crimtane = tile.get("16");
    JsonValue Demonite = tile.get("17");

    JsonValue Cobalt = tile.get("18");
    JsonValue Palladium = tile.get("19");
    JsonValue Mythril = tile.get("20");
    JsonValue Orhicallum = tile.get("21");
    JsonValue Adamantite = tile.get("22");
    JsonValue Titanium = tile.get("23");

    JsonValue Water = tile.get("24");
    JsonValue Lava = tile.get("25");
    JsonValue Honey = tile.get("26");
    JsonValue Clout = tile.get("27");

    //----------------------------------------------Create Sprites------------------------------------------------------
    SpriteDiscrete sprPlayer = new SpriteDiscrete(texPlay, 300, 1720, 0.5, 0, 500, 50, 50);

    //----------------------------------------------Create Other classes------------------------------------------------
    private SpriteBatch batch = new SpriteBatch();
    private SpriteBatch fixedBatch = new SpriteBatch();
    boolean[] arbKeys = new boolean[4]; //0 is w, 1 is d, 2 is s, 3 is a
    public static Tile[][] subsetBoxes = new Tile[10][10];
    //add mouse pressed to this array?

    //----------------------------------------------Constructor---------------------------------------------------------
    public ScrPlay(GamTerarria game, Viewport viewport) {
        this.game = game;
        this.viewport = viewport;
        sprPlayer.init();

        nInitScreenWidth = Gdx.graphics.getWidth();
        nInitScreenHeight = Gdx.graphics.getHeight();

        sprPlayer.setX(100 * Constants.TILEWIDTH);
        sprPlayer.setY(100 * Constants.TILEHEIGHT);

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        cam.position.set(sprPlayer.getX(), sprPlayer.getY(), 0);
        cam.update();

        for (int x = 0; x < 30; x++) {
            inventory[x] = null;
        }
        for (int x = 0; x < 10; x++) {
            hotbar[x] = null;
        }

    }

    //----------------------------------------------My Functions------------------------------------------------
    public void drawMap() {
        fixedBatch.begin();
        fixedBatch.draw(texBack, 0, 0, nInitScreenWidth, nInitScreenHeight);
        fixedBatch.end();

        batch.begin();

        for (int y = 0; y < Constants.WORLDHEIGHT; y++) {
            for (int x = 0; x < Constants.WORLDWIDTH; x++) {
                if (ScrLoad.sprBoxes[y][x] instanceof Tile) {
                    ScrLoad.sprBoxes[y][x].draw(batch);
                }
            }
        }
        sprPlayer.draw(batch);
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

        if (arbKeys[0] == true) {
            sprPlayer.jump();
        }
        if (arbKeys[1] == true) {
            sprPlayer.changeDir(3);
        } else if (arbKeys[3] == true) {
            sprPlayer.changeDir(-3);
        } else {
            sprPlayer.changeDir(0);
        }
    }

    public void updateCam() {
        batch.setProjectionMatrix(cam.combined);
        cam.position.set(sprPlayer.getX(), sprPlayer.getY(), 0);
        cam.update();
    }

    //----------------------------------------------Abstract Methods------------------------------------------------
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //want to create subset here
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    int nX, nY;
                    try{
                        nX = (int) sprPlayer.getX() / Constants.TILEWIDTH - 5 + x;
                        nY = (int) sprPlayer.getY() / Constants.TILEHEIGHT - 5 + y;
                        subsetBoxes[y][x] = ScrLoad.sprBoxes[nY][nX];
                    }catch (Exception e){}
                }
            }


        keyAction();
        sprPlayer.move(subsetBoxes);

        updateCam();
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
        texPlay.dispose();
    }

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 MousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(MousePos);

        if (canMine(button, MousePos) == true) {

            int nMouseXtile = (int) (MousePos.x / Constants.TILEWIDTH);
            int nMouseYtile = (int) (MousePos.y / Constants.TILEHEIGHT);

            //try catch to get rid of need for bounds checking edges of array
            try {
                if (nCurrentBlockDurability == 0) {
                    ScrLoad.sprBoxes[nMouseYtile][nMouseXtile] = null;
                } else {
                    nCurrentBlockDurability--;
                }

                System.out.println(ScrLoad.sprBoxes[nMouseYtile][nMouseXtile]);
            } catch (ArrayIndexOutOfBoundsException e) {
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
        Vector3 MousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(MousePos);

        if (canMine(pointer, MousePos) == true) {

            int nMouseXtile = (int) (MousePos.x / Constants.TILEWIDTH);
            int nMouseYtile = (int) (MousePos.y / Constants.TILEHEIGHT);

            //try catch to get rid of need for bounds checking edges of array
            try {
                ScrLoad.sprBoxes[nMouseYtile][nMouseXtile] = null;

                System.out.println(ScrLoad.sprBoxes[nMouseYtile][nMouseXtile]);
            } catch (ArrayIndexOutOfBoundsException e) {
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
