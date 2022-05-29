import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TextParser {

    //method to filter out any punctuation
    public static String FilterDelims(String input) {
        String delims = " \t,.:;?!\"'><()[]{}-_=+&*%$#@~`";
        List<String> wordList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delims);
        String t;

        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            wordList.add(t);
        }
        String listString = String.join(" ", wordList);
        return listString;
    }

    //method to parse/determine the command input by the user
    public static List<String> parseInput(String userInput){

        String command = userInput.trim().toLowerCase();
        
        boolean foundExamineWords = false;
        boolean foundMoveWords = false;
        boolean foundTalkWords = false;
        boolean foundTakeWords = false;
        boolean foundDropWords = false;
        boolean foundCombatWords = false;
        boolean foundUseWords = false;
        boolean foundQuitWords = false;

        List<String> words = new ArrayList<>(Arrays.asList(command.split(" ")));

        //lists containing possible words for specific in-game commands or possible inputs
        List<String> examineActions = new ArrayList<>(Arrays.asList("look", "check", "inspect", "examine", "study"));
        List<String> moveActions = new ArrayList<>(Arrays.asList("move", "go", "walk", "travel"));
        List<String> talkActions = new ArrayList<>(Arrays.asList("talk", "chat"));
        List<String> takeActions = new ArrayList<>(Arrays.asList("take", "grab", "nab"));
        List<String> combatActions = new ArrayList<>(Arrays.asList("fight", "battle", "duel"));
        List<String> yesActions = new ArrayList<>(Arrays.asList("yes", "correct", "y", "yeah", "yea", "yep", "mhm", "right", "true"));
        List<String> noActions = new ArrayList<>(Arrays.asList("no", "n", "nope", "wrong", "nuhuh", "nah", "incorrect", "false"));
        List<String> quitActions = new ArrayList<>(Arrays.asList("quit", "exit", "q"));
        List<String> directions = new ArrayList<>(Arrays.asList("north", "n", "south", "s", "east", "e", "west", "w"));

        int remainingWordsindex = 0;
        String remainingWords;
        List<String> result = new ArrayList<>();

        if(words.size() > 0){

            //EXAMINE
            //Parsing words for the examine command
            if(words.get(0).equals("look") && words.get(1).equals("at")){
                remainingWordsindex = 2;
                foundExamineWords = true;
                
            }
            else if (examineActions.contains(words.get(0))) {
                remainingWordsindex = 1;
                foundExamineWords = true;
                
            }


            if(foundExamineWords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);

                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }
                    }
                    command = "examine";
                    result.add(command);
                    result.add(remainingWords);
                    return result;
                } 
            }
            
            //Parsing words for checking inventory without examine command
            if(words.size()==1 && (words.get(0).equals("inventory") || words.get(0).equals("i"))){
                remainingWordsindex = 0;
                command = "examine";
                result.add(command);
                result.add("inventory");
                return result;
            }

            //MOVE
            //Parsing the words for the move command
            //Parsing words for one word commands for moving
            if(words.size()==1 && directions.contains(words.get(0))){
                remainingWordsindex = 0;
                foundMoveWords = true;
            }

            if(moveActions.contains(words.get(0)) && words.get(1).equals("to")){
                remainingWordsindex = 2;
                foundMoveWords = true;
                
            }
            else if (moveActions.contains(words.get(0))) {
                remainingWordsindex = 1;
                foundMoveWords = true;
                
            }


            if(foundMoveWords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);

                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }

                    }
                    command = "move";
                    result.add(command);
                    result.add(remainingWords);
                    return result; 
                } 
            }

            //TALK
            //Parsing the words for the talk command
            if(talkActions.contains(words.get(0)) && (words.get(1).equals("to") || words.get(1).equals("with"))){
                remainingWordsindex = 2;
                foundTalkWords = true;
                
            }
            else if (talkActions.contains(words.get(0))) {
                remainingWordsindex = 1;
                foundTalkWords = true;
                
            }


            if(foundTalkWords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);
                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }
                    }
                    command = "talk";
                    result.add(command);
                    result.add(remainingWords);
                    return result;
                } 
            }

            //TAKE
            //Parsing the words for the take command
            if (takeActions.contains(words.get(0))) {
                remainingWordsindex = 1;
                foundTakeWords = true;
                
            }
            if(words.get(0).equals("pick") && words.get(1).equals("up")){
                remainingWordsindex = 2;
                foundTakeWords = true;
                
            }

            if(foundTakeWords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);
                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }

                    }
                    command = "take";
                    result.add(command);
                    result.add(remainingWords);
                    return result;
                } 
            }

            //DROP
            //Parsing the words for the drop command
            if (words.get(0).equals("drop")) {
                remainingWordsindex = 1;
                foundDropWords = true;
                
            }
            if((words.get(0).equals("set") || words.get(0).equals("put")) && words.get(1).equals("down")){
                remainingWordsindex = 2;
                foundDropWords = true;
                
            }

            if(foundDropWords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);
                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }
                    }
                    command = "drop";
                    result.add(command);
                    result.add(remainingWords);
                    return result;
                } 
            }

            //COMBAT
            //Parsing the words for the combat command
            if(combatActions.contains(words.get(0)) && words.get(1).equals("with")){
                remainingWordsindex = 2;
                foundCombatWords = true;
                
            }
            else if (combatActions.contains(words.get(0))) {
                remainingWordsindex = 1;
                foundCombatWords = true;
                
            }


            if(foundCombatWords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);
                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }

                    }
                    command = "combat";
                    result.add(command);
                    result.add(remainingWords);
                    return result;
                } 
            }

            //USE
            //Parsing the words for the use command
            if(words.get(0).equals("use")){
                remainingWordsindex = 1;
                foundUseWords = true;
                
            }

            if(foundUseWords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);
                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }
                    }
                    command = "use";
                    result.add(command);
                    result.add(remainingWords);
                    return result;
                } 
            }             

            //YES
            //Parsing the words for the yes command
            if (yesActions.contains(words.get(0))) {
                
                command = "yes";
                result.add(command);
                return result;
            }

            //NO
            //Parsing the words for the no command
            if (noActions.contains(words.get(0))) {
                
                command = "no";
                result.add(command);
                return result;
            }

            //SAVING
            //Parsing the words for the save game
            //save game, save progress, save 
            if(words.size() == 1 && (words.get(0).equals("save"))){
                command = "save";
                result.add(command);
                return result;
            }

            if(words.get(0).equals("save") && (words.get(1).equals("game") || words.get(1).equals("progress"))){
                command = "save";
                result.add(command);
                return result;
            }


            //LOADING
            //Parsing the words for the load game command
            //load game, load progress, continue save, load
            if(words.size() == 1 && (words.get(0).equals("load"))){
                command = "load";
                result.add(command);
                return result;
            }

            if(words.size() == 2 && words.get(0).equals("load") && (words.get(1).equals("progress") || words.get(1).equals("game") || words.get(1).equals("save"))){
                command = "load";
                result.add(command);
                return result;
            }

            if(words.size() == 2 &&  words.get(0).equals("continue") && (words.get(1).equals("progress") || words.get(1).equals("save") || words.get(1).equals("game"))){
                command = "load";
                result.add(command);
                return result;
            }
            
            //QUIT
            //Parsing the words for the quit command
            if(quitActions.contains(words.get(0))){
                remainingWordsindex = 1;
                foundQuitWords = true;
                command = "quit";
                result.add(command);
                return result;
                
            }

        }

        return result; 
    }

    public static List<String> processInput(String input){
        List<String> parsed;
        input = FilterDelims(input).trim();
        parsed = parseInput(input);
        return parsed;
    }

    public static void main(String[] args) throws IOException{
        
        Scanner in;
        String input;
        List<String> output;

        in = new Scanner(new InputStreamReader(System.in));
        do {
            System.out.print("> ");
            input = in.nextLine();
            input = FilterDelims(input);
            output = parseInput(input);
            System.out.println(output);
        } while (output.stream().anyMatch(s -> !s.equals("quit")));
    }
}

/* testing the different possible commands


*/