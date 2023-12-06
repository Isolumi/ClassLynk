package ai.classlynk.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //Holds some data for calculations in the OptimalTimetableCalculator

    String id;
    List<ClassBundle> info;
    List<Node> adj;


    public Node(String id,List<ClassBundle> info){
        this.id = id;
        this.info = info;
        adj = new ArrayList<>();
    }

}
