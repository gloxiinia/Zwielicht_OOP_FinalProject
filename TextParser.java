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
        
        boolean foundExaminewords = false;
        boolean foundMovewords = false;
        boolean foundTalkwords = false;
        boolean foundTakewords = false;
        boolean foundCombatwords = false;
        boolean foundUsewords = false;
        boolean foundQuitwords = false;

        List<String> words = new ArrayList<>(Arrays.asList(command.split(" ")));

        //lists containing possible words for specific in-game commands or possible inputs
        List<String> examineActions = new ArrayList<>(Arrays.asList("look", "check", "inspect", "examine", "study"));
        List<String> moveActions = new ArrayList<>(Arrays.asList("move", "go", "walk", "travel"));
        List<String> talkActions = new ArrayList<>(Arrays.asList("talk", "chat"));
        List<String> takeActions = new ArrayList<>(Arrays.asList("take", "grab", "nab"));
        List<String> combatActions = new ArrayList<>(Arrays.asList("fight", "battle", "duel"));
        List<String> yesActions = new ArrayList<>(Arrays.asList("yes", "correct", "y", "yeah", "yea", "yep", "mhm", "right"));
        List<String> noActions = new ArrayList<>(Arrays.asList("no", "n", "nope", "wrong", "nuhuh", "nah"));
        List<String> quitActions = new ArrayList<>(Arrays.asList("quit", "exit", "q"));

        int remainingWordsindex = 0;
        String remainingWords;
        List<String> result = new ArrayList<>();

        if(words.size() > 0){

            //Parsing words for the examine command
            if(words.get(0).equals("look") && words.get(1).equals("at")){
                remainingWordsindex = 2;
                foundExaminewords = true;
                
            }
            else if (examineActions.stream().anyMatch(s -> s.equals(words.get(0)))) {
                remainingWordsindex = 1;
                foundExaminewords = true;
                
            }


            if(foundExaminewords == true){
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
                } 
            }

            //Parsing the words for the move command
            if(moveActions.stream().anyMatch(s -> s.equals(words.get(0))) && words.get(1).equals("to")){
                remainingWordsindex = 2;
                foundMovewords = true;
                
            }
            else if (moveActions.stream().anyMatch(s -> s.equals(words.get(0)))) {
                remainingWordsindex = 1;
                foundMovewords = true;
                
            }


            if(foundMovewords == true){
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
                } 
            }

            //Parsing the words for the talk command
            if(talkActions.stream().anyMatch(s -> s.equals(words.get(0))) && (words.get(1).equals("to") || words.get(1).equals("with"))){
                remainingWordsindex = 2;
                foundTalkwords = true;
                
            }
            else if (talkActions.stream().anyMatch(s -> s.equals(words.get(0)))) {
                remainingWordsindex = 1;
                foundTalkwords = true;
                
            }


            if(foundTalkwords == true){
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
                } 
            }

            //Parsing the words for the take command
            if (takeActions.stream().anyMatch(s -> s.equals(words.get(0)))) {
                remainingWordsindex = 1;
                foundTakewords = true;
                
            }
            if(words.get(0).equals("pick") && words.get(1).equals("up")){
                remainingWordsindex = 2;
                foundTakewords = true;
                
            }

            if(foundTakewords == true){
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
                } 
            }

            //Parsing the words for the combat command
            if(combatActions.stream().anyMatch(s -> s.equals(words.get(0))) && words.get(1).equals("with")){
                remainingWordsindex = 2;
                foundCombatwords = true;
                
            }
            else if (combatActions.stream().anyMatch(s -> s.equals(words.get(0)))) {
                remainingWordsindex = 1;
                foundCombatwords = true;
                
            }


            if(foundCombatwords == true){
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
                } 
            }

            //Parsing the words for the use command
            if(words.get(0).equals("use")){
                remainingWordsindex = 1;
                foundUsewords = true;
                
            }

            if(foundUsewords == true){
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
                } 
            }                      

            //Parsing the words for the yes command
            if (yesActions.stream().anyMatch(s -> s.equals(words.get(0)))) {
                
                command = "yes";
                result.add(command);
            }

            //Parsing the words for the no command
            if (noActions.stream().anyMatch(s -> s.equals(words.get(0)))) {
                
                command = "no";
                result.add(command);
            }

            //Parsing the words for the quit command
            if(quitActions.stream().anyMatch(s -> s.equals(words.get(0)))){
                remainingWordsindex = 1;
                foundQuitwords = true;
                
            }
        
            if(foundQuitwords == true){
                if (words.size() > remainingWordsindex){
                    remainingWords = "";
                    for(int i = remainingWordsindex; i < words.size(); i++){
                        remainingWords += words.get(i);
                        //for objects/nouns with more than one word (e.g. book of spells)
                        if (i < words.size() - 1){
                            remainingWords += " ";
                        }
                    }
                    command = "quit";
                    result.add(command);
                    result.add(remainingWords);
                } 
            } 

        }

        return result; 
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