package gameobjects;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Dialogue implements java.io.Serializable {
    private boolean isText;
    private ArrayList<String> nextDialogueLine;
    private String dialogue;
    private BigDecimal angerChange, fearChange, relationChange, happinessChange;

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

    public void setAngerChange(BigDecimal angerChange){
        this.angerChange = angerChange;
    }

    public void setFearChange(BigDecimal fearChange){
        this.fearChange = fearChange;
    }

    public void setRelationChange(BigDecimal relationChange){
        this.relationChange = relationChange;
    }

    public void setHappinessChange(BigDecimal happinessChange){
        this.happinessChange = happinessChange;
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

    public BigDecimal getAngerChange(){
        return this.angerChange;
    }

    public BigDecimal getFearChange(){
        return this.fearChange;
    }

    public BigDecimal getRelationChange(){
        return this.relationChange;
    }

    public BigDecimal getHappinessChange(){
        return this.happinessChange;
    }

}
