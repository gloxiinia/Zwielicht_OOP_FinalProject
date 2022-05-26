package globals;

//enumerated type to make reading the code easier
public enum Direction implements java.io.Serializable{
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static final int NOEXIT = -1;
}
