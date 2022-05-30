package gameobjects;
import java.util.ArrayList;

public class Dialogue implements java.io.Serializable {
    private boolean isText;
    private ArrayList<String> nextDialogueLine;
    private String dialogue;

    public Dialogue(){

    }

    public Dialogue(ArrayList<String> nextDialogueLine, String dialogue){
        
        this.nextDialogueLine = nextDialogueLine;
        this.dialogue = dialogue;
    }

    Dialogue(boolean isText, ArrayList<String> nextDialogueLine, String dialogue){
        this.isText = isText;
        this.nextDialogueLine = nextDialogueLine;
        this.dialogue = dialogue;
    }

    //setters
    public void setIsText(boolean isText){
        this.isText = isText;
    }

    public void setnextDialogueLine(ArrayList<String> nextDialogueLine){
        this.nextDialogueLine = nextDialogueLine;
    }

    public void setDialogue(String dialogue){
        this.dialogue = dialogue;
    }

    //getters
    public boolean getIsText(){
        return this.isText;
    }

    public void printnextDialogueLine(){
        for(String number : nextDialogueLine){
            System.out.println(number);
        }
    }

    public ArrayList<String> getNextDialogueLine(){
        return this.nextDialogueLine;
    }

    public int getFirstNextDialogue(){
        int firstnextDialogueLine = Integer.parseInt(this.nextDialogueLine.get(0));
        return firstnextDialogueLine;
    }

    public String getDialogue(){
        return this.dialogue;
    }
}
