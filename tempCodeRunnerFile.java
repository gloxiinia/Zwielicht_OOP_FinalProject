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