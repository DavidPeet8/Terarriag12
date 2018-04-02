package gdx.game.commonclasses;

public class InventoryObj {
    Item[] aritHotbar = new Item[10];
    Item[] aritInventory = new Item[30];

    public InventoryObj() {
        for (int x = 0; x < 30; x++) {
            aritInventory[x] = null;
        }
        for (int x = 0; x < 10; x++) {
            aritHotbar[x] = null;
        }

//        aritHotbar[1] = new Item();
//        aritHotbar[2] = new Item();
//        aritHotbar[3] = new Item();
//        aritHotbar[4] = new Item();
    }

    public Item[] getHotbar(){
        return aritHotbar;
    }
    public void setHotbar(Item[] newBar){
        aritHotbar = newBar;
    }
    public Item[] getInvenory(){
        return aritInventory;
    }
    public void setInventory(Item[] newInventoy){
        aritInventory = newInventoy;
    }


}
