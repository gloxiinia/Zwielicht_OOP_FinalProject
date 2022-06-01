import java.math.BigDecimal;
import java.util.ArrayList;

import gameobjects.Actor;
import gameobjects.ThingList;

public class Testing {
    public static void main(String[] args){
        ThingList test = new ThingList();
        BigDecimal num1 = new BigDecimal("5");
        BigDecimal num2 = new BigDecimal("-1");
        BigDecimal num3 = new BigDecimal("-3");
        BigDecimal num4 = new BigDecimal("3");
        BigDecimal num5 = new BigDecimal("0.25");
        BigDecimal num6 = new BigDecimal("-0.5");
        BigDecimal num7 = new BigDecimal("0.75");
        BigDecimal num8 = new BigDecimal("-0.5");
        ArrayList<String> test2 = new ArrayList<>();
        Actor a1 = new Actor("edel", "description", "examination", 1, test2,test, num1, num1, num1, num1, num5,num6,num7,num8);
        a1.addAnger(BigDecimal.ONE);
        a1.addAnger(num2);
        a1.addAnger(num3);
        a1.printNPCCurrentAttributes();
    }
}
