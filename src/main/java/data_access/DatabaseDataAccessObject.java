package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import entity.Class;
import entity.Course;
import entity.Timetable;
import entity.User;
import use_case.explore_courses.ExploreCoursesDataAccessInterface;
import use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import static java.lang.Float.parseFloat;
import static java.lang.String.valueOf;

public class DatabaseDataAccessObject implements ExploreCoursesDataAccessInterface, SaveViewTimetablesDataAccessInterface {

    Firestore db;

    public DatabaseDataAccessObject() {
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.getApplicationDefault();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setProjectId("timetablegenerator-e55f3")
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }


    //temp for adding courses to database
    public void saveCourse() {

    }

    @Override
    public ArrayList<Course> loadCourses() {
        return null;
    }



    @Override
    public void save(User user, Timetable timetable) {
        //get already saved timetables
        DocumentReference docRef = db.collection("Users").document(user.getId());
        ApiFuture<DocumentSnapshot> readRes = docRef.get();
        try {
            DocumentSnapshot document = readRes.get();
            if (document.exists()) {
                //this should return a dictionary of String keys that are the day of the week, with values being a map
                // //of strings representing the names of the details of a class to a string representing that detail
                Map<String, ArrayList<Map<String, ArrayList<Map<String, String>>>>> timetables = new HashMap<>();
                Map<String, Object> data = document.getData();
                //the returned object from the data is guaranteed to be of this form (an array of maps which represent
                //individual timetables)
                ArrayList<Map<String, ArrayList<Map<String, String>>>> savedTimetables = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) data.get("Timetables");

                Map<String, List<Class>> timetableData = timetable.getClasses();

                Map<String, ArrayList<Map<String, String>>> converted = convertTimetableToData(timetableData);

                savedTimetables.add(converted);

                //removes duplicate timetables
                Set<Map<String, ArrayList<Map<String, String>>>> noDuplicatesTimetables = new LinkedHashSet<>(savedTimetables);
                savedTimetables.clear();
                savedTimetables.addAll(noDuplicatesTimetables);


                timetables.put("Timetables", getMaps(timetable, document));

                db.collection("Users").document(user.getId()).set(savedTimetables);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    private static Map<String, ArrayList<Map<String, String>>> convertTimetableToData(Map<String, List<Class>> timetableData) {
        Map<String, ArrayList<Map<String, String>>> converted = new HashMap<>();
        for (String key : timetableData.keySet()) {
            ArrayList<Map<String, String>> currClasses = new ArrayList<>();
            for (Class classData : timetableData.get(key)) {
                Map<String, String> temp = new HashMap<>();
                temp.put("courseId", classData.getCourseId());
                temp.put("classId", classData.getClassId());
                temp.put("duration", String.valueOf(classData.getDuration()));
                temp.put("isTutorial", valueOf(classData.isTutorial()));
                temp.put("time", classData.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                temp.put("weekday", classData.getWeekday());
                temp.put("location", classData.getLocation());
                temp.put("building", classData.getBuilding());
                currClasses.add(temp);
            }
            converted.put(key, currClasses);
        }
        return converted;
    }

    private static ArrayList<Map<String, ArrayList<Map<String, String>>>> getMaps(Timetable timetable, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        //the returned object from the data is guaranteed to be of this form (an array of maps which represent
        //individual timetables)
        ArrayList<Map<String, ArrayList<Map<String, String>>>> timetables = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) data.get("Timetables");

        Map<String, List<Class>> timetableData = timetable.getClasses();

        Map<String, ArrayList<Map<String, String>>> converted = convertTimetableToData(timetableData);

        timetables.add(converted);

        //removes duplicate timetables
        Set<Map<String, ArrayList<Map<String, String>>>> noDuplicatesTimetables = new LinkedHashSet<>(timetables);
        timetables.clear();
        timetables.addAll(noDuplicatesTimetables);
        return timetables;
    }


    @Override
    public ArrayList<Timetable> load(User user) {

        DocumentReference docRef = db.collection("Users").document(user.getId());
        ApiFuture<DocumentSnapshot> res = docRef.get();
        try {
            DocumentSnapshot document = res.get();
            if (document.exists()) {
                ArrayList<Map<String, ArrayList<Map<String, String>>>> data = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) document.getData().get("Timetables");
                ArrayList<Timetable> timetables = new ArrayList<>();

                for(Map<String, ArrayList<Map<String, String>>> timetableData : data)
                {
                    Map<String, List<Class>> timetable = new HashMap<>();
                    for(String key : timetableData.keySet())
                    {
                        //convert the classes in the day into Class classes
                        //ArrayList<Map<String, String>>
                        ArrayList<Class> classes = new ArrayList<>();
                        for(Map<String, String> d : timetableData.get(key))
                        {
                            classes.add(new Class(d.get("courseId"), d.get("classId"), parseFloat(d.get("duration")),
                                    LocalTime.parse(d.get("time"), DateTimeFormatter.ofPattern("HH:mm")), d.get("weekday"), d.get("building"), d.get("location"), (!Objects.equals(d.get("isTutorial"), "false"))));
                        }
                        timetable.put(key, classes);
                    }
                    timetables.add(new Timetable(timetable));
                }
                return timetables;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        //if there are no saved timetables for this user
        return null;
    }

    @Override
    public void delete(User user, Timetable timetable) {
        DocumentReference docref = db.collection("Users").document(user.getId());


        try {
            DocumentSnapshot data = docref.get().get();
            if(data.exists())
            {
                Map<String, ArrayList<Map<String, String>>> convertedTimetable = convertTimetableToData(timetable.getClasses());
                ArrayList<Map<String, ArrayList<Map<String, String>>>> updatedTimetableArray = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) data.getData().get("Timetables");
                updatedTimetableArray.remove(convertedTimetable);

                docref.update("Timetables", updatedTimetableArray);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
