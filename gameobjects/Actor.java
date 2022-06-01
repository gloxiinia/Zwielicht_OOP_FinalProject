package gameobjects;

import java.math.BigDecimal;
import java.time.InstantSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Actor extends ThingHolder {

    private BigDecimal angerTendency, fearTendency, relationTendency, anger, fear, relation, happiness, happinessTendency;
    private ArrayList<String> inDialogueResponses = new ArrayList<>();
    private String inDialogueWith;
    private Scene scene;
    private ArrayList<Dialogue> dialogueList = new ArrayList<>();
    private boolean isInDialogue;
    private Map<Scene, Integer> sceneVisits = new HashMap<Scene, Integer>();

    //constructor for player
    public Actor(String aName, String aDescription, String anExamination, Scene aScene, ArrayList<String> aliases, ThingList tl, boolean isInDialogue, String inDialogueWith){
        super(aName, aDescription, anExamination, aliases, tl);
        this.scene = aScene;
        this.isInDialogue = isInDialogue;
        this.inDialogueWith = inDialogueWith;
    }
    
    public Actor(String aName, String aDescription, String anExamination, Scene aScene, int location, ArrayList<String> aliases, ThingList tl, 
                    BigDecimal anger, BigDecimal fear, BigDecimal relation, BigDecimal happiness, BigDecimal angerTendency, BigDecimal fearTendency, BigDecimal relationTendency, BigDecimal happinessTendency, 
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
        this.happinessTendency = happinessTendency;
        this.inDialogueResponses = inDialogueResponses;
    }

    public Actor(String aName, String aDescription, String anExamination, int location, ArrayList<String> aliases, ThingList tl, 
                    BigDecimal anger, BigDecimal fear, BigDecimal relation, BigDecimal happiness, BigDecimal angerTendency, BigDecimal fearTendency, BigDecimal relationTendency, BigDecimal happinessTendency){
        super(aName, aDescription, anExamination, location, aliases, tl);
        this.anger = anger;
        this.fear = fear;
        this.relation = relation;
        this.happiness = happiness;
        this.angerTendency = angerTendency;
        this.fearTendency = fearTendency;
        this.relationTendency = relationTendency;
        this.happinessTendency = happinessTendency;
    }

    //setters
    public void setScene(Scene aScene){
        this.scene = aScene;
    }
    //setting the actor's attributes
    public void setAnger(BigDecimal anger){
        this.anger = anger;
    }
    public void setFear(BigDecimal fear){
        this.fear = fear;
    }
    public void setRelation(BigDecimal relation){
        this.relation = relation;
    }
    public void setHappiness(BigDecimal happiness){
        this.happiness = happiness ;
    }

    //setting the actor's attribute tendencies (how prone are they to each attribute)
    public void setAngerTendency(BigDecimal angerTendency){
        this.angerTendency = angerTendency;
    }
    public void setFearTendency(BigDecimal fearTendency){
        this.fearTendency = fearTendency;
    }
    public void setRelationTendency(BigDecimal relationTendency){
        this.relationTendency = relationTendency;
    }
    public void setHappinessTendency(BigDecimal happinessTendency){
        this.happinessTendency = happinessTendency ;
    }

    //methods for adding to their attributes with a multiplier from their tendencies
    public void addAnger(BigDecimal angerChange){
        if(!(angerChange.compareTo(BigDecimal.ZERO) == 0)){
            if(this.angerTendency.compareTo(BigDecimal.ZERO) < 0){
                this.anger = this.anger.subtract(angerChange.multiply(getAngerTendency()));
            }else{
                this.anger = this.anger.add(angerChange.multiply(getAngerTendency()));
            }
        }
    }
    public void addFear(BigDecimal fearChange){
        if(!(fearChange.compareTo(BigDecimal.ZERO) == 0)){
            if(this.fearTendency.compareTo(BigDecimal.ZERO) < 0){
                this.fear = this.fear.subtract(fearChange.multiply(getFearTendency()));
            }else{
                this.fear = this.fear.add(fearChange.multiply(getFearTendency()));
            }
        }
    }
    public void addRelation(BigDecimal relationChange){
        if(!(relationChange.compareTo(BigDecimal.ZERO) == 0)){
            if(this.relationTendency.compareTo(BigDecimal.ZERO) < 0){
                this.relation = this.relation.subtract(relationChange.multiply(getRelationTendency()));
            }else{
                this.relation = this.relation.add(relationChange.multiply(getRelationTendency()));
            }
        }
        
    }

    public void addHappiness(BigDecimal happinessChange){
        if(!(happinessChange.compareTo(BigDecimal.ZERO) == 0)){
            if(this.happinessTendency.compareTo(BigDecimal.ZERO) < 0){
                this.happiness = this.happiness.subtract(happinessChange.multiply(getHappinessTendency()));
            }else{
                this.happiness = this.happiness.add(happinessChange.multiply(getHappinessTendency()));
            }
        }
    }

    

    //for all attribute changes
    public void addAllAttributes(BigDecimal angerChange, BigDecimal fearChange, BigDecimal relationChange, BigDecimal happinessChange){
        addAnger(angerChange);
        addFear(fearChange);
        addRelation(relationChange);
        addHappiness(happinessChange);
    }


    //setting whether an actor is in a dialogue
    public void setIfActorIsInDialogue(boolean isInDialogue){
        this.isInDialogue = isInDialogue;
    }
    //setting which other actor this actor is in a dialogue with
    public void setWhoActorInDialogueWith(String aName){
        this.inDialogueWith = aName;
    }
    //adding new scenes and their visiting number to the scenes and visits hashmap
    public void addSceneAndVisits(Scene aScene, int numVisits){
        this.sceneVisits.put(aScene, numVisits);
    }
    //add a visit (increment by 1) to the passed scene's value
    public void addVisit(Scene aScene){
        this.sceneVisits.merge(aScene, 1, Integer::sum);
    }


    //getters
    public Scene getScene(){
        return this.scene;
    }

    public BigDecimal getAnger(){
        return this.anger;
    }
    
    public BigDecimal getFear(){
        return this.fear;
    }
    
    public BigDecimal getRelation(){
        return this.relation;
    }
    
    public BigDecimal getHappiness(){
        return this.happiness;
    }

    public BigDecimal getAngerTendency() {
        return this.angerTendency;
    }
    public BigDecimal getFearTendency() {
        return this.fearTendency;
    }
    public BigDecimal getRelationTendency() {
        return this.relationTendency;
    }

    public BigDecimal getHappinessTendency(){
        return this.happinessTendency;
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

    public void printDialogues(){
        for(Dialogue d : this.dialogueList){
            System.out.println(d.getDialogue());
        }
    }

    public int getSpecificSceneVisits(Scene aScene){
        for (Scene i : this.sceneVisits.keySet()) {
            if(i.equals(aScene)){
                return this.sceneVisits.get(i);
            }
          }
        return 0;
    }

    public void printNPCCurrentAttributes(){

        System.out.println("Anger\t\t: " + getAnger());
        System.out.println("Fear\t\t:  " + getFear());
        System.out.println("Relation\t:  " + getRelation());
        System.out.println("Happiness\t: " + getHappiness());
    }
 




}
