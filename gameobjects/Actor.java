package gameobjects;

import java.util.ArrayList;

public class Actor extends ThingHolder {

    private int anger, fear, relation, happiness; 
    double angerTendency, fearTendency, relationTendency, happinessTendency;
    private Dialogue dialogue;
    private Scene scene;
    private ArrayList<Dialogue> dialogueList = new ArrayList<>();

    public Actor(String aName, String aDescription, String anExamination, Scene aScene, ArrayList<String> aliases, ThingList tl){
        super(aName, aDescription, anExamination, aliases, tl);
        this.scene = aScene;
    }
    
    public Actor(String aName, String aDescription, String anExamination, Scene aScene, int location, ArrayList<String> aliases, ThingList tl, 
                    int anger, int fear, int relation, int happiness, double angerTendency, double fearTendency, double relationTendency, double happinessTendency){
        super(aName, aDescription, anExamination, location, aliases, tl);
        this.scene = aScene;
        this.anger = anger;
        this.fear = fear;
        this.relation = relation;
        this.happiness = happiness;
        this.angerTendency = angerTendency;
        this.fearTendency = fearTendency;
        this.relationTendency = relationTendency;
    }

    //setters
    public void setScene(Scene aScene){
        this.scene = aScene;
    }
    public void setAnger(int anger){
        this.anger = anger;
    }
    public void setFear(int fear){
        this.fear = fear;
    }
    public void setRelation(int relation){
        this.relation = relation;
    }
    public void setHappiness(int happiness){
        this.happiness = happiness;
    }


    //getters
    public Scene getScene(){
        return this.scene;
    }

    public int getAnger(){
        return this.anger;
    }
    
    public int getFear(){
        return this.fear;
    }
    
    public int getRelation(){
        return this.relation;
    }
    
    public int getHappiness(){
        return this.happiness;
    }


}
