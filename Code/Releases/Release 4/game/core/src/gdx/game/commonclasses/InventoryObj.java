package gdx.game.commonclasses;

public class InventoryObj {

    //<editor-fold desc="Init">
    int nActiveSlot = 0;
    Item[] aritHotbar = new Item[10];
    Item[] aritInventory = new Item[30];
    Item active = aritHotbar[nActiveSlot];
    //</editor-fold>

    public InventoryObj() {
        //<editor-fold desc="Set Inventory & hotbar to null">
        for (int x = 0; x < 30; x++) {
            aritInventory[x] = null;
        }
        for (int x = 0; x < 10; x++) {
            aritHotbar[x] = null;
        }
        //</editor-fold>

//        aritHotbar[1] = new Item();
//        aritHotbar[2] = new Item();
//        aritHotbar[3] = new Item();
//        aritHotbar[4] = new Item();
    }

    public Item[] getHotbar(){
        return aritHotbar;
    }

    public void addToHotbar(Item item){
    }
    public Item getActive() {
        return active;
    }
    public void setActive(int nAmnt) {
        nActiveSlot += nAmnt;
        if(nActiveSlot < 0){
           nActiveSlot = 9;
        }
        if(nActiveSlot > 9){
            nActiveSlot = 0;
        }
        active = aritHotbar[nActiveSlot];
    }

    public Item[] getInvenory(){
        return aritInventory;
    }

    public void addToInventory(Item item){
        boolean Continue = true;
        //--------------Stack-----------------

        for (int i = 0; i < aritHotbar.length; i++) {
            if (aritHotbar[i] instanceof Item) {
                if (aritHotbar[i].sType == item.sType) {
                    if (aritHotbar[i].nStack < Constants.ITEMSTACKLIMIT) {
                        aritHotbar[i].nStack++;
                        Continue = false;
                        break;
                    }
                }
            }
        }

        if(Continue) {
            for (int i = 0; i < aritInventory.length; i++) {
                if (aritInventory[i] instanceof Item) {
                    if (aritInventory[i].sType == item.sType) {
                        if (aritInventory[i].nStack < Constants.ITEMSTACKLIMIT) {
                            aritInventory[i].nStack++;
                            Continue = false;
                            break;
                        }
                    }
                }
            }
        }

        //----------------add to null-----------------

        if(Continue) {
            for (int i = 0; i < aritHotbar.length; i++) {
                if (aritHotbar[i] == null) {
                    aritHotbar[i] = item;
                    Continue = false;
                    break;
                }
            }
        }

        if(Continue) {
            for (int i = 0; i < aritInventory.length; i++) {
                if (aritInventory[i] == null) {
                    aritInventory[i] = item;
                    break;
                }
            }
        }
    }


}
