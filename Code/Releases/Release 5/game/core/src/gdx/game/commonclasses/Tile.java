package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonValue;

public class Tile extends Sprite{

    //<editor-fold desc="Init">
    private int nDurablility, nRequiredToolLevel, nRarity, nToolLevel, nMineRate, nDamage;
    private String sType, sRequiredToolType, sBiome, sHardmode, sToolType;
    private JsonValue jvJson;
    private Texture tex;
    //</editor-fold>

    public Tile(Texture _tex, float fW, float fH, JsonValue jvJson){
        super(_tex);
        tex = _tex;
        setSize(fW, fH);
        this.jvJson = jvJson;

        if(jvJson != null){
            sType = jvJson.getString("Type");
            sBiome = jvJson.getString("Biome");
            sHardmode = jvJson.getString("Hardmode");
            sRequiredToolType = jvJson.getString("RequiredToolType");
            sToolType = jvJson.getString("ToolType");

            nDurablility = jvJson.getInt("Durability");
            nRarity = jvJson.getInt("Rarity");
            nRequiredToolLevel = jvJson.getInt("RequiredToolLevel");
            nToolLevel = jvJson.getInt("ToolLevel");
            nMineRate = jvJson.getInt("MineRate");
            nDamage = jvJson.getInt("Damage");
        }
    }

    //<editor-fold desc="Getters and setters">
    public int getDurability(){
        return nDurablility;
    }
    public String getRequiredToolType(){return sRequiredToolType;}
    public int getRequiredToolLevel(){return nRequiredToolLevel;}
    public void setDurability(int d){
        nDurablility = d;
    }
    public Texture getTex(){
        return tex;
    }
    public JsonValue getJson(){
        return jvJson;
    }
    //</editor-fold>


    public static Tile createTile(Item i){
        Texture tex = i.getTex(); // dont want to reload, exreme overhead from reloading textures, get from static class
        Tile tile = new Tile(tex, 16, 16, i.jvItem);
        return tile;
    }

}
