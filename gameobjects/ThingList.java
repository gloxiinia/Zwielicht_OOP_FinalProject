package gameobjects;

import java.util.ArrayList;

public class ThingList extends ArrayList<Thing> {
    //return the list + description of each item in the ThingList
    public String describeThings(){
        String s = "";
        if(this.size() == 0){
            s = "There's nothing here, it's empty.";
        }else{
            for(Thing i: this){
                s += s + i.getName() + ": \n" + i.getDescription() + '\n';
            }
        }

        return s;
    }

    //return a specific thing
    public Thing thisItem(String aName){
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
