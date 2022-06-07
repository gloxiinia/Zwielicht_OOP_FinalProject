package globals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gameobjects.Actor;
import gameobjects.Scene;
import gameobjects.Thing;
import gameobjects.ThingList;

public class ReadFile {

    //method to read and create the game items
    public static ThingList createItems(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("TestItems.txt"));
            String line = reader.readLine();
            ThingList items = new ThingList();

            while(!line.equals("ENDOFFILE")){
                //item name
                String name = line;
                name = name.trim().toLowerCase();
                
                line = reader.readLine();

                //item location
                int location = Integer.parseInt(line);
                line = reader.readLine();

                //item aliases (other names for the item)
                ArrayList<String> aliases = new ArrayList<>(Arrays.asList(line.split(",")));
                line = reader.readLine();
                
                //item description
                String description = "";
                while(!line.equals("PICKUP")){
                    description += line + '\n';
                    line = reader.readLine();
                }
                line = reader.readLine();

                //is the item pickupable?
                boolean isPickupable = Boolean.parseBoolean(line);
                line = reader.readLine();
                
                //is the item usable?
                //boolean isUsable = Boolean.parseBoolean(line);
                //line = reader.readLine();

                //is the item a key item?
                boolean isKeyItem = Boolean.parseBoolean(line);
                line = reader.readLine();

                //item examination (detailed description when you examine a location)
                String examination = "";
                while(!line.equals("END")){
                    examination += line + '\n';
                    line = reader.readLine();
                }

                Thing anItem = new Thing(name, description, examination, location, aliases, isPickupable, isKeyItem);
                items.add(anItem);

                line = reader.readLine();
            }

