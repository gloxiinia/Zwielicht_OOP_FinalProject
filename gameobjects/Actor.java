package gameobjects;

import java.util.ArrayList;

public class Actor extends ThingHolder {
    private String dialogue;
    private Scene scene;
    private ArrayList<String> dialogueList = new ArrayList<>();
    
    public Actor(String aName, String aDescription, String anExamination, Scene aScene, ThingList tl){
        super(aName, aDescription, anExamination, tl);
        this.scene = aScene;
    }

    //setters
    public void setScene(Scene aScene){
        this.scene = aScene;
    }

    //getters
    public Scene getScene(){
        return this.scene;
    }


}
