package gameobjects;

import java.util.ArrayList;

public class Scene extends ThingHolder{

    private int north, south, east, west;
    private boolean isDialogueFinished;
    private ArrayList<Actor> sceneNPCs = new ArrayList<>();

    public Scene(String aName, String aDescription, String anExamination, int n, int s, int e, int w, ThingList tl){
        super(aName, aDescription, anExamination, tl);
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
    }

    public Scene(String aName, String aDescription, String anExamination, int n, int s, int e, int w, ThingList tl, ArrayList<Actor> sceneNPCs){
        super(aName, aDescription, anExamination, tl);
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
        this.sceneNPCs = sceneNPCs;
    }

    //setters

    //directional exits
    public void setNorth(int north){
        this.north = north;
    }

    public void setSouth(int south){
        this.south = north;
    }

    public void setEast(int east){
        this.east = east;
    }

    public void setWest(int west){
        this.west = west;
    }



    public void setSceneNPCs(ArrayList<Actor> sceneNPCs){
        this.sceneNPCs = sceneNPCs;
    }

    public void setIfDialogueIsFinished(boolean check){
        this.isDialogueFinished = check;
    }


    //getters
    public int getNorth(){
        return this.north;
    }

    public int getSouth(){
        return this.south;
    }

    public int getEast(){
        return this.east;
    }
    
    public int getWest(){
        return this.west;
    }

    public ArrayList<Actor> getSceneNPCs(){
        return this.sceneNPCs;
    }

    public Actor getNPC(String aName){
        for(Actor npc : this.sceneNPCs){
            if(aName.equals(npc.getName()) || npc.getAliases().contains(aName));
            return npc;
        }
        return null;
    }

    public ArrayList<String> getNPCNames(){
        ArrayList<String> sceneNPCNames = new ArrayList<>();
        for(Actor npc : this.sceneNPCs){
            sceneNPCNames.add(npc.getName().toLowerCase());
            for(String alias : npc.getAliases()){
                sceneNPCNames.add(alias.toLowerCase());
            }
        }
        return sceneNPCNames;
    }

    public boolean getIsDialogueFinished(){
        return this.isDialogueFinished;
    }

    
}
