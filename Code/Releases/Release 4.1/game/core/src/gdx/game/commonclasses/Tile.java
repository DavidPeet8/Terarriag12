package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonValue;

public class Tile extends Sprite{ //implement disposability

    //<editor-fold desc="Init">
    int nDurablility, nRequiredToolLevel, nRarity, nToolLevel, nMineRate, nDamage;
    String sType, sRequiredToolType, sBiome, sHardmode, sToolType;
    JsonValue jvJson;
    //</editor-fold>

    public Tile(Texture tex, float fW, float fH, JsonValue jvJson){
        super(tex);
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

    public int getDurability(){
        return nDurablility;
    }
    public String getRequiredToolType(){return sRequiredToolType;}
    public int getRequiredToolLevel(){return nRequiredToolLevel;}
    public void setDurability(int d){
        nDurablility = d;
    }


    public static Tile createTile(Item i){
        Texture tex = new texture(i.getPath());
        Tile tile = new Tile(tex, );
        return tile;
    }

}
