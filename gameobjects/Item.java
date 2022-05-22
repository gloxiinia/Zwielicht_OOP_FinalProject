package gameobjects;

import java.util.ArrayList;

public class Item extends Thing {
    private int location;
    private boolean isKeyItem;

    public Item(String aName, String aDescription, String anExamination, int location, ArrayList<String> aliases, boolean isPickupable, boolean isUsable, boolean isKeyItem){
        super(aName, aDescription, anExamination, aliases, isPickupable, isUsable, isKeyItem);
        this.location = location;

    }

    public int getLocation(){
        return this.location;
    }


}
