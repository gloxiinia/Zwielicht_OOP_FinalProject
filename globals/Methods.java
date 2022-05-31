package globals;
import java.util.ArrayList;
import gameobjects.Scene;


public class Methods implements java.io.Serializable{
    public static String capitalizeString(String lower){
        String output = lower.substring(0, 1).toUpperCase()  + lower.substring(1);
        return output;
    }

    
    public static void typewriterEffect(String word){
        int i;
        for(i = 0; i < word.length(); i++){
            System.out.printf("%c", word.charAt(i));
            try{
                Thread.sleep(50);//0.1s pause between characters
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public void printsceneList(ArrayList<Scene> sceneList){
        for(int i = 0; i < sceneList.size(); i++){
            System.out.println(sceneList.get(i).getName());
            System.out.println(sceneList.get(i).getDescription());
            System.out.println(sceneList.get(i).getExamination());
            System.out.println(sceneList.get(i).getNorth());
            System.out.println(sceneList.get(i).getSouth());
            System.out.println(sceneList.get(i).getEast());
            System.out.println(sceneList.get(i).getWest());
        }
    }
    public void printSceneObjects(ArrayList<Scene> sceneList){
        for(Scene s : sceneList){
            System.out.println(s.getThings());
        }
    }
    
    public static boolean isNumeric(String string) {
        int intValue;
            
        if(string == null || string.equals("")) {
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
    


    
}
