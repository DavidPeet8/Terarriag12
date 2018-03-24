package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonValue;

public class Item{

    int nNumHeld = 0;
    String sType;
    boolean isTool;
    
    public Item(Texture tex, JsonValue item){
        isTool =  item.getBoolean("isTool");
        sType = item.getString("Type");
    }

    public static Item createItem(Tile t){
        Item item = new Item(t.tex, t.json);
        return item;
    }
    
}
