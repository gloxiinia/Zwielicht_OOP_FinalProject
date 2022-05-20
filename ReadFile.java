
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import gameobjects.Item;
import gameobjects.Scene;

public class ReadFile {
    
    public static ArrayList<Scene> createScenes(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("TestScenes.txt"));
            String line = reader.readLine();
            ArrayList<Scene> scenes = new ArrayList<>();

            while(!line.equals("ENDOFFILE")){
                //location name
                String name = line;
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

                Scene aScene = new Scene(name, description, examination, n, s, e, w);
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

    public static ArrayList<Item> createItems(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("TestItems.txt"));
            String line = reader.readLine();
            ArrayList<Item> items = new ArrayList<>();

            while(!line.equals("ENDOFFILE")){
                //item name
                String name = line;
                name = name.toLowerCase();
                
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

                //item examination (detailed description when you examine a location)
                String examination = "";
                while(!line.equals("END")){
                    examination += line + '\n';
                    line = reader.readLine();
                }

                Item anItem = new Item(name, description, examination, location, aliases, isPickupable, isUsable);
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

    //Testing out the ReadFile methods
    public static void main(String[] args ){
        ArrayList<Scene> checking = createScenes();
        for(int i = 0; i < checking.size(); i++){
            System.out.println(checking.get(i).getName());
            System.out.println(checking.get(i).getDescription());
            System.out.println(checking.get(i).getExamination());
        }

        ArrayList<Item> checking2 = createItems();
        for(int i = 0; i < checking2.size(); i++){
            System.out.println(checking2.get(i).getName());
            System.out.println(checking2.get(i).getDescription());
            System.out.println(checking2.get(i).getExamination());
            System.out.println(checking2.get(i).isItemPickupable());
            System.out.println(checking2.get(i).isItemUsable());
            System.out.println(checking2.get(i).getAliases());
        }
    }
}
