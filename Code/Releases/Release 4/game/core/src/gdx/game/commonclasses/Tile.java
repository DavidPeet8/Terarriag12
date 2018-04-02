package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonValue;

public class Tile extends Sprite{

    //<editor-fold desc="Init">
    public Texture tex;
    boolean bGravAffected, bSolid;
    int nDurablility;
    JsonValue jvJson;
    //</editor-fold>

    public Tile(Texture tex, float fW, float fH, boolean bGravAffected, boolean bSolid, JsonValue jvJson){
        super(tex);
        setSize(fW, fH);

        this.tex = tex;
        this.bGravAffected = bGravAffected;
        this.bSolid = bSolid;
        this.jvJson = jvJson;

        if(jvJson != null){
            nDurablility = jvJson.getInt("Durability");
        }
    }

    public int getDurability(){
        return nDurablility;
    }
    public void setDurability(int d){
        nDurablility = d;
    }

}
