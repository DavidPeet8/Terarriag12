package gdx.game.commonclasses;

public class InventoryObj {

    //<editor-fold desc="Init">
    private int nActiveSlot = 0;
    private Item[] aritHotbar = new Item[10];
    private Item[][] aritInventory = new Item[3][10];
    //</editor-fold>

    public InventoryObj() {
        //<editor-fold desc="Set Inventory & hotbar to null">
        for (int y = 0; y < 3; y++) {
            for(int r = 0; r < 10; r++) {
                aritInventory[y][r] = null;
            }
        }
        for (int x = 0; x < 10; x++) {
            aritHotbar[x] = null;
        }
        //</editor-fold>

        //<editor-fold desc="Initial set Hotbar">
        aritHotbar[0] = new Item("44");
        aritHotbar[1] = new Item("45");
        aritHotbar[2] = new Item("46");
        aritHotbar[3] = new Item("47");
        //</editor-fold>
    }

    //<editor-fold desc="Getters">
    public Item getActive() {
        return aritHotbar[nActiveSlot];
    }
    public int getActiveSlot() {
        return nActiveSlot;
    }
    public Item[] getHotbar (){
        return aritHotbar;
    }
    public Item[][] getInvenory(){
        return aritInventory;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void switchActive(int nAmnt) {
        nActiveSlot += nAmnt;
        if(nActiveSlot < 0){
           nActiveSlot = 9;
        }
        if(nActiveSlot > 9){
            nActiveSlot = 0;
        }
    }

    public void addTo(Item item){
        boolean Continue = true;
        //--------------Stack-----------------

        for (int i = 0; i < aritHotbar.length; i++) {
            if (aritHotbar[i] instanceof Item) {
                if (aritHotbar[i].getType() == item.getType()) {
                    if (aritHotbar[i].getStack() < Constants.ITEMSTACKLIMIT) {
                        aritHotbar[i].alterStack(1);
                        Continue = false;
                        break;
                    }
                }
            }
        }

        if(Continue) {
            for (int i = 0; i < aritInventory.length; i++) {
                for(int r = 0; r < aritInventory[i].length; r++) {
                    if (aritInventory[i][r] instanceof Item) {
                        if (aritInventory[i][r].getType() == item.getType()) {
                            if (aritInventory[i][r].getStack() < Constants.ITEMSTACKLIMIT) {
                                aritInventory[i][r].alterStack(1);
                                Continue = false;
                                break;
                            }
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
            outerloop: //lables loop not break point
            for (int i = 0; i < aritInventory.length; i++) {
                for (int r = 0; r< aritInventory[i].length; r++) {
                    if (aritInventory[i][r] == null) {
                        aritInventory[i][r] = item;
                        break outerloop;
                    }
                }
            }
        }
    }

    public void setActive(Item i) {
        aritHotbar[nActiveSlot] = i;
    }
    //</editor-fold>
}
//https://stackoverflow.com/questions/886955/breaking-out-of-nested-loops-in-java