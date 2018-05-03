package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonValue;

import java.awt.*;

import static gdx.game.commonclasses.Textures.*;

public class Item {

    //<editor-fold desc="Init">
    private int nDurablility, nRequiredToolLevel, nRarity, nToolLevel, nMineRate, nDamage;
    private int nX, nY, nWidth, nHeight;
    private String sType, sRequiredToolType, sBiome, sHardmode, sToolType;
    private int nStack = 1;//set as one so when you get one it starts saying you have one
    private JsonValue jvItem;

    private transient Texture tex; //lets texture not be saved, thus not takig up the overhead space, still a thinner tile?

    private static Rectangle rect = new Rectangle(0,0,0,0);
    //</editor-fold>

    //----------------------Constructors----------------------------

    public Item(JsonValue jvItem, Texture tex){//mined blocks will give the constructor
        this.tex = tex;
        this.jvItem = jvItem;
        readJSON(jvItem);
    }

    public Item(String index){ //tools will access this constructor currently as it is initially given this, no tex needed
        jvItem = jvTile.get(index);
        readJSON(jvItem);
        String sPath = jvItem.getString("Path");
        tex = (Texture) itemHashMap.get(sPath);
    }

    //---------------------My Functions-----------------------------

    public void readJSON(JsonValue jvItem){
        sType = jvItem.getString("Type");
        sBiome = jvItem.getString("Biome");
        sHardmode = jvItem.getString("Hardmode");
        sRequiredToolType = jvItem.getString("RequiredToolType");
        sToolType = jvItem.getString("ToolType");

        nDurablility = jvItem.getInt("Durability");
        nRarity = jvItem.getInt("Rarity");
        nRequiredToolLevel = jvItem.getInt("RequiredToolLevel");
        nToolLevel = jvItem.getInt("ToolLevel");
        nMineRate = jvItem.getInt("MineRate");
        nDamage = jvItem.getInt("Damage");
    }

    //<editor-fold desc="Getters">
    public String getToolType(){
        return sToolType;
    }
    public String getType(){return sType;}
    public String getRequiredToolType(){
        return sRequiredToolType;
    }
    public int getToolLevel(){
        return nToolLevel;
    }
    public int getMineRate(){
        return nMineRate;
    }
    public int getDamage(){
        return nDamage;
    }

    public Texture getTex(){
        return tex;
    }
    public int getStack(){
        return nStack;
    }

    public JsonValue getJvItem() {
        return jvItem;
    }

    public Rectangle getBoundingRectangle(){
        rect.x = nX;
        rect.y = nY;
        rect.width = nWidth;
        rect.height = nHeight;
        return rect;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void alterStack(int s){
        nStack += s;
    }
    public void setPosition(int x, int y){
        nX = x;
        nY = y;
    }
    public void setSize(int w, int h){
        nHeight = h;
        nWidth = w;
    }
    //</editor-fold>

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(tex, nX, nY, nWidth, nHeight);
        batch.end();
    }
    //--------------------Static functions--------------------------

    public static Item createItem(Tile t){
        Item item = new Item(t.getJson(), t.getTex());
        return item;
    }
    
}
