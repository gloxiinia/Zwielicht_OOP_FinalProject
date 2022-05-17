package gameobjects;

import java.util.ArrayList;
public class Scene extends Thing {

    private String north, south, east, west;
    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Character> characters = new ArrayList<>();

    public Scene(String name, String description, String examination, String n, String s, String e, String w){
        super(name, description, examination);
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;

    }

    //setters

    //directional exits
    public void setNorth(String north){
        this.north = north;
    }

    public void setSouth(String south){
        this.south = north;
    }

    public void setEast(String east){
        this.east = east;
    }

    public void setWest(String west){
        this.west = west;
    }

    //adding objects
    public void addObject(){
        Object object = new Object();
        objects.add(object);
    }

    //adding Characters
    public void addCharacter(){
        Character Character = new Character();
        characters.add(Character);
    }

    //getters
    public String getNorth(){
        return this.north;
    }

    public String getSouth(){
        return this.south;
    }

    public String getEast(){
        return this.east;
    }
    
    public String getWest(){
        return this.west;
    }

    public ArrayList<Object> getObjectsInScene(){
        return this.objects;
    }

    public ArrayList<Character> getCharactersInScene(){
        return this.characters;
    }
    
}
