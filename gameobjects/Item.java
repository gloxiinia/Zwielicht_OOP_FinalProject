package gameobjects;

import java.util.ArrayList;

public class Item extends Thing {


    public Item(String aName, String aDescription, String anExamination, int aLocation, ArrayList<String> aliases, boolean isPickupable, boolean isUsable, boolean isKeyItem){
        super(aName, aDescription, anExamination, aLocation, aliases, isPickupable, isUsable, isKeyItem);
    

    }



}
