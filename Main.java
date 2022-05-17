public class Main {
    

        // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
  
    // Declaring the color
    // Custom declaration
    public static final String ANSI_RED = "\u001B[31m";
  
    // Main driver method
    public static void main(String[] args)
    {
        // Printing the text on console prior adding
        // the desired color
        System.out.println(ANSI_RED + "This text is red" + ANSI_RESET + " this text is not");
    }
}