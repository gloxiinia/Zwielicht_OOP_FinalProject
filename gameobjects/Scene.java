package gameobjects;

public class Scene extends Thing {

    private int north, south, east, west;

    public Scene(String aName, String aDescription, String anExamination, int n, int s, int e, int w){
        super(aName, aDescription, anExamination);
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;

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

}
