import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import gameobjects.Actor;
import gameobjects.Dialogue;
import gameobjects.Scene;
import gameobjects.ThingList;

public class Test {
    public static ArrayList<Integer> nextList = new ArrayList<>();
    public void startDialogue(){
        System.out.println("You've started dialogue.");
    }

    public void endDialogue(){
        
    }
    public static String getSpecificLine(int lineNum, String fileName) throws IOException{
        String line = Files.readAllLines(Paths.get(fileName)).get(lineNum - 1);
        return line;
    }

    public static ArrayList<String> parseLine(String line){
        ArrayList<String> splitLine = new ArrayList<>(Arrays.asList(line.split("@")));
        splitLine.add(splitLine.get(splitLine.size()-1).trim());
        return splitLine;
    }

    public static ArrayList<String> getnextDialogueLine(ArrayList<String> line){
        ArrayList<String> nextDialogueLine = new ArrayList<>(Arrays.asList(line.get(line.size()-1).split(",")));
        return nextDialogueLine;
    }

    public static void getDialogues(String aLine) throws NumberFormatException, IOException{
        ArrayList<String> splitDialogue = parseLine(aLine);
        String dialogue = splitDialogue.get(1);
        ArrayList<String> nextDialogueLine = getnextDialogueLine(splitDialogue);
        Dialogue myDialogue = new Dialogue();
        myDialogue.setnextDialogueLine(nextDialogueLine);
        myDialogue.setDialogue(dialogue);

        if(myDialogue.getFirstNextDialogue() != -1){
            if(splitDialogue.get(0).equals("text")){
                nextList.clear();
                System.out.println(myDialogue.getDialogue());
                for(String number : myDialogue.getNextDialogueLine()){
                    String nextLine = getSpecificLine(Integer.parseInt(number.trim()), "TestScene.txt");
                    getDialogues(nextLine);
                }
            }else{
                nextList.add(myDialogue.getFirstNextDialogue());
                System.out.println(nextList.size() + ". " + myDialogue.getDialogue());
            }
        }else{
            nextList.clear();
            System.out.println(myDialogue.getDialogue());
            
        }
 
    }

    public static String getDialogues2(String aLine, String previousMessage) throws NumberFormatException, IOException{
        ArrayList<String> splitDialogue = parseLine(aLine);
        String dialogue = splitDialogue.get(1);
        ArrayList<String> nextDialogueLine = getnextDialogueLine(splitDialogue);
        String msg;
        Dialogue myDialogue = new Dialogue();
        myDialogue.setnextDialogueLine(nextDialogueLine);
        myDialogue.setDialogue(dialogue);
        msg = previousMessage;

        if(myDialogue.getFirstNextDialogue() != -1){
            if(splitDialogue.get(0).equals("text")){
                nextList.clear();
                msg += myDialogue.getDialogue();
                for(String number : myDialogue.getNextDialogueLine()){
                    String nextLine = getSpecificLine(Integer.parseInt(number.trim()), "TestScene.txt");
                    String test = getDialogues2(nextLine, msg);
                }
                
            }else{
                nextList.add(myDialogue.getFirstNextDialogue());
                msg += nextList.size() + ". " + myDialogue.getDialogue();
            }
        }else{
            nextList.clear();
            msg += myDialogue.getDialogue();
            
        }
        return msg;
 
    }


    public static void EdelmarScene(int count){
        try{
            
            BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
            String nextLine = getSpecificLine(count, "TestScene.txt");
            System.out.println(getDialogues2(nextLine, ""));
            if(nextList.isEmpty()){
                System.out.println("THE END");
            }else{

                System.out.print("> ");
                String choice = userReader.readLine();
                if(choice.equals("1")){
                    count = nextList.get(0);
                    //getDialogues(getSpecificLine(count, "TestScene.txt"));
                }else if(choice.equals("2")){
                    count = nextList.get(1);
                    //getDialogues(getSpecificLine(count, "TestScene.txt"));
                }else if(nextList.size() == 3 && choice.equals("3")){
                    count = nextList.get(2);
                    //getDialogues(getSpecificLine(count, "TestScene.txt"));
                }else{
                    System.out.println("That's not a valid option.");
                }
                EdelmarScene(count);
            }


        } catch (IOException e){
            System.out.println("File could not be accessed, try again!");
        }
    }
    public static void main(String[]args) throws IOException{

        //String line32 = Files.readAllLines(Paths.get("TestScene.txt")).get(32-1);
        //System.out.println(line32);
        ArrayList<Scene> map = ReadFile.createScenes();
        ArrayList<String> aliasesEl = new ArrayList<>(Arrays.asList("Edel", "El"));
        ArrayList<String> dialogueEl = new ArrayList<>(Arrays.asList("Test1", "Test2"));
        ThingList playerInventory = new ThingList();
        ArrayList<String> playerAliases = new ArrayList<>(Arrays.asList("me", "V", "myself", "I")); 
        ThingList inventoryEl = new ThingList();
        Actor player = new Actor("Vega", "Not worth getting into my backstory.", "Really, nothing noteworthy here.", map.get(0), playerAliases, playerInventory, false);
        Actor Edelmar = new Actor("Edelmar", "aDescription", "anExamination", map.get(1) , 1, aliasesEl, inventoryEl,6 , 1, 4, 4, 0.25, -0.5, 0.75, -0.5, false);

        if(map.get(1).getVisits() == 1){
            System.out.println("This is your first visit to this room.");
        }

        
        EdelmarScene(1);
        

        


    }


}
