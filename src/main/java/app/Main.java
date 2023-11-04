package app;

import data_access.DatabaseDataAccessObject;
import entity.Class;
import entity.Timetable;
import entity.User;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        DatabaseDataAccessObject dba = new DatabaseDataAccessObject();
        ArrayList<Timetable> tt = dba.load(new User("asdf123:)"));
        System.out.println("wai");
        Class temp =  tt.get(0).getClasses().get("Monday").get(0);
        temp.setBuilding("dave's chicken");
        ArrayList<Class> wai = new ArrayList<>();
        wai.add(temp);
        tt.get(0).getClasses().put("Monday", wai);
        dba.save(new User("asdf123:)"), tt.get(0));
    }
}
