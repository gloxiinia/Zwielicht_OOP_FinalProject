package gameobjects;

public class ThingHolder extends Thing{

    private ThingList things = new ThingList();

    public ThingHolder(String aName, String aDescription, String anExamination,  ThingList tl){
        super(aName, aDescription, anExamination);
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
