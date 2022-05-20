package gameobjects;

import java.util.ArrayList;

public class Item extends Thing {
    private boolean isPickupable;
    private boolean isUsable;
    private int location;

    public Item(String aName, String aDescription, String anExamination, int location, ArrayList<String> aliases, boolean isPickupable, boolean isUsable){
        super(aName, aDescription, anExamination, aliases);
        this.isPickupable = isPickupable;
        this.isUsable = isUsable;
        this.location = location;

    }

    //setters
    public void setItemPickupability(boolean isPickupable){
        this.isPickupable = isPickupable;
    }

    public void setItemUsability(boolean isUsable){
        this.isUsable = isUsable;
    }

    //getters

    public boolean isItemPickupable(){
        return this.isPickupable;
    }

    public boolean isItemUsable(){
        return this.isUsable;
    }

    public int getLocation(){
        return this.location;
    }


}
