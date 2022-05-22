package gameobjects;

import java.util.ArrayList;

public class Thing {
    //Thing object that will be the superclass to all other game objects

    private String name, description, examination;
    private boolean isPickupable, isUsable, isKeyItem;
    private ArrayList<String> aliases = new ArrayList<>();

    //constructor method
    public Thing(){
        this.name = "[REDACTED]";
        this.description = "[CLASSIFIED]";
        this.examination = "[HIGHER LEVEL ACCESS REQUIRED]";
    }

    public Thing(String name, String description, String examination){
        this.name = name;
        this.description = description;
        this.examination = examination;
    }

    public Thing(String name, String description, String examination, ArrayList<String> aliases){
        this.aliases = aliases;
        this.name = name;
        this.description = description;
        this.examination = examination;
    }

    //constructor for Items
    public Thing(String name, String description, String examination, ArrayList<String> aliases, boolean isPickupable, boolean isUsable, boolean isKeyItem){
        this.aliases = aliases;
        this.name = name;
        this.description = description;
        this.examination = examination;
        this.isPickupable = isPickupable;
        this.isUsable = isUsable;
        this.isKeyItem = isKeyItem;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setExamination(String examination){
        this.examination = examination;
    }

    public void addAlias(String alias){
        this.aliases.add(alias);
    }

    public void setThingPickupability(boolean isPickupable){
        this.isPickupable = isPickupable;
    }

    public void setThingUsability(boolean isUsable){
        this.isUsable = isUsable;
    }

    //getters
    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getExamination(){
        return this.examination;
    }

    public ArrayList<String> getAliases(){
        return this.aliases;
    }

    public boolean isThingPickupable(){
        return this.isPickupable;
    }

    public boolean isThingUsable(){
        return this.isUsable;
    }

    public boolean isThingKey(){
        return isKeyItem;
    }


}
