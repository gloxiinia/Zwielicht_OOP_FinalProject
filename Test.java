import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import gameobjects.Actor;
import gameobjects.Scene;
import globals.Direction;


public class Test {

        //Testing
        private ArrayList<Scene> map;
        private Actor player;
        List<String> commands = new ArrayList<>(Arrays.asList("examine", "move", "talk", "take", "use", "yes", "no", "quit"));


        public Test(){
            this.map = ReadFile.createScenes();
            player = new Actor("player", "Not worth getting into my backstory.", "Really, nothing noteworthy here.", map.get(0));
            

        }
        public void printMap(){
            for(int i = 0; i < this.map.size(); i++){
                System.out.println(this.map.get(i).getName());
                System.out.println(this.map.get(i).getDescription());
                System.out.println(this.map.get(i).getExamination());
            }
        }

        public String ProcessVerb(ArrayList<String> wordlist){
            String verb;
            String noun;
            String msg = "";
            verb = wordlist.get(0);
            noun = wordlist.get(1);
            if (!commands.contains(verb)){
                msg = "You either made a typo, or that verb isn't recognized.";
            }else{

            }

            return msg;
        }
      
        // Main driver method
        public static void main(String[] args)
        {


            Test works = new Test();
            works.printMap();

            

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
