package gameobjects;

import java.util.ArrayList;

public class Thing {
    //Thing object that will be the superclass to all other game objects

    private String name, description, examination;
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
}
