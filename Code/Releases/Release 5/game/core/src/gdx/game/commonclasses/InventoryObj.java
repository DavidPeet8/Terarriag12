package gdx.game.commonclasses;

public class InventoryObj {

    //<editor-fold desc="Init">
    private int nActiveSlot = 0;
    private Item[] aritHotbar = new Item[10];
    private Item[] aritInventory = new Item[30];
    private Item itActive;
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

        aritHotbar[0] = new Item("28");
        aritHotbar[1] = new Item("29");
        aritHotbar[2] = new Item("30");
        aritHotbar[3] = new Item("31");

        itActive = aritHotbar[nActiveSlot];
    }

    public Item getActive() {
        return itActive;
    }
    public void setActive(int nAmnt) {
        nActiveSlot += nAmnt;
        if(nActiveSlot < 0){
           nActiveSlot = 9;
        }
        if(nActiveSlot > 9){
            nActiveSlot = 0;
        }
        itActive = aritHotbar[nActiveSlot];
    }

    public Item[] getInvenory(){
        return aritInventory;
    }

    public void addTo(Item item){
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

    public Item[] getHotbar (){
        return aritHotbar;
    }

}
