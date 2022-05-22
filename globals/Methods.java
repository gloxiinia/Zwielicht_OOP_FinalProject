package globals;
import java.util.ArrayList;
import gameobjects.Scene;


public class Methods {
    public static String capitalizeString(String lower){
        String output = lower.substring(0, 1).toUpperCase()  + lower.substring(1);
        return output;
    }

    //public String getAllSceneNames(ArrayList<Scene> sceneList){


        
    //}


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
}
