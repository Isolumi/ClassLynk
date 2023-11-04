package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import entity.Class;
import entity.Course;
import entity.Timetable;
import entity.User;
import use_case.explore_courses.ExploreCoursesDataAccessInterface;
import use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

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
                ArrayList<Map<String, ArrayList<Map<String, String>>>> timetables = getMaps(timetable, document);

                ApiFuture<WriteResult> res = db.collection("Users").document(user.getId()).set(timetables);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Map<String, ArrayList<Map<String, String>>>> getMaps(Timetable timetable, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        //the returned object from the data is guaranteed to be of this form (an array of maps which represent
        //individual timetables)
        ArrayList<Map<String, ArrayList<Map<String, String>>>> timetables = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) data.get("Timetables");

        Map<String, Object> uploadData = new HashMap<>();
        Map<String, List<Class>> timetableData = timetable.getClasses();

        Map<String, ArrayList<Map<String, String>>> converted = new HashMap<>();
        for (String key : timetableData.keySet()) {
            ArrayList<Map<String, String>> currClasses = new ArrayList<>();
            for (Class classData : timetableData.get(key)) {
                Map<String, String> temp = new HashMap<>();
                temp.put("id", classData.getId());
                //"false if not tutorial", "true" if tutorial
                temp.put("isTutorial", valueOf(classData.isTutorial()));
                //EEEE represents a day of the week
                temp.put("time", classData.getTime().format(DateTimeFormatter.ofPattern("EEEE, HH:mm")));
                temp.put("location", classData.getLocation());
                temp.put("building", classData.getBuilding());
                currClasses.add(temp);
            }
            converted.put(key, currClasses);
        }

        timetables.add(converted);

        //removes duplicate timetables
        Set<Map<String, ArrayList<Map<String, String>>>> noDuplicatesTimetables = new LinkedHashSet<>(timetables);
        timetables.clear();
        timetables.addAll(noDuplicatesTimetables);
        return timetables;
    }


    @Override
    public Timetable load(User user) {

        DocumentReference docRef = db.collection("Users").document(user.getId());
        ApiFuture<DocumentSnapshot> res = docRef.get();
        try {
            DocumentSnapshot document = res.get();
            if (document.exists()) {
                ArrayList<Map<String, ArrayList<Map<String, String>>>> data = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) document.getData();


                //TODO: Convert this data back into a timetable object

                Map<String, List<Class>> timetableData = new HashMap<>();
                data


                //map<String, List<Class>>

                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        //if there are no saved timetables for this user
        return null;
    }
}
