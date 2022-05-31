import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import globals.Methods;

public class Main {

    //date and time formatter
    static DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
    
    //creating a new buffered reader for getting input
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
  
    // Declaring the color
    // Custom declaration
    public static final String ANSI_RED = "\u001B[31m";

    static Game game;

    //method for checking if a save file exists 
    private static boolean checkSaveExists(String playerName, String saveName) {
        boolean exists;
        File file = new File("gamesaves/" + playerName + "/" + saveName + ".sav");
        if (file.exists()) {
            exists = true;
        }else{
            exists = false;
        }
        return exists;
        
    }
    //method for checking if a player folder exists
    private static boolean checkFolderExists(String playerName) {
        boolean folderExist;
        File folder = new File("gamesaves/" + playerName);
        if (folder.exists()) {
            folderExist = true;
        }else{
            folderExist = false;
        }
        return folderExist;

    }

    //method for deleting a player folder
    private static void deleteFolder(String playerName) {

        File folder = new File("gamesaves/" + playerName);
        folder.delete();

    }

    //method for deleting a save file
    private static void deleteSave(String playerName, String saveName) {

        File folder = new File("gamesaves/" + playerName + "/" + saveName + ".sav") ;
        folder.delete();

    }

    //method for formatting string to lower case and trimmed
    private static String lowerTrim(String word){
        return word.trim().toLowerCase();
    }

    //method for getting the last time a file was modified
    private static String getLastFileModified(String playerName, String saveName){
        File file = new File("gamesaves/" + playerName + "/" + saveName + ".sav");
        long lastModified = file.lastModified();
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastModified), ZoneId.systemDefault());
        String formattedDate = date.format(myDateFormatter);
        return formattedDate;

    }

    //method for getting the current local date/time
    private static String currentDateTime(){
        LocalDateTime dateNow = LocalDateTime.now();
        return dateNow.format(myDateFormatter);

    }

    //method for saving the game
    private static void saveGame(String saveName, String playerName){
        try{
            FileOutputStream fos = new FileOutputStream("gamesaves/" + playerName + "/" + saveName + ".sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.println("\nYour progress was succesfully saved.");
        
        }catch (Exception e){
            System.out.print("Serialization error. Save was unsuccessful.\n" + e.getClass() + ": " + e.getMessage());
        }
    }
    
    //method for loading a game save
    private static void loadGame(String saveName, String playerName){
        try{
            FileInputStream fis = new FileInputStream("gamesaves/" + playerName + "/" + saveName + ".sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            System.out.println("\nGame successfully loaded.");
        
        }catch (Exception e){
            System.out.print("Serialization error. Load was unsuccessful.\n" + e.getClass() + ": " + e.getMessage());
        }
    }

    public static boolean processSave() throws IOException{
        boolean checkFile = false;
        String playerName;
        String saveName;
        List<String> response;
        System.out.print("Enter your player name: \n" + "> " );
        playerName = in.readLine();
        System.out.println("\nWould you like to save this character?");
        System.out.println("Player Name: " + playerName);
        System.out.println(currentDateTime());
        System.out.print("> ");
        response = TextParser.parseInput(in.readLine());
        if(response.get(0).equals("yes")){
            System.out.println("\nEnter the name of your save:");
            System.out.print("> ");
            saveName = lowerTrim(in.readLine());
            playerName = lowerTrim(playerName);
            checkFile = checkSaveExists(playerName, saveName);
            processSave2(playerName, saveName, checkFile);
        }else{
            System.out.println("Got it, save process cancelled.");
        }
        return checkFile;

    }

    public static void processSave2(String playerName, String saveName, boolean checkFile) throws IOException{
        List<String> response;
        if(checkFile){
            System.out.println("\nA save file with the same name already exists. Overwrite it?");
            System.out.println("Player Name\t: " + playerName);
            System.out.println("Last Save Date\t: " + getLastFileModified(playerName, saveName));
            System.out.print("> ");
            response = TextParser.parseInput(in.readLine());
            if(response.get(0).equals("yes")){
                saveGame(saveName, playerName);
            }else{
                System.out.println("\nGot it, save process cancelled");
            }
        }else{
            File file = new File("gamesaves/"+ playerName);
            file.mkdir();
            saveGame(saveName, playerName);
        }
    }

    public static boolean processLoad() throws IOException{
        boolean checkFolder = false;
        boolean checkFile = false;
        String playerName;
        String saveName;
        List<String> response;
        System.out.print("Enter your player name: \n" + "> " );
        playerName = in.readLine();
        checkFolder = checkFolderExists(lowerTrim(playerName));
        if(!checkFolder){
            System.out.println("There are no saved characters with that name. Please check if you've made a typo.\n");
        }else{
            System.out.println("\nWould you like to load this character?");
            System.out.println("Player Name: " + playerName);
            
            System.out.print("> ");
            response = TextParser.parseInput(in.readLine());
            if(response.get(0).equals("yes")){
                System.out.println("\nEnter the name of your save:");
                System.out.print("> ");
                saveName = lowerTrim(in.readLine());
                playerName = lowerTrim(playerName);
                checkFile = checkSaveExists(playerName, saveName);
                processLoad2(playerName, saveName, checkFile);
            }else{
                System.out.println("Got it, load process cancelled.");
            }
        }

        return checkFolder;

    }

    public static void processLoad2(String playerName, String saveName, boolean checkFile) throws IOException{
        List<String> response;
        if(!checkFile){
            System.out.println("No save files were found with that name. Please check if you've made a typo.");
        }else{
            System.out.println("Load this save file?");
            System.out.println("Player Name\t: " + playerName);
            System.out.println("Last Save Date\t: " + getLastFileModified(playerName, saveName));
            System.out.print("> ");
            response = TextParser.parseInput(in.readLine());
            if(response.get(0).equals("yes")){
                loadGame(saveName, playerName);
            }else{
                System.out.println("\nGot it, load process cancelled");
            }
        }
    }

    public static void showIntro(){
    
        

    }

    // Main driver method
    public static void main(String[] args) throws IOException
    {
        // Printing the text on console prior adding
        // the desired color
        //System.out.println(ANSI_RED + "This text is red" + ANSI_RESET + " this text is not");

        showIntro();

        game = new Game();
        String input;
        String output = "";
        String checkQuit = "";
        List<String> outputs;


        do {
            System.out.print("> ");
            input = in.readLine();
            outputs = TextParser.processInput(input);
            System.out.println();
            
            if(outputs.isEmpty()){
                output = "I can't recognize that, sorry.\n"; 

            }else{
                checkQuit = outputs.get(0); 
                switch (outputs.get(0)) {
                    case "save":
                        processSave();
                        break;
                    case "load":
                        processLoad();
                        break;
                    default:
                        output = game.RunCommand(outputs);
                        break;
                //playing out the office scene
                }if(game.getPlayer().getLocation() == 1 && game.getPlayer().getSpecificSceneVisits(game.getPlayer().getScene()) == 1 && !game.getPlayer().getScene().getIsDialogueFinished()){
                    game.startDialogue("edelmar", "TestScene.txt");
                }
                
            }
            Methods.typewriterEffect(output);

        } while (!"quit".equals(checkQuit));
     }

}