            reader.close();
            return items;

        } catch (IOException e){
            System.out.println("File could not be accessed, try again!");
        }
        return null;
    }

    public static ArrayList<Actor> createSceneNPCs(Scene aScene){

        ThingList allItems = createItems();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Edelmar.txt"));
            String line = reader.readLine();
            ArrayList<Actor> NPCs = new ArrayList<>();

            while(!line.equals("ENDOFFILE")){
                //actor name
                String name = line;
                name = name.trim().toLowerCase();
                
                line = reader.readLine();

                //actor location
                int location = Integer.parseInt(line);
                line = reader.readLine();

                //actor aliases (other names for the actor)
                ArrayList<String> aliases = new ArrayList<>(Arrays.asList(line.split(",")));
                line = reader.readLine();

                //actor inventory
                ArrayList<String> actorInventoryList = new ArrayList<>(Arrays.asList(line.split(",")));
                ThingList actorInventory = new ThingList();
                for(String itemName : actorInventoryList){
                    actorInventory.add(allItems.thisThing(itemName));
                }
                line = reader.readLine();
                
                //actor description
                String description = "";
                while(!line.equals("ATTRIBUTES")){
                    description += line + '\n';
                    line = reader.readLine();
                }
                line = reader.readLine();

                //actor attributes
                ArrayList<String> attributesList = new ArrayList<>(Arrays.asList(line.split(",")));
                //base anger
                BigDecimal baseAnger = new BigDecimal(attributesList.get(0));
                //base fear
                BigDecimal baseFear = new BigDecimal(attributesList.get(1));
                //base relation
                BigDecimal baseRelation = new BigDecimal(attributesList.get(2));
                //base happiness
                BigDecimal baseHappiness = new BigDecimal(attributesList.get(3));

                line = reader.readLine();

                //actor tendencies
                ArrayList<String> tendenciesList = new ArrayList<>(Arrays.asList(line.split(",")));
                //angertendency (How prone they are to anger)
                BigDecimal angerTendency = new BigDecimal(tendenciesList.get(0));
                //fearTendency (How prone they are to fear)
                BigDecimal fearTendency = new BigDecimal(tendenciesList.get(1));
                //relationTendency (How prone they are to relation)
                BigDecimal relationTendency = new BigDecimal(tendenciesList.get(2));
                //happinessTendency (How prone they are to happiness)
                BigDecimal happinessTendency = new BigDecimal(tendenciesList.get(3));
                line = reader.readLine();

                //examine response when in dialogue
                ArrayList<String> dialogueResponseList = new ArrayList<>(Arrays.asList(line.split("@")));
                line = reader.readLine();

                
                //item examination (detailed description when you examine a location)
                String examination = "";
                while(!line.equals("END")){
                    examination += line + '\n';
                    line = reader.readLine();
                }

                Actor anActor = new Actor(name, description, examination, aScene, location, aliases, actorInventory, baseAnger, baseFear, baseRelation, baseHappiness, angerTendency,
                                    fearTendency, relationTendency, happinessTendency, dialogueResponseList);
                NPCs.add(anActor);

                line = reader.readLine();
            }

            reader.close();
            return NPCs;

        } catch (IOException e){
            System.out.println("File could not be accessed, try again!");
        }
        return null;
    }

    //method to read and create the game scenes
    public static ArrayList<Scene> createScenes(){

        ThingList allItems = createItems();
        

        try{
            BufferedReader reader = new BufferedReader(new FileReader("TestScenes.txt"));
            String line = reader.readLine();
            ArrayList<Scene> scenes = new ArrayList<>();

            while(!line.equals("ENDOFFILE")){
                //location name
                String name = line.trim();
                line = reader.readLine();

                //location index
                String index = line.trim();
                int numIndex = Integer.parseInt(index);
                line = reader.readLine();

                //location exits
                ArrayList<String> neighbors = new ArrayList<>(Arrays.asList(line.split(",")));

                int n = Integer.parseInt(neighbors.get(0));
                int s = Integer.parseInt(neighbors.get(1));
                int e = Integer.parseInt(neighbors.get(2));
                int w = Integer.parseInt(neighbors.get(3));
                line = reader.readLine();
                

                //location description
                String description = "";
                while(!line.equals("EXAMINE")){
                    description += line + '\n';
                    line = reader.readLine();
                }
                line = reader.readLine();

                //location examination (detailed description when you examine a location)
                String examination = "";
                while(!line.equals("END")){
                    examination += line + '\n';
                    line = reader.readLine();
                }

                ThingList sceneItems = new ThingList();
                for(Thing i : allItems){
                    if(i.getLocation() == numIndex ){
                        sceneItems.add(i);
                    }
                }

                Scene aScene = new Scene(name, description, examination, n, s, e, w, sceneItems);
                ArrayList<Actor> sceneNPCs = createSceneNPCs(aScene);
                aScene.setSceneNPCs(sceneNPCs);
                scenes.add(aScene);

                line = reader.readLine();

            }

            reader.close();
            return scenes;

        } catch (IOException e){
            System.out.println("File could not be accessed, try again!");
        }
        return null;

    }

    //getting a specific line in a txt file
    public static String getSpecificLine(int lineNum, String fileName) throws IOException{
        String line = Files.readAllLines(Paths.get(fileName)).get(lineNum - 1);
        return line;
    }

    //parsing a line in a txt by splitting it according to the regex (@)
    public static ArrayList<String> parseLine(String line){
        ArrayList<String> splitLine = new ArrayList<>(Arrays.asList(line.split("@")));
        splitLine.add(splitLine.get(splitLine.size()-1).trim());
        return splitLine;
    }

    //splitting an ArrayList<String> by commas
    public static ArrayList<String> splitByCommas(ArrayList<String> line, int position){
        ArrayList<String> splitLine = new ArrayList<>();
        if(position < 0){
            List<String> result = Arrays.asList(line.get(line.size() + position).split(","));
            splitLine.addAll(result);
        }else{
            List<String> result = Arrays.asList(line.get(position).split(","));
            splitLine.addAll(result);
            
        }
        return splitLine;
    }

    //Testing out the ReadFile methods
    public static void main(String[] args ){
        ArrayList<Scene> checking = createScenes();





    }
}
