package gameobjects;

import java.util.ArrayList;

public class ThingHolder extends Thing{

    private ThingList things = new ThingList();

    public ThingHolder(String aName, String aDescription, String anExamination, ThingList tl){
        super(aName, aDescription, anExamination);
        this.things = tl;
    }

    public ThingHolder(String aName, String aDescription, String anExamination, ArrayList<String> aliases, ThingList tl){
        super(aName, aDescription, anExamination, aliases);
        this.things = tl;
    }

    //constructor for NPCs
    public ThingHolder(String aName, String aDescription, String anExamination,  int location, ArrayList<String> aliases, ThingList tl){
        super(aName, aDescription, anExamination, location, aliases);
        this.things = tl;
    }

    //setter
    public void setThings(ThingList things){
        this.things = things;
    }

    //getter
    public ThingList getThings(){
        return things;
    }
}
