
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import gameobjects.Item;
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
                boolean isUsable = Boolean.parseBoolean(line);
                line = reader.readLine();

                //is the item a key item?
                boolean isKeyItem = Boolean.parseBoolean(line);
                line = reader.readLine();

                //item examination (detailed description when you examine a location)
                String examination = "";
                while(!line.equals("END")){
                    examination += line + '\n';
                    line = reader.readLine();
                }

                Item anItem = new Item(name, description, examination, location, aliases, isPickupable, isUsable, isKeyItem);
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

    public static ThingList createNPCs(){
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
                boolean isUsable = Boolean.parseBoolean(line);
                line = reader.readLine();

                //is the item a key item?
                boolean isKeyItem = Boolean.parseBoolean(line);
                line = reader.readLine();

                //item examination (detailed description when you examine a location)
                String examination = "";
                while(!line.equals("END")){
                    examination += line + '\n';
                    line = reader.readLine();
                }

                Item anItem = new Item(name, description, examination, location, aliases, isPickupable, isUsable, isKeyItem);
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
                int visits = 0;
                Scene aScene = new Scene(name, description, examination, n, s, e, w, sceneItems, visits);
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

    //Testing out the ReadFile methods
    public static void main(String[] args ){
        ArrayList<Scene> checking = createScenes();
        for(Scene i : checking){
            System.out.println(i.getName());
            System.out.println(i.getDescription());
            System.out.println(i.getExamination());
            ThingList k = i.getThings();
            System.out.println(k.describeThings());

        }




    }
}
