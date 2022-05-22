package gameobjects;

import java.util.ArrayList;
import globals.Methods;

public class ThingList extends ArrayList<Thing> {
    //return the list + description of each item in the ThingList
    public String describeThings(){
        String s = "";
        if(this.size() == 0){
            s = "There's nothing here, it's empty.";
        }else{
            for(Thing i: this){
                s += s + Methods.capitalizeString(i.getName()) + ": \n" + i.getDescription() + '\n';
            }
        }

        return s;
    }

    public ArrayList<String> allThingNamesList(ThingList ItemList){
        ArrayList<String> allItemNames = new ArrayList<>();
        ArrayList<String> thingAliases = new ArrayList<>();
        for(Thing t : ItemList){
            allItemNames.add(t.getName());
            thingAliases = t.getAliases();
            for(String alias : thingAliases){
                allItemNames.add(alias);
            }
        }
        return allItemNames;
    }

    //return a specific thing
    public Thing thisThing(String aName){
        Thing something = null;
        String thingName = "";
        ArrayList<String> thingAliases;
        for (Thing i : this){
            thingAliases = i.getAliases();
            thingName = i.getName().trim().toLowerCase();
            if(thingName.equals(aName) || thingAliases.contains(aName)){
                something = i;
            }
        }
        return something;
    }   

}
