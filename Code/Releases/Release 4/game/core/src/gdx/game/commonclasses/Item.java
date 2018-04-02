package gdx.game.commonclasses;

import com.badlogic.gdx.utils.JsonValue;

public class Item{

    String sType;
    boolean isTool;
    
    public Item(JsonValue jvItem){
        isTool =  jvItem.getBoolean("isTool");
        sType = jvItem.getString("Type");
    }

    public static Item createItem(Tile t){
        Item item = new Item(t.jvJson);
        return item;
    }
    
}
