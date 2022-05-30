package gameobjects;

import java.time.InstantSource;
import java.util.ArrayList;

public class Actor extends ThingHolder {

    private double angerTendency, fearTendency, relationTendency, happinessTendency, anger, fear, relation, happiness; 
    private ArrayList<String> inDialogueResponses = new ArrayList<>();
    private String inDialogueWith;
    private Scene scene;
    private ArrayList<Dialogue> dialogueList = new ArrayList<>();
    private boolean isInDialogue;

    //constructor for player
    public Actor(String aName, String aDescription, String anExamination, Scene aScene, ArrayList<String> aliases, ThingList tl, boolean isInDialogue, String inDialogueWith){
        super(aName, aDescription, anExamination, aliases, tl);
        this.scene = aScene;
        this.isInDialogue = isInDialogue;
        this.inDialogueWith = inDialogueWith;
    }
    
    public Actor(String aName, String aDescription, String anExamination, Scene aScene, int location, ArrayList<String> aliases, ThingList tl, 
                    double anger, double fear, double relation, double happiness, double angerTendency, double fearTendency, double relationTendency, double happinessTendency, 
                    ArrayList<String> inDialogueResponses){
        super(aName, aDescription, anExamination, location, aliases, tl);
        this.scene = aScene;
        this.anger = anger;
        this.fear = fear;
        this.relation = relation;
        this.happiness = happiness;
        this.angerTendency = angerTendency;
        this.fearTendency = fearTendency;
        this.relationTendency = relationTendency;
        this.inDialogueResponses = inDialogueResponses;
    }

    //setters
    public void setScene(Scene aScene){
        this.scene = aScene;
    }
    public void setAnger(int anger, double angerTendency){
        this.anger = anger * angerTendency;
    }
    public void setFear(int fear, double fearTendency){
        this.fear = fear * fearTendency;
    }
    public void setRelation(int relation, double relationTendency){
        this.relation = relation * relationTendency;
    }
    public void setHappiness(int happiness, double happinessTendency){
        this.happiness = happiness * happinessTendency;
    }
    public void setIfActorIsInDialogue(boolean isInDialogue){
        this.isInDialogue = isInDialogue;
    }
    public void setWhoActorInDialogueWith(String aName){
        this.inDialogueWith = aName;
    }


    //getters
    public Scene getScene(){
        return this.scene;
    }

    public double getAnger(){
        return this.anger;
    }
    
    public double getFear(){
        return this.fear;
    }
    
    public double getRelation(){
        return this.relation;
    }
    
    public double getHappiness(){
        return this.happiness;
    }

    public boolean isActorInDialogue(){
        return this.isInDialogue;
    }

    public String getExamineResponse(){
        return this.inDialogueResponses.get(0);
    }

    public String getMoveResponse(){
        return this.inDialogueResponses.get(1);
    }

    public String getTakeResponse(){
        return this.inDialogueResponses.get(2);
    }

    public String getDropResponse(){
        return this.inDialogueResponses.get(3);
    }

    public String getWhoActorInDialogueWith(){
        return this.inDialogueWith;
    }




}
