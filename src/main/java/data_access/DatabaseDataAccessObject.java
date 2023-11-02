package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import entity.Course;
import entity.Timetable;
import entity.User;
import use_case.explore_courses.ExploreCoursesDataAccessInterface;
import use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class DatabaseDataAccessObject implements ExploreCoursesDataAccessInterface, SaveViewTimetablesDataAccessInterface {

    Firestore db;
    public DatabaseDataAccessObject()
    {
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

    public void test()
    {
        // Create a Map to store the data we want to set
        Map<String, Object> docData = new HashMap<>();
        docData.put("name", "Los Angeles");
        docData.put("state", "CA");
        docData.put("country", "USA");
        docData.put("regions", Arrays.asList("west_coast", "socal"));
// Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(docData);
// ...
// future.get() blocks on response
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    //temp
    public void saveCourse()
    {

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
            if(document.exists())
            {
                //this should return a dictionary of String keys that are the day of the week, and string array containing
                //class data
                Map<String, Object> data = document.getData();
                //the returned object from the data is guaranteed to be of this form (an array of maps which represent
                //individual timetables)
                Set<Map<String, Object>> timetables = (Set<Map<String, Object>>) data.get("Timetables");

                Map<String, Object> uploadData = new HashMap<>();
                String[] timetableData = timetable.castToStringArray();
                uploadData.put("Monday", timetableData[0]);
                uploadData.put("Tuesday", timetableData[1]);
                uploadData.put("Wednesday", timetableData[2]);
                uploadData.put("Thursday", timetableData[3]);
                uploadData.put("Friday", timetableData[4]);
                timetables.add(uploadData);

                ApiFuture<WriteResult> res = db.collection("Users").document(user.getId()).set(timetables);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Timetable load(User user) {

        DocumentReference docRef = db.collection("Users").document(user.getId());
        ApiFuture<DocumentSnapshot> res = docRef.get();
        try {
            DocumentSnapshot document = res.get();
            if(document.exists())
            {
                //this should return a dictionary of String keys that are the day of the week, and string array containing
                //class data
                Map<String, Object> data = document.getData();

                //TODO: Convert this data back into a timetable object
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        //if there are no saved timetables for this user
        return null;
    }
}
