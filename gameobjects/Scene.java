package gameobjects;

import java.util.ArrayList;

public class Scene extends ThingHolder{

    private int north, south, east, west, visits;
    private ArrayList<Actor> sceneNPCs = new ArrayList<>();

    public Scene(String aName, String aDescription, String anExamination, int n, int s, int e, int w, ThingList tl, int visits){
        super(aName, aDescription, anExamination, tl);
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
        this.visits = visits;
    }

    public Scene(String aName, String aDescription, String anExamination, int n, int s, int e, int w, ThingList tl, int visits, ArrayList<Actor> sceneNPCs){
        super(aName, aDescription, anExamination, tl);
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
        this.visits = visits;
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

    public void addVisit(){
        this.visits++;
    }

    public void setSceneNPCs(ArrayList<Actor> sceneNPCs){
        this.sceneNPCs = sceneNPCs;
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

    public int getVisits(){
        return this.visits;
    }

    public Actor getNPC(String aName){
        for(Actor npc : sceneNPCs){
            if(aName.equals(npc.getName()));
            return npc;
        }
        return null;
    }

    
}
