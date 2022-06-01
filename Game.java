
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameobjects.Actor;
import gameobjects.Dialogue;
import gameobjects.Scene;
import gameobjects.Thing;
import gameobjects.ThingList;
import globals.Direction;
import globals.Methods;
import globals.ReadFile;
import globals.TextParser;



public class Game implements java.io.Serializable{

        //Creating the needed assets
        private ArrayList<Scene> map; 
        private ArrayList<String> allItemNames;
        private ThingList allItems;
        private ArrayList<String> sceneNames;
        private ArrayList<String> allNPCNames;
        private Actor player;
        private ArrayList<Integer> nextList = new ArrayList<>();
        private ArrayList<Dialogue> listOfChoices = new ArrayList<>();
        private Map<Scene, Integer> playerVisits = new HashMap<>();
        List<String> commands = new ArrayList<>(Arrays.asList("examine", "move", "talk", "take", "drop", "use", "yes", "no", "quit"));
        List<String> directions = new ArrayList<>(Arrays.asList("north", "south", "east", "west"));
        List<String> miscNouns = new ArrayList<>(Arrays.asList("inventory", "i", "around"));

        public ArrayList<Integer> getNextList(){
            return this.nextList;
        }

        //constructor
        public Game(){
            //creating the game map + a list of all scene names by reading the Scenes file
            this.map = ReadFile.createScenes();
            this.sceneNames = getAllSceneNames();
            //getting the list of all NPC names
            this.allNPCNames = getAllNPCNames();
            //creating a list of all items + all item names by reading the Items file
            this.allItems = ReadFile.createItems();
            this.allItemNames = allItems.allThingNamesList(allItems);
            //creating the player inventory
            ThingList playerInventory = new ThingList();
            setSceneVisitMap();

            ArrayList<String> playerAliases = new ArrayList<>(Arrays.asList("me", "myself", "I"));
            player = new Actor("Vega", "Not worth getting into my backstory.", "Really, nothing noteworthy here.", 
                                map.get(0), playerAliases, playerInventory, false, "");
            
            

        }

        //DIALOGUE
        public void startDialogue(String actorName, String fileName){
            getPlayer().setIfActorIsInDialogue(true);
            while(getPlayer().isActorInDialogue()){
                getPlayer().setWhoActorInDialogueWith(actorName);
                System.out.println(getActiveDialogueNPC().getDescription());
                processActorScene(1, fileName, getActiveDialogueNPC());
            }
        }
        ArrayList<String> attributeChanges = new ArrayList<>();
        //method for changing an actor's attributes
        public void addAttributeChanges(Dialogue aDialogue, ArrayList<String> attributeLine){
            attributeChanges = ReadFile.splitByCommas(attributeLine, 2);
            System.out.println(attributeChanges);
            BigDecimal angerChange = new BigDecimal(attributeChanges.get(0));
            BigDecimal fearChange = new BigDecimal(attributeChanges.get(1));
            BigDecimal relationChange = new BigDecimal(attributeChanges.get(2));
            BigDecimal happinessChange = new BigDecimal(attributeChanges.get(3));
            
            aDialogue.setAngerChange(angerChange);
            aDialogue.setFearChange(fearChange);
            aDialogue.setRelationChange(relationChange);
            aDialogue.setHappinessChange(happinessChange);
            
            

            
        }
        
        public ArrayList<Dialogue> getDialogues(String aLine, Actor anActor) throws NumberFormatException, IOException{
            ArrayList<String> splitDialogue = ReadFile.parseLine(aLine);
            ArrayList<String> nextDialogueLine = ReadFile.splitByCommas(splitDialogue, -1);
            Dialogue myDialogue = new Dialogue();
            myDialogue.setnextDialogueLine(nextDialogueLine);
            myDialogue.setDialogue(splitDialogue.get(1));
    
            if(myDialogue.getFirstNextDialogue() != -1){
                if(splitDialogue.get(0).equals("text")){
                    nextList.clear();
                    System.out.println(myDialogue.getDialogue());
                    System.out.println();
                    for(String number : myDialogue.getNextDialogueLine()){
                        String nextLine = ReadFile.getSpecificLine(Integer.parseInt(number.trim()), "TestScene.txt");
                        getDialogues(nextLine, anActor);
                    }
                    
                }
                else{
                    addAttributeChanges(myDialogue, splitDialogue);
                    nextList.add(myDialogue.getFirstNextDialogue());
                    System.out.println(nextList.size() + ". " + myDialogue.getDialogue());
                    listOfChoices.add(myDialogue);
                    
                }
            }else{
                if(splitDialogue.get(0).equals("option")){
                    System.out.println("checking");
                    addAttributeChanges(myDialogue, splitDialogue);
                }
                nextList.clear();
                System.out.println(myDialogue.getDialogue());
                
            }
            return listOfChoices;
     
        }

