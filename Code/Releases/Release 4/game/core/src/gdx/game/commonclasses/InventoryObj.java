package gdx.game.commonclasses;

public class InventoryObj {
    Item[] hotbar = new Item[10];
    Item[] inventory = new Item[30];

    public InventoryObj() {
        for (int x = 0; x < 30; x++) {
            inventory[x] = null;
        }
        for (int x = 0; x < 10; x++) {
            hotbar[x] = null;
        }

    }

    public Item[] getHotbar(){
        return hotbar;
    }
    public void setHotbar(Item[] newBar){
        hotbar = newBar;
    }
    public Item[] getInvenory(){
        return inventory;
    }
    public void setInventory(Item[] newInventoy){
        inventory = newInventoy;
    }


}
