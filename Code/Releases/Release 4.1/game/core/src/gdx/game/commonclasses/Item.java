package gdx.game.commonclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Item{

    //<editor-fold desc="Init">
    int nDurablility, nRequiredToolLevel, nRarity, nToolLevel, nMineRate, nDamage;
    String sType, sRequiredToolType, sBiome, sHardmode, sToolType;
    int nStack = 0;

    JsonReader jrJson = new JsonReader();
    JsonValue jvTile = jrJson.parse(Gdx.files.local("JSON/Tile.json"));
    //</editor-fold>

    //----------------------Constructors----------------------------

    public Item(JsonValue jvItem){
        readJSON(jvItem);
    }

    public Item(String index){
        JsonValue jvItem = jvTile.get(index);
        readJSON(jvItem);
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

    public String getToolType(){
        return sRequiredToolType;
    }
    public int getToolLevel(){
        return nToolLevel;
    }
    public int getMineRate(){
        return nMineRate;
    }

    //--------------------Static functions--------------------------

    public static Item createItem(Tile t){
        Item item = new Item(t.jvJson);
        return item;
    }
    
}