        public void processActorScene(int count, String fileName, Actor anActor){
            try{
                List<String> outputs = new ArrayList<>();
                String output;
                BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
                String nextLine = ReadFile.getSpecificLine(count, fileName);
                listOfChoices = getDialogues(nextLine, anActor);
                System.out.println();
                if(this.getNextList().isEmpty()){
                    getPlayer().setIfActorIsInDialogue(false);
                    getActiveScene().setIfDialogueIsFinished(true);
                    System.out.println("This is the end of the Edelmar Dialogue.\nYou are now free to roam around the map. Have fun!");
                }else{
                    System.out.print("> ");
                    String choice = userReader.readLine();
                    outputs = TextParser.processInput(choice);
                    output = RunCommand(outputs);
                    
                    if(!outputs.isEmpty()){
                        if(Methods.isNumeric(output)){
                            count = Integer.parseInt(output);
                            getActiveDialogueNPC().printNPCCurrentAttributes();
                            processActorScene(count, fileName, anActor);
                        }else{
                            System.out.println(output);
                            processActorScene(count, fileName, anActor);
                        }

                    }else{
                        System.out.println(output);
                        processActorScene(count, fileName, anActor);
                    }
                    
                }
    
            } catch (IOException e){
                System.out.println("File could not be accessed, try again!");
            }
        }
    
        public void endDialogue(){
    
        }

        public Actor getActiveDialogueNPC(){
            return getActiveScene().getNPC(getPlayer().getWhoActorInDialogueWith());
        }
       

        //methods to run the intended commandS

        //EXAMINING-----------------------------------------------------------------------
        private String examineItem(String itemName){
            String msg = "";
            Thing aSceneItem = getActiveScene().getThings().thisThing(itemName);
            Thing anInventoryItem = getPlayer().getThings().thisThing(itemName);

            //flags for whether a sceneitem or aninventoryitem is found
            boolean sceneItemFound = true;
            boolean inventoryItemFound = true;

            //checking inventory
            if((itemName.equals("inventory") || itemName.equals("i"))){ // check the i command
                showInventory(getPlayer());
            }
            else if(allNPCNames.contains(itemName)){
                if(getActiveScene().getNPC(itemName).equals(getActiveDialogueNPC()) && getPlayer().isActorInDialogue()){
                    msg = getActiveDialogueNPC().getName();
                    msg += getActiveScene().getNPC(itemName).getExamination();
                }else{
                    msg = "They're not in your current area. How would you examine them?";
                }

            }
            //examining the active scene
            else if(itemName.equals("around")){
                msg = getActiveScene().getExamination();
            }
            //checking if the desired item is an inventory item
            else if(anInventoryItem == null){
                msg = "There's nothing named " + itemName + " in your inventory.";
                inventoryItemFound = false;
            }
            //checking if the desired item is an item in the scene
            else if(aSceneItem == null){
                msg = "There's nothing named " + itemName + " in this location.";
                sceneItemFound = false;
            }
            //found an item in the inventory
            if (!sceneItemFound && inventoryItemFound){
                msg = anInventoryItem.getExamination();
            }
            //found an item in the scene
            if(sceneItemFound && !inventoryItemFound){
                msg = aSceneItem.getExamination();
            }
            
            return msg;
        }
        //EXAMINE END----------------------------------------------------------------------

        //TAKING AND DROPPING--------------------------------------------------------------
        private void transferItem(Thing aThing, ThingList fromList, ThingList toList){
            fromList.remove(aThing);
            toList.add(aThing);

        }

        private void showInventory(Actor actor){
            System.out.println("-------------------------INVENTORY-------------------------\n");
            System.out.println(actor.getThings().describeThings());
            System.out.println("\n-----------------------------------------------------------\n");
        }

        public String takeItem(String itemName){
            String msg = "";
            Thing t = getActiveScene().getThings().thisThing(itemName);
            if (itemName.equals("")){
                itemName = "nothing";
            }if(t == null){
                msg = "There's nothing named " + itemName + " in here.";
            }else{
                if(t.isThingPickupable() == true){
                    transferItem(t, player.getScene().getThings(), player.getThings());
                    msg =  Methods.capitalizeString(itemName) + " has been taken.";
                }else{
                    msg = "I don't think you can pick that up.";
                }
            }

            return msg;
        }

