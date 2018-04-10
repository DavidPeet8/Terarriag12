package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonValue;

import static gdx.game.commonclasses.Textures.*;

public class Item{

    //<editor-fold desc="Init">
    int nDurablility, nRequiredToolLevel, nRarity, nToolLevel, nMineRate, nDamage;
    String sType, sRequiredToolType, sBiome, sHardmode, sToolType;
    int nStack = 0;
    JsonValue jvItem;

    transient Texture tex; //lets texture not be saved, thus not takig up the overhead space, still a thinner tile?

    //</editor-fold>

    //----------------------Constructors----------------------------

    public Item(JsonValue jvItem, Texture tex){//mined blocks will give the constructor
        this.tex = tex;
        this.jvItem = jvItem;
        readJSON(jvItem);
    }

    public Item(String index){ //tools will acess this constructor currently as it is initially given this, no tex needed
        jvItem = jvTile.get(index);
        readJSON(jvItem); //error line
    }

    //---------------------My Functions-----------------------------

    public void readJSON(JsonValue jvItem){
        sType = jvItem.getString("Type");
        sBiome = jvItem.getString("Biome");
        sHardmode = jvItem.getString("Hardmode");
        sRequiredToolType = jvItem.getString("RequiredToolType"); //for some reason not loading properly
        sToolType = jvItem.getString("ToolType");

        nDurablility = jvItem.getInt("Durability");
        nRarity = jvItem.getInt("Rarity");
        nRequiredToolLevel = jvItem.getInt("RequiredToolLevel");
        nToolLevel = jvItem.getInt("ToolLevel");
        nMineRate = jvItem.getInt("MineRate");
        nDamage = jvItem.getInt("Damage");
    }

    public String getToolType(){
        return sToolType;
    }
    public String getRequiredToolType(){
        return sRequiredToolType;
    }
    public int getToolLevel(){
        return nToolLevel;
    }
    public int getMineRate(){
        return nMineRate;
    }

    public Texture getTex(){
        return tex;
    }

    //--------------------Static functions--------------------------

    public static Item createItem(Tile t){
        Item item = new Item(t.getJson(), t.getTex());
        return item;
    }
    
}