        public String dropItem(String itemName){
            String msg = "";
            Thing t = getPlayer().getThings().thisThing(itemName);
            if(getPlayer().isActorInDialogue()){
                msg = getActiveDialogueNPC().getDropResponse() + "\n" ;
            }else{
                if (itemName.equals("")){
                    msg = "You have to *name* the object. I can't read your mind.";
                }if(t == null){
                    msg = "There's nothing named " + itemName + " in your inventory.";
                }else{
                    if(t.isThingKey() == false){
                        transferItem(t, player.getThings(), player.getScene().getThings());
                        msg =  "\n" + Methods.capitalizeString(itemName) + " has been dropped.";
                    }else{
                        msg = "I don't think you should drop that.";
                    }
                }
            }

            return msg;
        }

        //TAKING AND DROPPING END--------------------------------------------------------------

        
        //MOVEMENT
        //methods for moving the player around the map
        private void goN() {
            moveHandler(movePlayerTo(Direction.NORTH));
        }
    
        private void goS() {
            moveHandler(movePlayerTo(Direction.SOUTH));
        }
    
        private void goW() {
            moveHandler(movePlayerTo(Direction.WEST));
        }
    
        private void goE() {
            moveHandler(movePlayerTo(Direction.EAST));
        }

        //method to move an Actor (NPC, PC) to a scene
        int moveTo(Actor anActor, Direction aDirection){
            Scene activeScene = anActor.getScene();
            int exit;
            switch(aDirection){
                case NORTH:
                    exit = activeScene.getNorth();
                    break;
                case SOUTH:
                    exit = activeScene.getSouth();
                    break;
                case EAST:
                    exit = activeScene.getEast();
                    break;
                case WEST:
                    exit = activeScene.getWest();
                    break;
                default:
                    exit = Direction.NOEXIT; 
                    break;
            }
            if (exit != Direction.NOEXIT){
                moveActorTo(anActor, map.get(exit));
            }
            return exit;
        }
        
        //method to try a scene change
        //returns a special message if unsuccessful
        //returns the scene name + description if successful
        void moveHandler(int sceneNumber) {      
            String msg;
            if (sceneNumber == Direction.NOEXIT) {
                msg = "You can't travel that way, sorry.\n";
            }else {
                getPlayer().setLocation(sceneNumber);
                getPlayer().addVisit(getActiveScene());
                msg = "You are now in " + getActiveScene().getName() + ". " + '\n'+ getActiveScene().getDescription();

            }
            System.out.print(msg);
        }


        //method to call the moveTo method for an Actor (NPC, PC)
        void moveActorTo(Actor aPerson, Scene aScene) {
            aPerson.setScene(aScene);
        }

        //method to call the moveTo method for the player character (PC)
        public int movePlayerTo(Direction dir){
            return moveTo(player, dir);
            
        }

        //method to return the player object
        public Actor getPlayer() {
            return player;
        }

        //method to return the active scene
        public Scene getActiveScene(){
            return getPlayer().getScene();
        }

        //method to return each of the scene's names
        public ArrayList<String> getAllSceneNames(){
            ArrayList<String> allSceneNames = new ArrayList<>();
            for(Scene s : this.map){
                allSceneNames.add(s.getName().toLowerCase());
            }
            return allSceneNames;
        }

        //method to return each of the NPC names
        public ArrayList<String> getAllNPCNames(){
            ArrayList<String> allNPCNames = new ArrayList<>();
            for(Scene s : this.map){
                allNPCNames.addAll(s.getNPCNames());
            }
            return allNPCNames;
        }

        //method to return one specific dialogue that is an option for the player
        public Dialogue getADialogueOption(int index){
            return this.listOfChoices.get(index);
        }

        //method to assign the Scenes a visit value in the hashmap
        public void setSceneVisitMap(){
            for(Scene s : this.map){
                playerVisits.put(s, 0);
            }
        }

        //Might delete
        public void printMap(){
            for(int i = 0; i < this.map.size(); i++){
                System.out.println(this.map.get(i).getName());
                System.out.println(this.map.get(i).getDescription());
                System.out.println(this.map.get(i).getExamination());
                System.out.println(this.map.get(i).getNorth());
                System.out.println(this.map.get(i).getSouth());
                System.out.println(this.map.get(i).getEast());
                System.out.println(this.map.get(i).getWest());
            }
        }
        public void printSceneObjects(){
            for(Scene s : this.map){
                System.out.println(s.getThings());
            }
        }
        //Might Delete

        //method for processing one word commands
        public String ProcessVerb(List<String> wordList){
            String verb;
            String msg = "";
            verb = wordList.get(0);
            if(verb.equals("quit")){
                msg += "Thank you for playing the game!";
            }else if(getPlayer().isActorInDialogue() && TextParser.isNumberChoiceValid(verb)){
                if(verb.equals("1") && getPlayer().isActorInDialogue()){
                    msg = String.valueOf(this.getNextList().get(0));
                    getActiveDialogueNPC().addAllAttributes(getADialogueOption(0).getAngerChange(), 
                    getADialogueOption(0).getFearChange(), getADialogueOption(0).getRelationChange(), getADialogueOption(0).getHappinessChange());
                    listOfChoices.clear();
                    System.out.println();

                }else if(verb.equals("2") && getPlayer().isActorInDialogue()){
                    msg = String.valueOf(this.getNextList().get(1));
                    getActiveDialogueNPC().addAllAttributes(getADialogueOption(1).getAngerChange(), 
                    getADialogueOption(1).getFearChange(), getADialogueOption(1).getRelationChange(), getADialogueOption(1).getHappinessChange());
                    System.out.println();
                    
                }else if(this.getNextList().size() == 3 && verb.equals("3") && getPlayer().isActorInDialogue()){
                    msg = String.valueOf(this.getNextList().get(2));
                    getActiveDialogueNPC().addAllAttributes(getADialogueOption(2).getAngerChange(), 
                    getADialogueOption(2).getFearChange(), getADialogueOption(2).getRelationChange(), getADialogueOption(2).getHappinessChange());
                    System.out.println();
                }
            }else{
                msg = "That's not a recognized action.\n";
                return msg;
            }
            return msg;
        }

        //method for processing two word commands
        public String ProcessVerbNoun(List<String> wordlist){
            String verb;
            String noun;
            String msg = "";
            boolean error = false;
            verb = wordlist.get(0);
            noun = wordlist.get(1);
            if(!commands.contains(verb)){
                msg = "You either made a typo, or " + verb +" isn't a recognized action.\n";
                error = true;
            }if(!allItemNames.contains(noun) && !directions.contains(noun) && !miscNouns.contains(noun) && !allNPCNames.contains(noun)){
                msg += "You either made a typo, or " + noun + " isn't a recognized noun.\n";
                error = true;
            }if(!error){
                if(getPlayer().isActorInDialogue()){
                    if(verb.equals("move")){
                        msg += "You're currently talking to someone, finish up first.\n\n";
                        msg += getActiveDialogueNPC().getMoveResponse() + "\n" ;
                    }else if(verb.equals("examine") && (getActiveScene().getNPC(noun).equals(getActiveDialogueNPC())) && 
                            (noun.equals(getActiveDialogueNPC().getName()) || getActiveDialogueNPC().getAliases().contains(noun))){
                        msg += getActiveDialogueNPC().getExamineResponse() + "\n\n";
                        msg += getActiveDialogueNPC().getExamination();
                    }else if(verb.equals("examine") && noun.equals("inventory")){
                        showInventory(getPlayer());
                        msg += getActiveDialogueNPC().getExamineResponse() + "\n" ;
                    }else if(verb.equals("take")){
                        msg += getActiveDialogueNPC().getTakeResponse() + "\n" ;
                    }else if(verb.equals("examine")){
                        msg += getActiveDialogueNPC().getExamineResponse() + "\n" ;
                    }
                }else{
                    if(directions.contains(noun)){ //processing move commands
                        switch(noun){
                            case "north":
                                goN();
                                break;
                            case "south":
                                goS();
                                break;
                            case "east":
                                goE();
                                break;
                            case "west":
                                goW();
                                break;
                            default:
                                msg = "You either made a typo, or " + noun + " isn't a recognized direction.\n";
                                break;
                        }
                    }else if(verb.equals("examine")){ //processing examine commands
                        msg = examineItem(noun);
                    }else if(verb.equals("take")){ //processing take commands
                        msg = takeItem(noun);
    
                    }else if(verb.equals("drop")){ //processing drop commands
                        msg = dropItem(noun);
    
                    }
                }


            }

            return msg;
        }

        //method to call the processVerbNoun method and run the player's intended command
        public String RunCommand(List<String> inputstr) {
            String s;  
            if(inputstr.size() == 2){
                s = ProcessVerbNoun(inputstr);
            }else if(inputstr.size() == 1){
                s = ProcessVerb(inputstr);
            }else{
                s = "That's not a valid option.\n";
            }    
            
            return s;
        }

      
        // Main driver method
        public static void main(String[] args) throws IOException
        {


        }
}

        // Declaring ANSI_RESET so that we can reset the color
        //public static final String ANSI_RESET = "\u001B[0m";
  
        // Declaring the color
        // Custom declaration
        //public static final String ANSI_RED = "\u001B[31m";

        // Printing the text on console prior adding
        // the desired color
        //System.out.println(ANSI_RED + "This text is red" + ANSI_RESET + " this text is not");
        
        //randomizer
        //String[] available_cards = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Queen", "King", "Jack", "Ace"};  
        //java.util.Random random = new java.util.Random();
        //int random_computer_card = random.nextInt(available_cards.length);
        //System.out.println(available_cards[random_computer_card]);
